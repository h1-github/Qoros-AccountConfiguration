package com.wanghuan.accountconfiguration.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.wanghuan.accountconfiguration.R;

/**
 * Created by h1 on 16/3/29 11:26.
 * email: h18501667737@gmail.com
 */
public class TestView extends View{

    private int measureWidth;
    private int measureHeight;

    private Bitmap bitmap;

    public TestView(Context context) {
        super(context);
        init(context);
    }

    public TestView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public TestView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public TestView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }


    private void init(Context context){
        bitmap = BitmapFactory.decodeResource(getResources() , R.mipmap.qi_meter);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        Log.d("h1" ,"onMeasure measureWidth : " + measureWidth);
        Log.d("h1" ,"onMeasure measureHeight : " + measureHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("h1", "onDraw bitmap getWidth : " + bitmap.getWidth());
        Log.d("h1", "onDraw bitmap getHeight : " + bitmap.getHeight());

        canvas.drawColor(Color.CYAN);

        Paint paint = new Paint();
        Rect rectRes = new Rect(0 , 0 , bitmap.getWidth() , bitmap.getHeight());
        Rect rect = new Rect(0 , 0 , measureWidth * 2 , measureHeight);
        canvas.drawBitmap(bitmap , rectRes , rect , paint);
    }
}
