package com.wanghuan.accountconfiguration.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.wanghuan.accountconfiguration.util.ACUtils;
import com.wanghuan.accountconfiguration.util.L;
import com.wanghuan.accountconfiguration.util.ViewUtils;
import com.wanghuan.accountconfiguration.view.objectviews.EnterNumber;
import com.wanghuan.accountconfiguration.view.objectviews.SexObject;
import com.wanghuan.accountconfiguration.view.objectviews.WelcomeDotObject;

/**
 * Created by h1 on 16/3/29 14:31.
 * email: h18501667737@gmail.com
 */
public class AccountConfigurationView extends View{

    float x;
    float y;
    float lastX;
    float lastY;

    private TouchFeedback touchFeedback;

    public void setTouchFeedback(TouchFeedback touchFeedback) {
        this.touchFeedback = touchFeedback;
    }

    private int screenWidth;
    private int screenHeight;

    private Paint MOVE_LINE_PAINT = new Paint();

    private EnterNumber enterNumber;
    private SexObject sexObject;
    private WelcomeDotObject welcomeDotObject;

    private void init(Context context) {
        screenWidth = ViewUtils.getScreenWidthThoughContext(context);
        screenHeight = ViewUtils.getScreenHeightThoughContext(context);

        MOVE_LINE_PAINT.setAntiAlias(true);
        MOVE_LINE_PAINT.setStrokeWidth(50);
        MOVE_LINE_PAINT.setColor(Color.parseColor("#6699FF"));

        enterNumber = new EnterNumber(context);
        sexObject = new SexObject(context);
        welcomeDotObject = new WelcomeDotObject(context);
    }

    public void startEnterNumber(){
        enterNumber.setIsDraw(true);
        enterNumber.drawEnter(this);
    }

    public void welcomeMove(){
        enterNumber.setIsDraw(true);
        enterNumber.drawMove(this);
    }

    public void welcomeDotEnter(){
        welcomeDotObject.setIsDraw(true);
        welcomeDotObject.drawEnter(this);
    }

    public void sexEnter(){
        sexObject.setIsDraw(true);
        sexObject.drawEnter(this);
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);

        L.d("onDraw ACStatus.welcome welcome_active : " + ACStatus.welcome_active);
        L.d("onDraw ACStatus.welcome_dash_number : " + ACStatus.welcome_dash_number);
        if(ACStatus.welcome_active && ACStatus.welcome_dash_number){
            canvas.drawLine(enterNumber.getCenter().x , enterNumber.getCenter().y,
                    x , y , MOVE_LINE_PAINT);
        }

        if(welcomeDotObject.isDraw()){
            canvas.drawLine(enterNumber.getCenter().x, enterNumber.getCenter().y,
                    welcomeDotObject.getCenter().x, welcomeDotObject.getCenter().y , welcomeDotObject.getLinePaint());
            canvas.drawLine(welcomeDotObject.getCenter().x, welcomeDotObject.getCenter().y ,
                    sexObject.getCenter().x , sexObject.getCenter().y , welcomeDotObject.getLinePaint());
            canvas.drawCircle(welcomeDotObject.getCenter().x, welcomeDotObject.getCenter().y,
                    welcomeDotObject.getRadius(), welcomeDotObject.getDotPaint());
            canvas.drawCircle(welcomeDotObject.getCenter().x , welcomeDotObject.getCenter().y ,
                    welcomeDotObject.getRadiusCore() , welcomeDotObject.getDotWhitePaint());

        }
        if(enterNumber.isDraw()){
            canvas.drawCircle(enterNumber.getCenter().x,
                    enterNumber.getCenter().y, enterNumber.getRadius(), enterNumber.getPaint());
            canvas.drawText("18501667737", enterNumber.getCenter().x, enterNumber.getCenter().y, enterNumber.getTextPaint());
        }

        if(sexObject.isDraw()){
            canvas.drawCircle(sexObject.getCenter().x,
                    sexObject.getCenter().y, sexObject.getRadius(), sexObject.getPaint());
            canvas.drawText("开始", sexObject.getCenter().x, sexObject.getCenter().y, sexObject.getTextPaint());
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();
                //welcome
                if(ACStatus.welcome_active
                        && ACUtils.withPointRadius(x, y, enterNumber.getPoint2() , enterNumber.getRadius())){
                    ACStatus.welcome_dash_number = true;
                    L.d("ACTION_DOWN true");
                }
                break;
            case MotionEvent.ACTION_MOVE:
                //welcome
                if(ACStatus.welcome_active = true
                        && ACStatus.welcome_dash_number
                        && ACUtils.withPointRadius(x, y, welcomeDotObject.getCenter() , welcomeDotObject.getRadiusAction())){
                    ACStatus.welcome_dash_dot = true;
                    L.d("ACTION_MOVE TRUE");
                }
                break;
            case MotionEvent.ACTION_UP:
                ACStatus.welcome_dash_number = false;

                if(ACStatus.welcome1
                        && ACUtils.withPointRadius(x, y, enterNumber.getPoint2() , enterNumber.getRadius())
                        && touchFeedback != null){
                    touchFeedback.onClickWelcome1();
                }
                lastX = x;
                lastY = y;
                break;
        }
        invalidate();
        return true;
    }

    public AccountConfigurationView(Context context) {
        super(context);
        init(context);
    }

    public AccountConfigurationView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public AccountConfigurationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public AccountConfigurationView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }
}
