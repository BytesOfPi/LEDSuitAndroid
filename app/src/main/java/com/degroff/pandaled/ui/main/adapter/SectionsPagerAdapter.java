package com.degroff.pandaled.ui.main.adapter;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.degroff.pandaled.R;
import com.degroff.pandaled.ble.BLEScanner;
import com.degroff.pandaled.ui.main.fragment.ControlFragment;
import com.degroff.pandaled.ui.main.fragment.MatrixFragment;
import com.degroff.pandaled.ui.main.fragment.NavFragment;
import com.degroff.pandaled.ui.main.fragment.PaletteFragment;
import com.degroff.pandaled.ui.main.fragment.ScanFragment;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter
    {
    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_1, R.string.tab_text_2, R.string.tab_text_3, R.string.tab_text_4};
    private final Context mContext;
    private final BLEScanner bleScanner;

    public SectionsPagerAdapter(final Context context, final BLEScanner bleScanner, final FragmentManager fm)
        {
        super(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        mContext = context;
        this.bleScanner = bleScanner;
        }

    @Override
    public Fragment getItem(final int position)
        {
        //------------------------------------------------------------
        // Build the tab fragments - happens when first viewing
        switch ( position )
            {
            case 0:
                return ScanFragment.newInstance(bleScanner, "", "");
            case 1:
                //  return PatternListFragment.newInstance(1);
                // return PatternListFragment.newInstance();
                return ControlFragment.newInstance();
            case 2:
                //return NavFragment.newInstance(bleScanner.getStatus());
                return PaletteFragment.newInstance(bleScanner);
            case 3:
                return MatrixFragment.newInstance(bleScanner);
            }
        return NavFragment.newInstance(bleScanner.getStatus());
        }

    @Nullable
    @Override
    public CharSequence getPageTitle(final int position)
        {
        return mContext.getResources().getString(TAB_TITLES[position]);
        }

    @Override
    public int getCount()
        {
        // Show 3 total pages.
        return 4;
        }
    }