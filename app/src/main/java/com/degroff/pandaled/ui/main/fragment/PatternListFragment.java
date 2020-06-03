package com.degroff.pandaled.ui.main.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.degroff.pandaled.R;
import com.degroff.pandaled.ui.main.adapter.PatternListAdapter;
import com.degroff.pandaled.ui.main.fragment.content.PatternListContent;
import com.degroff.pandaled.ui.main.listener.PatternListActionListener;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link PatternListActionListener}
 * interface.
 */
public class PatternListFragment extends Fragment
    {
    private PatternListActionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PatternListFragment()
        {
        }

    // Create PatternListNewFragment
    public static PatternListFragment newInstance()
        {
        return new PatternListFragment();
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
        final View rootView = inflater.inflate(R.layout.fragment_pattern_list, container, false);

        //-----------------------------------------------------------
        // build and attach view
        if ( rootView != null )
            {
            final PatternListAdapter adapter = new PatternListAdapter(rootView.getContext(), R.layout.custom_pattern_item, PatternListContent.ITEMS, mListener);
            final ListView lv = (ListView) rootView.findViewById(R.id.lv_list_patterns);
            lv.setAdapter(adapter);
            }

        return rootView;
        }


    @Override
    public void onAttach(final Context context)
        {
        super.onAttach(context);
        if ( context instanceof PatternListActionListener )
            {
            mListener = (PatternListActionListener) context;
            }
        else
            {
            throw new RuntimeException(context.toString()
                    + " must implement PatternListActionListener");
            }
        }

    @Override
    public void onDetach()
        {
        super.onDetach();
        mListener = null;
        }


    }
