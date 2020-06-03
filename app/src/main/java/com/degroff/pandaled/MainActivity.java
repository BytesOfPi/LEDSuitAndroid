package com.degroff.pandaled;

import android.bluetooth.BluetoothAdapter;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.degroff.pandaled.ble.BLEDevice;
import com.degroff.pandaled.ble.BLEScanner;
import com.degroff.pandaled.ui.main.adapter.SectionsPagerAdapter;
import com.degroff.pandaled.ui.main.fragment.NavFragment;
import com.degroff.pandaled.ui.main.fragment.ScanFragment;
import com.degroff.pandaled.ui.main.fragment.content.ListItem;
import com.degroff.pandaled.ui.main.listener.PatternListActionListener;
import com.degroff.pandaled.util.Toolbox;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity
        implements ScanFragment.OnFragmentInteractionListener,
        PatternListActionListener,
        NavFragment.OnFragmentInteractionListener
    {
    public static final int REQUEST_ENABLE_BLE = 1;
    private static final String TAG = "MainActivity";
    private BLEScanner bleScanner;

    @Override
    protected void onCreate(final Bundle savedInstanceState)
        {
        Log.v(TAG, "onCreate...");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //------------------------------------------------------------
        // Setup BLE
        if ( !getPackageManager().hasSystemFeature(PackageManager.FEATURE_BLUETOOTH_LE) )
            {
            Toast.makeText(this, "BLE not supported", Toast.LENGTH_SHORT);
            finish();
            }
        bleScanner = new BLEScanner(this, 10000);

        //------------------------------------------------------------
        // Setup Tabs
        final SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(getApplicationContext(), bleScanner, getSupportFragmentManager());
        Log.v(TAG, "Setting up view pager...");
        final ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        final TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);

        //------------------------------------------------------------
        // Setup Toolbar
        Log.v(TAG, "Setting up toolbar...");
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        }

    /**
     * onConfigurationChanged() - Someone decided to rotate
     *
     * @param newConfig
     */
    @Override
    public void onConfigurationChanged(@NonNull final Configuration newConfig)
        {
        //------------------------------------------------------------
        // Log when user flips orientation
        if ( newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE )
            {
            Log.e(TAG, "ORIENTATION_LANDSCAPE");
            }
        else if ( newConfig.orientation == Configuration.ORIENTATION_PORTRAIT )
            {
            Log.e(TAG, "ORIENTATION_PORTRAIT");
            }
        super.onConfigurationChanged(newConfig);
        }

    /**
     * onCreateOptionsMenu - Create the options menu
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(final Menu menu)
        {
        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_panda, menu);
        return true;
        }

    @Override
    protected void onStart()
        {
        //------------------------------------------------------------
        // Registering the BLE scanner when Activity starts
        super.onStart();
        registerReceiver(bleScanner.getReceiver(), new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED));
        }

    @Override
    protected void onStop()
        {
        //------------------------------------------------------------
        // Stop a BLE scan if it's running and unregister the receiver
        Log.v(TAG, "Shutting down BLE scans and unregistering receiver");
        bleScanner.stopScan();
        unregisterReceiver(bleScanner.getReceiver());
        Log.v(TAG, "BLE Cleanup done");
        super.onStop();
        }

    @Override
    protected void onPause()
        {
        //------------------------------------------------------------
        // Stop a BLE scan if it's running before pausing app
        bleScanner.stopScan();
        super.onPause();
        }

    /**
     * onActivityResult - when a launched activity is complete, catch it and log
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(final int requestCode, final int resultCode, @Nullable final Intent data)
        {
        super.onActivityResult(requestCode, resultCode, data);
        //------------------------------------------------------------
        // Log when the Bluetooth request comes back to make sure
        // Bluetooth is turned on.
        if ( requestCode == REQUEST_ENABLE_BLE )
            {
            if ( resultCode == RESULT_OK )
                { Toolbox.toast(getApplicationContext(), "Yay... BlueTooth on");}
            else if ( resultCode == RESULT_CANCELED )
                { Toolbox.toast(getApplicationContext(), "Please Turn on BlueTooth");}
            }
        }

    @Override
    public boolean onOptionsItemSelected(@NonNull final MenuItem item)
        {
        Log.v(TAG, "Options item selected...");
        switch ( item.getItemId() )
            {
            case R.id.opt_ble_scan:
                if ( !bleScanner.isScanning() )
                    { bleScanner.startScan(); }
                else
                    { bleScanner.stopScan(); }
                return true;
            case R.id.item2:
                Toast.makeText(this, "item2", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item3:
                Toast.makeText(this, "item3", Toast.LENGTH_SHORT).show();
                return true;
            }
        return super.onOptionsItemSelected(item);
        }

    @Override
    public void onWindowFocusChanged(final boolean hasFocus)
        {
        super.onWindowFocusChanged(hasFocus);
        }

    //########################################################################################
    // TAB VIEW LISTENER METHODS
    @Override
    public void onFragmentInteraction(final Uri uri)
        {
        final Toast toast = Toast.makeText(getApplicationContext(), uri.getPath(), Toast.LENGTH_SHORT);
        toast.show();
        }

    @Override
    public void onNavButton(final String val)
        {
        //------------------------------------------------------------
        // Send selected navigation command to device
        bleScanner.sendBLEString(val, BLEDevice.BLE_SEND_SUIT_CHARACTERISTIC);
        }

    @Override
    public void onPatternClick(final ListItem item)
        {
        //------------------------------------------------------------
        // Send selected pattern command to device
        bleScanner.sendBLEString(item.prefix + item.id, item.bleChar); //BLEDevice.BLE_SEND_SUIT_CHARACTERISTIC;
        }
    }