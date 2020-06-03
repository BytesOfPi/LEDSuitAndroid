package com.degroff.pandaled.ui.main.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.degroff.pandaled.R;
import com.degroff.pandaled.ui.main.fragment.content.ListItem;
import com.degroff.pandaled.ui.main.listener.PatternListActionListener;

import java.util.List;

public class ControlListAdapter extends ArrayAdapter<ListItem>
    {
    /**
     * Class member variables
     */
    private static final String TAG = "ControlListAdapter";
    private final Context context;
    private final int layoutResourceId;
    private final List<ListItem> patterns;
    private final PatternListActionListener listener;

    public ControlListAdapter(@NonNull final Context context, final int layoutResourceId, @NonNull final List<ListItem> patterns, final PatternListActionListener listener)
        {
        super(context, layoutResourceId, patterns);
        this.context = context;
        this.layoutResourceId = layoutResourceId;
        this.patterns = patterns;
        this.listener = listener;
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
        // Map Pattern to UI
        final ListItem item = patterns.get(position);
        final TextView tvHead = (TextView) convertView.findViewById(R.id.tv_list_head);
        final TextView tvDetail = (TextView) convertView.findViewById(R.id.tv_list_detail);
        final StringBuilder sb = new StringBuilder(item.details)
                .append(" (").append(item.id)
                .append(")");
        tvHead.setText(item.content);
        tvDetail.setText(sb.toString());

        final ImageView iv = (ImageView) convertView.findViewById(R.id.iv_icon);
        iv.setImageResource((item.drawable != 0) ? item.drawable : R.drawable.ic_wifi_white_24dp);

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
                    listener.onPatternClick(item);
                    }
                }
            });

        return convertView;
        }
    }
