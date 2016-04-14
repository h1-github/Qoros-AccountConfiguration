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
import com.wanghuan.accountconfiguration.view.objectviews.BirthdayDotObject;
import com.wanghuan.accountconfiguration.view.objectviews.BirthdayObject;
import com.wanghuan.accountconfiguration.view.objectviews.CircleCloseObject;
import com.wanghuan.accountconfiguration.view.objectviews.CircleDotObject;
import com.wanghuan.accountconfiguration.view.objectviews.CircleObject;
import com.wanghuan.accountconfiguration.view.objectviews.CircleOpenObject;
import com.wanghuan.accountconfiguration.view.objectviews.CompleteObject;
import com.wanghuan.accountconfiguration.view.objectviews.EmailDotObject;
import com.wanghuan.accountconfiguration.view.objectviews.EmailObject;
import com.wanghuan.accountconfiguration.view.objectviews.EnterNumber;
import com.wanghuan.accountconfiguration.view.objectviews.HeadAlbumObject;
import com.wanghuan.accountconfiguration.view.objectviews.HeadCameraObject;
import com.wanghuan.accountconfiguration.view.objectviews.HeadChangeObject;
import com.wanghuan.accountconfiguration.view.objectviews.HeadDotObject;
import com.wanghuan.accountconfiguration.view.objectviews.HeadIconObject;
import com.wanghuan.accountconfiguration.view.objectviews.HeadSkipObject;
import com.wanghuan.accountconfiguration.view.objectviews.LastCloseObject;
import com.wanghuan.accountconfiguration.view.objectviews.LastDotObject;
import com.wanghuan.accountconfiguration.view.objectviews.LastObject;
import com.wanghuan.accountconfiguration.view.objectviews.LastOpenObject;
import com.wanghuan.accountconfiguration.view.objectviews.NicknameDotObject;
import com.wanghuan.accountconfiguration.view.objectviews.NicknameObject;
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
    private BirthdayDotObject birthdayDotObject;
    private NicknameObject nicknameObject;
    private NicknameDotObject nicknameDotObject;
    private EmailObject emailObject;
    private EmailDotObject emailDotObject;
    private CircleObject circleObject;
    private CircleDotObject circleDotObject;
    private CircleOpenObject circleOpenObject;
    private CircleCloseObject circleCloseObject;
    private LastObject lastObject;
    private LastDotObject lastDotObject;
    private LastOpenObject lastOpenObject;
    private LastCloseObject lastCloseObject;
    private CompleteObject completeObject;


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
        nicknameObject = new NicknameObject(context);
        nicknameDotObject = new NicknameDotObject(context);
        birthdayDotObject = new BirthdayDotObject(context);
        emailObject = new EmailObject(context);
        emailDotObject = new EmailDotObject(context);
        circleObject = new CircleObject(context);
        circleDotObject = new CircleDotObject(context);
        circleOpenObject = new CircleOpenObject(context);
        circleCloseObject = new CircleCloseObject(context);
        lastObject = new LastObject(context);
        lastDotObject = new LastDotObject(context);
        lastOpenObject = new LastOpenObject(context);
        lastCloseObject = new LastCloseObject(context);
        completeObject = new CompleteObject(context);
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
    public void drawBirthdayQuadToNickname(){
        birthdayDotObject.setIsDraw(false);
        ACStatus.draw_birthday_quad_nick = true;
    }

    public void drawNicknameQuadToEmail(){
        nicknameDotObject.setIsDraw(false);
        ACStatus.draw_nickname_quad_email = true;
    }

    public void drawEmailQuadToCircle(){
        emailDotObject.setIsDraw(false);
        ACStatus.draw_email_quad_circle = true;
    }

    public void drawCircleQuadToLast(){
        circleDotObject.setIsDraw(false);
        circleOpenObject.setIsDraw(false);
        circleCloseObject.setIsDraw(false);
        ACStatus.draw_circle_quad_last = true;
    }

    public void drawLastQuadToComplete(){
        lastDotObject.setIsDraw(false);
        lastOpenObject.setIsDraw(false);
        lastCloseObject.setIsDraw(false);
        ACStatus.draw_last_quad_completed = true;
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
        releaseWelcome();
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

    public void headSkipDotMove(){
            headCameraObject.setIsDraw(true);
            headAlbumObject.setIsDraw(true);
            headSkipObject.setIsDraw(true);
            headCameraObject.drawMove(this);
            headAlbumObject.drawMove(this);
            headSkipObject.drawMove(this);
    }

    public void headExitBirthdayEnter(){
        releaseSex();
        headIconObject.setIsDraw(true);
        headIconObject.drawExit(this);
        birthdayObject.setIsDraw(true);
        birthdayObject.setText("生日");
        birthdayObject.drawMove(this);

        nicknameObject.setIsDraw(true);
        nicknameObject.setText("下一步");
        nicknameObject.drawEnter(this);
        birthdayDotObject.setIsDraw(true);
        birthdayDotObject.drawEnter(this);
    }

    public void birthdayDotMove(){
        birthdayDotObject.setIsDraw(true);
        birthdayDotObject.drawMove(this);
    }

    public void nicknameDotMove(){
        nicknameDotObject.setIsDraw(true);
        nicknameDotObject.drawMove(this);
    }

    public void emailDotMove(){
        emailDotObject.setIsDraw(true);
        emailDotObject.drawMove(this);
    }

    public void circleDotMove(){
//        circleDotObject.setIsDraw(true);
//        circleDotObject.drawMove(this);

        circleOpenObject.setText("参加");
        circleCloseObject.setText("否");
        circleOpenObject.setIsDraw(true);
        circleOpenObject.drawMove(this);
        circleCloseObject.setIsDraw(true);
        circleCloseObject.drawMove(this);
    }

    public void lastDotMove(){
        lastDotObject.setIsDraw(true);
        lastDotObject.drawMove(this);

        lastOpenObject.setIsDraw(true);
        lastOpenObject.drawMove(this);
        lastCloseObject.setIsDraw(true);
        lastCloseObject.drawMove(this);
    }

    public void birthdayExitNicknameEnter(){
        releaseHeadIcon();
        birthdayObject.setIsDraw(true);
        birthdayObject.drawExit(this);
        nicknameObject.setIsDraw(true);
        nicknameObject.setText("昵称");
        nicknameObject.drawMove(this);
        nicknameDotObject.setIsDraw(true);
        nicknameDotObject.drawEnter(this);

        emailObject.setIsDraw(true);
        emailObject.setText("下一步");
        emailObject.drawEnter(this);
    }

    public void nicknameExitEmailEnter(){
        releaseBirthday();
        nicknameObject.setIsDraw(true);
        nicknameObject.drawExit(this);
        emailObject.setIsDraw(true);
        emailObject.setText("电子邮箱");
        emailObject.drawMove(this);
        emailDotObject.setIsDraw(true);
        emailDotObject.drawEnter(this);

        circleObject.setIsDraw(true);
        circleObject.setText("下一步");
        circleObject.drawEnter(this);
    }

    public void emailExitCircleEnter(){
        releaseNickname();
        emailObject.setIsDraw(true);
        emailObject.drawExit(this);
        circleObject.setIsDraw(true);
        circleObject.setText("车友圈");
        circleObject.drawMove(this);
//        circleDotObject.setIsDraw(true);
//        circleDotObject.drawEnter(this);
        circleOpenObject.setText("参加");
        circleCloseObject.setText("否");
        circleOpenObject.setIsDraw(true);
        circleOpenObject.drawEnter(this);
        circleCloseObject.setIsDraw(true);
        circleCloseObject.drawEnter(this);

        lastObject.setIsDraw(true);
        lastObject.setText("下一步");
        lastObject.drawEnter(this);
    }

    public void circleExitLastEnter(){
        releaseEmail();
        circleObject.setIsDraw(true);
        circleObject.drawExit(this);
        lastObject.setIsDraw(true);
        lastObject.setText("监控范围");
        lastObject.drawMove(this);
        lastDotObject.setIsDraw(true);
        lastDotObject.drawEnter(this);

        lastOpenObject.setText("开启");
        lastCloseObject.setText("关闭");
        lastOpenObject.setIsDraw(true);
        lastOpenObject.drawEnter(this);
        lastCloseObject.setIsDraw(true);
        lastCloseObject.drawEnter(this);

        completeObject.setIsDraw(true);
        completeObject.setText("完成设置");
        completeObject.drawEnter(this);
    }

    public void lastExitCompleted(){
        releaseCircle();
        lastObject.setIsDraw(true);
        lastObject.drawExit(this);
        completeObject.setIsDraw(true);
        completeObject.setText("完成设置");
        completeObject.drawMove(this);
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

        if(birthdayDotObject.isDraw()){
            Path path = new Path();
            path.moveTo(birthdayObject.getCenter().x , birthdayObject.getCenter().y);
            path.lineTo(birthdayDotObject.getCenter().x, birthdayDotObject.getCenter().y);
            canvas.drawPath(path,
                    ACStatus.birthday_dash_dot ? birthdayDotObject.getBlueLinePaint() : birthdayDotObject.getGreyLinePaint());
            Path path2 = new Path();
            path2.moveTo(birthdayDotObject.getCenter().x, birthdayDotObject.getCenter().y);
            path2.lineTo(nicknameObject.getCenter().x, nicknameObject.getCenter().y);
            canvas.drawPath(path2,
                    ACStatus.birthday_dash_next ? nicknameObject.getBlueLinePaint() : nicknameObject.getGreyLinePaint());
            canvas.drawCircle(birthdayDotObject.getCenter().x, birthdayDotObject.getCenter().y,
                    birthdayDotObject.getRadius_dot(),
                    ACStatus.birthday_dash_dot ? birthdayDotObject.getBlueDotPaint() : birthdayDotObject.getGreyDotPaint());
            canvas.drawCircle(birthdayDotObject.getCenter().x, birthdayDotObject.getCenter().y,
                    birthdayDotObject.getRadius_dot_core(), birthdayDotObject.getDotWhitePaint());
        }

        if(nicknameDotObject.isDraw()){
            Path path = new Path();
            path.moveTo(nicknameObject.getCenter().x , nicknameObject.getCenter().y);
            path.lineTo(nicknameDotObject.getCenter().x, nicknameDotObject.getCenter().y);
            canvas.drawPath(path,
                    ACStatus.nick_dash_dot ? nicknameDotObject.getBlueLinePaint() : nicknameDotObject.getGreyLinePaint());
            Path path2 = new Path();
            path2.moveTo(nicknameDotObject.getCenter().x, nicknameDotObject.getCenter().y);
            path2.lineTo(emailObject.getCenter().x, emailObject.getCenter().y);
            canvas.drawPath(path2,
                    ACStatus.nick_dash_next ? nicknameDotObject.getBlueLinePaint() : nicknameDotObject.getGreyLinePaint());
            canvas.drawCircle(nicknameDotObject.getCenter().x, nicknameDotObject.getCenter().y,
                    nicknameDotObject.getRadius_dot(),
                    ACStatus.nick_dash_dot ? nicknameDotObject.getBlueDotPaint() : nicknameDotObject.getGreyDotPaint());
            canvas.drawCircle(nicknameDotObject.getCenter().x, nicknameDotObject.getCenter().y,
                    nicknameDotObject.getRadius_dot_core(), nicknameDotObject.getDotWhitePaint());
        }

        if(emailDotObject.isDraw()){
            Path path = new Path();
            path.moveTo(emailObject.getCenter().x , emailObject.getCenter().y);
            path.lineTo(emailDotObject.getCenter().x, emailDotObject.getCenter().y);
            canvas.drawPath(path,
                    ACStatus.email_dash_dot ? emailDotObject.getBlueLinePaint() : emailDotObject.getGreyLinePaint());
            Path path2 = new Path();
            path2.moveTo(emailDotObject.getCenter().x, emailDotObject.getCenter().y);
            path2.lineTo(circleObject.getCenter().x, circleObject.getCenter().y);
            canvas.drawPath(path2,
                    ACStatus.email_dash_next ? emailDotObject.getBlueLinePaint() : emailDotObject.getGreyLinePaint());
            canvas.drawCircle(emailDotObject.getCenter().x, emailDotObject.getCenter().y,
                    emailDotObject.getRadius_dot(),
                    ACStatus.email_dash_dot ? emailDotObject.getBlueDotPaint() : emailDotObject.getGreyDotPaint());
            canvas.drawCircle(emailDotObject.getCenter().x, emailDotObject.getCenter().y,
                    emailDotObject.getRadius_dot_core(), emailDotObject.getDotWhitePaint());
        }

//        if(circleDotObject.isDraw()){
//            Path path = new Path();
//            path.moveTo(circleObject.getCenter().x , circleObject.getCenter().y);
//            path.lineTo(circleDotObject.getCenter().x, circleDotObject.getCenter().y);
//            canvas.drawPath(path,
//                    ACStatus.circle_dash_dot ? circleDotObject.getBlueLinePaint() : circleDotObject.getGreyLinePaint());
//            Path path2 = new Path();
//            path2.moveTo(circleDotObject.getCenter().x, circleDotObject.getCenter().y);
//            path2.lineTo(lastObject.getCenter().x, lastObject.getCenter().y);
//            canvas.drawPath(path2,
//                    ACStatus.circle_dash_next ? circleDotObject.getBlueLinePaint() : circleDotObject.getGreyLinePaint());
//            canvas.drawCircle(circleDotObject.getCenter().x, circleDotObject.getCenter().y,
//                    circleDotObject.getRadius_dot(),
//                    ACStatus.circle_dash_dot ? circleDotObject.getBlueDotPaint() : circleDotObject.getGreyDotPaint());
//            canvas.drawCircle(circleDotObject.getCenter().x, circleDotObject.getCenter().y,
//                    circleDotObject.getRadius_dot_core(), circleDotObject.getDotWhitePaint());
//        }

        if(circleOpenObject.isDraw()){
            Path path = new Path();
            path.moveTo(circleObject.getCenter().x , circleObject.getCenter().y);
            path.lineTo(circleOpenObject.getCenter().x, circleOpenObject.getCenter().y);
            canvas.drawPath(path,
                    ACStatus.circle_dash_open ? circleOpenObject.getBlueLinePaint() : circleOpenObject.getGreyLinePaint());
            Path path2 = new Path();
            path2.moveTo(circleOpenObject.getCenter().x, circleOpenObject.getCenter().y);
            path2.lineTo(lastObject.getCenter().x, lastObject.getCenter().y);
            canvas.drawPath(path2,
                    (ACStatus.circle_dash_open & ACStatus.circle_dash_next)
                            ? circleOpenObject.getBlueLinePaint() : circleOpenObject.getGreyLinePaint());
            canvas.drawCircle(circleOpenObject.getCenter().x, circleOpenObject.getCenter().y,
                    circleOpenObject.getRadius_small(),
                    ACStatus.circle_dash_open ? circleOpenObject.getBlueDotPaint() : circleOpenObject.getGreyDotPaint());
            canvas.drawText(circleOpenObject.getText() , circleOpenObject.getCenter().x, circleOpenObject.getCenter().y,
                    circleOpenObject.getTextPaint());
        }

        if(circleCloseObject.isDraw()){
            Path path = new Path();
            path.moveTo(circleObject.getCenter().x , circleObject.getCenter().y);
            path.lineTo(circleCloseObject.getCenter().x, circleCloseObject.getCenter().y);
            canvas.drawPath(path,
                    ACStatus.circle_dash_close ? circleCloseObject.getBlueLinePaint() : circleCloseObject.getGreyLinePaint());
            Path path2 = new Path();
            path2.moveTo(circleCloseObject.getCenter().x, circleCloseObject.getCenter().y);
            path2.lineTo(lastObject.getCenter().x, lastObject.getCenter().y);
            canvas.drawPath(path2,
                    (ACStatus.circle_dash_close && ACStatus.circle_dash_next)
                            ? circleCloseObject.getBlueLinePaint() : circleCloseObject.getGreyLinePaint());
            canvas.drawCircle(circleCloseObject.getCenter().x, circleCloseObject.getCenter().y,
                    circleCloseObject.getRadius_small(),
                    ACStatus.circle_dash_close ? circleCloseObject.getBlueDotPaint() : circleCloseObject.getGreyDotPaint());
            canvas.drawText(circleCloseObject.getText(), circleCloseObject.getCenter().x, circleCloseObject.getCenter().y,
                    circleCloseObject.getTextPaint());
        }

//        if(lastDotObject.isDraw()){
//            Path path = new Path();
//            path.moveTo(lastObject.getCenter().x , lastObject.getCenter().y);
//            path.lineTo(lastDotObject.getCenter().x, lastDotObject.getCenter().y);
//            canvas.drawPath(path,
//                    ACStatus.last_dash_dot ? lastDotObject.getBlueLinePaint() : lastDotObject.getGreyLinePaint());
//            Path path2 = new Path();
//            path2.moveTo(lastDotObject.getCenter().x, lastDotObject.getCenter().y);
//            path2.lineTo(completeObject.getCenter().x, completeObject.getCenter().y);
//            canvas.drawPath(path2,
//                    ACStatus.last_dash_next ? lastDotObject.getBlueLinePaint() : lastDotObject.getGreyLinePaint());
//            canvas.drawCircle(lastDotObject.getCenter().x, lastDotObject.getCenter().y,
//                    lastDotObject.getRadius_dot(),
//                    ACStatus.last_dash_dot ? lastDotObject.getBlueDotPaint() : lastDotObject.getGreyDotPaint());
//            canvas.drawCircle(lastDotObject.getCenter().x, lastDotObject.getCenter().y,
//                    lastDotObject.getRadius_dot_core(), lastDotObject.getDotWhitePaint());
//        }
        if(lastOpenObject.isDraw()){
            Path path = new Path();
            path.moveTo(lastObject.getCenter().x , lastObject.getCenter().y);
            path.lineTo(lastOpenObject.getCenter().x, lastOpenObject.getCenter().y);
            canvas.drawPath(path,
                    ACStatus.last_dash_open ? lastOpenObject.getBlueLinePaint() : lastOpenObject.getGreyLinePaint());
            Path path2 = new Path();
            path2.moveTo(lastOpenObject.getCenter().x, lastOpenObject.getCenter().y);
            path2.lineTo(completeObject.getCenter().x, completeObject.getCenter().y);
            canvas.drawPath(path2,
                    (ACStatus.last_dash_open &&  ACStatus.last_dash_next)
                            ? lastOpenObject.getBlueLinePaint() : lastOpenObject.getGreyLinePaint());
            canvas.drawCircle(lastOpenObject.getCenter().x, lastOpenObject.getCenter().y,
                    lastOpenObject.getRadius_small(),
                    ACStatus.last_dash_open ? lastOpenObject.getBlueDotPaint() : lastOpenObject.getGreyDotPaint());
            canvas.drawText(lastOpenObject.getText(), lastOpenObject.getCenter().x, lastOpenObject.getCenter().y,
                    lastOpenObject.getTextPaint());
        }
        if(lastCloseObject.isDraw()){
            Path path = new Path();
            path.moveTo(lastObject.getCenter().x , lastObject.getCenter().y);
            path.lineTo(lastCloseObject.getCenter().x, lastCloseObject.getCenter().y);
            canvas.drawPath(path,
                    ACStatus.last_dash_close ? lastCloseObject.getBlueLinePaint() : lastCloseObject.getGreyLinePaint());
            Path path2 = new Path();
            path2.moveTo(lastCloseObject.getCenter().x, lastCloseObject.getCenter().y);
            path2.lineTo(completeObject.getCenter().x, completeObject.getCenter().y);
            canvas.drawPath(path2,
                    (ACStatus.last_dash_close && ACStatus.last_dash_next)
                            ? lastCloseObject.getBlueLinePaint() : lastCloseObject.getGreyLinePaint());
            canvas.drawCircle(lastCloseObject.getCenter().x, lastCloseObject.getCenter().y,
                    lastCloseObject.getRadius_small(),
                    ACStatus.last_dash_close ? lastCloseObject.getBlueDotPaint() : lastCloseObject.getGreyDotPaint());
            canvas.drawText(lastCloseObject.getText(), lastCloseObject.getCenter().x, lastCloseObject.getCenter().y,
                    lastCloseObject.getTextPaint());
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
        if(ACStatus.birthday_showing && ACStatus.birthday_dash_object){
            canvas.drawLine(birthdayObject.getCenter().x, birthdayObject.getCenter().y,
                    x, y, MOVE_LINE_PAINT);
        }
        if(ACStatus.nick_showing && ACStatus.nick_dash_object){
            canvas.drawLine(nicknameObject.getCenter().x, nicknameObject.getCenter().y,
                    x, y, MOVE_LINE_PAINT);
        }
        if(ACStatus.email_showing && ACStatus.email_dash_object){
            canvas.drawLine(emailObject.getCenter().x, emailObject.getCenter().y,
                    x, y, MOVE_LINE_PAINT);
        }
        if(ACStatus.circle_showing && ACStatus.circle_dash_object){
            canvas.drawLine(circleObject.getCenter().x, circleObject.getCenter().y,
                    x, y, MOVE_LINE_PAINT);
        }
        if(ACStatus.last_showing && ACStatus.last_dash_object){
            canvas.drawLine(lastObject.getCenter().x, lastObject.getCenter().y,
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
        if(ACStatus.draw_birthday_quad_nick){
            Path path = new Path();
            path.moveTo(birthdayObject.getCenter().x, birthdayObject.getCenter().y);
            PointF quadPoint = ACUtils.calculateQuadPoint(birthdayObject.getCenter(), nicknameObject.getCenter(), ACUtils.QUAD_TYPE_LEFT_TOP);
            path.quadTo(quadPoint.x, quadPoint.y, nicknameObject.getCenter().x, nicknameObject.getCenter().y);
            canvas.drawPath(path, MOVE_QUAD_LINE_PAINT);
        }
        if(ACStatus.draw_nickname_quad_email){
            Path path = new Path();
            path.moveTo(nicknameObject.getCenter().x, nicknameObject.getCenter().y);
            PointF quadPoint = ACUtils.calculateQuadPoint(nicknameObject.getCenter(), emailObject.getCenter(), ACUtils.QUAD_TYPE_RIGHT_TOP);
            path.quadTo(quadPoint.x, quadPoint.y, emailObject.getCenter().x, emailObject.getCenter().y);
            canvas.drawPath(path, MOVE_QUAD_LINE_PAINT);
        }
        if(ACStatus.draw_email_quad_circle){
            Path path = new Path();
            path.moveTo(emailObject.getCenter().x, emailObject.getCenter().y);
            PointF quadPoint = ACUtils.calculateQuadPoint(emailObject.getCenter(), circleObject.getCenter(), ACUtils.QUAD_TYPE_RIGHT_TOP);
            path.quadTo(quadPoint.x, quadPoint.y, circleObject.getCenter().x, circleObject.getCenter().y);
            canvas.drawPath(path, MOVE_QUAD_LINE_PAINT);
        }
        if(ACStatus.draw_circle_quad_last){
            Path path = new Path();
            path.moveTo(circleObject.getCenter().x, circleObject.getCenter().y);
            PointF quadPoint = ACUtils.calculateQuadPoint(circleObject.getCenter(), lastObject.getCenter(), ACUtils.QUAD_TYPE_RIGHT_TOP_LAST);
            path.quadTo(quadPoint.x, quadPoint.y, lastObject.getCenter().x, lastObject.getCenter().y);
            canvas.drawPath(path, MOVE_QUAD_LINE_PAINT);
        }
        if(ACStatus.draw_last_quad_completed){
            Path path = new Path();
            path.moveTo(lastObject.getCenter().x, lastObject.getCenter().y);
            PointF quadPoint = ACUtils.calculateQuadPoint(lastObject.getCenter(), completeObject.getCenter(), ACUtils.QUAD_TYPE_RIGHT_BOTTOM);
            path.quadTo(quadPoint.x, quadPoint.y, completeObject.getCenter().x, completeObject.getCenter().y);
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
                    ACStatus.head_dash_next || ACStatus.head_dash_always ? R.mipmap.ac_normal_bg : R.mipmap.ac_bg_grey);
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

        if(nicknameObject.isDraw()){
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                    ACStatus.birthday_dash_next
                            || ACStatus.birthday_dash_always
                            || ACStatus.nick_showing? R.mipmap.ac_normal_bg : R.mipmap.ac_bg_grey);
            canvas.drawBitmap(bitmap , null ,
                    ACUtils.getBitmapRect(nicknameObject.getCenter() , nicknameObject.getRadius()) , null);
            canvas.drawText(nicknameObject.getText(), nicknameObject.getCenter().x, nicknameObject.getCenter().y, nicknameObject.getTextPaint());
        }

        if(emailObject.isDraw()){
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                    ACStatus.nick_dash_next
                            || ACStatus.nick_dash_always
                            || ACStatus.email_showing? R.mipmap.ac_normal_bg : R.mipmap.ac_bg_grey);
            canvas.drawBitmap(bitmap , null ,
                    ACUtils.getBitmapRect(emailObject.getCenter() , emailObject.getRadius()) , null);
            canvas.drawText(emailObject.getText(), emailObject.getCenter().x, emailObject.getCenter().y, emailObject.getTextPaint());
        }
        if(circleObject.isDraw()){
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                    ACStatus.email_dash_next
                            || ACStatus.email_dash_always? R.mipmap.ac_normal_bg : R.mipmap.ac_bg_grey);
            canvas.drawBitmap(bitmap , null ,
                    ACUtils.getBitmapRect(circleObject.getCenter() , circleObject.getRadius()) , null);
            canvas.drawText(circleObject.getText(), circleObject.getCenter().x, circleObject.getCenter().y, circleObject.getTextPaint());
        }
        if(lastObject.isDraw()){
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                    ACStatus.circle_dash_next
                            || ACStatus.circle_dash_always? R.mipmap.ac_normal_bg : R.mipmap.ac_bg_grey);
            canvas.drawBitmap(bitmap , null ,
                    ACUtils.getBitmapRect(lastObject.getCenter() , lastObject.getRadius()) , null);
            canvas.drawText(lastObject.getText(), lastObject.getCenter().x, lastObject.getCenter().y, lastObject.getTextPaint());
        }
        if(completeObject.isDraw()){
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                    ACStatus.last_dash_next
                            || ACStatus.last_dash_always? R.mipmap.ac_normal_bg : R.mipmap.ac_bg_grey);
            canvas.drawBitmap(bitmap , null ,
                    ACUtils.getBitmapRect(completeObject.getCenter() , completeObject.getRadius()) , null);
            canvas.drawText(completeObject.getText(), completeObject.getCenter().x, completeObject.getCenter().y, completeObject.getTextPaint());
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
                //birthday
                if((ACStatus.birthday_showing)
                        && ACUtils.withPointRadius(x, y, birthdayObject.getCenter() , birthdayObject.getRadius())){
                    ACStatus.birthday_dash_object = true;
                }
                //nickname
                if((ACStatus.nick_showing)
                        && ACUtils.withPointRadius(x, y, nicknameObject.getCenter() , nicknameObject.getRadius())){
                    ACStatus.nick_dash_object = true;
                }
                //email
                if((ACStatus.email_showing)
                        && ACUtils.withPointRadius(x, y, emailObject.getCenter() , emailObject.getRadius())){
                    ACStatus.email_dash_object = true;
                }
                //circle
                if((ACStatus.circle_showing)
                        && ACUtils.withPointRadius(x, y, circleObject.getCenter() , circleObject.getRadius())){
                    ACStatus.circle_dash_object = true;
                }
                //last
                if((ACStatus.last_showing)
                        && ACUtils.withPointRadius(x, y, lastObject.getCenter() , lastObject.getRadius())){
                    ACStatus.last_dash_object = true;
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
                //birthday
                if(ACStatus.birthday_showing == true
                        && ACStatus.birthday_dash_object
                        && ACUtils.withPointRadius(x, y, birthdayDotObject.getCenter() , birthdayDotObject.getRadius_dot_action())){
                    ACStatus.birthday_dash_dot = true;
                }
                ACStatus.birthday_dash_next = ACStatus.birthday_dash_dot
                        && ACUtils.withPointRadius(x, y, nicknameObject.getCenter() , nicknameObject.getRadius());
                //nickname
                if(ACStatus.nick_showing == true
                        && ACStatus.nick_dash_object
                        && ACUtils.withPointRadius(x, y, nicknameDotObject.getCenter() , nicknameDotObject.getRadius_dot_action())){
                    ACStatus.nick_dash_dot = true;
                }
                ACStatus.nick_dash_next = ACStatus.nick_dash_dot
                        && ACUtils.withPointRadius(x, y, emailObject.getCenter() , emailObject.getRadius());
                //email
                if(ACStatus.email_showing == true
                        && ACStatus.email_dash_object
                        && ACUtils.withPointRadius(x, y, emailDotObject.getCenter() , emailDotObject.getRadius_dot_action())){
                    ACStatus.email_dash_dot = true;
                }
                ACStatus.email_dash_next = ACStatus.email_dash_dot
                        && ACUtils.withPointRadius(x, y, circleObject.getCenter() , circleObject.getRadius());
                //circle
                if(ACStatus.circle_showing == true
                        && ACStatus.circle_dash_object
                        && ACUtils.withPointRadius(x, y, circleOpenObject.getCenter() , circleOpenObject.getRadius_small())){
                    ACStatus.circle_dash_open = true;
                    ACStatus.circle_dash_close = false;
                }
                if(ACStatus.circle_showing == true
                        && ACStatus.circle_dash_object
                        && ACUtils.withPointRadius(x, y, circleCloseObject.getCenter() , circleCloseObject.getRadius_small())){
                    ACStatus.circle_dash_close = true;
                    ACStatus.circle_dash_open = false;
                }
                ACStatus.circle_dash_next = (ACStatus.circle_dash_open || ACStatus.circle_dash_close)
                        && ACUtils.withPointRadius(x, y, lastObject.getCenter() , lastObject.getRadius());
                //last
                if(ACStatus.last_showing == true
                        && ACStatus.last_dash_object
                        && ACUtils.withPointRadius(x, y, lastOpenObject.getCenter() , lastOpenObject.getRadius_small())){
                    ACStatus.last_dash_open = true;
                    ACStatus.last_dash_close = false;
                }
                if(ACStatus.last_showing == true
                        && ACStatus.last_dash_object
                        && ACUtils.withPointRadius(x, y, lastCloseObject.getCenter() , lastCloseObject.getRadius_small())){
                    ACStatus.last_dash_open = false;
                    ACStatus.last_dash_close = true;
                }
                ACStatus.last_dash_next = (ACStatus.last_dash_open || ACStatus.last_dash_close)
                        && ACUtils.withPointRadius(x, y, completeObject.getCenter() , completeObject.getRadius());
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

                ACStatus.welcome_dash_number = false;
                ACStatus.welcome_dash_dot = false;
                ACStatus.welcome_dash_start = false;

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
                        ACStatus.head_dash_always = true;
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
                    ACStatus.head_dash_object = false;
                    ACStatus.head_dash_camera = false;
                    ACStatus.head_dash_album = false;
                    ACStatus.head_dash_skip = false;
                    ACStatus.head_dash_next = false;
                    ACStatus.head_dash_dot = false;
                    ACStatus.head_dash_change = false;
                }

                if(ACStatus.birthday_showing && ACStatus.birthday_dash_next && touchFeedback!= null){
                    ACStatus.birthday_dash_object = false;
                    ACStatus.birthday_dash_always = true;
                    touchFeedback.onClickBirthdayNext();
                }else{
                    ACStatus.birthday_dash_object = false;
                    ACStatus.birthday_dash_dot = false;
                    ACStatus.circle_dash_open = false;
                    ACStatus.circle_dash_close = false;
                    ACStatus.birthday_dash_next = false;
//                    ACStatus.birthday_dash_always = false;
                }

                if(ACStatus.nick_showing && ACStatus.nick_dash_next && touchFeedback!= null){
                    ACStatus.nick_dash_object = false;
                    ACStatus.nick_dash_always = true;
                    touchFeedback.onClickNicknameNext();
                }else{
                    ACStatus.nick_dash_object = false;
                    ACStatus.nick_dash_dot = false;
                    ACStatus.nick_dash_next = false;
//                    ACStatus.nick_dash_always = false;
                }

                if(ACStatus.email_showing && ACStatus.email_dash_next && touchFeedback!= null){
                    ACStatus.email_dash_object = false;
                    ACStatus.email_dash_always = true;
                    touchFeedback.onClickEMailNext();
                }else{
                    ACStatus.email_dash_object = false;
                    ACStatus.email_dash_dot = false;
                    ACStatus.email_dash_next = false;
//                    ACStatus.nick_dash_always = false;
                }

                if(ACStatus.circle_showing && ACStatus.circle_dash_next && touchFeedback!= null){
                    ACStatus.circle_dash_object = false;
                    ACStatus.circle_dash_always = true;
                    touchFeedback.onClickCircleNext();
                }else{
                    ACStatus.circle_dash_object = false;
                    ACStatus.circle_dash_dot = false;
                    ACStatus.circle_dash_next = false;
//                    ACStatus.nick_dash_always = false;
                }

                if(ACStatus.last_showing && ACStatus.last_dash_next && touchFeedback!= null){
                    ACStatus.last_dash_object = false;
                    ACStatus.last_dash_always = true;
                    touchFeedback.onClickCompleted();
                }else{
                    ACStatus.last_dash_object = false;
                    ACStatus.last_dash_dot = false;
                    ACStatus.last_dash_open = false;
                    ACStatus.last_dash_close = false;
                    ACStatus.last_dash_next = false;
//                    ACStatus.nick_dash_always = false;
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

    private void releaseWelcome(){
        enterNumber.setIsDraw(false);
        welcomeDotObject.setIsDraw(false);
    }

    private void releaseSex(){
        ACStatus.draw_welcome_quad_sex = false;
        sexObject.setIsDraw(false);
        sexBoyObject.setIsDraw(false);
        sexGirlObject.setIsDraw(false);
    }

    private void releaseHeadIcon(){
        ACStatus.draw_sex_quad_head_icon = false;
        headIconObject.setIsDraw(false);
        headCameraObject.setIsDraw(false);
        headAlbumObject.setIsDraw(false);
        headSkipObject.setIsDraw(false);
        headDotObject.setIsDraw(false);
        headChangeObject.setIsDraw(false);
    }

    private void releaseBirthday(){
        ACStatus.draw_head_quad_birthday = false;
        birthdayObject.setIsDraw(false);
        birthdayDotObject.setIsDraw(false);
    }

    private void releaseNickname(){
        ACStatus.draw_birthday_quad_nick = false;
        nicknameObject.setIsDraw(false);
        nicknameDotObject.setIsDraw(false);
    }

    private void releaseEmail(){
        ACStatus.draw_nickname_quad_email = false;
        emailObject.setIsDraw(false);
        emailDotObject.setIsDraw(false);
    }

    private void releaseCircle(){
        ACStatus.draw_email_quad_circle = false;
        circleObject.setIsDraw(false);
        circleDotObject.setIsDraw(false);
        circleOpenObject.setIsDraw(false);
        circleCloseObject.setIsDraw(false);
    }

    public void releaseALL(){
        enterNumber.setIsDraw(false);
        sexObject.setIsDraw(false);
        welcomeDotObject.setIsDraw(false);
        headIconObject.setIsDraw(false);
        sexBoyObject.setIsDraw(false);
        sexGirlObject.setIsDraw(false);
        birthdayObject.setIsDraw(false);
        headCameraObject.setIsDraw(false);
        headAlbumObject.setIsDraw(false);
        headSkipObject.setIsDraw(false);
        headDotObject.setIsDraw(false);
        headChangeObject.setIsDraw(false);
        nicknameObject.setIsDraw(false);
        nicknameDotObject.setIsDraw(false);
        birthdayDotObject.setIsDraw(false);
        emailObject.setIsDraw(false);
        emailDotObject.setIsDraw(false);
        circleObject.setIsDraw(false);
        circleDotObject.setIsDraw(false);
        circleOpenObject.setIsDraw(false);
        circleCloseObject.setIsDraw(false);
        lastObject.setIsDraw(false);
        lastDotObject.setIsDraw(false);
        lastOpenObject.setIsDraw(false);
        lastCloseObject.setIsDraw(false);
        completeObject.setIsDraw(false);
        ACStatus.falseAll();
    }
}
