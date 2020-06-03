package com.degroff.pandaled.ui.main.listener;

import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.degroff.pandaled.util.Fx;

/**
 * CardSlideClickListener
 * Sends a quick message event to the MainActivity based on a button press
 */
public class CardSlideClickListener implements View.OnClickListener
    {
    CardView card;
    TextView title;
    ListView list;

    public CardSlideClickListener(final CardView card, final TextView title, final ListView list)
        {
        this.card = card;
        this.title = title;
        this.list = list;
        }

    @Override
    public void onClick(final View v)
        {
        if ( card.isShown() )
            {
            title.setVisibility(View.GONE);
            list.setVisibility(View.GONE);
            Fx.slide(v.getContext(), card, true);
            card.setVisibility(View.GONE);
            }
        else
            {
            title.setVisibility(View.VISIBLE);
            list.setVisibility(View.VISIBLE);
            card.setVisibility(View.VISIBLE);
            Fx.slide(v.getContext(), card, false);
            }
        }
    }