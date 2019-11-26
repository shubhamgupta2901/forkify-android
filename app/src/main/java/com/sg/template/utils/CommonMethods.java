package com.sg.template.utils;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.util.TypedValue;


/**
 * Created by shubham on 22/9/17.
 */

public class CommonMethods {

    /**
     * Method used to change the color of a drawable on run time for temporary use
     *
     * @param mContext context of the activity
     * @param resID    The resource Id Of Icon whose color is to be changed
     * @param color    the resource Id Of Color to which the icon is to be changed
     * @return Drawable Drawable of Icon with changed Color
     */
    public static Drawable setColorFilterOnIcons(Context mContext, int resID, int color) {

        try {
            Drawable newIcon = getDrawable(mContext, resID);
            assert newIcon != null;
            newIcon.mutate();
            newIcon.setColorFilter(getColor(mContext, color), PorterDuff.Mode.SRC_ATOP);
            //newIcon.setColorFilter(color, PorterDuff.Mode.SRC_ATOP);
            return newIcon;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * It will return the hexcode of the color  and depending upon the sdk version it will return the values
     *
     * @param id The id of the drawable
     * @return return the drawable
     */
    public static Drawable getDrawable(Context context, int id) {
        Resources mResources = context.getResources();
        final int version = Build.VERSION.SDK_INT;

        try {
            if (context != null && !((Activity) context).isFinishing()) {
                if (version >= 23) {
                    return ContextCompat.getDrawable(context, id);
                } else {
                    return mResources.getDrawable(id);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * It will return the hexcode of the color  and depending upon the sdk version it will return the values
     *
     * @param context Instance of Activity which is calling it
     * @param id      The id of the color
     * @return return the hexcode of the color
     */
    public static int getColor(Context context, int id) {

        if (context != null) {
            final int version = Build.VERSION.SDK_INT;
            if (version >= 23) {
                return ContextCompat.getColor(context, id);
            } else {
                return context.getResources().getColor(id);
            }
        }
        return 0;
    }

    public static float convertPixelsToDp(float px, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float dp = px / ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return dp;
    }

    public static float convertDpToPixel(float dp, Context context) {
        Resources resources = context.getResources();
        DisplayMetrics metrics = resources.getDisplayMetrics();
        float px = dp * ((float) metrics.densityDpi / DisplayMetrics.DENSITY_DEFAULT);
        return px;
    }

    public static int dpToPx(Context c, int dp) {

        Resources r = c.getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, r.getDisplayMetrics()));
    }


    public static boolean isActivityAlive(Activity mActvity) {
        return (mActvity != null && mActvity.isFinishing() == false);
    }


}
