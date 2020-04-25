package com.degroff.pandaled.ui.main.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.degroff.pandaled.R;
import com.degroff.pandaled.ble.BLEScanner;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ScanFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ScanFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ScanFragment extends Fragment
    {
    private BLEScanner bleScanner = null;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ScanFragment() {}

    public ScanFragment(final BLEScanner bleScanner)
        {
        this.bleScanner = bleScanner;
        }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ScanFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ScanFragment newInstance(final BLEScanner bleScanner, final String param1, final String param2)
        {
        final ScanFragment fragment = new ScanFragment(bleScanner);
        final Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
        }

    @Override
    public void onCreate(final Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);
        if ( getArguments() != null )
            {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            }
        }

    @Override
    public void onCreateOptionsMenu(@NonNull final Menu menu, @NonNull final MenuInflater inflater)
        {
        inflater.inflate(R.menu.menu_panda, menu);
        super.onCreateOptionsMenu(menu, inflater);
        }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState)
        {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_scan, container, false);

        //-----------------------------------------------------------
        // build and attach view
        if ( rootView != null )
            {
            bleScanner.buildScanListView(rootView);
            }
        return rootView;
        }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(final Uri uri)
        {
        if ( mListener != null )
            {
            mListener.onFragmentInteraction(uri);
            }
        }

    @Override
    public void onAttach(final Context context)
        {
        super.onAttach(context);
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
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
        }
    }
