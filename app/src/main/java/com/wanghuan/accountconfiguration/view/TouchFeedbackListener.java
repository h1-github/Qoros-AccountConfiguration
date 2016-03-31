package com.wanghuan.accountconfiguration.view;

/**
 * Created by h1 on 16/3/30 14:10.
 * email: h18501667737@gmail.com
 */
public abstract class TouchFeedbackListener implements TouchFeedback{

    @Override
    public void onClickWelcome1() {
        ACStatus.welcome1 = false;
        ACStatus.welcome_showing = true;
    }

    @Override
    public void onClickWelcomeStart() {

    }
}
