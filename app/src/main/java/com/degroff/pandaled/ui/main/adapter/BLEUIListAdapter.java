package com.degroff.pandaled.ui.main.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.degroff.pandaled.R;
import com.degroff.pandaled.ble.BLEDevice;

import java.util.List;

public class BLEUIListAdapter extends ArrayAdapter<BLEDevice>
    {
    /**
     * Class member variables
     */
    private static final String TAG = "BLEUIListAdapter";
    private final Context context;
    private final BLEListActionListener listener;
    private final int layoutResourceId;
    private final List<BLEDevice> devices;


    /**
     * Constructor
     *
     * @param context
     * @param layoutResourceId
     * @param devices
     */
    public BLEUIListAdapter(final Context context, final BLEListActionListener listener, final int layoutResourceId, final List<BLEDevice> devices)
        {
        super(context, layoutResourceId, devices);
        this.context = context;
        this.listener = listener;
        this.layoutResourceId = layoutResourceId;
        this.devices = devices;
        }


    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent)
        {
        //-----------------------------------------------------------------
        // If the convertView hasn't been created, create it
        if ( convertView == null )
            {
            final LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layoutResourceId, parent, false);
            }
        Log.v(TAG, "View ID: " + convertView.getId());

        //-----------------------------------------------------------------
        // Map BLE device to UI
        final TextView tvDeviceName = convertView.findViewById(R.id.tv_device_name);
        final TextView tvAddr = convertView.findViewById(R.id.tv_device_addr);
        final BLEDevice device = devices.get(position);
        tvAddr.setText(device.getAddress());
        final StringBuilder sb = new StringBuilder(device.getName())
                .append(" (")
                .append(device.getRssi())
                .append(")");
        tvDeviceName.setText(sb);

        //-----------------------------------------------------------------
        // Set On Click Listener
        convertView.setOnClickListener(new View.OnClickListener()
            {
            @Override
            public void onClick(final View v)
                {
                if ( null != listener )
                    {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    listener.onConnectBLEDeviceClick(device);
                    }
                }
            });

        return convertView;
        }


    public interface BLEListActionListener
        {
        // TODO: Update argument type and name
        void onConnectBLEDeviceClick(BLEDevice bleDevice);
        }
    }
