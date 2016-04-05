package com.wanghuan.accountconfiguration.util;

/**
 * Created by h1 on 16/4/3 01:01.
 * email: h18501667737@gmail.com
 */
public class TextUtils {

    /**
     *
     * @param text
     * @return
     */
    public static boolean isEmpty(String text){
        if(text == null || text.equals("")){
            return true;
        }
        return false;
    }
}
