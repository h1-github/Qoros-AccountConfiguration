package com.wanghuan.accountconfiguration.view;

/**
 * Created by h1 on 16/3/30 13:59.
 * email: h18501667737@gmail.com
 */
public class ACStatus {

    public static boolean animating;

    public static boolean welcome1;
    public static boolean welcome_showing;

    public static boolean welcome_dash_number;
    public static boolean welcome_dash_dot;
    public static boolean welcome_dash_start;
    public static boolean welcome_dash_always;
    public static boolean draw_welcome_quad_sex;

    public static boolean sex1;
    public static boolean sex2;

    public static boolean sex_showing;
    public static boolean sex_dash_object;
    public static boolean sex_dash_boy;
    public static boolean sex_dash_girl;
    public static boolean sex_dash_next;
    public static boolean sex_dash_always;
    public static boolean draw_sex_quad_head_icon;

    public static boolean head_showing_choose;
    public static boolean head_showing_change;
    public static boolean head_dash_object;
    public static boolean head_dash_camera;
    public static boolean head_dash_album;
    public static boolean head_dash_skip;
    public static boolean head_dash_next;
    public static boolean head_dash_always;
    public static boolean draw_head_quad_birthday;

    public static boolean head_dash_dot;
    public static boolean head_dash_change;

    public static boolean birthday_showing;
    public static boolean birthday_dash_object;
    public static boolean birthday_dash_dot;
    public static boolean birthday_dash_next;
    public static boolean birthday_dash_always;
    public static boolean draw_birthday_quad_nick;

    public static boolean nick_showing;
    public static boolean nick_dash_object;
    public static boolean nick_dash_dot;
    public static boolean nick_dash_always;
    public static boolean nick_dash_next;
    public static boolean draw_nickname_quad_email;

    public static boolean email_showing;
    public static boolean email_dash_object;
    public static boolean email_dash_dot;
    public static boolean email_dash_always;
    public static boolean email_dash_next;
    public static boolean draw_email_quad_circle;

    public static boolean circle_showing;
    public static boolean circle_dash_object;
    public static boolean circle_dash_dot;
    public static boolean circle_dash_open;
    public static boolean circle_dash_close;
    public static boolean circle_dash_always;
    public static boolean circle_dash_next;
    public static boolean draw_circle_quad_last;

    public static boolean last_showing;
    public static boolean last_dash_object;
    public static boolean last_dash_dot;
    public static boolean last_dash_open;
    public static boolean last_dash_close;
    public static boolean last_dash_always;
    public static boolean last_dash_next;
    public static boolean draw_last_quad_completed;

    public static void falseAll(){
        animating = false;

        welcome1 = false;
        welcome_showing = false;

        welcome_dash_number = false;
        welcome_dash_dot = false;
        welcome_dash_start = false;
        welcome_dash_always = false;
        draw_welcome_quad_sex = false;

        sex1 = false;
        sex2 = false;

        sex_showing = false;
        sex_dash_object = false;
        sex_dash_boy = false;
        sex_dash_girl = false;
        sex_dash_next = false;
        sex_dash_always = false;
        draw_sex_quad_head_icon = false;

        head_showing_choose = false;
        head_showing_change = false;
        head_dash_object = false;
        head_dash_camera = false;
        head_dash_album = false;
        head_dash_skip = false;
        head_dash_next = false;
        head_dash_always = false;
        draw_head_quad_birthday = false;

        head_dash_dot = false;
        head_dash_change = false;

        birthday_showing = false;
        birthday_dash_object = false;
        birthday_dash_dot = false;
        birthday_dash_next = false;
        birthday_dash_always = false;
        draw_birthday_quad_nick = false;

        nick_showing = false;
        nick_dash_object = false;
        nick_dash_dot = false;
        nick_dash_always = false;
        nick_dash_next = false;
        draw_nickname_quad_email = false;

        email_showing = false;
        email_dash_object = false;
        email_dash_dot = false;
        email_dash_always = false;
        email_dash_next = false;
        draw_email_quad_circle = false;

        circle_showing = false;
        circle_dash_object = false;
        circle_dash_dot = false;
        circle_dash_always = false;
        circle_dash_next = false;
        draw_circle_quad_last = false;

        last_showing = false;
        last_dash_object = false;
        last_dash_dot = false;
        last_dash_open = false;
        last_dash_close = false;
        last_dash_always = false;
        last_dash_next = false;
        draw_last_quad_completed = false;
    }
}
