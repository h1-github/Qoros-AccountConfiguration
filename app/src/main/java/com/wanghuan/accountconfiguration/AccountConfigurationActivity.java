package com.wanghuan.accountconfiguration;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.*;
import android.os.Process;
import android.preference.PreferenceManager;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.nineoldandroids.animation.AnimatorSet;
import com.nineoldandroids.animation.ObjectAnimator;
import com.wanghuan.accountconfiguration.util.DensityUtil;
import com.wanghuan.accountconfiguration.util.L;
import com.wanghuan.accountconfiguration.util.UriToPath;
import com.wanghuan.accountconfiguration.util.ViewUtils;
import com.wanghuan.accountconfiguration.view.AccountConfigurationView;
import com.wanghuan.accountconfiguration.view.TouchFeedbackListener;

import java.io.File;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.account_configuration_layout);
        ButterKnife.bind(this);

        DATA_PATH = this.getExternalCacheDir().getAbsolutePath();

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
                case DRAW_HEAD_QUAD_BIRTHDAY:
                    drawQuadLine(DRAW_HEAD_QUAD_BIRTHDAY);
                    break;
                case HEAD_TO_BIRTHDAY:
                    headExitBirthdayEnter();
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
    public void headExit() {
        acView.headDotMove();
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
