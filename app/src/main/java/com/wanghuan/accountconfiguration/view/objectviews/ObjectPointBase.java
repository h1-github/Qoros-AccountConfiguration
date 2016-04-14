package com.wanghuan.accountconfiguration.view.objectviews;

import android.content.Context;
import android.graphics.PointF;

import com.wanghuan.accountconfiguration.util.ACUtils;

/**
 * Created by h1 on 16/3/31 10:53.
 * email: h18501667737@gmail.com
 */
public abstract class ObjectPointBase extends ObjectBase{

    private final static float POINT_3_OFFSET_02 = 0.2f;
    private final static float POINT_3_OFFSET = 0.3f;
    private final static float POINT_3_OFFSET_2 = 0.4f;

    public ObjectPointBase(Context context) {
        super(context);
    }

    private float radius = SEX_DEFAULT_RADIUS;
    private float radius_dot = DOT_DEFAULT_RADIUS;
    private float radius_dot_core = DOT_DEFAULT_RADIUS_CORE;
    private float radius_dot_action = DOT_DEFAULT_RADIUS_ACTION;
    private float radius_small = SEX_ITEM_DEFAULT_RADIUS;

    public float getRadius() {
        return radius;
    }

    public float getRadius_dot() {
        return radius_dot;
    }

    public float getRadius_dot_core() {
        return radius_dot_core;
    }

    public float getRadius_dot_action() {
        return radius_dot_action;
    }

    public float getRadius_small() {
        return radius_small;
    }

    public final static int SEX_DEFAULT_RADIUS = 200;
    public final static int DOT_DEFAULT_RADIUS = 20;
    public final static int DOT_DEFAULT_RADIUS_CORE = 10;
    public final static int DOT_DEFAULT_RADIUS_ACTION = 50;
    public final static int SEX_ITEM_DEFAULT_RADIUS = 90;

    public PointF welcome_point1 = new PointF(measureWidth , measureHeight/2);
    public PointF welcome_point2 = new PointF(measureWidth/2 , measureHeight/2 + measureHeight/6);
    public PointF welcome_point3 = new PointF(measureWidth * POINT_3_OFFSET , measureHeight/2 + measureHeight/4);
    public PointF welcome_point4 = new PointF(-measureWidth , measureHeight);

    public PointF sex_point1 = new PointF(measureWidth + SEX_DEFAULT_RADIUS , measureHeight/2);
    public PointF sex_point2 = new PointF(measureWidth - SEX_DEFAULT_RADIUS * 0.6f, measureHeight * 0.5f);
    public PointF sex_point3 = new PointF(measureWidth * POINT_3_OFFSET , measureHeight/2 + measureHeight/4);
    public PointF sex_point4 = new PointF(-measureWidth , measureHeight);

    public PointF welcome_dot_point1 = new PointF(measureWidth + DOT_DEFAULT_RADIUS , measureHeight/2);
    public PointF welcome_dot_point2 = new PointF(measureWidth * 0.55f, measureHeight * 0.55f);
    public PointF welcome_dot_point3 = ACUtils.calculateMidPoint(welcome_point3 , sex_point2);
    public PointF welcome_dot_point4 = new PointF(-measureWidth , measureHeight);

    public PointF headIcon_point1 = new PointF(measureWidth + SEX_DEFAULT_RADIUS , measureHeight/2);
    public PointF headIcon_point2 = new PointF(measureWidth - SEX_DEFAULT_RADIUS * 0.6f, measureHeight * 0.4f);
    public PointF headIcon_point3 = new PointF(measureWidth * POINT_3_OFFSET , measureHeight/2 + measureHeight/4);
    public PointF headIcon_point4 = new PointF(-measureWidth , measureHeight);

    public PointF sexBoy_point1 = new PointF(measureWidth + SEX_DEFAULT_RADIUS , measureHeight/2);
    public PointF sexBoy_point2 = new PointF(measureWidth * 0.35f, measureHeight/2);
    public PointF sexBoy_point3 = ACUtils.calculateMidPoint(sex_point3 , headIcon_point2);
    public PointF sexBoy_point4 = new PointF(-measureWidth , measureHeight);

    public PointF sexGirl_point1 = new PointF(measureWidth + SEX_DEFAULT_RADIUS , measureHeight/2);
    public PointF sexGirl_point2 = new PointF(measureWidth * 0.75f, measureHeight * 0.75f);
    public PointF sexGirl_point3 = ACUtils.calculateMidPoint(sex_point3 , headIcon_point2);
    public PointF sexGirl_point4 = new PointF(-measureWidth , measureHeight);

    public PointF headCamera_point1 = new PointF(measureWidth + SEX_DEFAULT_RADIUS , measureHeight/2);
    public PointF headCamera_point2 = new PointF(measureWidth * 0.35f, measureHeight * 0.4f);
    public PointF headCamera_point3 = ACUtils.calculateMidPoint(sex_point3 , headIcon_point2);
    public PointF headCamera_point4 = new PointF(-measureWidth , measureHeight);

    public PointF headAlbum_point1 = new PointF(measureWidth + SEX_DEFAULT_RADIUS , measureHeight/2);
    public PointF headAlbum_point2 = new PointF(measureWidth * 0.5f, measureHeight * 0.55f);
    public PointF headAlbum_point3 = ACUtils.calculateMidPoint(sex_point3 , headIcon_point2);
    public PointF headAlbum_point4 = new PointF(-measureWidth , measureHeight);

    public PointF headSkip_point1 = new PointF(measureWidth + SEX_DEFAULT_RADIUS , measureHeight/2);
    public PointF headSkip_point2 = new PointF(measureWidth * 0.75f, measureHeight * 0.65f);
    public PointF headSkip_point3 = ACUtils.calculateMidPoint(sex_point3 , headIcon_point2);
    public PointF headSkip_point4 = new PointF(-measureWidth , measureHeight);

    public PointF headDot_point1 = new PointF(measureWidth + SEX_DEFAULT_RADIUS , measureHeight/2);
    public PointF headDot_point2 = new PointF(measureWidth * 0.45f, measureHeight * 0.4f);
    public PointF headDot_point3 = ACUtils.calculateMidPoint(sex_point3 , headIcon_point2);
    public PointF headDot_point4 = new PointF(-measureWidth , measureHeight);

    public PointF headChange_point1 = new PointF(measureWidth + SEX_DEFAULT_RADIUS , measureHeight/2);
    public PointF headChange_point2 = new PointF(measureWidth * 0.75f, measureHeight * 0.85f);
    public PointF headChange_point3 = ACUtils.calculateMidPoint(sex_point3 , headIcon_point2);
    public PointF headChange_point4 = new PointF(-measureWidth , measureHeight);

    public PointF birthday_point1 = new PointF(measureWidth + SEX_DEFAULT_RADIUS , measureHeight/2);
    public PointF birthday_point2 = new PointF(measureWidth - SEX_DEFAULT_RADIUS * 0.6f, measureHeight * 0.3f);
    public PointF birthday_point3 = new PointF(measureWidth * POINT_3_OFFSET , measureHeight * 0.5f);
    public PointF birthday_point4 = new PointF(-measureWidth , measureHeight);

    public PointF nickname_point1 = new PointF(measureWidth + SEX_DEFAULT_RADIUS , measureHeight/2);
    public PointF nickname_point2 = new PointF(measureWidth - SEX_DEFAULT_RADIUS * 0.6f, measureHeight * 0.4f);
    public PointF nickname_point3 = new PointF(measureWidth * POINT_3_OFFSET , measureHeight * 0.4f);
    public PointF nickname_point4 = new PointF(-measureWidth , 0);

    public PointF email_point1 = new PointF(measureWidth + SEX_DEFAULT_RADIUS , measureHeight/2);
    public PointF email_point2 = new PointF(measureWidth - SEX_DEFAULT_RADIUS * 0.6f, measureHeight * 0.6f);
    public PointF email_point3 = new PointF(measureWidth * POINT_3_OFFSET_2 , measureHeight * 0.45f);
    public PointF email_point4 = new PointF(-measureWidth , -measureHeight * 1.3f);

    public PointF circle_point1 = new PointF(measureWidth + SEX_DEFAULT_RADIUS , measureHeight/2);
    public PointF circle_point2 = new PointF(measureWidth - SEX_DEFAULT_RADIUS * 1.3f, measureHeight - SEX_DEFAULT_RADIUS * 0.7f);
    public PointF circle_point3 = new PointF(measureWidth * POINT_3_OFFSET_02 , measureHeight * 0.35f);
    public PointF circle_point4 = new PointF(0 , -measureHeight * 1.0f);

    public PointF last_point1 = new PointF(measureWidth + SEX_DEFAULT_RADIUS , measureHeight/2);
    public PointF last_point2 = new PointF(measureWidth - SEX_DEFAULT_RADIUS * 0.6f, measureHeight * 0.6f);
    public PointF last_point3 = new PointF(measureWidth - SEX_DEFAULT_RADIUS * 0.7f , measureHeight * 0.35f);
    public PointF last_point4 = new PointF(-measureWidth , -measureHeight);

    public PointF complete_point1 = new PointF(measureWidth + SEX_DEFAULT_RADIUS , measureHeight/2);
    public PointF complete_point2 = new PointF(measureWidth * 0.35f, measureHeight * 0.8f);
    public PointF complete_point3 = new PointF(measureWidth * POINT_3_OFFSET_2 , measureHeight * 0.45f);
    public PointF complete_point4 = new PointF(-measureWidth , -measureHeight);

    public PointF birthdayDot_point1 = new PointF(measureWidth + SEX_DEFAULT_RADIUS , measureHeight/2);
    public PointF birthdayDot_point2 = new PointF(measureWidth * 0.55f, measureHeight * 0.4f);
    public PointF birthdayDot_point3 = ACUtils.calculateMidPoint(birthday_point3 , nickname_point2);
    public PointF birthdayDot_point4 = new PointF(-measureWidth , measureHeight);

    public PointF nicknameDot_point1 = new PointF(measureWidth + SEX_DEFAULT_RADIUS , measureHeight/2);
    public PointF nicknameDot_point2 = new PointF(measureWidth * 0.65f, measureHeight * 0.45f);
    public PointF nicknameDot_point3 = ACUtils.calculateMidPoint(birthday_point3 , email_point2);
    public PointF nicknameDot_point4 = new PointF(-measureWidth , measureHeight);


    public PointF emailDot_point1 = new PointF(measureWidth + SEX_DEFAULT_RADIUS , measureHeight/2);
    public PointF emailDot_point2 = new PointF(measureWidth * 0.6f, measureHeight * 0.60f);
    public PointF emailDot_point3 = ACUtils.calculateMidPoint(nickname_point3 , circle_point2);
    public PointF emailDot_point4 = new PointF(-measureWidth , measureHeight);

    public PointF circleDot_point1 = new PointF(measureWidth + SEX_DEFAULT_RADIUS , measureHeight/2);
    public PointF circleDot_point2 = new PointF(measureWidth * 0.6f, measureHeight * 0.60f);
    public PointF circleDot_point3 = ACUtils.calculateMidPoint(circle_point3 , last_point2);
    public PointF circleDot_point4 = new PointF(-measureWidth , measureHeight);

    public PointF circleOpen_point1 = new PointF(measureWidth + SEX_DEFAULT_RADIUS , measureHeight/2);
    public PointF circleOpen_point2 = new PointF(measureWidth * 0.6f, measureHeight * 0.40f);
    public PointF circleOpen_point3 = ACUtils.calculateMidPoint(circle_point3 , last_point2);
    public PointF circleOpen_point4 = new PointF(-measureWidth , measureHeight);

    public PointF circleClose_point1 = new PointF(measureWidth + SEX_DEFAULT_RADIUS , measureHeight/2);
    public PointF circleClose_point2 = new PointF(measureWidth * 0.5f, measureHeight * 0.60f);
    public PointF circleClose_point3 = ACUtils.calculateMidPoint(circle_point3 , last_point2);
    public PointF circleClose_point4 = new PointF(-measureWidth , measureHeight);

    public PointF lastDot_point1 = new PointF(measureWidth + SEX_DEFAULT_RADIUS , measureHeight/2);
    public PointF lastDot_point2 = new PointF(measureWidth * 0.6f, measureHeight * 0.60f);
    public PointF lastDot_point3 = ACUtils.calculateMidPoint(last_point3 , complete_point2);
    public PointF lastDot_point4 = new PointF(-measureWidth , measureHeight);

    public PointF lastOpen_point1 = new PointF(measureWidth + SEX_DEFAULT_RADIUS , measureHeight/2);
    public PointF lastOpen_point2 = new PointF(measureWidth * 0.7f, measureHeight * 0.70f);
    public PointF lastOpen_point3 = ACUtils.calculateMidPoint(last_point3 , complete_point2);
    public PointF lastOpen_point4 = new PointF(-measureWidth , measureHeight);

    public PointF lastClose_point1 = new PointF(measureWidth + SEX_DEFAULT_RADIUS , measureHeight/2);
    public PointF lastClose_point2 = new PointF(measureWidth * 0.5f, measureHeight * 0.50f);
    public PointF lastClose_point3 = ACUtils.calculateMidPoint(last_point3 , complete_point2);
    public PointF lastClose_point4 = new PointF(-measureWidth , measureHeight);

}
