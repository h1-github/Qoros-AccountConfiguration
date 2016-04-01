package com.wanghuan.accountconfiguration;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.wanghuan.accountconfiguration.util.DensityUtil;
import com.wanghuan.accountconfiguration.util.ViewUtils;
import com.wanghuan.accountconfiguration.view.AccountConfigurationView;
import com.wanghuan.accountconfiguration.view.TouchFeedbackListener;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by h1 on 16/3/29 14:32.
 * email: h18501667737@gmail.com
 */
public class AccountConfigurationActivity extends Activity implements StepCallback{

    private final static int ENTER_ICON_ANIMATION = 1001;
    private final static int WELCOME_ENTER_ANIMATION = 1002;
    private final static int DRAW_WELCOME_QUAD_SEX = 1003;
    private final static int WELCOME_TO_SEX = 1004;
    private final static int DRAW_SEX_QUAD_HEAD_ICON = 1005;
    private final static int SEX_TO_HEAD_ICON = 1006;

    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.subtitle)
    TextView subTitle;
    @Bind(R.id.step_image)
    ImageView stepImage;
    @Bind(R.id.enter_icon)
    ImageView enterIcon;
    @Bind(R.id.account_configuration_view)
    AccountConfigurationView acView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_configuration_layout);
        ButterKnife.bind(this);
        acView.setTouchFeedback(touchFeedbackListener);
        enter();
    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case ENTER_ICON_ANIMATION:
                    enterHandler();
                    break;
                case WELCOME_ENTER_ANIMATION:
                    welcomeEnter();
                    break;
                case DRAW_WELCOME_QUAD_SEX:
                    drawQuadLine(DRAW_WELCOME_QUAD_SEX);
                    break;
                case WELCOME_TO_SEX:
                    welcomeExitSexEnter();
                    break;
                case DRAW_SEX_QUAD_HEAD_ICON:
                    drawQuadLine(DRAW_SEX_QUAD_HEAD_ICON);
                    break;
                case SEX_TO_HEAD_ICON:
                    sexExitHeadIconEnter();
                    break;
            }
        }
    };

    @Override
    public void enter() {
        title.setText("致\n时代独立者");
        startTitleAnimation();
        Message message = new Message();
        message.what = ENTER_ICON_ANIMATION;
        handler.sendMessageDelayed(message , 1500);
    }

    @Override
    public void enterHandler() {
        stepImage.setVisibility(View.VISIBLE);
        int marginTop = DensityUtil.dp2px(this , 20) + stepImage.getMeasuredHeight();
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(enterIcon , "scaleX" , 1 , 6 , 6 , 0.3f);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(enterIcon , "scaleY" , 1 , 6 , 6 ,0.3f);
        ObjectAnimator objectAnimator3 = ObjectAnimator.ofFloat(enterIcon , "translationX" , 0 ,
                ViewUtils.getScreenWidth(this) * 1.1f ,ViewUtils.getScreenWidth(this) * 1.1f , 0);
        ObjectAnimator objectAnimator4 = ObjectAnimator.ofFloat(enterIcon , "translationY" , 0 ,
                300 ,300 , -ViewUtils.getScreenHeight(this)/2 + marginTop);
        ObjectAnimator objectAnimator5 = ObjectAnimator.ofFloat(stepImage , "alpha" , 0 , 0 , 0 , 0 , 0 , 0 , 0 , 1);
        ObjectAnimator objectAnimator6 = ObjectAnimator.ofFloat(enterIcon , "alpha" , 1 , 1 , 1 , 1 , 1 , 1 , 1 , 0);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimator1,
                objectAnimator2,
                objectAnimator3,
                objectAnimator4,
                objectAnimator5,
                objectAnimator6);
        animatorSet.setDuration(3000);
        animatorSet.start();
        Message message = new Message();
        message.what = WELCOME_ENTER_ANIMATION;
        handler.sendMessageDelayed(message, 2000);
    }

    @Override
    public void welcomeEnter() {
        title.setText("欢迎\n首次登录观致逸云");
        subTitle.setText("只需几个步骤开启并设置好\n专属你的安全出行方案");
        startTitleAnimation();
        startSubTitleAnimation();
        acView.startEnterNumber();
    }

    @Override
    public void welcomeMove() {
        acView.welcomeMove();
        acView.sexEnter();
        acView.welcomeDotEnter();
    }

    @Override
    public void welcomeExit() {
        acView.welcomeDotMove();
        Message message = new Message();
        message.what = DRAW_WELCOME_QUAD_SEX;
        handler.sendMessageDelayed(message, 900);
        Message message2 = new Message();
        message2.what = WELCOME_TO_SEX;
        handler.sendMessageDelayed(message2, 1800);
    }

    @Override
    public void welcomeExitSexEnter() {
        stepImage.setBackgroundResource(R.mipmap.welcome_step_2);
        title.setText("性别");
        subTitle.setText("请选择你的性别");
        startTitleAnimation();
        startSubTitleAnimation();
        acView.welcomeExitSexEnter();
    }

    @Override
    public void sexExit() {
        acView.sexDotMove();
        Message message = new Message();
        message.what = DRAW_SEX_QUAD_HEAD_ICON;
        handler.sendMessageDelayed(message, 900);
        Message message2 = new Message();
        message2.what = SEX_TO_HEAD_ICON;
        handler.sendMessageDelayed(message2, 1800);
    }

    @Override
    public void sexExitHeadIconEnter() {
        stepImage.setBackgroundResource(R.mipmap.welcome_step_3);
        title.setText("头像");
        subTitle.setText("晒出你的\n时代独立者的一面");
        startTitleAnimation();
        startSubTitleAnimation();
        acView.sexExitHeadIconEnter();

    }

    @Override
    public void headIcon() {

    }

    @Override
    public void startTitleAnimation() {
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(title , "translationX" , 300 , 0);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(title , "alpha" , 0.5f , 1);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimator1 , objectAnimator2);
        animatorSet.setDuration(1500);
        animatorSet.start();
    }

    @Override
    public void startSubTitleAnimation() {
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(subTitle , "translationX" , 400 , 0);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(subTitle , "alpha" , 0.3f , 1);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimator1 , objectAnimator2);
        animatorSet.setDuration(1500);
        animatorSet.start();
    }

    private TouchFeedbackListener touchFeedbackListener = new TouchFeedbackListener() {
        @Override
        public void onClickWelcome1() {
            super.onClickWelcome1();
            welcomeMove();
        }

        @Override
        public void onClickWelcomeStart() {
            super.onClickWelcomeStart();
            welcomeExit();
        }

        @Override
        public void onClickSexNext() {
            super.onClickSexNext();
            sexExit();
        }

        @Override
        public void onClickHeadNext() {
            super.onClickHeadNext();
        }
    };

    private void drawQuadLine(int lineType){
        switch (lineType){
            case DRAW_WELCOME_QUAD_SEX:
                acView.drawWelcomeQuadToSex();
                break;
            case DRAW_SEX_QUAD_HEAD_ICON:
                acView.drawSexQuadToHeadIcon();
                break;
        }
    }
}
