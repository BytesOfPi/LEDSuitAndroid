package com.degroff.pandaled.ui.main.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.degroff.pandaled.R;
import com.degroff.pandaled.ui.main.adapter.ControlListAdapter;
import com.degroff.pandaled.ui.main.fragment.content.ControlListContent;
import com.degroff.pandaled.ui.main.listener.CardSlideClickListener;
import com.degroff.pandaled.ui.main.listener.PatternListActionListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ControlFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ControlFragment extends Fragment
    {
    private PatternListActionListener mListener;

    ListView lvOutfit;
    ListView lvSuit;
    ListView lvMatrix;
    ListView lvCape;

    public ControlFragment()
        {
        }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment ControlFragment.
     */
    public static ControlFragment newInstance()
        {
        final ControlFragment fragment = new ControlFragment();
        return fragment;
        }

    @Override
    public void onCreate(final Bundle savedInstanceState)
        {
        super.onCreate(savedInstanceState);
        }

    private void setupLists(final View rootView)
        {
        ControlListAdapter adapter = new ControlListAdapter(rootView.getContext(), R.layout.custom_control_item, ControlListContent.OUTFIT_ITEMS, mListener);
        lvOutfit = rootView.findViewById(R.id.lv_list_outfit_patterns);
        lvOutfit.setAdapter(adapter);

        adapter = new ControlListAdapter(rootView.getContext(), R.layout.custom_control_item, ControlListContent.SUIT_ITEMS, mListener);
        lvSuit = rootView.findViewById(R.id.lv_list_suit_patterns);
        lvSuit.setAdapter(adapter);

        adapter = new ControlListAdapter(rootView.getContext(), R.layout.custom_control_item, ControlListContent.MATRIX_ITEMS, mListener);
        lvMatrix = rootView.findViewById(R.id.lv_list_matrix_patterns);
        lvMatrix.setAdapter(adapter);

        adapter = new ControlListAdapter(rootView.getContext(), R.layout.custom_control_item, ControlListContent.CAPE_ITEMS, mListener);
        lvCape = rootView.findViewById(R.id.lv_list_cape_patterns);
        lvCape.setAdapter(adapter);
        }

    private void setupCardSlide(final View rootView)
        {
        //------------------------------------------------------------
        // Find the valuable components
        final TextView txtOutfitTitle = rootView.findViewById(R.id.tv_outfit_title);
        final TextView txtSuitTitle = rootView.findViewById(R.id.tv_suit_title);
        final TextView txtMatrixTitle = rootView.findViewById(R.id.tv_matrix_title);
        final TextView txtCapeTitle = rootView.findViewById(R.id.tv_cape_title);

        final TextView txtOutfitTitleEH = rootView.findViewById(R.id.tv_outfit_extra_head);
        final TextView txtSuitTitleEH = rootView.findViewById(R.id.tv_suit_extra_head);
        final TextView txtMatrixTitleEH = rootView.findViewById(R.id.tv_matrix_extra_head);
        final TextView txtCapeTitleEH = rootView.findViewById(R.id.tv_cape_extra_head);

        //------------------------------------------------------------
        // Assign titles and click listeners
        final CardView cardOutfitDetail = rootView.findViewById(R.id.card_outfit_view);
        txtOutfitTitle.setOnClickListener(new CardSlideClickListener(cardOutfitDetail, txtOutfitTitleEH, lvOutfit));
        final CardView cardSuitDetail = rootView.findViewById(R.id.card_suit_view);
        txtSuitTitle.setOnClickListener(new CardSlideClickListener(cardSuitDetail, txtSuitTitleEH, lvSuit));
        final CardView cardMatrixDetail = rootView.findViewById(R.id.card_matrix_view);
        txtMatrixTitle.setOnClickListener(new CardSlideClickListener(cardMatrixDetail, txtMatrixTitleEH, lvMatrix));
        final CardView cardCapeDetail = rootView.findViewById(R.id.card_cape_view);
        txtCapeTitle.setOnClickListener(new CardSlideClickListener(cardCapeDetail, txtCapeTitleEH, lvCape));

        //------------------------------------------------------------
        // Hide until its title is clicked
        cardOutfitDetail.setVisibility(View.GONE);
        cardSuitDetail.setVisibility(View.GONE);
        cardMatrixDetail.setVisibility(View.GONE);
        cardCapeDetail.setVisibility(View.GONE);

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
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState)
        {
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.fragment_control, container, false);

        //------------------------------------------------------------
        // Setup Lists
        setupLists(rootView);

        //------------------------------------------------------------
        // Setup the card slide menu
        setupCardSlide(rootView);

        return rootView;
        }

    }
