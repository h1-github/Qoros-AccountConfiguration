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
public class EnterNumber extends ObjectPointBase {

    private float radius = 250;

    private PointF center = new PointF();

    public PointF getPoint2() {
        return welcome_point2;
    }

    public PointF getCenter() {
        return center;
    }

    public float getRadius() {
        return radius;
    }

    public void setRadius(float radius) {
        this.radius = radius;
    }

    public EnterNumber(Context context) {
        super(context);
    }

    public Paint getTextPaint(){
        Paint paint = new Paint();
        paint.setColor(Color.WHITE);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(50);
        paint.setStrokeWidth(15);
        return paint;
    }

    @Override
    public void drawEnter(final View view) {
        super.drawMove(view);
        paint.setColor(colorBlue);
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,1);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                center = calculate(welcome_point1, welcome_point2, value);
                view.postInvalidate();
                ACStatus.animating = value != 1;
                ACStatus.welcome1 = value == 1;
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
                center = calculate(welcome_point2, welcome_point3, value);
                view.postInvalidate();
                ACStatus.animating = value != 1;
            }
        });
        valueAnimator.start();
    }

    @Override
    public void drawExit(final View view) {
        super.drawMove(view);
        paint.setColor(colorBlue);
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,1);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                center = calculate(welcome_point3, welcome_point4, value);
                view.postInvalidate();
                ACStatus.animating = value != 1;
            }
        });
        valueAnimator.start();
    }

}
