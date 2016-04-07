package com.wanghuan.accountconfiguration;

/**
 * Created by h1 on 16/3/29 14:43.
 * email: h18501667737@gmail.com
 */
public interface StepCallback {

    void enter();
    void enterHandler();

    void welcomeEnter();
    void welcomeMove();
    void welcomeExit();
    void welcomeExitSexEnter();

    void sexExit();
    void sexExitHeadIconEnter();

    void headExit();
    void headExitBirthdayEnter();

    void birthdayExit();
    void birthdayExitNicknameEnter();

    void nicknameExit();
    void nicknameExitEmailEnter();

    void emailExit();
    void emailExitCircleEnter();

    void circleExit();
    void circleExitLastEnter();

    void lastCompleted();
    void lastCompletedThenNextEnter();

    void startTitleAnimation();
    void startSubTitleAnimation();

}
