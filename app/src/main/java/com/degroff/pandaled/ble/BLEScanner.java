package com.degroff.pandaled.ble;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanResult;
import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.degroff.pandaled.MainActivity;
import com.degroff.pandaled.R;
import com.degroff.pandaled.ui.main.adapter.BLEUIListAdapter;
import com.degroff.pandaled.util.Toolbox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BLEScanner implements BLEUIListAdapter.BLEListActionListener, BLEDevice.BLEDeviceListener
    {
    private static final String TAG = "BLEScanner";
    private final BluetoothAdapter bleAdapter;
    private final Handler handler;
    private boolean scanning;

    List<String> approvedPrefixes;

    //---------------------------------------------------------------
    // List / Map of scanned devices
    private final Map<String, BLEDevice> deviceMap;
    private final List<BLEDevice> deviceList;
    //---------------------------------------------------------------
    // Broadcast receiver and UI device list adapter
    private final BLEBroadcastReceiver receiver;
    private BLEUIListAdapter adapter;

    private final MainActivity mainActivity;
    private final long scanPeriod;

    private BLEDevice currentBLEDevice = null;

    /**
     * Constructor
     *
     * @param mainActivity
     * @param scanPeriod
     */
    public BLEScanner(final MainActivity mainActivity, final long scanPeriod)
        {
        this.mainActivity = mainActivity;
        this.scanPeriod = scanPeriod;

        //------------------------------------------------------------
        // Initialize approved prefixes
        final String[] prefixes = {"Panda", "Nate"};
        approvedPrefixes = Arrays.asList(prefixes);

        //------------------------------------------------------------
        // Handler creates a post delay to stop BLE scanning
        handler = new Handler();

        //------------------------------------------------------------
        // Get the Bluetooth adapter from phone's manager
        final BluetoothManager manager = (BluetoothManager) mainActivity.getSystemService(Context.BLUETOOTH_SERVICE);
        bleAdapter = manager.getAdapter();

        //------------------------------------------------------------
        // Create a BLE Broadcast receiver
        receiver = new BLEBroadcastReceiver(getContext());
        //------------------------------------------------------------
        // Initialize collections of BLE devices to empty
        deviceList = new ArrayList<>();
        deviceMap = new HashMap<>();
        }

    /**
     * isScanning - Return if device is still scanning
     */
    public boolean isScanning() { return scanning; }

    public boolean isConnected() { return currentBLEDevice != null && currentBLEDevice.isConnected();}

    public String getStatus()
        {
        if ( !isConnected() )
            {
            return "NOT CONNECTED";
            }
        return "CONNECTED TO [" + currentBLEDevice.getName() + "]";
        }

    /**
     * getReceiver() return the BLE Broadcast receiver
     *
     * @return
     */
    public BLEBroadcastReceiver getReceiver() { return receiver; }

    public BLEDevice getCurrentBLEDevice()
        {
        return currentBLEDevice;
        }

    public boolean sendBLEString(final String patt, final int sendBLEChar) //BLEDevice.BLE_SEND_STRAND_CHARACTERISTIC
    {
    //------------------------------------------------------------
    // If the BLEDevice is connected, set pattern
    if ( isConnected() )
        {
        return currentBLEDevice.sendBLEString(patt, sendBLEChar);
        }
    Toolbox.toast(getContext(), "Device not ready to send commands");
    return false;
    }

    /**
     * buildScanListView() - Wire up the List adapter for the scanned id list in the UI
     *
     * @param view
     */
    public void buildScanListView(final View view)
        {
        adapter = new BLEUIListAdapter(view.getContext(), this, R.layout.custom_scan_item, deviceList);
        final ListView lv = (ListView) view.findViewById(R.id.lv_list_devices);
        lv.setAdapter(adapter);
        }

    /**
     * clearDeviceLists() - Clear all found devices from list and alert UI
     */
    public void clearDeviceLists()
        {
        deviceList.clear();
        deviceMap.clear();
        adapter.notifyDataSetChanged();
        }

    public Context getContext() { return mainActivity.getApplicationContext(); }

    /**
     * addDevice() - Scanner found a device.  add it to list and notify UI
     *
     * @param device
     * @param rssi
     */
    public void addDevice(final BluetoothDevice device, final int rssi)
        {
        Log.v(TAG, "Adding Device: " + device.getName());
        //------------------------------------------------------------
        // If device already added, update signal strength
        final String address = device.getAddress();
        if ( deviceMap.containsKey(address) )
            { deviceMap.get(address).setRssi(rssi); }
        //------------------------------------------------------------
        // Otherwise, add device to list
        else
            {
            final BLEDevice bleDevice = new BLEDevice(device, rssi);
            deviceMap.put(address, bleDevice);
            deviceList.add(bleDevice);
            }
        //------------------------------------------------------------
        // Notify data changed
        adapter.notifyDataSetChanged();
        }

    /**
     * startScan - Start scanning for BLE devices
     */
    public void startScan()
        {
        Toolbox.toast(getContext(), "Starting scan...");
        //------------------------------------------------------------
        // Clear the list of devices and make sure user has Bluetooth
        // turned on
        clearDeviceLists();
        if ( !Toolbox.checkBLEAdapter(bleAdapter) )
            {
            Toolbox.requestUserBluetooth(mainActivity);
            }
        else
            {
            scanLeDevice(true);
            }
        }

    /**
     * stopScan - Stop scanning for BLE devices
     */
    public void stopScan()
        {
        //------------------------------------------------------------
        // Stop BLE Scan
        Toolbox.toast(getContext(), "Stopping scan...");
        scanLeDevice(false);
        }

    /**
     * scanLeDevice() - Main method that will start or stop a BLE scan.  It also handles
     * automatically shutting itself down with the handler
     *
     * @param enable
     */
    private void scanLeDevice(final boolean enable)
        {
        //------------------------------------------------------------
        // If we're telling it to scan and we're not currently scanning
        // go ahead and start scan
        if ( enable && !scanning )
            {
            Toolbox.toast(getContext(), "Starting BLE scan...");

            //------------------------------------------------------------
            // Set a timer in x millis to stop the scan
            handler.postDelayed(new Runnable()
                {
                @Override
                public void run()
                    {
                    Toolbox.toast(getContext(), "Stopping BLE scan...");
                    scanning = false;
                    bleAdapter.getBluetoothLeScanner().stopScan(myLECallback);
                    Log.v(TAG, "STOPPED SCANNING AUTOMATICALLY...");
                    }
                }, scanPeriod);

            //------------------------------------------------------------
            // Start scanning and attach the callback
            scanning = true;
            bleAdapter.getBluetoothLeScanner().startScan(myLECallback);
            Log.v(TAG, "STARTED SCANNING...");
            }
        else
            {
            scanning = false;
            bleAdapter.getBluetoothLeScanner().stopScan(myLECallback);
            Log.v(TAG, "STOPPED SCANNING MANUALLY...");
            }
        }

    private boolean prefixMatch(final String val)
        {
        for ( final String pre : approvedPrefixes )
            {
            if ( val.startsWith(pre) )
                { return true; }
            }
        return false;
        }

    //---------------------------------------------------------
    // Custom ScanCallback method that will trap the event a new
    // BLE device is found
    private final ScanCallback myLECallback = new ScanCallback()
        {
        private static final String TAG = "ScanCallback";

        @Override
        public void onScanResult(final int callbackType, final ScanResult result)
            {
            final BluetoothDevice device = result.getDevice();
            Log.v(TAG, new StringBuilder("onScanResult() [")
                    .append(device.getName()).append("][")
                    .append(device.getAddress()).append("][")
                    .append(result.getRssi()).append("]").toString());

            //---------------------------------------------------------
            // Only care about BLE devices with a device name
            if ( device.getName() != null &&
                    prefixMatch(device.getName()) )
                {
                addDevice(device, result.getRssi());
                }

            super.onScanResult(callbackType, result);
            }

        @Override
        public void onScanFailed(final int errorCode)
            {
            Log.v(TAG, "onScanFailed()");
            super.onScanFailed(errorCode);
            }
        };

    @Override
    public void onConnectBLEDeviceClick(final BLEDevice bleDevice)
        {
        //---------------------------------------------------------
        // Take care of the existing current device (if exists)
        if ( currentBLEDevice != null )
            {
            //---------------------------------------------------------
            // If we're trying to connect to the device we're already
            // connected to... do nothing
            if ( currentBLEDevice.getAddress().equals(bleDevice.getAddress())
                    && currentBLEDevice.getState() == BLEDevice.STATE_CONNECTED )
                {
                Toolbox.toast(getContext(), "Nothing changed.  Same device selected...");
                return;
                }

            // Disconnect from existing BLE Device (if applicable)
            Toolbox.toast(getContext(), "Disconnecting from: " + currentBLEDevice.getName());
            currentBLEDevice.disconnect();
            }

        //---------------------------------------------------------
        // Point the current device to the selected device and
        // setup the listener
        currentBLEDevice = bleDevice;
        currentBLEDevice.setBLEDeviceListener(this);

        Toolbox.toast(getContext(), "Connecting to: " + bleDevice.getName());
        bleDevice.connect(getContext());
        }

    @Override
    public void onPatternUpdate(final String patt)
        {
        mainActivity.runOnUiThread(new Runnable()
            {
            @Override
            public void run()
                {
                final TextView tv = (TextView) mainActivity.findViewById(R.id.tv_pattern);
                tv.setText(patt);
                }
            });
        }

    @Override
    public void onStatusUpdate(final String deviceStatus)
        {
        mainActivity.runOnUiThread(new Runnable()
            {
            @Override
            public void run()
                {
                final TextView tv = (TextView) mainActivity.findViewById(R.id.tv_device);
                tv.setText(deviceStatus);
                }
            });

        }
    }
