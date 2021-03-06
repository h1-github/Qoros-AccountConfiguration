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
public class SexGirlObject extends ObjectPointBase {

    private float radius = SEX_ITEM_DEFAULT_RADIUS;

    private PointF center = new PointF();


    public PointF getCenter() {
        return center;
    }

    public float getRadius() {
        return radius;
    }

    public SexGirlObject(Context context) {
        super(context);
    }

    public Paint getDotWhitePaint(){
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        return paint;
    }

    @Override
    public void drawEnter(final View view) {
        super.drawMove(view);
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,1);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                center = calculate(sexGirl_point1, sexGirl_point2, value);
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
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0,1);
        valueAnimator.setDuration(1000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                center = calculate(sexGirl_point2, sexGirl_point3, value);
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
