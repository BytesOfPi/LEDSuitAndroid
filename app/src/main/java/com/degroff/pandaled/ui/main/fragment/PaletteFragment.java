package com.degroff.pandaled.ui.main.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.degroff.pandaled.R;
import com.degroff.pandaled.ble.BLEScanner;
import com.degroff.pandaled.ui.main.adapter.ControlListAdapter;
import com.degroff.pandaled.ui.main.fragment.content.ControlListContent;
import com.degroff.pandaled.ui.main.listener.CardSlideClickListener;
import com.degroff.pandaled.ui.main.listener.PatternListActionListener;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PaletteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PaletteFragment extends Fragment
    {
    private PatternListActionListener mListener;

    public static final int BLE_PAL_FIRST = 0x01;
    public static final int BLE_PAL_SECOND = 0x02;
    public static final int BLE_COL_FIRST = 0x03;
    public static final int BLE_COL_SECOND = 0x04;

    public static final String BLE_PREFIX_SUIT_PAL = "0";
    public static final String BLE_PREFIX_SUIT_PAL_SEC = "1";
    public static final String BLE_PREFIX_SUIT_COL = "2";
    public static final String BLE_PREFIX_SUIT_COL_2 = "3";
    public static final String BLE_PREFIX_MATRIX_PAL = "4";
    public static final String BLE_PREFIX_MATRIX_PAL_SEC = "5";
    public static final String BLE_PREFIX_MATRIX_COL = "6";
    public static final String BLE_PREFIX_CAPE_PAL = "7";
    public static final String BLE_PREFIX_CAPE_PAL_SEC = "8";
    public static final String BLE_PREFIX_CAPE_COL = "9";

    //---------------------------------------------------------
    // BLE scanner object
    private final BLEScanner bleScanner;

    //---------------------------------------------------------
    // List views
    private ListView lvPalette;
    private ListView lvPaletteSec;
    private ListView lvColor;
    private ListView lvColorSec;

    //---------------------------------------------------------
    // Radio Buttons
    private RadioButton rbSuit;
    private RadioButton rbMatrix;
    private RadioButton rbCape;

    public PaletteFragment(final BLEScanner bleScanner)
        {
        this.bleScanner = bleScanner;
        }

    /**
     * @param bleScanner
     * @return A new instance of fragment PaletteFragment.
     */
    public static PaletteFragment newInstance(final BLEScanner bleScanner)
        {
        final PaletteFragment fragment = new PaletteFragment(bleScanner);
        return fragment;
        }

    /**
     * Return prefix depending which radio button is selected
     *
     * @param suit
     * @param matrix
     * @param cape
     * @return
     */
    protected String getCompPrefix(final String suit, final String matrix, final String cape)
        {
        if ( rbSuit.isChecked() ) { return suit; }
        return (rbMatrix.isChecked()) ? matrix : cape;
        }

    /**
     * Return Prefix based on selection
     *
     * @param type
     * @return
     */
    protected String getPrefix(final int type)
        {
        switch ( type )
            {
            case BLE_PAL_FIRST:
                return getCompPrefix(BLE_PREFIX_SUIT_PAL, BLE_PREFIX_MATRIX_PAL, BLE_PREFIX_CAPE_PAL);
            case BLE_PAL_SECOND:
                return getCompPrefix(BLE_PREFIX_SUIT_PAL_SEC, BLE_PREFIX_MATRIX_PAL_SEC, BLE_PREFIX_CAPE_PAL_SEC);
            case BLE_COL_FIRST:
            case BLE_COL_SECOND:
                return getCompPrefix(BLE_PREFIX_SUIT_COL, BLE_PREFIX_MATRIX_COL, BLE_PREFIX_CAPE_COL);
            }
        return "";
        }

    /**
     * Setup the lists
     *
     * @param rootView
     */
    private void setupLists(final View rootView)
        {
        //---------------------------------------------------------
        // Create list adapter for primary palette selection
        ControlListAdapter adapter = new ControlListAdapter(rootView.getContext(), R.layout.custom_control_item, ControlListContent.PAL_ITEMS, item ->
        {
        bleScanner.sendBLEString(item.prefix + getPrefix(BLE_PAL_FIRST) + item.id, item.bleChar);
        });
        lvPalette = rootView.findViewById(R.id.lv_list_pal_first);
        lvPalette.setAdapter(adapter);

        //---------------------------------------------------------
        // Create list adapter for secondary palette selection
        adapter = new ControlListAdapter(rootView.getContext(), R.layout.custom_control_item, ControlListContent.PAL_ITEMS, item ->
        {
        bleScanner.sendBLEString(item.prefix + getPrefix(BLE_PAL_SECOND) + item.id, item.bleChar);
        });
        lvPaletteSec = rootView.findViewById(R.id.lv_list_pal_second);
        lvPaletteSec.setAdapter(adapter);

        //---------------------------------------------------------
        // Create list adapter for primary color selection
        adapter = new ControlListAdapter(rootView.getContext(), R.layout.custom_control_item, ControlListContent.COL_ITEMS, item ->
        {
        bleScanner.sendBLEString(item.prefix + getPrefix(BLE_COL_FIRST) + item.id, item.bleChar);
        });
        lvColor = rootView.findViewById(R.id.lv_list_pal_col_first);
        lvColor.setAdapter(adapter);

        //---------------------------------------------------------
        // Create list adapter for secondary palette selection
        adapter = new ControlListAdapter(rootView.getContext(), R.layout.custom_control_item, ControlListContent.COL_ITEMS, item ->
        {
        bleScanner.sendBLEString(item.prefix + getPrefix(BLE_COL_SECOND) + item.id, item.bleChar);
        });
        lvColorSec = rootView.findViewById(R.id.lv_list_pal_col_second);
        lvColorSec.setAdapter(adapter);
        }

    private void setupCardSlide(final View rootView)
        {
        //------------------------------------------------------------
        // Find the valuable components
        final TextView txtPalFirstTitle = rootView.findViewById(R.id.tv_pal_first_title);
        final TextView txtPalSecondTitle = rootView.findViewById(R.id.tv_pal_second_title);
        final TextView txtColFirstTitle = rootView.findViewById(R.id.tv_pal_col_first_title);
        final TextView txtColSecondTitle = rootView.findViewById(R.id.tv_pal_col_second_title);

        final TextView txtPalFirstTitleEH = rootView.findViewById(R.id.tv_pal_first_xtra_head);
        final TextView txtPalSecondTitleEH = rootView.findViewById(R.id.tv_pal_second_xtra_head);
        final TextView txtColFirstTitleEH = rootView.findViewById(R.id.tv_pal_col_first_xtra_head);
        final TextView txtColSecondTitleEH = rootView.findViewById(R.id.tv_pal_col_second_xtra_head);

        //------------------------------------------------------------
        // Assign titles and click listeners
        final CardView cardPalFirstDetail = rootView.findViewById(R.id.card_pal_first_view);
        txtPalFirstTitle.setOnClickListener(new CardSlideClickListener(cardPalFirstDetail, txtPalFirstTitleEH, lvPalette));
        final CardView cardPalSecondDetail = rootView.findViewById(R.id.card_pal_second_view);
        txtPalSecondTitle.setOnClickListener(new CardSlideClickListener(cardPalSecondDetail, txtPalSecondTitleEH, lvPaletteSec));
        final CardView cardColFirstDetail = rootView.findViewById(R.id.card_pal_col_first_view);
        txtColFirstTitle.setOnClickListener(new CardSlideClickListener(cardColFirstDetail, txtColFirstTitleEH, lvColor));
        final CardView cardColSecondDetail = rootView.findViewById(R.id.card_pal_col_second_view);
        txtColSecondTitle.setOnClickListener(new CardSlideClickListener(cardColSecondDetail, txtColSecondTitleEH, lvColorSec));

        //------------------------------------------------------------
        // Hide until its title is clicked
        cardPalFirstDetail.setVisibility(View.GONE);
        cardPalSecondDetail.setVisibility(View.GONE);
        cardColFirstDetail.setVisibility(View.GONE);
        cardColSecondDetail.setVisibility(View.GONE);
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
        final View rootView = inflater.inflate(R.layout.fragment_palette, container, false);
        //------------------------------------------------------------
        // Setup Lists
        setupLists(rootView);

        //------------------------------------------------------------
        // Setup the card slide menu
        setupCardSlide(rootView);

        //---------------------------------------------------------
        // Store handle on radio buttons
        rbSuit = rootView.findViewById(R.id.rb_suit);
        rbMatrix = rootView.findViewById(R.id.rb_matrix);
        rbCape = rootView.findViewById(R.id.rb_cape);

        return rootView;
        }
    }
