package com.wanghuan.accountconfiguration.view.objectviews;

import android.content.Context;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.Paint;
import android.graphics.PathEffect;
import android.graphics.PointF;
import android.view.View;

import com.wanghuan.accountconfiguration.util.ViewUtils;

/**
 * Created by h1 on 16/3/29 15:34.
 * email: h18501667737@gmail.com
 */
public abstract class ObjectBase implements ObjectInterface{

    Context context;
    int measureWidth;
    int measureHeight;

    boolean isDraw = false;

    PointF center = new PointF();

    public PointF getCenter() {
        return center;
    }

    public void setCenter(PointF center) {
        this.center = center;
    }

    int colorWhite;
    int colorBlue;
    int colorGrey;

    private String text;
    private int bgColor = colorGrey;
    private int textColor = colorWhite;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getTextColor() {
        return textColor;
    }

    public int getBgColor() {
        return bgColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    public void setBgBlueColor() {
        setBgColor(colorBlue);
    }

    public void setBgGreyColor() {
        setBgColor(colorGrey);
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public void setTextColorBlue() {
        setTextColor(colorBlue);
    }

    public void setTextColorGrey(){
        setTextColor(colorGrey);
    }

    public boolean isDraw() {
        return isDraw;
    }

    public void setIsDraw(boolean isDraw) {
        this.isDraw = isDraw;
    }

    public ObjectBase(Context context) {
        this.context = context;
        this.measureWidth = ViewUtils.getScreenWidthThoughContext(context);
        this.measureHeight = ViewUtils.getScreenHeightThoughContext(context);
        paint.setAntiAlias(true);

        colorWhite = Color.WHITE;
        colorBlue = Color.parseColor("#009BF4");
        colorGrey = Color.parseColor("#DDDDDD");
    }

    Paint paint = new Paint();

    public Paint getPaint() {
        paint.setColor(bgColor);
        return paint;
    }

    public void setPaint(Paint paint) {
        this.paint = paint;
    }

    public PointF calculate(PointF from , PointF to , float animatedValue){
        PointF pointF = new PointF();
        if(from.x > to.x){
            pointF.x = from.x - Math.abs(from.x - to.x) * animatedValue;
        }else{
            pointF.x = from.x + Math.abs(from.x - to.x) * animatedValue;
        }
        if(from.y < to.y){
            pointF.y = from.y + Math.abs(from.y - to.y) * animatedValue;
        }else{
            pointF.y = from.y - Math.abs(from.y - to.y) * animatedValue;
        }
        return pointF;
    }

    public Paint getGreyDotPaint(){
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(colorGrey);
        paint.setStrokeWidth(10);
        return paint;
    }

    public Paint getBlueDotPaint(){
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(colorBlue);
        paint.setStrokeWidth(10);
        return paint;
    }

    public Paint getWhiteDotPaint(){
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setColor(Color.WHITE);
        return paint;
    }

    public Paint getGreyLinePaint(){
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        PathEffect effects = new DashPathEffect(new float[]{10,10,10,10},10);
        paint.setPathEffect(effects);
        paint.setAntiAlias(true);
        paint.setColor(colorGrey);
        paint.setStrokeWidth(10);
        return paint;
    }

    public Paint getBlueLinePaint(){
        Paint paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setColor(colorBlue);
        paint.setStrokeWidth(10);
        return paint;
    }

    public Paint getTextPaint(){
        Paint paint = new Paint();
        paint.setColor(colorWhite);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setTextSize(50);
        paint.setStrokeWidth(15);
        return paint;
    }

    @Override
    public void drawEnter(View view) {

    }

    @Override
    public void drawMove(View view) {

    }

    @Override
    public void drawExit(View view) {

    }
}
