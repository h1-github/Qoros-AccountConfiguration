package com.wanghuan.accountconfiguration.util;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

/**
 * Created by h1 on 16/3/11 16:42.
 * email: h18501667737@gmail.com
 */
public class L {

    private static boolean isDebug = true;

    private final static String TAG = "h1";

    private final static String ERROR_MESSAGE_EMPTY = "log message is null or equal ''";

    /**
     * if isDebug -> Log.d(tag , message)
     * @param tag
     * @param message
     */
    public static void d(String tag , String message){
        if(isDebug){
            Log.d(tag , TextUtils.isEmpty(message) ? ERROR_MESSAGE_EMPTY : message);
        }
    }

    /**
     * if isDebug -> Log.e(tag , message)
     * @param tag
     * @param message
     */
    public static void e(String tag , String message){
        if(isDebug){
            Log.e(tag, TextUtils.isEmpty(message) ? ERROR_MESSAGE_EMPTY : message);
        }
    }

    /**
     * if isDebug -> Log.d(TAG , message)
     * @param message
     */
    public static void d(String message){
        d(TAG , message);
    }

    /**
     * if isDebug -> Log.d(TAG , message)
     * @param message
     */
    public static void dWithMethodName(Context context, String message){
        d(TAG , " [ " + StackUtils.getMethodName(context) + "() ] " + message);
    }

    /**
     * if isDebug -> Log.d(TAG , message)
     * @param message
     */
    public static void dWithClassName(Context context , String message){
        d(TAG , " [ " + context.getClass().getSimpleName() + " ] " + message);
    }

    /**
     * if isDebug -> Log.d(TAG , message)
     * @param message
     */
    public static void dWithClassMethodName(Context context , String message){
        d(TAG , " [ " + context.getClass().getSimpleName() + " -> " + StackUtils.getMethodName(context) + "() ] " + message);
    }

    /**
     * if isDebug -> Log.d(TAG , message)
     */
    public static void dWithClassMethodName(Context context){
        d(TAG , " [ " + context.getClass().getSimpleName() + " -> " + StackUtils.getMethodName(context) + "() ] ");
    }

    /**
     * if isDebug -> Log.e(TAG , message)
     * @param message
     */
    public static void e(String message){
        e(TAG , message);
    }
}
