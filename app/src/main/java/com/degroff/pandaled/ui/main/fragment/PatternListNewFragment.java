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
import com.degroff.pandaled.ui.main.fragment.content.PatternListContent.PatternItem;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link PatternListActionListener}
 * interface.
 */
public class PatternListNewFragment extends Fragment
    {
    private PatternListActionListener mListener;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PatternListNewFragment()
        {
        }

    // Create PatternListNewFragment
    public static PatternListNewFragment newInstance()
        {
        return new PatternListNewFragment();
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
        final View rootView = inflater.inflate(R.layout.fragment_pattern_scan, container, false);

        //-----------------------------------------------------------
        // build and attach view
        if ( rootView != null )
            {
            final PatternListAdapter adapter = new PatternListAdapter(rootView.getContext(), R.layout.fragment_pattern_item, PatternListContent.ITEMS, mListener);
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

    /**
     * PatternListActionListener interface
     * Defines the interface for a class to listen to Pattern List actions
     */
    public interface PatternListActionListener
        {
        void onPatternClick(PatternItem item);
        }
    }
