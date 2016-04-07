package com.wanghuan.accountconfiguration;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.nineoldandroids.animation.Animator;
import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.wanghuan.accountconfiguration.util.DensityUtil;
import com.wanghuan.accountconfiguration.util.L;
import com.wanghuan.accountconfiguration.util.UriToPath;
import com.wanghuan.accountconfiguration.util.ViewUtils;
import com.wanghuan.accountconfiguration.view.AccountConfigurationView;
import com.wanghuan.accountconfiguration.view.TouchFeedbackListener;
import com.wanghuan.accountconfiguration.view.objectviews.ObjectPointBase;
import com.wanghuan.accountconfiguration.view.wheel.LoopView;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by h1 on 16/3/29 14:32.
 * email: h18501667737@gmail.com
 */
public class AccountConfigurationActivity extends Activity implements StepCallback{

    private final String SAVE_DATA_NAME = "wel_save_data_name";
    private final String SAVE_DATA_PATH = "wel_save_data_path";
    /**
     * 拍照
     */
    public final int CODE_CAMERA = 101;
    /**
     * 图库
     */
    public final int CODE_PHOTO = 102;
    /**
     * 图库 4.4以上版本*/
    public final int CODE_PHOTO_URI = 105;

    /**
     * 裁剪
     */
    public final int CODE_CAT = 103;
    /**
     * 预览
     */
    public final int CODE_PREVIEW = 104;
    private int outSize = 200;

    /**
     * 图片缓存路径
     * */
    private String DATA_PATH = "";
    /**
     * 图片的名字
     * */
    private String DATA_NAME = "";

    private String createDataName() {
        return "qoros_cache.jpg";
    }

    public String createDataPath() {
        return DATA_PATH + File.separator + DATA_NAME;
    }

    /**
     * 拍照
     */
    public void requestToCamera() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA},
                    CODE_CAMERA);
        }else{
            toCamera();
        }
    }

    public void toCamera() {
        Intent mIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        DATA_NAME = createDataName();
        File f = new File(DATA_PATH, DATA_NAME);
        mIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(f));
        savePath();
        startActivityForResult(mIntent, CODE_CAMERA);
    }

    /**
     * 图库
     */
    public void requestToPhoto() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                    CODE_PHOTO);
        }else{
            toPhoto();
        }
    }

    public void toPhoto() {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);// ACTION_OPEN_DOCUMENT
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/jpeg");
        if (android.os.Build.VERSION.SDK_INT >= 19) {
            startActivityForResult(intent, CODE_PHOTO_URI);
        } else {
            startActivityForResult(intent, CODE_PHOTO);
        }
    }

    /**
     * 裁剪图片
     * */
    public void toCatPicture(Intent mIntent,int code) {
        String path = getResultUriToPath(code, mIntent);
        L.d("toCatPicture path : " + path);
        acView.HeadDotChangeEnter(path);
//        Intent intent = new Intent(this, ActivityCutPhoto.class);
//        intent.putExtra(ActivityCutPhoto.OUT_PATH, getResultUriToPath(code, mIntent));
//        intent.putExtra(ActivityCutPhoto.OUT_SIZE, outSize);
//        startActivityForResult(intent, CODE_CAT);
    }

    public String getResultUriToPath(int code,Intent mIntent){
        String path = "";
        if(code == CODE_CAMERA){
            readPath();
            path = createDataPath() ;
        }else if(code == CODE_PHOTO_URI){
            Uri uri = mIntent.getData();
            path = UriToPath.getPath(this, uri);
        }else if(code == CODE_PHOTO){
            Uri uri = mIntent.getData();
            Cursor c = this.getContentResolver().query(uri, new String[]{MediaStore.Images.Media.DATA}, null, null, null);
            if (c != null) {
                c.moveToFirst();
                int columnIndex = c.getColumnIndex( MediaStore.Images.Media.DATA);
                path = c.getString(columnIndex);
            }
        }

        return path;
    }

    private void savePath() {
        SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(
                this).edit();
        edit.putString(SAVE_DATA_NAME, DATA_NAME);
        edit.putString(SAVE_DATA_PATH, DATA_PATH);
        edit.apply();
    }

    private void readPath() {
        SharedPreferences sp = PreferenceManager
                .getDefaultSharedPreferences(this);
        DATA_NAME = sp.getString(SAVE_DATA_NAME, "");
        DATA_PATH = sp.getString(SAVE_DATA_PATH, "");
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == CODE_CAMERA||requestCode == CODE_PHOTO||requestCode == CODE_PHOTO_URI){
            if(resultCode == Activity.RESULT_OK){
                toCatPicture(data, requestCode);
            }
        }else if(requestCode == CODE_CAT && resultCode == Activity.RESULT_OK){

        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CODE_PHOTO) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                toPhoto();
            } else {
                // Permission Denied
            }
        }
        if (requestCode == CODE_CAMERA) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                toCamera();
            } else {
                // Permission Denied
            }
        }
    }

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

    private final static int ENTER_ICON_ANIMATION = 1001;
    private final static int WELCOME_ENTER_ANIMATION = 1002;
    private final static int DRAW_WELCOME_QUAD_SEX = 1003;
    private final static int WELCOME_TO_SEX = 1004;
    private final static int DRAW_SEX_QUAD_HEAD_ICON = 1005;
    private final static int SEX_TO_HEAD_ICON = 1006;
    private final static int DRAW_HEAD_QUAD_BIRTHDAY = 1007;
    private final static int HEAD_TO_BIRTHDAY = 1008;
    private final static int DRAW_BIRTHDAY_QUAD_NICKNAME = 1009;
    private final static int BIRTHDAY_TO_NICKNAME = 1010;
    private final static int DRAW_NICKNAME_QUAD_EMAIL = 1011;
    private final static int NICKNAME_TO_EMAIL = 1012;
    private final static int DRAW_EMAIL_QUAD_CIRCLE = 1013;
    private final static int EMAIL_TO_CIRCLE = 1014;
    private final static int DRAW_CIRCLE_QUAD_LAST = 1015;
    private final static int CIRCLE_TO_LAST = 1016;
    private final static int DRAW_LAST_QUAD_COMPLETED = 1017;
    private final static int LAST_COMPLETED = 1018;

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
                case DRAW_HEAD_QUAD_BIRTHDAY:
                    drawQuadLine(DRAW_HEAD_QUAD_BIRTHDAY);
                    break;
                case HEAD_TO_BIRTHDAY:
                    headExitBirthdayEnter();
                    break;
                case DRAW_BIRTHDAY_QUAD_NICKNAME:
                    drawQuadLine(DRAW_BIRTHDAY_QUAD_NICKNAME);
                    break;
                case BIRTHDAY_TO_NICKNAME:
                    birthdayExitNicknameEnter();
                    break;
                case DRAW_NICKNAME_QUAD_EMAIL:
                    drawQuadLine(DRAW_NICKNAME_QUAD_EMAIL);
                    break;
                case NICKNAME_TO_EMAIL:
                    nicknameExitEmailEnter();
                    break;
                case DRAW_EMAIL_QUAD_CIRCLE:
                    drawQuadLine(DRAW_EMAIL_QUAD_CIRCLE);
                    break;
                case EMAIL_TO_CIRCLE:
                    emailExitCircleEnter();
                    break;
                case DRAW_CIRCLE_QUAD_LAST:
                    drawQuadLine(DRAW_CIRCLE_QUAD_LAST);
                    break;
                case CIRCLE_TO_LAST:
                    circleExitLastEnter();
                    break;
                case DRAW_LAST_QUAD_COMPLETED:
                    drawQuadLine(DRAW_LAST_QUAD_COMPLETED);
                    break;
                case LAST_COMPLETED:
                    lastCompletedThenNextEnter();
                    break;
            }
        }
    };

    @Bind(R.id.birthday_loopview_layout)
    LinearLayout birthdayLoopLayout;
    @Bind(R.id.welcome_birthday_year)
    LoopView year;
    @Bind(R.id.welcome_birthday_month)
    LoopView month;
    @Bind(R.id.welcome_birthday_day)
    LoopView day;

    @Bind(R.id.welcome_nickname_edit)
    EditText nicknameEditText;
    @Bind(R.id.setting_doing)
    ImageView settingDoing;

    private int yearResult;
    private int monthResult;
    private int dayResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_configuration_layout);
        ButterKnife.bind(this);

        DATA_PATH = this.getExternalCacheDir().getAbsolutePath();

        acView.setTouchFeedback(touchFeedbackListener);
        enter();
        initBirthdayLoopView();
        initNicknameView();
    }

    private void initBirthdayLoopView(){
        ((RelativeLayout.LayoutParams)birthdayLoopLayout.getLayoutParams()).setMargins(
                DensityUtil.dp2px(this , 60),
                ViewUtils.getScreenHeight(this)/2 + ObjectPointBase.SEX_DEFAULT_RADIUS/2,
                DensityUtil.dp2px(this , 60),
                0
        );
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        yearResult = calendar.get(Calendar.YEAR);
        monthResult = calendar.get(Calendar.MONTH);
        dayResult = calendar.get(Calendar.DAY_OF_MONTH);

        //init
        ArrayList<String> listYears = new ArrayList<>();
        for (int i = 20; i <= 99; i++) {
            listYears.add("19" + i + "年");
        }
        for (int i = 0; i <= 19; i++) {
            if(i < 10){
                listYears.add("200" + i + "年");
            }else{
                listYears.add("20" + i + "年");
            }
        }
//        year.setListener(onYearItemSelectedListener);
        year.setItems(listYears);
        year.setInitPosition(listYears.size() - (2019 - yearResult) - 1);
        year.setTextSize(20);

        ArrayList<String> listMonths = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            listMonths.add(i + "月");
        }
//        month.setListener(onMonthItemSelectedListener);
        month.setItems(listMonths);
        month.setInitPosition(monthResult);
        month.setTextSize(20);

        ArrayList<String> listDays = new ArrayList<>();
        for (int i = 1; i <= 31; i++) {
            listDays.add(i + "月");
        }
//        day.setListener(onDayItemSelectedListener);
        day.setItems(listDays);
        day.setInitPosition(dayResult - 1);
        day.setTextSize(20);
    }

    private void initNicknameView() {
        ((RelativeLayout.LayoutParams) nicknameEditText.getLayoutParams()).setMargins(
//                (int)(ViewUtils.getScreenWidth(this) * 0.3f) + ObjectPointBase.SEX_DEFAULT_RADIUS / 2,
                DensityUtil.dp2px(this, 60),
                (int)(ViewUtils.getScreenHeight(this) * 0.3f),
                DensityUtil.dp2px(this, 60),
                0
        );
    }



    @Override
    public void enter() {
        title.setText("致\n时代独立者");
        startTitleAnimation();
        Message message = new Message();
        message.what = ENTER_ICON_ANIMATION;
        handler.sendMessageDelayed(message, 1500);
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
    public void headExit() {
        acView.headSkipDotMove();
        Message message = new Message();
        message.what = DRAW_HEAD_QUAD_BIRTHDAY;
        handler.sendMessageDelayed(message, 900);
        Message message2 = new Message();
        message2.what = HEAD_TO_BIRTHDAY;
        handler.sendMessageDelayed(message2, 1800);
    }

    @Override
    public void headExitBirthdayEnter() {
        stepImage.setBackgroundResource(R.mipmap.welcome_step_4);
        title.setText("生日");
        subTitle.setText("来自观致汽车的\n神秘祝福礼物");
        startTitleAnimation();
        startSubTitleAnimation();
        acView.headExitBirthdayEnter();
        showBirthdayLoopLayout();
    }

    @Override
    public void birthdayExit() {
        acView.birthdayDotMove();
        Message message = new Message();
        message.what = DRAW_BIRTHDAY_QUAD_NICKNAME;
        handler.sendMessageDelayed(message, 900);
        Message message2 = new Message();
        message2.what = BIRTHDAY_TO_NICKNAME;
        handler.sendMessageDelayed(message2, 1800);
    }

    @Override
    public void birthdayExitNicknameEnter() {
        stepImage.setBackgroundResource(R.mipmap.welcome_step_5);
        title.setText("昵称");
        subTitle.setText("观致还有可通过昵称\n与其他为好友及互动");
        startTitleAnimation();
        startSubTitleAnimation();
        acView.birthdayExitNicknameEnter();
        hiddenBirthdayLoopLayout();
        showNicknameLayout();
    }

    @Override
    public void nicknameExit() {
        acView.nicknameDotMove();
        Message message = new Message();
        message.what = DRAW_NICKNAME_QUAD_EMAIL;
        handler.sendMessageDelayed(message, 900);
        Message message2 = new Message();
        message2.what = NICKNAME_TO_EMAIL;
        handler.sendMessageDelayed(message2, 1800);
    }

    @Override
    public void nicknameExitEmailEnter() {
        stepImage.setBackgroundResource(R.mipmap.welcome_step_6);
        title.setText("电子邮箱");
        subTitle.setText("此电子邮箱用于安全目的\n确认你的身份或重设忘记的密码\n首次验证邮箱\n会发送至此电子邮箱");
        startTitleAnimation();
        startSubTitleAnimation();
        acView.nicknameExitEmailEnter();
        hiddenNicknameLayout();
    }

    @Override
    public void emailExit() {
        acView.emailDotMove();
        Message message = new Message();
        message.what = DRAW_EMAIL_QUAD_CIRCLE;
        handler.sendMessageDelayed(message, 900);
        Message message2 = new Message();
        message2.what = EMAIL_TO_CIRCLE;
        handler.sendMessageDelayed(message2, 1800);
    }

    @Override
    public void emailExitCircleEnter() {
        stepImage.setBackgroundResource(R.mipmap.welcome_step_7);
        title.setText("车友圈");
        subTitle.setText("一起分享观致驾驶乐趣\n与观致车友互动");
        startTitleAnimation();
        startSubTitleAnimation();
        acView.emailExitCircleEnter();
    }

    @Override
    public void circleExit() {
        acView.circleDotMove();
        Message message = new Message();
        message.what = DRAW_CIRCLE_QUAD_LAST;
        handler.sendMessageDelayed(message, 900);
        Message message2 = new Message();
        message2.what = CIRCLE_TO_LAST;
        handler.sendMessageDelayed(message2, 1800);
    }

    @Override
    public void circleExitLastEnter() {
        stepImage.setBackgroundResource(R.mipmap.welcome_step_8);
        title.setText("车友圈");
        subTitle.setText("一起分享观致驾驶乐趣\n与观致车友互动");
        startTitleAnimation();
        startSubTitleAnimation();
        acView.circleExitLastEnter();
    }

    @Override
    public void lastCompleted() {
        acView.lastDotMove();
        Message message = new Message();
        message.what = DRAW_LAST_QUAD_COMPLETED;
        handler.sendMessageDelayed(message, 900);
        Message message2 = new Message();
        message2.what = LAST_COMPLETED;
        handler.sendMessageDelayed(message2, 1800);
    }

    @Override
    public void lastCompletedThenNextEnter() {
        stepImage.setVisibility(View.INVISIBLE);
        subTitle.setVisibility(View.GONE);
        title.setText("设置中...");
        startTitleAnimation();
        acView.lastExitCompleted();
        showSettingLayout();
    }

    private void showSettingLayout(){
        settingDoing.setVisibility(View.VISIBLE);
        settingDoing.setBackgroundResource(R.mipmap.welcome_step_9);
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(settingDoing , "alpha" , 0 , 1);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(settingDoing , "translationY" , 300 , 0);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimator1,
                objectAnimator2);
        animatorSet.setDuration(1700);
        animatorSet.start();
    }

    private void showBirthdayLoopLayout(){
        birthdayLoopLayout.setVisibility(View.VISIBLE);
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(birthdayLoopLayout , "alpha" , 0 , 1);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(birthdayLoopLayout , "translationY" , 300 , 0);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimator1,
                objectAnimator2);
        animatorSet.setDuration(1700);
        animatorSet.start();
    }

    private void hiddenBirthdayLoopLayout(){
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(birthdayLoopLayout , "alpha" , 1 , 0);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(birthdayLoopLayout , "translationY" , 0 , 300);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimator1,
                objectAnimator2);
        animatorSet.setDuration(1700);
        animatorSet.start();
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                birthdayLoopLayout.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
    }

    private void showNicknameLayout(){
        nicknameEditText.setVisibility(View.VISIBLE);
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(nicknameEditText , "alpha" , 0 , 1);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(nicknameEditText , "translationX" , 300 , 0);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimator1,
                objectAnimator2);
        animatorSet.setDuration(1700);
        animatorSet.start();
    }

    private void hiddenNicknameLayout(){
        ObjectAnimator objectAnimator1 = ObjectAnimator.ofFloat(nicknameEditText , "alpha" , 1 , 0);
        ObjectAnimator objectAnimator2 = ObjectAnimator.ofFloat(nicknameEditText , "translationX" , 0 , 300);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(objectAnimator1,
                objectAnimator2);
        animatorSet.setDuration(1700);
        animatorSet.start();
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                nicknameEditText.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
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

        @Override
        public void onClickHeadCamera() {
            super.onClickHeadCamera();
//            requestToCamera();
        }

        @Override
        public void onClickHeadAlbum() {
            super.onClickHeadAlbum();
            requestToPhoto();
        }

        @Override
        public void onClickHeadSkip() {
            super.onClickHeadSkip();
            headExit();
        }

        @Override
        public void onClickHeadChange() {
            super.onClickHeadChange();
            acView.HeadCameraAlbumSkipEnter();
        }

        @Override
        public void onClickBirthdayNext() {
            super.onClickBirthdayNext();
            birthdayExit();
        }

        @Override
        public void onClickNicknameNext() {
            super.onClickNicknameNext();
            nicknameExit();
        }

        @Override
        public void onClickEMailNext() {
            super.onClickEMailNext();
            emailExit();
        }

        @Override
        public void onClickCircleNext() {
            super.onClickCircleNext();
            circleExit();
        }

        @Override
        public void onClickCompleted() {
            super.onClickCompleted();
            lastCompleted();
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
            case DRAW_HEAD_QUAD_BIRTHDAY:
                acView.drawHeadQuadToBirthday();
                break;
            case DRAW_BIRTHDAY_QUAD_NICKNAME:
                acView.drawBirthdayQuadToNickname();
                break;
            case DRAW_NICKNAME_QUAD_EMAIL:
                acView.drawNicknameQuadToEmail();
                break;
            case DRAW_EMAIL_QUAD_CIRCLE:
                acView.drawEmailQuadToCircle();
                break;
            case DRAW_CIRCLE_QUAD_LAST:
                acView.drawCircleQuadToLast();
                break;
            case DRAW_LAST_QUAD_COMPLETED:
                acView.drawLastQuadToComplete();
                break;
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK){
            android.os.Process.killProcess(Process.myPid());
        }
        return super.onKeyDown(keyCode, event);
    }
}
