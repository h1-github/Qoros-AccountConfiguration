package com.wanghuan.accountconfiguration.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.wanghuan.accountconfiguration.util.L;

/**
 * Created by h1 on 16/3/30 13:22.
 * email: h18501667737@gmail.com
 */
public class TouchFeedBackView extends View{

    float x;
    float y;
    float lastX;
    float lastY;

    private TouchFeedback touchFeedback;

    public void setTouchFeedback(TouchFeedback touchFeedback) {
        this.touchFeedback = touchFeedback;
    }

    private void init(Context context){
//        this.setTou
        L.d("TouchFeedBackView : init");
    }

    public TouchFeedBackView(Context context) {
        super(context);
        init(context);
    }

    public TouchFeedBackView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TouchFeedBackView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TouchFeedBackView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();

                break;
            case MotionEvent.ACTION_MOVE:
//                if(ACStatus.welcome1 && ACUtils.withPointRadius(x , y , )){
//
//                }
                break;
            case MotionEvent.ACTION_UP:
                lastX = x;
                lastY = y;
                break;
        }

        return true;
    }



}
