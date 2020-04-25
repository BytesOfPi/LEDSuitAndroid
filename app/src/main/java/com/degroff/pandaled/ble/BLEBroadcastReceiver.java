package com.degroff.pandaled.ble;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.degroff.pandaled.util.Toolbox;

public class BLEBroadcastReceiver extends BroadcastReceiver
    {
    private final Context activityContext;

    public BLEBroadcastReceiver(final Context context)
        {
        activityContext = context;
        }

    @Override
    public void onReceive(final Context context, final Intent intent)
        {
        final String action = intent.getAction();
        if ( action.equals(BluetoothAdapter.ACTION_STATE_CHANGED) )
            {
            final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);
            switch ( state )
                {
                case BluetoothAdapter.STATE_OFF:
                    Toolbox.toast(activityContext, "Bluetooth is off");
                    return;
                case BluetoothAdapter.STATE_ON:
                    Toolbox.toast(activityContext, "Bluetooth is on");
                    return;
                case BluetoothAdapter.STATE_TURNING_ON:
                    Toolbox.toast(activityContext, "Bluetooth is turning on...");
                    return;
                case BluetoothAdapter.STATE_TURNING_OFF:
                    Toolbox.toast(activityContext, "Bluetooth is turning off...");
                    return;
                }
            }
        }
    }
