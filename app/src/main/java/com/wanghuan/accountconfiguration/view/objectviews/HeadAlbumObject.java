package com.wanghuan.accountconfiguration.view.objectviews;

import android.animation.ValueAnimator;
import android.content.Context;
import android.view.View;

import com.wanghuan.accountconfiguration.view.ACStatus;

/**
 * Created by h1 on 16/3/31 14:34.
 * email: h18501667737@gmail.com
 */
public class HeadAlbumObject extends ObjectPointBase{

    public HeadAlbumObject(Context context) {
        super(context);
    }

    private float radius = SEX_ITEM_DEFAULT_RADIUS;

    @Override
    public float getRadius() {
        return radius;
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
                center = calculate(headAlbum_point1, headAlbum_point2, value);
                view.postInvalidate();
                ACStatus.animating = value != 1;
            }
        });
        valueAnimator.start();
    }

    @Override
    public void drawMove(final View view) {
        super.drawMove(view);
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,1);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                center = calculate(headAlbum_point2, headAlbum_point3, value);
                view.postInvalidate();
                ACStatus.animating = value != 1;
            }
        });
        valueAnimator.start();
    }

    @Override
    public void drawExit(final View view) {
        super.drawExit(view);
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,1);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                center = calculate(headAlbum_point3, headAlbum_point4, value);
                view.postInvalidate();
                ACStatus.animating = value != 1;
            }
        });
        valueAnimator.start();
    }
}
