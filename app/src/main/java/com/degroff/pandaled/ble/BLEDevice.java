package com.degroff.pandaled.ble;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothProfile;
import android.content.Context;
import android.util.Log;

import java.util.UUID;

/**
 * BLEDevice
 * This class is a wrapper class for the BLE device we connect to
 */
public class BLEDevice
    {
    private static final String TAG = "BLEDevice";
    private static final String DUMMY_NAME = "Unknown";
    private static final String DUMMY_ADDR = "123";
    private static final UUID UUID_LED_SERVICE = UUID.fromString("a5ff4b63-9be5-49c8-8ac4-3d376ae2cca3");
    private static final UUID UUID_CLIENT_CHARACTERISTIC_CONFIG = UUID.fromString("00002902-0000-1000-8000-00805f9b34fb");

    private static final UUID UUID_LED_SET_CHAR = UUID.fromString("9082f7cb-c208-43d6-9848-e0802df34321");

    private static final UUID UUID_LED_SET_FULL_PATT_CHAR = UUID.fromString("9082f7cb-c208-43d6-9848-e0802df34321");

    private static final UUID UUID_LED_GET_SUIT_PATTERN_CHAR = UUID.fromString("ecd0fe34-fa4c-4cd0-9a4e-991d57ef5008");
    private static final UUID UUID_LED_SET_SUIT_PATTERN_CHAR = UUID.fromString("2539da47-36d5-4e26-8789-621e683aa294");
    private static final UUID UUID_LED_SET_MATRIX_PATT_CHAR = UUID.fromString("1c29d445-46e7-40c1-ad45-e87d10f4ba79");
    private static final UUID UUID_LED_SET_CAPE_PATT_CHAR = UUID.fromString("e6f0ebf4-6b06-4d0a-a3a0-d1c91e7b5919");

    private static final UUID UUID_LED_SET_PAL_COL_CHAR = UUID.fromString("66efc725-2d5b-4e88-a381-cd6cb4d78cfb");

    private static final UUID UUID_LED_SET_MATRIX_SCROLL_CHAR = UUID.fromString("799b6016-8120-4b10-b6c3-0c58178ebd00");
    private static final UUID UUID_LED_SET_MATRIX_TIMER_CHAR = UUID.fromString("a8ea0d63-4c4d-4535-a420-fb97053d32b8");


    private int connectionState = STATE_DISCONNECTED;
    public static final int STATE_DISCONNECTED = 0;
    public static final int STATE_CONNECTING = 1;
    public static final int STATE_DISCOVERING = 2;
    public static final int STATE_CONNECTED = 3;

    public static final int BLE_SEND_CHARACTERISTIC = 0x00;

    public static final int BLE_SEND_FULL_CHARACTERISTIC = 0x05;//0x00;
    public static final int BLE_SEND_SUIT_CHARACTERISTIC = 0x01;
    public static final int BLE_SEND_MATRIX_PATT_CHARACTERISTIC = 0x02;
    public static final int BLE_SEND_CAPE_PATT_CHARACTERISTIC = 0x03;

    public static final int BLE_SEND_PAL_COL_CHARACTERISTIC = 0x04;

    public static final int BLE_SEND_MATRIX_SCROLL_CHARACTERISTIC = 0x0A;
    public static final int BLE_SEND_MATRIX_TIMER_CHARACTERISTIC = 0x0B;

    private BluetoothDevice device;
    private int rssi;
    private BLEDeviceListener listener = null;

    private BluetoothGatt gatt;
    private BluetoothGattCharacteristic gattGetPatternCharacteristic;

    private BluetoothGattCharacteristic bleSendCharacteristic;

    private BluetoothGattCharacteristic bleSendFullPattCharacteristic;
    private BluetoothGattCharacteristic bleSendSuitPattCharacteristic;
    private BluetoothGattCharacteristic bleSendMatrixPattCharacteristic;
    private BluetoothGattCharacteristic bleSendCapePattCharacteristic;

    private BluetoothGattCharacteristic bleSendPalColCharacteristic;

    private BluetoothGattCharacteristic bleSendMatrixScrollCharacteristic;
    private BluetoothGattCharacteristic bleSendMatrixTimerCharacteristic;

    public BLEDevice(final BluetoothDevice device, final int rssi)
        {
        this.device = device;
        this.rssi = rssi;
        }

    public void setBLEDeviceListener(final BLEDeviceListener listener) { this.listener = listener; }

    public boolean isConnected() { return connectionState == STATE_CONNECTED; }

    public boolean sendBLEString(final String patt, final int sendBLEChar)
        {
        if ( getState() != STATE_CONNECTED ) { return false; }
        BluetoothGattCharacteristic sendBLE = bleSendSuitPattCharacteristic;

        switch ( sendBLEChar )
            {
            case BLE_SEND_CHARACTERISTIC:
                sendBLE = bleSendCharacteristic;
                break;
            case BLE_SEND_FULL_CHARACTERISTIC:
                sendBLE = bleSendFullPattCharacteristic;
                break;
            case BLE_SEND_SUIT_CHARACTERISTIC:
                sendBLE = bleSendSuitPattCharacteristic;
                break;
            case BLE_SEND_CAPE_PATT_CHARACTERISTIC:
                sendBLE = bleSendCapePattCharacteristic;
                break;
            case BLE_SEND_MATRIX_PATT_CHARACTERISTIC:
                sendBLE = bleSendMatrixPattCharacteristic;
                break;
            case BLE_SEND_PAL_COL_CHARACTERISTIC:
                sendBLE = bleSendPalColCharacteristic;
                break;
            case BLE_SEND_MATRIX_SCROLL_CHARACTERISTIC:
                sendBLE = bleSendMatrixScrollCharacteristic;
                break;
            case BLE_SEND_MATRIX_TIMER_CHARACTERISTIC:
                sendBLE = bleSendMatrixTimerCharacteristic;
            }

        sendBLE.setValue(patt);
        Log.i(TAG, "Setting pattern(" + patt + ")");
        return gatt.writeCharacteristic(sendBLE);
        }

    public int getState() { return connectionState; }

    public String getName() { return device != null && device.getName() != null ? device.getName() : DUMMY_NAME; }

    public BluetoothDevice getDevice() { return device; }

    public void setDevice(final BluetoothDevice device) { this.device = device; }

    public String getAddress() { return device != null ? device.getAddress() : DUMMY_ADDR; }

    public int getRssi() { return rssi; }

    public void setRssi(final int rssi) { this.rssi = rssi; }

    public void disconnect()
        {
        gatt.disconnect();
        connectionState = STATE_DISCONNECTED;
        }

    public void connect(final Context context)
        {
        //---------------------------------------------------------
        // Set Status and start connecting
        connectionState = STATE_CONNECTING;
        listener.onStatusUpdate("Connecting...");
        gatt = device.connectGatt(context, true, new BluetoothGattCallback()
            {
            //---------------------------------------------------------
            // Handle when you connect and disconnect
            @Override
            public void onConnectionStateChange(final BluetoothGatt gatt, final int status, final int newState)
                {
                Log.i(TAG, "onConnectionStateChange()");
                //---------------------------------------------------------
                // Once you're connected, start discovering services
                if ( newState == BluetoothProfile.STATE_CONNECTED )
                    {
                    listener.onStatusUpdate("Discovering...");
                    Log.i(TAG, "STATE_DISCOVERING");
                    connectionState = STATE_DISCOVERING;
                    gatt.discoverServices();
                    Log.i(TAG, "Attempting to start service discovery:" +
                            gatt.discoverServices());
                    }
                //---------------------------------------------------------
                // If you're disconnecting, log that as well
                else if ( newState == BluetoothProfile.STATE_DISCONNECTED )
                    {
                    listener.onStatusUpdate("Disconnected");
                    Log.i(TAG, "STATE_DISCONNECTED");
                    connectionState = STATE_DISCONNECTED;
                    Log.i(TAG, "Disconnected from GATT server.");
                    }
                }

            @Override
            public void onServicesDiscovered(final BluetoothGatt gatt, final int status)
                {
                //---------------------------------------------------------
                // Once you've discovered the service...
                connectionState = STATE_CONNECTED;
                listener.onStatusUpdate("[" + getDevice().getName() + "]");
                final BluetoothGattService service = gatt.getService(UUID_LED_SERVICE);
                if ( service != null )
                    {
                    //---------------------------------------------------------
                    // Get a hold of the Set pattern
                    bleSendCharacteristic = service.getCharacteristic(UUID_LED_SET_CHAR);
                    bleSendFullPattCharacteristic = service.getCharacteristic(UUID_LED_SET_FULL_PATT_CHAR);
                    bleSendSuitPattCharacteristic = service.getCharacteristic(UUID_LED_SET_SUIT_PATTERN_CHAR);
                    bleSendMatrixPattCharacteristic = service.getCharacteristic(UUID_LED_SET_MATRIX_PATT_CHAR);
                    bleSendCapePattCharacteristic = service.getCharacteristic(UUID_LED_SET_CAPE_PATT_CHAR);

                    //---------------------------------------------------------
                    // Get a hold of the Set palette / color
                    bleSendPalColCharacteristic = service.getCharacteristic(UUID_LED_SET_PAL_COL_CHAR);

                    //---------------------------------------------------------
                    // Extra characteristics
                    bleSendMatrixScrollCharacteristic = service.getCharacteristic(UUID_LED_SET_MATRIX_SCROLL_CHAR);
                    bleSendMatrixTimerCharacteristic = service.getCharacteristic(UUID_LED_SET_MATRIX_TIMER_CHAR);

                    //---------------------------------------------------------
                    // Turn on notification of the Get Pattern characteristic
                    Log.v(TAG, "Getting Get Pattern...");
                    gattGetPatternCharacteristic = service.getCharacteristic(UUID_LED_GET_SUIT_PATTERN_CHAR);
                    gatt.setCharacteristicNotification(gattGetPatternCharacteristic, true);
                    final BluetoothGattDescriptor descriptor =
                            gattGetPatternCharacteristic.getDescriptor(UUID_CLIENT_CHARACTERISTIC_CONFIG);
                    descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
                    gatt.writeDescriptor(descriptor);
                    }

                super.onServicesDiscovered(gatt, status);
                }

            @Override
            public void onCharacteristicRead(final BluetoothGatt gatt, final BluetoothGattCharacteristic characteristic, final int status)
                {
                Log.v(TAG, "onCharacteristicRead(): " + new String(characteristic.getValue()));
                super.onCharacteristicRead(gatt, characteristic, status);
                }

            @Override
            public void onCharacteristicWrite(final BluetoothGatt gatt, final BluetoothGattCharacteristic characteristic, final int status)
                {
                //---------------------------------------------------------
                // Log when writing pattern characteristic
                if ( characteristic.getUuid().equals(UUID_LED_SET_SUIT_PATTERN_CHAR) && listener != null )
                    {
                    final String msg = new String(characteristic.getValue());
                    Log.v(TAG, "onCharacteristicWrite(): [" + msg + "]");
                    }
                super.onCharacteristicWrite(gatt, characteristic, status);
                }

            @Override
            public void onCharacteristicChanged(final BluetoothGatt gatt, final BluetoothGattCharacteristic characteristic)
                {
                //---------------------------------------------------------
                // Send pattern characteristic to listener
                final String msg = new String(characteristic.getValue());
                Log.v(TAG, "onCharacteristicChanged() [" + msg + "]");
                if ( characteristic.getUuid().equals(UUID_LED_GET_SUIT_PATTERN_CHAR) && listener != null )
                    {
                    Log.v(TAG, "sending to listener() [" + msg + "]");
                    listener.onPatternUpdate(msg);
                    }
                super.onCharacteristicChanged(gatt, characteristic);
                }
            });
        }

    public interface BLEDeviceListener
        {
        void onPatternUpdate(String patt);

        void onStatusUpdate(String patt);
        }
    }
