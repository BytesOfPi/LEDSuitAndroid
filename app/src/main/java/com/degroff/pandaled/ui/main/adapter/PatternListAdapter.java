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
import com.degroff.pandaled.ui.main.fragment.PatternListNewFragment.PatternListActionListener;
import com.degroff.pandaled.ui.main.fragment.content.PatternListContent.PatternItem;

import java.util.List;

public class PatternListAdapter extends ArrayAdapter<PatternItem>
    {
    /**
     * Class member variables
     */
    private static final String TAG = "PatternListAdapter";
    private final Context context;
    private final int layoutResourceId;
    private final List<PatternItem> patterns;
    private final PatternListActionListener listener;

    public PatternListAdapter(@NonNull final Context context, final int layoutResourceId, @NonNull final List<PatternItem> patterns, final PatternListActionListener listener)
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
        final PatternItem item = patterns.get(position);
        final TextView tvPattern = (TextView) convertView.findViewById(R.id.tv_pattern);
        final StringBuilder sb = new StringBuilder(item.details)
                .append(" (").append(item.id)
                .append(")");
        tvPattern.setText(sb.toString());

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
