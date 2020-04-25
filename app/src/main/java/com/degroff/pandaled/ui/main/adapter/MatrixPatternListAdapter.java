package com.degroff.pandaled.ui.main.adapter;

import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.degroff.pandaled.R;
import com.degroff.pandaled.ui.main.spinner.PatternMatrixListItem;

import java.util.List;

public class MatrixPatternListAdapter extends ArrayAdapter<PatternMatrixListItem>
    {
    public MatrixPatternListAdapter(@NonNull final Context context, final List<PatternMatrixListItem> items)
        {
        super(context, 0, items);
        }

    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull final ViewGroup parent)
        {
        return customView(position, convertView, parent);
        }

    @Override
    public View getDropDownView(final int position, @Nullable final View convertView, @NonNull final ViewGroup parent)
        {
        return customView(position, convertView, parent);
        }

    public View customView(final int position, @Nullable View convertView, @NonNull final ViewGroup parent)
        {
        if ( convertView == null )
            {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.custom_spinner_pattern_item, parent, false);
            }
        final PatternMatrixListItem item = getItem(position);
        final ImageView spinImg = convertView.findViewById(R.id.ivCustomSpinnerPattImage);
        final TextView spinText = convertView.findViewById(R.id.tvCustomSpinPattText);
        final boolean darkMode = isDarkMode(convertView.getResources().getConfiguration());
        if ( item != null )
            {
            spinImg.setImageResource(darkMode ? item.getImgIDDark() : item.getImgID());
            spinText.setText(item.getText());
            }
        return convertView;
        }

    private boolean isDarkMode(final Configuration config)
        {
        return (config.uiMode & Configuration.UI_MODE_NIGHT_MASK) == Configuration.UI_MODE_NIGHT_YES;
        }
    }
