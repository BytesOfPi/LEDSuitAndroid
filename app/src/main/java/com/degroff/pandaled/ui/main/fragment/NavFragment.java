package com.degroff.pandaled.ui.main.fragment;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.degroff.pandaled.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NavFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NavFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NavFragment extends Fragment
    {
    private static final String TAG = "NavFragment";

    private OnFragmentInteractionListener mListener;
    private String status = "NOT CONNECTED";

    public NavFragment()
        {
        // Required empty public constructor
        }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment NavFragment.
     */
    public static NavFragment newInstance(final String status)
        {
        final NavFragment fragment = new NavFragment();
        final Bundle args = new Bundle();
        fragment.setArguments(args);
        fragment.setStatus(status);
        return fragment;
        }

    /**
     * onCreate()
     * This method is executed once on creation.
     *
     * @param savedInstanceState - A bundle of parameters passed from constructor.
     */
    @Override
    public void onCreate(final Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);
        }

    /**
     * onCreateView()
     * This method is executed every time the view is created
     *
     * @param inflater           - Builds the view
     * @param container          - Don't know what this ViewGroup is
     * @param savedInstanceState - Saved instance?
     */
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState)
        {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_nav, container, false);
        setupButtons(rootView);
        final TextView tv = (TextView) rootView.findViewById(R.id.tv_ble_status);
        tv.setText(status);

        return rootView;
        }

    @Override
    public void onDestroyView()
        {
        super.onDestroyView();
        }

    @Override
    public void onAttach(final Context context)
        {
        super.onAttach(context);
        //------------------------------------------------------------
        // Since the MainActivity will listen to this fragment, we
        // hold on to the listener and fire actions when events happen
        // in this fragment
        if ( context instanceof OnFragmentInteractionListener )
            {
            mListener = (OnFragmentInteractionListener) context;
            }
        else
            {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
            }
        }

    @Override
    public void onDetach()
        {
        super.onDetach();
        mListener = null;
        }

    //########################################################################################
    // CUSTOM methods

    public void setStatus(final String status) { this.status = status;}

    /**
     * setupButtons()
     * Used to attach button clicks to events
     */
    public void setupButtons(final View view)
        {
        Log.v(TAG, "Setting up buttons...");
        //------------------------------------------------------------
        // Find the buttons and attach the appropriate message listeners
        final Button btnBack = view.findViewById(R.id.button_back);
        final Button btnNext = view.findViewById(R.id.button_next);
        btnBack.setOnClickListener(new NavButtonClickListener("back"));
        btnNext.setOnClickListener(new NavButtonClickListener("next"));
        }

    //########################################################################################
    // CUSTOM Interfaces and classes

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener
        {
        void onNavButton(String val);
        }

    /**
     * NavButtonClickListener
     * Sends a quick message event to the MainActivity based on a button press
     */
    public class NavButtonClickListener implements View.OnClickListener
        {
        private final String msg;

        public NavButtonClickListener(final String msg)
            {
            this.msg = msg;
            }

        @Override
        public void onClick(final View v)
            {
            mListener.onNavButton(msg);
            }
        }
    }
