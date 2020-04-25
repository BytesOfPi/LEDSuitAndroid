package com.degroff.pandaled.util;

import android.content.Context;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.degroff.pandaled.R;

public class Fx
    {
    /**
     * @param ctx
     * @param v
     */
    public static void slide(final Context ctx, final View v, final boolean goingUp)
        {
        final Animation a = AnimationUtils.loadAnimation(ctx, (goingUp) ? R.anim.slide_up : R.anim.slide_down);
        if ( a != null )
            {
            a.reset();
            if ( v != null )
                {
                v.clearAnimation();
                v.startAnimation(a);
                }
            }
        }
    }
