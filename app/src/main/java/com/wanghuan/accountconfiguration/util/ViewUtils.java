package com.wanghuan.accountconfiguration.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.util.DisplayMetrics;

/**
 * Created by h1 on 16/3/28 10:21.
 * email: h18501667737@gmail.com
 */
public class ViewUtils {

    public static int getScreenWidth(Activity activity){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    public static int getScreenHeight(Activity activity){
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    public static int getScreenWidthThoughContext(Context context){
        return context.getResources().getDisplayMetrics().widthPixels;
    }

    public static int getScreenHeightThoughContext(Context context){
        return context.getResources().getDisplayMetrics().heightPixels;
    }

    public static int getStatusBarHeight(Activity activity){
        Rect rect = new Rect();
        activity.getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        return rect.height();
    }




}
