package com.wanghuan.accountconfiguration.view.objectviews;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.view.View;

import com.wanghuan.accountconfiguration.view.ACStatus;

/**
 * Created by h1 on 16/3/29 15:34.
 * email: h18501667737@gmail.com
 */
public class WelcomeDotObject extends ObjectBase {

    private final static int DEFAULT_RADIUS = 20;
    private final static int DEFAULT_RADIUS_CORE = 10;
    private final static int DEFAULT_RADIUS_ACTION = 50;

    private PointF point1 = new PointF(measureWidth + DEFAULT_RADIUS , measureHeight/2);
    private PointF point2 = new PointF(measureWidth * 0.55f, measureHeight * 0.55f);
    private PointF point3 = new PointF(measureWidth * 0.35f , point2.y);
    private PointF point4 = new PointF(-measureWidth , measureHeight);

    private float radius = DEFAULT_RADIUS;
    private float radiusCore = DEFAULT_RADIUS_CORE;
    private float radiusAction = DEFAULT_RADIUS_ACTION;

    private PointF center = new PointF();

    public PointF getPoint1() {
        return point1;
    }

    public PointF getPoint2() {
        return point2;
    }

    public PointF getPoint3() {
        return point3;
    }

    public PointF getPoint4() {
        return point4;
    }

    public PointF getCenter() {
        return center;
    }

    public float getRadius() {
        return radius;
    }

    public float getRadiusCore() {
        return radiusCore;
    }

    public float getRadiusAction() {
        return radiusAction;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public WelcomeDotObject(Context context) {
        super(context);
    }

    public Paint getDotPaint(){
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(colorGrey);
        paint.setStrokeWidth(10);
        return paint;
    }

    public Paint getDotWhitePaint(){
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        return paint;
    }

    public Paint getLinePaint(){
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(colorGrey);
        paint.setStrokeWidth(10);
        return paint;
    }

    @Override
    public void drawEnter(final View view) {
        super.drawMove(view);
        paint.setColor(colorGrey);
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,1);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                center = calculate(point1, point2, value);
                view.postInvalidate();
                ACStatus.animating = value != 1;
                ACStatus.sex1 = value == 1;
            }
        });
        valueAnimator.start();
    }

    @Override
    public void drawMove(final View view) {
        super.drawMove(view);
        paint.setColor(colorBlue);
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,1);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                center = calculate(point2, point3, value);
                view.postInvalidate();
                ACStatus.animating = value != 1;
            }
        });
        valueAnimator.start();
    }

    @Override
    public void drawExit(final View view) {
        super.drawMove(view);
    }

}
