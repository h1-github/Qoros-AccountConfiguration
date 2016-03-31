package com.wanghuan.accountconfiguration.util;

import android.graphics.PointF;

/**
 * Created by h1 on 16/3/29 15:00.
 * email: h18501667737@gmail.com
 */
public class ACUtils {

    public static boolean withPointRadius(int x , int y , PointF target , float radius){
        PointF cur = new PointF(x , y);
        return withPointRadius(cur , target , radius);
    }

    public static boolean withPointRadius(float x , float y , PointF target , float radius){
        PointF cur = new PointF(x , y);
        return withPointRadius(cur , target , radius);
    }

    public static boolean withPointRadius(PointF cur , PointF target , float radius){
        double space_2 = Math.pow(Math.abs(cur.x - target.x) , 2) + Math.pow(Math.abs(cur.y - target.y) , 2);
        double space = Math.sqrt(space_2);
        if(radius > space){
            return true;
        }
        return false;
    }

}
