package com.wanghuan.accountconfiguration.view.objectviews;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.View;

import com.wanghuan.accountconfiguration.util.ViewUtils;

/**
 * Created by h1 on 16/3/29 15:34.
 * email: h18501667737@gmail.com
 */
public abstract class ObjectBase implements ObjectInterface{

    Context context;
    int measureWidth;
    int measureHeight;

    boolean isDraw = false;

    int colorBlue;
    int colorGrey;

    public boolean isDraw() {
        return isDraw;
    }

    public void setIsDraw(boolean isDraw) {
        this.isDraw = isDraw;
    }

    public ObjectBase(Context context) {
        this.context = context;
        this.measureWidth = ViewUtils.getScreenWidthThoughContext(context);
        this.measureHeight = ViewUtils.getScreenHeightThoughContext(context);
        paint.setAntiAlias(true);

        colorBlue = Color.parseColor("#6699FF");
        colorGrey = Color.parseColor("#DDDDDD");
    }

    Paint paint = new Paint();

    public Paint getPaint() {
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public PointF calculate(PointF from , PointF to , float animatedValue){
        PointF pointF = new PointF();
        if(from.x > to.x){
            pointF.x = from.x - Math.abs(from.x - to.x) * animatedValue;
        }else{
            pointF.x = from.x + Math.abs(from.x - to.x) * animatedValue;
        }
        if(from.y < to.y){
            pointF.y = from.y + Math.abs(from.y - to.y) * animatedValue;
        }else{
            pointF.y = from.y - Math.abs(from.y - to.y) * animatedValue;
        }
        return pointF;
    }

    @Override
    public void drawEnter(View view) {

    }

    @Override
    public void drawMove(View view) {

    }

    @Override
    public void drawExit(View view) {

    }
}
