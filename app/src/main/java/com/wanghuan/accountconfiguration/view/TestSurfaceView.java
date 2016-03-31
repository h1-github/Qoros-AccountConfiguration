package com.wanghuan.accountconfiguration.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 * Created by h1 on 16/3/29 11:23.
 * email: h18501667737@gmail.com
 */
public class TestSurfaceView extends SurfaceView{
    public TestSurfaceView(Context context) {
        super(context);
    }

    public TestSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public TestSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TestSurfaceView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }
}
