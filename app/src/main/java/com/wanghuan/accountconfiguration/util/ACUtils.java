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

    public static final int QUAD_TYPE_LEFT_TOP = 1;
    public static final int QUAD_TYPE_LEFT_BOTTOM = 2;
    public static final int QUAD_TYPE_RIGHT_TOP = 3;
    public static final int QUAD_TYPE_RIGHT_BOTTOM = 4;

    public static final int QUAD_OFFSET = 50;

    public static PointF calculateQuadPoint(PointF start , PointF end , int quadType){
        PointF quadPoint = new PointF();
        switch (quadType){
            case QUAD_TYPE_LEFT_TOP:
                quadPoint.set((start.x + end.x)/2 - QUAD_OFFSET, (start.y + end.y)/2 - QUAD_OFFSET);
                break;
            case QUAD_TYPE_LEFT_BOTTOM:
                quadPoint.set((start.x + end.x)/2 , (start.y + end.y)/2);
                break;
            case QUAD_TYPE_RIGHT_TOP:
                quadPoint.set((start.x + end.x)/2 , (start.y + end.y)/2);
                break;
            case QUAD_TYPE_RIGHT_BOTTOM:
                quadPoint.set((start.x + end.x)/2 , (start.y + end.y)/2);
                break;
        }
        return quadPoint;
    }

    public static PointF calculateMidPoint(PointF start , PointF end){
        PointF middle = new PointF();
        middle.set((start.x + end.x)/2 , (start.y + end.y)/2);
        return middle;
    }

}
