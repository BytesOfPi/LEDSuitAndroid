package com.degroff.pandaled.ui.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.degroff.pandaled.R;
import com.degroff.pandaled.ble.BLEScanner;
import com.degroff.pandaled.util.Fx;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ControlFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ControlFragment extends Fragment
    {
    private final BLEScanner bleScanner;
    TextView txtSuitTitle;
    TextView txtSuitExtra;

    public ControlFragment(final BLEScanner bleScanner)
        {
        this.bleScanner = bleScanner;
        }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ControlFragment.
     */
    public static ControlFragment newInstance(final BLEScanner bleScanner)
        {
        final ControlFragment fragment = new ControlFragment(bleScanner);
        return fragment;
        }

    @Override
    public void onCreate(final Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);
        }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState)
        {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_control, container, false);

        //------------------------------------------------------------
        // Find the valuable components
        txtSuitTitle = rootView.findViewById(R.id.suit_title);
        txtSuitExtra = rootView.findViewById(R.id.suit_extra);
        txtSuitTitle.setOnClickListener(new SuitClickListener());
        // hide until its title is clicked
        txtSuitExtra.setVisibility(View.GONE);

        return rootView;
        }

    /**
     * NavButtonClickListener
     * Sends a quick message event to the MainActivity based on a button press
     */
    public class SuitClickListener implements View.OnClickListener
        {

        @Override
        public void onClick(final View v)
            {
            if ( txtSuitExtra.isShown() )
                {
                Fx.slide(v.getContext(), txtSuitExtra, true);
                txtSuitExtra.setVisibility(View.GONE);
                }
            else
                {
                txtSuitExtra.setVisibility(View.VISIBLE);
                Fx.slide(v.getContext(), txtSuitExtra, false);
                }
            }
        }
    }
