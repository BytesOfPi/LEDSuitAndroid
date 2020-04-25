package com.degroff.pandaled.ui.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.degroff.pandaled.R;
import com.degroff.pandaled.ble.BLEDevice;
import com.degroff.pandaled.ble.BLEScanner;
import com.degroff.pandaled.ui.main.adapter.MatrixPatternListAdapter;
import com.degroff.pandaled.ui.main.spinner.PatternMatrixListItem;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MatrixFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MatrixFragment extends Fragment
    {
    private Spinner spinMin;
    private Spinner spinSec;
    private Spinner spinPatt;
    private TextView tvMessage;
    private final BLEScanner bleScanner;

    public MatrixFragment(final BLEScanner bleScanner)
        {
        this.bleScanner = bleScanner;
        }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param bleScanner
     * @return A new instance of fragment MatrixFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MatrixFragment newInstance(final BLEScanner bleScanner)
        {
        final MatrixFragment fragment = new MatrixFragment(bleScanner);
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
        final View rootView = inflater.inflate(R.layout.fragment_matrix, container, false);

        //------------------------------------------------------------
        // Find the valuable components
        spinMin = rootView.findViewById(R.id.spinnerMinutes);
        spinSec = rootView.findViewById(R.id.spinnerSec);
        spinPatt = rootView.findViewById(R.id.spinnerMatrixPattern);
        tvMessage = rootView.findViewById(R.id.tvScrollMsg);

        //------------------------------------------------------------
        // Load up the Timer spinner values
        addTimerSpinnerValues(spinMin, rootView);
        addTimerSpinnerValues(spinSec, rootView);

        //------------------------------------------------------------
        // Load up Matrix pattern spinner values
        addPatternSpinnerValues(spinPatt, rootView);

        //------------------------------------------------------------
        // Setup Buttons
        setupPatternButtons(rootView);
        setupScrollButtons(rootView);
        setupTimerButtons(rootView);

        return rootView;
        }

    private void setupPatternButtons(final View rootView)
        {
        //------------------------------------------------------------
        // Send message to change pattern
        final ImageButton btnPattSend = rootView.findViewById(R.id.btnPattSend);
        btnPattSend.setOnClickListener(v ->
        {
        final String myMsg = ((PatternMatrixListItem) spinPatt.getSelectedItem()).getText();
        Toast.makeText(getActivity().getBaseContext(), "Sending: " + myMsg, Toast.LENGTH_SHORT).show();
        bleScanner.sendBLEString(myMsg, BLEDevice.BLE_SEND_MATRIX_PATT_CHARACTERISTIC);
        });
        }

    private void setupScrollButtons(final View rootView)
        {

        //------------------------------------------------------------
        // Send text to scroll on Device and the start message
        final ImageButton btnScrollSend = rootView.findViewById(R.id.btnScrollSend);
        btnScrollSend.setOnClickListener(v ->
        {
        final String myMsg = tvMessage.getText().toString();
        Toast.makeText(getActivity().getBaseContext(), "Sending: " + myMsg, Toast.LENGTH_SHORT).show();
        bleScanner.sendBLEString(myMsg, BLEDevice.BLE_SEND_MATRIX_SCROLL_CHARACTERISTIC);
        Toast.makeText(getActivity().getBaseContext(), "START: ", Toast.LENGTH_SHORT).show();
        bleScanner.sendBLEString("###", BLEDevice.BLE_SEND_MATRIX_SCROLL_CHARACTERISTIC);
        });

        //------------------------------------------------------------
        // Send message to start scroll what is there
        final ImageButton btnScrollStart = rootView.findViewById(R.id.btnScrollStart);
        btnScrollStart.setOnClickListener(v -> bleScanner.sendBLEString("###", BLEDevice.BLE_SEND_MATRIX_SCROLL_CHARACTERISTIC));

        //------------------------------------------------------------
        // Send message to stop scrolling
        final ImageButton btnScrollStop = rootView.findViewById(R.id.btnScrollStop);
        btnScrollStop.setOnClickListener(v ->
        {
        Toast.makeText(getActivity().getBaseContext(), "Clear: ", Toast.LENGTH_SHORT).show();
        bleScanner.sendBLEString("***", BLEDevice.BLE_SEND_MATRIX_SCROLL_CHARACTERISTIC);
        Toast.makeText(getActivity().getBaseContext(), "Stop: ", Toast.LENGTH_SHORT).show();
        bleScanner.sendBLEString("@@@", BLEDevice.BLE_SEND_MATRIX_SCROLL_CHARACTERISTIC);
        });

        //------------------------------------------------------------
        // TODO: Send message to clear out stored text

        }

    private void setupTimerButtons(final View rootView)
        {
        //------------------------------------------------------------
        // Send message to Send Timer
        final ImageButton btnTimerSend = rootView.findViewById(R.id.btnTimerSend);
        btnTimerSend.setOnClickListener(v ->
        {
        final String myMin = (String) spinMin.getSelectedItem();
        final String mySec = (String) spinSec.getSelectedItem();
        final StringBuilder msg = new StringBuilder("***").append(myMin).append(":").append(mySec);
        Toast.makeText(getActivity().getBaseContext(), "Set Time: " + msg.toString(), Toast.LENGTH_SHORT).show();
        bleScanner.sendBLEString(msg.toString(), BLEDevice.BLE_SEND_MATRIX_TIMER_CHARACTERISTIC);
        });
        //------------------------------------------------------------
        // Send message to Start Timer
        final ImageButton btnTimerStart = rootView.findViewById(R.id.btnTimerStart);
        btnTimerStart.setOnClickListener(v ->
        {
        Toast.makeText(getActivity().getBaseContext(), "Start Timer", Toast.LENGTH_SHORT).show();
        bleScanner.sendBLEString("###", BLEDevice.BLE_SEND_MATRIX_TIMER_CHARACTERISTIC);
        });
        //------------------------------------------------------------
        // Send message to Pause Timer
        final ImageButton btnTimerPause = rootView.findViewById(R.id.btnTimerPause);
        btnTimerPause.setOnClickListener(v ->
        {
        Toast.makeText(getActivity().getBaseContext(), "Pause Timer", Toast.LENGTH_SHORT).show();
        bleScanner.sendBLEString("===", BLEDevice.BLE_SEND_MATRIX_TIMER_CHARACTERISTIC);
        });
        //------------------------------------------------------------
        // Send message to Stop Timer
        final ImageButton btnTimerStop = rootView.findViewById(R.id.btnTimerStop);
        btnTimerStop.setOnClickListener(v ->
        {
        Toast.makeText(getActivity().getBaseContext(), "Stop Timer", Toast.LENGTH_SHORT).show();
        bleScanner.sendBLEString("@@@", BLEDevice.BLE_SEND_MATRIX_TIMER_CHARACTERISTIC);
        });

        }

    /* addPatternSpinnerValues()
     * Loads pattern selecter spinner up with patterns to choose from
     */
    private void addPatternSpinnerValues(final Spinner spin, final View rootView)
        {
        final List<PatternMatrixListItem> list = new ArrayList<>();

        list.add(new PatternMatrixListItem("Cycle", R.drawable.ic_wb_sunny_black_24dp, R.drawable.ic_wb_sunny_white_24dp));
        list.add(new PatternMatrixListItem("Pulse", R.drawable.ic_wifi_black_24dp, R.drawable.ic_wifi_white_24dp));
        list.add(new PatternMatrixListItem("Spin", R.drawable.ic_bluetooth_black_24dp, R.drawable.ic_bluetooth_white));
        list.add(new PatternMatrixListItem("Fire", R.drawable.ic_whatshot_black_24dp, R.drawable.ic_whatshot_white_24dp));

        final MatrixPatternListAdapter adapter = new MatrixPatternListAdapter(getActivity().getBaseContext(), list);
        if ( spin != null )
            {
            spin.setAdapter(adapter);
            }
        }

    /* addTimerSpinnerValues()
     * Loads Minutes and Seconds spinner up with values from 0 - 59
     */
    private void addTimerSpinnerValues(final Spinner spin, final View rootView)
        {
        final List<String> values = new ArrayList<>();
        for ( int i = 0; i < 60; i++ )
            { values.add(i + ""); }

        final ArrayAdapter<String> adaptValues = new ArrayAdapter<String>(rootView.getContext(), android.R.layout.simple_list_item_1, values);
        adaptValues.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adaptValues);
        }
    }