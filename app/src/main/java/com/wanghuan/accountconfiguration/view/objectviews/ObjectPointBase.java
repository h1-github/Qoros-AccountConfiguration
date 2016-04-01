package com.wanghuan.accountconfiguration.view.objectviews;

import android.content.Context;
import android.graphics.PointF;

import com.wanghuan.accountconfiguration.util.ACUtils;

/**
 * Created by h1 on 16/3/31 10:53.
 * email: h18501667737@gmail.com
 */
public abstract class ObjectPointBase extends ObjectBase{

    public ObjectPointBase(Context context) {
        super(context);
    }

    private float radius = SEX_DEFAULT_RADIUS;

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public final static int SEX_DEFAULT_RADIUS = 200;
    public final static int DOT_DEFAULT_RADIUS = 20;
    public final static int DOT_DEFAULT_RADIUS_CORE = 10;
    public final static int DOT_DEFAULT_RADIUS_ACTION = 50;
    public final static int SEX_ITEM_DEFAULT_RADIUS = 90;

    public PointF welcome_point1 = new PointF(measureWidth , measureHeight/2);
    public PointF welcome_point2 = new PointF(measureWidth/2 , measureHeight/2 + measureHeight/6);
    public PointF welcome_point3 = new PointF(measureWidth * 0.35f , measureHeight/2 + measureHeight/4);
    public PointF welcome_point4 = new PointF(-measureWidth , measureHeight);

    public PointF sex_point1 = new PointF(measureWidth + SEX_DEFAULT_RADIUS , measureHeight/2);
    public PointF sex_point2 = new PointF(measureWidth - SEX_DEFAULT_RADIUS * 0.6f, measureHeight/2);
    public PointF sex_point3 = new PointF(measureWidth * 0.35f , measureHeight/2 + measureHeight/4);
    public PointF sex_point4 = new PointF(-measureWidth , measureHeight);

    public PointF welcome_dot_point1 = new PointF(measureWidth + DOT_DEFAULT_RADIUS , measureHeight/2);
    public PointF welcome_dot_point2 = new PointF(measureWidth * 0.55f, measureHeight * 0.55f);
    public PointF welcome_dot_point3 = ACUtils.calculateMidPoint(welcome_point3 , sex_point2);
    public PointF welcome_dot_point4 = new PointF(-measureWidth , measureHeight);

    public PointF headIcon_point1 = new PointF(measureWidth + SEX_DEFAULT_RADIUS , measureHeight/2);
    public PointF headIcon_point2 = new PointF(measureWidth - SEX_DEFAULT_RADIUS * 0.6f, measureHeight/2);
    public PointF headIcon_point3 = new PointF(measureWidth * 0.35f , measureHeight/2 + measureHeight/4);
    public PointF headIcon_point4 = new PointF(-measureWidth , measureHeight);

    public PointF sexBoy_point1 = new PointF(measureWidth + SEX_DEFAULT_RADIUS , measureHeight/2);
    public PointF sexBoy_point2 = new PointF(measureWidth * 0.35f, measureHeight/2);
    public PointF sexBoy_point3 = ACUtils.calculateMidPoint(sex_point3 , headIcon_point2);
    public PointF sexBoy_point4 = new PointF(-measureWidth , measureHeight);

    public PointF sexGirl_point1 = new PointF(measureWidth + SEX_DEFAULT_RADIUS , measureHeight/2);
    public PointF sexGirl_point2 = new PointF(measureWidth * 0.75f, measureHeight * 0.75f);
    public PointF sexGirl_point3 = ACUtils.calculateMidPoint(sex_point3 , headIcon_point2);
    public PointF sexGirl_point4 = new PointF(-measureWidth , measureHeight);


}
