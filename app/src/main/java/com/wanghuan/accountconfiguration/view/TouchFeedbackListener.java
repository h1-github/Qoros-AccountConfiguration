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
        ACStatus.welcome_showing = false;
        ACStatus.sex_showing = true;
    }

    @Override
    public void onClickSexNext() {
        ACStatus.sex_showing = false;
        ACStatus.head_showing_choose = true;
    }

    @Override
    public void onClickHeadNext() {

    }

    @Override
    public void onClickHeadCamera() {

    }

    @Override
    public void onClickHeadAlbum() {

    }

    @Override
    public void onClickHeadSkip() {

    }

    @Override
    public void onClickHeadChange() {

    }
}
