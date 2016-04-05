package com.wanghuan.accountconfiguration.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.wanghuan.accountconfiguration.R;
import com.wanghuan.accountconfiguration.util.ACUtils;
import com.wanghuan.accountconfiguration.util.BitmapUtil;
import com.wanghuan.accountconfiguration.util.TextUtils;
import com.wanghuan.accountconfiguration.util.ViewUtils;
import com.wanghuan.accountconfiguration.view.objectviews.BirthdayObject;
import com.wanghuan.accountconfiguration.view.objectviews.EnterNumber;
import com.wanghuan.accountconfiguration.view.objectviews.HeadAlbumObject;
import com.wanghuan.accountconfiguration.view.objectviews.HeadCameraObject;
import com.wanghuan.accountconfiguration.view.objectviews.HeadChangeObject;
import com.wanghuan.accountconfiguration.view.objectviews.HeadDotObject;
import com.wanghuan.accountconfiguration.view.objectviews.HeadIconObject;
import com.wanghuan.accountconfiguration.view.objectviews.HeadSkipObject;
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
    private BirthdayObject birthdayObject;
    private HeadCameraObject headCameraObject;
    private HeadAlbumObject headAlbumObject;
    private HeadSkipObject headSkipObject;
    private HeadDotObject headDotObject;
    private HeadChangeObject headChangeObject;

    private void init(Context context) {
        screenWidth = ViewUtils.getScreenWidthThoughContext(context);
        screenHeight = ViewUtils.getScreenHeightThoughContext(context);

        MOVE_LINE_PAINT.setAntiAlias(true);
        MOVE_LINE_PAINT.setStrokeWidth(30);
        MOVE_LINE_PAINT.setStyle(Paint.Style.FILL);
        MOVE_LINE_PAINT.setColor(Color.parseColor("#009BF4"));

        MOVE_QUAD_LINE_PAINT.setAntiAlias(true);
        MOVE_QUAD_LINE_PAINT.setStrokeWidth(50);
        MOVE_QUAD_LINE_PAINT.setStyle(Paint.Style.STROKE);
        MOVE_QUAD_LINE_PAINT.setColor(Color.parseColor("#009BF4"));

        enterNumber = new EnterNumber(context);
        sexObject = new SexObject(context);
        welcomeDotObject = new WelcomeDotObject(context);
        headIconObject = new HeadIconObject(context);
        sexBoyObject = new SexBoyObject(context);
        sexGirlObject = new SexGirlObject(context);
        birthdayObject = new BirthdayObject(context);
        headCameraObject = new HeadCameraObject(context);
        headAlbumObject = new HeadAlbumObject(context);
        headSkipObject = new HeadSkipObject(context);
        headDotObject = new HeadDotObject(context);
        headChangeObject = new HeadChangeObject(context);
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
        welcomeDotObject.setIsDraw(true);
        welcomeDotObject.drawMove(this);
        sexObject.setText("性别");
        sexObject.setBgBlueColor();
    }

    public void sexDotMove(){
        sexBoyObject.setIsDraw(true);
        sexBoyObject.drawMove(this);
        sexGirlObject.setIsDraw(true);
        sexGirlObject.drawMove(this);
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

    public void drawSexQuadToHeadIcon(){
        sexBoyObject.setIsDraw(false);
        sexGirlObject.setIsDraw(false);
        ACStatus.draw_sex_quad_head_icon = true;
    }

    public void drawHeadQuadToBirthday(){
        headCameraObject.setIsDraw(false);
        headAlbumObject.setIsDraw(false);
        headSkipObject.setIsDraw(false);
        headDotObject.setIsDraw(false);
        headChangeObject.setIsDraw(false);
        ACStatus.draw_head_quad_birthday = true;
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

    public void sexExitHeadIconEnter(){
        sexObject.drawExit(this);
        headIconObject.setText("头像");
        headIconObject.drawMove(this);

        birthdayObject.setIsDraw(true);
        birthdayObject.setText("下一步");
        birthdayObject.drawEnter(this);
        HeadCameraAlbumSkipEnter();
    }

    public void HeadCameraAlbumSkipEnter(){
        ACStatus.head_showing_change = false;
        ACStatus.head_showing_choose = true;

        ACStatus.head_dash_dot = false;
        ACStatus.head_dash_change = false;
        ACStatus.head_dash_next = false;

        headDotObject.setIsDraw(false);
        headChangeObject.setIsDraw(false);

        headIconObject.setShowIcon(false);
        headCameraObject.setIsDraw(true);
        headAlbumObject.setIsDraw(true);
        headSkipObject.setIsDraw(true);
        headCameraObject.drawEnter(this);
        headAlbumObject.drawEnter(this);
        headSkipObject.drawEnter(this);
    }

    public void HeadDotChangeEnter(String path){
        ACStatus.head_showing_choose = false;
        ACStatus.head_showing_change = true;

        ACStatus.head_dash_camera = false;
        ACStatus.head_dash_album = false;
        ACStatus.head_dash_skip = false;
        ACStatus.head_dash_next = false;

        headCameraObject.setIsDraw(false);
        headAlbumObject.setIsDraw(false);
        headSkipObject.setIsDraw(false);

        headDotObject.setIsDraw(true);
        headDotObject.drawEnter(this);
        headChangeObject.setIsDraw(true);
        headChangeObject.setText("更改");
        headChangeObject.drawEnter(this);

        if(!TextUtils.isEmpty(path)){
            headIconObject.setShowIcon(true);
            headIconObject.setPath(path);
        }
    }

    public void headDotMove(){
        if(ACStatus.head_showing_choose){
            headCameraObject.setIsDraw(true);
            headAlbumObject.setIsDraw(true);
            headSkipObject.setIsDraw(true);
            headCameraObject.drawMove(this);
            headAlbumObject.drawMove(this);
            headSkipObject.drawMove(this);
        }
        if(ACStatus.head_showing_change){
            headDotObject.setIsDraw(true);
            headChangeObject.setIsDraw(true);
            headDotObject.drawMove(this);
            headChangeObject.drawMove(this);
        }
    }

    public void headExitBirthdayEnter(){
        headIconObject.setIsDraw(true);
        headIconObject.drawExit(this);
        birthdayObject.setIsDraw(true);
        birthdayObject.setText("生日");
        birthdayObject.drawMove(this);

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
            Path path = new Path();
            path.moveTo(sexObject.getCenter().x , sexObject.getCenter().y);
            path.lineTo(sexBoyObject.getCenter().x, sexBoyObject.getCenter().y);
            canvas.drawPath(path,
                    ACStatus.sex_dash_boy ? sexBoyObject.getBlueLinePaint() : sexBoyObject.getGreyLinePaint());
            Path path2 = new Path();
            path2.moveTo(sexBoyObject.getCenter().x, sexBoyObject.getCenter().y);
            path2.lineTo(headIconObject.getCenter().x, headIconObject.getCenter().y);
            canvas.drawPath(path2,
                    (ACStatus.sex_dash_boy && ACStatus.sex_dash_next) ? sexBoyObject.getBlueLinePaint() : sexBoyObject.getGreyLinePaint());
        }
        if(sexGirlObject.isDraw()){
            Path path = new Path();
            path.moveTo(sexObject.getCenter().x , sexObject.getCenter().y);
            path.lineTo(sexGirlObject.getCenter().x, sexGirlObject.getCenter().y);
            canvas.drawPath(path,
                    ACStatus.sex_dash_girl ? sexGirlObject.getBlueLinePaint() : sexGirlObject.getGreyLinePaint());
            Path path2 = new Path();
            path2.moveTo(sexGirlObject.getCenter().x , sexGirlObject.getCenter().y);
            path2.lineTo(headIconObject.getCenter().x, headIconObject.getCenter().y);
            canvas.drawPath(path2,
                    (ACStatus.sex_dash_girl && ACStatus.sex_dash_next) ? sexGirlObject.getBlueLinePaint() : sexGirlObject.getGreyLinePaint());
        }

        if(headCameraObject.isDraw()){
            Path path = new Path();
            path.moveTo(headIconObject.getCenter().x , headIconObject.getCenter().y);
            path.lineTo(headCameraObject.getCenter().x, headCameraObject.getCenter().y);
            canvas.drawPath(path,
                    ACStatus.head_dash_camera ? headCameraObject.getBlueLinePaint() : headCameraObject.getGreyLinePaint());
            Path path2 = new Path();
            path2.moveTo(headCameraObject.getCenter().x , headCameraObject.getCenter().y);
            path2.lineTo(birthdayObject.getCenter().x, birthdayObject.getCenter().y);
            canvas.drawPath(path2,
                    (ACStatus.head_dash_camera && ACStatus.head_dash_next) ? headCameraObject.getBlueLinePaint() : headCameraObject.getGreyLinePaint());
        }
        if(headAlbumObject.isDraw()){
            Path path = new Path();
            path.moveTo(headIconObject.getCenter().x , headIconObject.getCenter().y);
            path.lineTo(headAlbumObject.getCenter().x, headAlbumObject.getCenter().y);
            canvas.drawPath(path,
                    ACStatus.head_dash_album ? headAlbumObject.getBlueLinePaint() : headAlbumObject.getGreyLinePaint());
            Path path2 = new Path();
            path2.moveTo(headAlbumObject.getCenter().x , headAlbumObject.getCenter().y);
            path2.lineTo(birthdayObject.getCenter().x, birthdayObject.getCenter().y);
            canvas.drawPath(path2,
                    (ACStatus.head_dash_album && ACStatus.head_dash_next) ? headAlbumObject.getBlueLinePaint() : headAlbumObject.getGreyLinePaint());
        }
        if(headSkipObject.isDraw()){
            Path path = new Path();
            path.moveTo(headIconObject.getCenter().x , headIconObject.getCenter().y);
            path.lineTo(headSkipObject.getCenter().x, headSkipObject.getCenter().y);
            canvas.drawPath(path,
                    ACStatus.head_dash_skip ? headSkipObject.getBlueLinePaint() : headSkipObject.getGreyLinePaint());
            Path path2 = new Path();
            path2.moveTo(headSkipObject.getCenter().x , headSkipObject.getCenter().y);
            path2.lineTo(birthdayObject.getCenter().x, birthdayObject.getCenter().y);
            canvas.drawPath(path2,
                    (ACStatus.head_dash_skip && ACStatus.head_dash_next) ? headSkipObject.getBlueLinePaint() : headSkipObject.getGreyLinePaint());
        }

        if(headDotObject.isDraw()){
            Path path = new Path();
            path.moveTo(headIconObject.getCenter().x , headIconObject.getCenter().y);
            path.lineTo(headDotObject.getCenter().x, headDotObject.getCenter().y);
            canvas.drawPath(path,
                    ACStatus.head_dash_dot ? headDotObject.getBlueLinePaint() : headDotObject.getGreyLinePaint());
            Path path2 = new Path();
            path2.moveTo(headDotObject.getCenter().x, headDotObject.getCenter().y);
            path2.lineTo(birthdayObject.getCenter().x, birthdayObject.getCenter().y);
            canvas.drawPath(path2,
                    ACStatus.head_dash_dot ? headDotObject.getBlueLinePaint() : headDotObject.getGreyLinePaint());
            canvas.drawCircle(headDotObject.getCenter().x, headDotObject.getCenter().y,
                    headDotObject.getRadius_dot(),
                    ACStatus.head_dash_dot ? headDotObject.getBlueDotPaint() : headDotObject.getGreyDotPaint());
            canvas.drawCircle(headDotObject.getCenter().x, headDotObject.getCenter().y,
                    headDotObject.getRadius_dot_core(), headDotObject.getDotWhitePaint());
        }

        if(headChangeObject.isDraw()){
            Path path = new Path();
            path.moveTo(headIconObject.getCenter().x, headIconObject.getCenter().y);
            path.lineTo(headChangeObject.getCenter().x, headChangeObject.getCenter().y);
            canvas.drawPath(path,
                    ACStatus.head_dash_change ? headDotObject.getBlueLinePaint() : headDotObject.getGreyLinePaint());
        }

        /**
         * draw moving Lines
         */

        if(ACStatus.welcome_showing && ACStatus.welcome_dash_number){
            canvas.drawLine(enterNumber.getCenter().x, enterNumber.getCenter().y,
                    x, y, MOVE_LINE_PAINT);
        }
        if(ACStatus.sex_showing && ACStatus.sex_dash_object){
            canvas.drawLine(sexObject.getCenter().x, sexObject.getCenter().y,
                    x, y, MOVE_LINE_PAINT);
        }
        if((ACStatus.head_showing_choose || ACStatus.head_showing_change) && ACStatus.head_dash_object){
            canvas.drawLine(headIconObject.getCenter().x, headIconObject.getCenter().y,
                    x, y, MOVE_LINE_PAINT);
        }

        /**
         * draw Quad Lines
         */
        if(ACStatus.draw_welcome_quad_sex){
            Path path = new Path();
            path.moveTo(enterNumber.getCenter().x, enterNumber.getCenter().y);
            PointF quadPoint = ACUtils.calculateQuadPoint(enterNumber.getCenter(), sexObject.getCenter(), ACUtils.QUAD_TYPE_LEFT_TOP);
            path.quadTo(quadPoint.x, quadPoint.y, sexObject.getCenter().x, sexObject.getCenter().y);
            canvas.drawPath(path, MOVE_QUAD_LINE_PAINT);
        }
        if(ACStatus.draw_sex_quad_head_icon){
            Path path = new Path();
            path.moveTo(sexObject.getCenter().x, sexObject.getCenter().y);
            PointF quadPoint = ACUtils.calculateQuadPoint(sexObject.getCenter(), headIconObject.getCenter(), ACUtils.QUAD_TYPE_LEFT_TOP);
            path.quadTo(quadPoint.x, quadPoint.y, headIconObject.getCenter().x, headIconObject.getCenter().y);
            canvas.drawPath(path, MOVE_QUAD_LINE_PAINT);
        }
        if(ACStatus.draw_head_quad_birthday){
            Path path = new Path();
            path.moveTo(headIconObject.getCenter().x, headIconObject.getCenter().y);
            PointF quadPoint = ACUtils.calculateQuadPoint(headIconObject.getCenter(), birthdayObject.getCenter(), ACUtils.QUAD_TYPE_LEFT_TOP);
            path.quadTo(quadPoint.x, quadPoint.y, birthdayObject.getCenter().x, birthdayObject.getCenter().y);
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
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                    ACStatus.welcome_dash_start || ACStatus.welcome_dash_always ? R.mipmap.ac_normal_bg : R.mipmap.ac_bg_grey);
            canvas.drawBitmap(bitmap, null,
                    ACUtils.getBitmapRect(sexObject.getCenter(), sexObject.getRadius()), null);
            if(ACStatus.sex_dash_boy || ACStatus.sex_dash_girl){
                Bitmap bitmapBoyLarge = BitmapFactory.decodeResource(getResources(),
                        ACStatus.sex_dash_boy ? R.mipmap.ac_sex_boy_large : R.mipmap.ac_sex_girl_large);
                canvas.drawBitmap(bitmapBoyLarge , null ,
                        ACUtils.getBitmapRect(sexObject.getCenter() , sexObject.getRadius()-30) , null);
            }else{
                canvas.drawText(sexObject.getText(), sexObject.getCenter().x, sexObject.getCenter().y, sexObject.getTextPaint());
            }
        }

        if(headIconObject.isDraw()){
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                    ACStatus.sex_dash_next || ACStatus.sex_dash_always ? R.mipmap.ac_normal_bg : R.mipmap.ac_bg_grey);
            canvas.drawBitmap(bitmap , null ,
                    ACUtils.getBitmapRect(headIconObject.getCenter() , headIconObject.getRadius()) , null);
            if(!headIconObject.isShowIcon()){
                canvas.drawText(headIconObject.getText(), headIconObject.getCenter().x, headIconObject.getCenter().y, headIconObject.getTextPaint());
            }else{
                Bitmap headBitmap = BitmapUtil.createCircleImage(
                        headIconObject.getPath() , (int)headIconObject.getRadius() - 20);
                canvas.drawBitmap(headBitmap, null,
                        ACUtils.getBitmapRect(headIconObject.getCenter(), headIconObject.getRadius() - 20), null);
            }
        }

        if(sexBoyObject.isDraw()){
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                    ACStatus.sex_dash_boy? R.mipmap.ac_sex_boy_touched : R.mipmap.ac_sex_boy);
            canvas.drawBitmap(bitmap, null,
                    ACUtils.getBitmapRect(sexBoyObject.getCenter(), sexBoyObject.getRadius()), null);
            canvas.drawText(sexBoyObject.getText(), sexBoyObject.getCenter().x, sexBoyObject.getCenter().y, sexBoyObject.getTextPaint());
        }
        if(sexGirlObject.isDraw()){
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                    ACStatus.sex_dash_girl? R.mipmap.ac_sex_girl_touched : R.mipmap.ac_sex_girl);
            canvas.drawBitmap(bitmap, null,
                    ACUtils.getBitmapRect(sexGirlObject.getCenter(), sexGirlObject.getRadius()), null);
            canvas.drawText(sexGirlObject.getText(), sexGirlObject.getCenter().x, sexGirlObject.getCenter().y, sexGirlObject.getTextPaint());
        }

        if(birthdayObject.isDraw()){
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                    ACStatus.head_dash_next? R.mipmap.ac_normal_bg : R.mipmap.ac_bg_grey);
            canvas.drawBitmap(bitmap , null ,
                    ACUtils.getBitmapRect(birthdayObject.getCenter() , birthdayObject.getRadius()) , null);
            canvas.drawText(birthdayObject.getText(), birthdayObject.getCenter().x, birthdayObject.getCenter().y, birthdayObject.getTextPaint());
        }
        if(headCameraObject.isDraw()){
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                    ACStatus.head_dash_camera ? R.mipmap.ac_icon_carmera_touched : R.mipmap.ac_icon_carmera);
            canvas.drawBitmap(bitmap, null,
                    ACUtils.getBitmapRect(headCameraObject.getCenter(), headCameraObject.getRadius()), null);
        }
        if(headAlbumObject.isDraw()){
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                    ACStatus.head_dash_album ? R.mipmap.ac_icon_carmera_touched : R.mipmap.ac_icon_album);
            canvas.drawBitmap(bitmap, null,
                    ACUtils.getBitmapRect(headAlbumObject.getCenter(), headAlbumObject.getRadius()), null);
        }
        if(headSkipObject.isDraw()){
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                    ACStatus.head_dash_skip ? R.mipmap.ac_icon_carmera_touched : R.mipmap.ac_icon_skip);
            canvas.drawBitmap(bitmap, null,
                    ACUtils.getBitmapRect(headSkipObject.getCenter(), headSkipObject.getRadius()), null);
        }
        if(headChangeObject.isDraw()){
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                    ACStatus.head_dash_change ? R.mipmap.ac_normal_bg : R.mipmap.ac_bg_grey);
            canvas.drawBitmap(bitmap , null ,
                    ACUtils.getBitmapRect(headChangeObject.getCenter() , headChangeObject.getRadius_small()) , null);
            canvas.drawText(headChangeObject.getText(), headChangeObject.getCenter().x, headChangeObject.getCenter().y, headChangeObject.getTextPaint());
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
                }
                //sex
                if(ACStatus.sex_showing
                        && ACUtils.withPointRadius(x, y, sexObject.getCenter() , sexObject.getRadius())){
                    ACStatus.sex_dash_object = true;
                }
                //head icon
                if((ACStatus.head_showing_choose || ACStatus.head_showing_change)
                        && ACUtils.withPointRadius(x, y, headIconObject.getCenter() , headIconObject.getRadius())){
                    ACStatus.head_dash_object = true;
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
                //sex
                if(ACStatus.sex_showing == true
                        && ACStatus.sex_dash_object
                        && ACUtils.withPointRadius(x, y, sexBoyObject.getCenter() , sexBoyObject.getRadius())){
                    ACStatus.sex_dash_boy = true;
                    ACStatus.sex_dash_girl = false;
                }
                if(ACStatus.sex_showing == true
                        && ACStatus.sex_dash_object
                        && ACUtils.withPointRadius(x, y, sexGirlObject.getCenter() , sexGirlObject.getRadius())){
                    ACStatus.sex_dash_girl = true;
                    ACStatus.sex_dash_boy = false;
                }
                ACStatus.sex_dash_next = (ACStatus.sex_dash_boy || ACStatus.sex_dash_girl)
                        && ACUtils.withPointRadius(x, y, headIconObject.getCenter() , headIconObject.getRadius());

                //head icon
                if(ACStatus.head_showing_choose
                        && ACStatus.head_dash_object
                        && ACUtils.withPointRadius(x, y, headCameraObject.getCenter() , headCameraObject.getRadius())){
                    ACStatus.head_dash_camera = true;
                    ACStatus.head_dash_album = false;
                    ACStatus.head_dash_skip = false;
                }
                if(ACStatus.head_showing_choose
                        && ACStatus.head_dash_object
                        && ACUtils.withPointRadius(x, y, headAlbumObject.getCenter() , headAlbumObject.getRadius())){
                    ACStatus.head_dash_album = true;
                    ACStatus.head_dash_camera = false;
                    ACStatus.head_dash_skip = false;
                }
                if(ACStatus.head_showing_choose
                        && ACStatus.head_dash_object
                        && ACUtils.withPointRadius(x, y, headSkipObject.getCenter() , headSkipObject.getRadius())){
                    ACStatus.head_dash_skip = true;
                    ACStatus.head_dash_camera = false;
                    ACStatus.head_dash_album = false;
                }
                if(ACStatus.head_showing_change
                        && ACStatus.head_dash_object
                        && ACUtils.withPointRadius(x , y , headDotObject.getCenter() , headDotObject.getRadius_dot_action())){
                    ACStatus.head_dash_dot = true;
                    ACStatus.head_dash_change = false;
                }
                if(ACStatus.head_showing_change
                        && ACStatus.head_dash_object
                        && ACUtils.withPointRadius(x , y , headChangeObject.getCenter() , headChangeObject.getRadius())){
                    ACStatus.head_dash_change = true;
                    ACStatus.head_dash_dot = false;
                }else{
                    ACStatus.head_dash_change = false;
                }
                ACStatus.head_dash_next =
                        (ACStatus.head_dash_camera || ACStatus.head_dash_album || ACStatus.head_dash_skip || ACStatus.head_dash_dot)
                        && ACUtils.withPointRadius(x, y, birthdayObject.getCenter() , birthdayObject.getRadius());


                break;
            case MotionEvent.ACTION_UP:
                if(ACStatus.welcome1
                        && ACUtils.withPointRadius(x, y, enterNumber.getPoint2() , enterNumber.getRadius())
                        && touchFeedback != null){
                    touchFeedback.onClickWelcome1();
                }
                if(ACStatus.welcome_showing && ACStatus.welcome_dash_start && touchFeedback!= null){
                    ACStatus.welcome_dash_always = true;
                    touchFeedback.onClickWelcomeStart();
                }

                if(ACStatus.sex_showing && ACStatus.sex_dash_next && touchFeedback!= null){
                    ACStatus.sex_dash_always = true;
                    ACStatus.sex_dash_object = false;
                    touchFeedback.onClickSexNext();
                }else{
                    ACStatus.sex_dash_object = false;
                    ACStatus.sex_dash_boy = false;
                    ACStatus.sex_dash_girl = false;
                    ACStatus.sex_dash_next = false;
                }

                if(ACStatus.head_showing_choose && ACStatus.head_dash_next && touchFeedback != null){
                    ACStatus.head_dash_object = false;
                    if(ACStatus.head_dash_camera){
                        touchFeedback.onClickHeadCamera();
                    }
                    if(ACStatus.head_dash_album){
                        touchFeedback.onClickHeadAlbum();
                    }
                    if(ACStatus.head_dash_skip){
                        touchFeedback.onClickHeadSkip();
                    }
                }else if(ACStatus.head_showing_change && ACStatus.head_dash_next && touchFeedback != null){
                    ACStatus.head_dash_always = true;
                    ACStatus.head_dash_object = false;
                    touchFeedback.onClickHeadNext();
                }else if(ACStatus.head_showing_change && ACStatus.head_dash_change && touchFeedback != null){
                    ACStatus.head_dash_always = true;
                    ACStatus.head_dash_object = false;
                    touchFeedback.onClickHeadChange();
                }else{
                    ACStatus.head_dash_always = false;
                    ACStatus.head_dash_object = false;
                    ACStatus.head_dash_camera = false;
                    ACStatus.head_dash_album = false;
                    ACStatus.head_dash_skip = false;
                    ACStatus.head_dash_next = false;
                    ACStatus.head_dash_dot = false;
                    ACStatus.head_dash_change = false;
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
