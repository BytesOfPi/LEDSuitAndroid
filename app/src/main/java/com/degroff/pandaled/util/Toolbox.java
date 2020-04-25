package com.degroff.pandaled.util;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.content.Intent;
import android.view.Gravity;
import android.widget.Toast;

import com.degroff.pandaled.MainActivity;

public final class Toolbox
    {

    /**
     * Check if adapter exists and is enabled
     *
     * @param adapter
     * @return
     */
    public static boolean checkBLEAdapter(final BluetoothAdapter adapter)
        { return !(adapter == null || !adapter.isEnabled()); }

    public static void requestUserBluetooth(final Activity activity)
        {
        final Intent enableBleIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        activity.startActivityForResult(enableBleIntent, MainActivity.REQUEST_ENABLE_BLE);
        }

    /**
     * Generate Toast Message
     *
     * @param context
     * @param msg
     */
    public static void toast(final Context context, final String msg)
        {
        final Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER | Gravity.BOTTOM, 0, 0);
        toast.show();
        }


    }
