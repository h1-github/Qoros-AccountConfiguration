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

    void headIcon();

    void startTitleAnimation();
    void startSubTitleAnimation();

}
