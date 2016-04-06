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
        ACStatus.head_showing_choose = false;
        ACStatus.head_showing_change = false;
        ACStatus.birthday_showing = true;
    }

    @Override
    public void onClickHeadCamera() {

    }

    @Override
    public void onClickHeadAlbum() {

    }

    @Override
    public void onClickHeadSkip() {
        ACStatus.head_showing_choose = false;
        ACStatus.head_showing_change = false;
        ACStatus.birthday_showing = true;
    }

    @Override
    public void onClickHeadChange() {

    }

    @Override
    public void onClickBirthdayNext() {
        ACStatus.birthday_showing = false;
        ACStatus.nick_showing = true;
    }
}
