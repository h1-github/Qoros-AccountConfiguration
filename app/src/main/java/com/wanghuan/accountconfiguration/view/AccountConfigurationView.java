package com.wanghuan.accountconfiguration.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.wanghuan.accountconfiguration.util.ACUtils;
import com.wanghuan.accountconfiguration.util.L;
import com.wanghuan.accountconfiguration.util.ViewUtils;
import com.wanghuan.accountconfiguration.view.objectviews.EnterNumber;
import com.wanghuan.accountconfiguration.view.objectviews.HeadIconObject;
import com.wanghuan.accountconfiguration.view.objectviews.SexBoyObject;
import com.wanghuan.accountconfiguration.view.objectviews.SexGirlObject;
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
    private Paint MOVE_QUAD_LINE_PAINT = new Paint();

    private EnterNumber enterNumber;
    private SexObject sexObject;
    private WelcomeDotObject welcomeDotObject;
    private HeadIconObject headIconObject;
    private SexBoyObject sexBoyObject;
    private SexGirlObject sexGirlObject;

    private void init(Context context) {
        screenWidth = ViewUtils.getScreenWidthThoughContext(context);
        screenHeight = ViewUtils.getScreenHeightThoughContext(context);

        MOVE_LINE_PAINT.setAntiAlias(true);
        MOVE_LINE_PAINT.setStrokeWidth(20);
        MOVE_LINE_PAINT.setStyle(Paint.Style.FILL);
        MOVE_LINE_PAINT.setColor(Color.parseColor("#6699FF"));

        MOVE_QUAD_LINE_PAINT.setAntiAlias(true);
        MOVE_QUAD_LINE_PAINT.setStrokeWidth(50);
        MOVE_QUAD_LINE_PAINT.setStyle(Paint.Style.STROKE);
        MOVE_QUAD_LINE_PAINT.setColor(Color.parseColor("#6699FF"));

        enterNumber = new EnterNumber(context);
        sexObject = new SexObject(context);
        welcomeDotObject = new WelcomeDotObject(context);
        headIconObject = new HeadIconObject(context);
        sexBoyObject = new SexBoyObject(context);
        sexGirlObject = new SexGirlObject(context);
    }

    public void startEnterNumber(){
        enterNumber.setBgBlueColor();
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

    public void welcomeDotMove(){
        ACStatus.welcome_dash_always = true;
        welcomeDotObject.setIsDraw(true);
        welcomeDotObject.drawMove(this);
        sexObject.setText("性别");
        sexObject.setBgBlueColor();
    }

    public void sexEnter(){
        sexObject.setText("开始");
        sexObject.setBgGreyColor();
        sexObject.setIsDraw(true);
        sexObject.drawEnter(this);
    }

    public void drawWelcomeQuadToSex(){
        welcomeDotObject.setIsDraw(false);
        ACStatus.draw_welcome_quad_sex = true;
    }

    public void welcomeExitSexEnter(){
        enterNumber.drawExit(this);
        sexObject.drawMove(this);

        headIconObject.setIsDraw(true);
        headIconObject.setBgGreyColor();
        headIconObject.setText("下一步");
        headIconObject.drawEnter(this);

        sexBoyObject.setIsDraw(true);
        sexBoyObject.setBgGreyColor();
        sexBoyObject.setText("男");
        sexBoyObject.drawEnter(this);

        sexGirlObject.setIsDraw(true);
        sexGirlObject.setBgGreyColor();
        sexGirlObject.setText("女");
        sexGirlObject.drawEnter(this);
    }

    @Override
    protected void onDraw(final Canvas canvas) {
        super.onDraw(canvas);

        /**
         *  draw Lines
         */

        if(welcomeDotObject.isDraw()){
            Path path = new Path();
            path.moveTo(enterNumber.getCenter().x , enterNumber.getCenter().y);
            path.lineTo(welcomeDotObject.getCenter().x, welcomeDotObject.getCenter().y);
            canvas.drawPath(path ,
                    ACStatus.welcome_dash_dot || ACStatus.welcome_dash_always
                    ? welcomeDotObject.getBlueLinePaint() : welcomeDotObject.getGreyLinePaint());
            Path path2 = new Path();
            path2.moveTo(welcomeDotObject.getCenter().x , welcomeDotObject.getCenter().y);
            path2.lineTo(sexObject.getCenter().x, sexObject.getCenter().y);
            canvas.drawPath(path2 ,
                    ACStatus.welcome_dash_start || ACStatus.welcome_dash_always
                            ? welcomeDotObject.getBlueLinePaint() : welcomeDotObject.getGreyLinePaint());
            canvas.drawCircle(welcomeDotObject.getCenter().x, welcomeDotObject.getCenter().y,
                    welcomeDotObject.getRadius(),
                    ACStatus.welcome_dash_dot || ACStatus.welcome_dash_always
                            ? welcomeDotObject.getBlueDotPaint() : welcomeDotObject.getGreyDotPaint());
            canvas.drawCircle(welcomeDotObject.getCenter().x , welcomeDotObject.getCenter().y ,
                    welcomeDotObject.getRadiusCore() , welcomeDotObject.getDotWhitePaint());

        }

        if(sexBoyObject.isDraw()){
//            canvas.drawLine(sexObject.getCenter().x, sexObject.getCenter().y,
//                    sexBoyObject.getCenter().x, sexBoyObject.getCenter().y,
//                    ACStatus.sex_dash_boy || ACStatus.sex_dash_always
//                            ? sexBoyObject.getBlueLinePaint() : sexBoyObject.getGreyLinePaint());
            Path path = new Path();
            path.moveTo(sexObject.getCenter().x , sexObject.getCenter().y);
            path.lineTo(sexBoyObject.getCenter().x, sexBoyObject.getCenter().y);
            canvas.drawPath(path,
                    ACStatus.sex_dash_boy || ACStatus.sex_dash_always
                            ? sexBoyObject.getBlueLinePaint() : sexBoyObject.getGreyLinePaint());
            Path path2 = new Path();
            path2.moveTo(sexBoyObject.getCenter().x , sexBoyObject.getCenter().y);
            path2.lineTo(headIconObject.getCenter().x, headIconObject.getCenter().y);
            canvas.drawPath(path2,
                    ACStatus.sex_dash_next || ACStatus.sex_dash_always
                            ? sexBoyObject.getBlueLinePaint() : sexBoyObject.getGreyLinePaint());
        }
        if(sexGirlObject.isDraw()){
            Path path = new Path();
            path.moveTo(sexObject.getCenter().x , sexObject.getCenter().y);
            path.lineTo(sexGirlObject.getCenter().x, sexGirlObject.getCenter().y);
            canvas.drawPath(path,
                    ACStatus.sex_dash_girl || ACStatus.sex_dash_always
                            ? sexGirlObject.getBlueLinePaint() : sexGirlObject.getGreyLinePaint());
            Path path2 = new Path();
            path2.moveTo(sexGirlObject.getCenter().x , sexGirlObject.getCenter().y);
            path2.lineTo(headIconObject.getCenter().x, headIconObject.getCenter().y);
            canvas.drawPath(path2,
                    ACStatus.sex_dash_next || ACStatus.sex_dash_always
                            ? sexGirlObject.getBlueLinePaint() : sexGirlObject.getGreyLinePaint());
        }

        /**
         * draw Quad Lines
         */

        if(ACStatus.welcome_showing && ACStatus.welcome_dash_number){
            canvas.drawLine(enterNumber.getCenter().x, enterNumber.getCenter().y,
                    x, y, MOVE_LINE_PAINT);
        }

        if(ACStatus.draw_welcome_quad_sex){
            Path path = new Path();
            path.moveTo(enterNumber.getCenter().x, enterNumber.getCenter().y);
            PointF quadPoint = ACUtils.calculateQuadPoint(enterNumber.getCenter(), sexObject.getCenter(), ACUtils.QUAD_TYPE_LEFT_TOP);
            path.quadTo(quadPoint.x, quadPoint.y, sexObject.getCenter().x, sexObject.getCenter().y);
            canvas.drawPath(path, MOVE_QUAD_LINE_PAINT);
        }

        /**
         * Draw Graphs
         */

        if(enterNumber.isDraw()){
            canvas.drawCircle(enterNumber.getCenter().x,
                    enterNumber.getCenter().y, enterNumber.getRadius(), enterNumber.getPaint());
            canvas.drawText("18501667737", enterNumber.getCenter().x, enterNumber.getCenter().y, enterNumber.getTextPaint());
        }

        if(sexObject.isDraw()){
            canvas.drawCircle(sexObject.getCenter().x,
                    sexObject.getCenter().y, sexObject.getRadius(), sexObject.getPaint());
            canvas.drawText(sexObject.getText(), sexObject.getCenter().x, sexObject.getCenter().y, sexObject.getTextPaint());
        }

        if(headIconObject.isDraw()){
            canvas.drawCircle(headIconObject.getCenter().x,
                    headIconObject.getCenter().y, headIconObject.getRadius(), headIconObject.getPaint());
            canvas.drawText(headIconObject.getText(), headIconObject.getCenter().x, headIconObject.getCenter().y, headIconObject.getTextPaint());
        }

        if(sexBoyObject.isDraw()){
            canvas.drawCircle(sexBoyObject.getCenter().x,
                    sexBoyObject.getCenter().y, sexBoyObject.getRadius(), sexBoyObject.getPaint());
            canvas.drawText(sexBoyObject.getText(), sexBoyObject.getCenter().x, sexBoyObject.getCenter().y, sexBoyObject.getTextPaint());
        }
        if(sexGirlObject.isDraw()){
            canvas.drawCircle(sexGirlObject.getCenter().x,
                    sexGirlObject.getCenter().y, sexGirlObject.getRadius(), sexGirlObject.getPaint());
            canvas.drawText(sexGirlObject.getText(), sexGirlObject.getCenter().x, sexGirlObject.getCenter().y, sexGirlObject.getTextPaint());
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        x = event.getX();
        y = event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //welcome
                if(ACStatus.welcome_showing
                        && ACUtils.withPointRadius(x, y, enterNumber.getPoint2() , enterNumber.getRadius())){
                    ACStatus.welcome_dash_number = true;
                    L.d("ACTION_DOWN true");
                }
                break;
            case MotionEvent.ACTION_MOVE:
                //welcome
                if(ACStatus.welcome_showing == true
                        && ACStatus.welcome_dash_number
                        && ACUtils.withPointRadius(x, y, welcomeDotObject.getCenter() , welcomeDotObject.getRadiusAction())){
                    ACStatus.welcome_dash_dot = true;
                }
                ACStatus.welcome_dash_start = ACStatus.welcome_dash_dot
                        && ACUtils.withPointRadius(x, y, sexObject.getCenter() , sexObject.getRadius());

                break;
            case MotionEvent.ACTION_UP:
                if(ACStatus.welcome1
                        && ACUtils.withPointRadius(x, y, enterNumber.getPoint2() , enterNumber.getRadius())
                        && touchFeedback != null){
                    touchFeedback.onClickWelcome1();
                }
                if(ACStatus.welcome_showing && ACStatus.welcome_dash_start && touchFeedback!= null){
                    touchFeedback.onClickWelcomeStart();
                }

                ACStatus.welcome_dash_number = false;
                ACStatus.welcome_dash_dot = false;
                ACStatus.welcome_dash_start = false;

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
