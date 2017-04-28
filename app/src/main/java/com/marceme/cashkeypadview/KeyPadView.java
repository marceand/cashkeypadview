package com.marceme.cashkeypadview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Marcelino Yax-marce7j@gmail.com-Android Developer
 *         Created on 4/28/2017.
 */

public class KeyPadView extends View {


    private static final int MARGIN_BOTTOM = 40;
    private static final int ROW_LENGTH = 4;
    private static final int COLUMN_LENGTH = 3;
    private static final int LINE_MARGIN = 80;

    private UIKey[][] keys;

    private Paint textKeyPaint;
    private Paint lineKeyPaint;
    private int halfOfKeyWidth;
    private int halfOfKeyHeight;

    public KeyPadView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTextKeyPaint();
        initLineKeyPaint();
        initKeys();
    }

    private void initTextKeyPaint() {
        textKeyPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textKeyPaint.setTextSize(56);
        textKeyPaint.setColor(Color.WHITE);
        textKeyPaint.setTextAlign(Paint.Align.CENTER);
    }

    private void initLineKeyPaint() {
        lineKeyPaint = new Paint();
        lineKeyPaint.setColor(Color.GREEN);
        lineKeyPaint.setAlpha(160);
    }

    private void initKeys() {
        keys = new UIKey[ROW_LENGTH][COLUMN_LENGTH];
        keys[0][0] = new UIKey("1");
        keys[0][1] = new UIKey("2");
        keys[0][2] = new UIKey("3");
        keys[1][0] = new UIKey("4");
        keys[1][1] = new UIKey("5");
        keys[1][2] = new UIKey("6");
        keys[2][0] = new UIKey("7");
        keys[2][1] = new UIKey("8");
        keys[2][2] = new UIKey("9");
        keys[3][0] = new UIKey(".");
        keys[3][1] = new UIKey("0");
        keys[3][2] = new UIKey("<");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int viewWidth = View.resolveSize(getDesiredWidth(), widthMeasureSpec);
        int viewHeight = View.resolveSize(getDesiredHeight(), heightMeasureSpec);

        halfOfKeyWidth = (keyWidth(viewWidth)) / 2;
        halfOfKeyHeight = (keyHeight(viewHeight)) / 2;

        setKeyCoordinate();

        setMeasuredDimension(viewWidth,viewHeight);
    }

    private void setKeyCoordinate() {

        for(int row = 0; row < ROW_LENGTH; row++){
            for(int column = 0; column < COLUMN_LENGTH; column++) {

                int keyXCenter = halfOfKeyWidth + 2 * column * halfOfKeyWidth;
                int keyYCenter = halfOfKeyHeight + 2 * row * halfOfKeyHeight;

                setTextKeyAxis(row, column, keyXCenter, keyYCenter);
                setLineKeyAxis(row, column, keyXCenter, keyYCenter);
            }
        }

    }

    private void setTextKeyAxis(int row, int column, int keyXCenter, int keyYCenter) {
        keys[row][column].setTextKeyCenterAxis(keyXCenter, keyYCenter);
    }

    private void setLineKeyAxis(int row, int column, int keyXCenter, int keyYCenter) {
        int startX = keyXCenter  - halfOfKeyWidth + LINE_MARGIN;
        int startY = keyYCenter + halfOfKeyHeight;
        int stopX = keyXCenter + halfOfKeyWidth - LINE_MARGIN;

        keys[row][column].setLineKeyAxis(startX, startY, stopX, startY);
    }

    @Override
    protected void onDraw(Canvas canvas) {

        for(int row = 0; row < ROW_LENGTH; row++){
            for(int column = 0; column < COLUMN_LENGTH; column++) {
                drawTextKey(canvas, row, column);
                drawLineUnderKey(canvas, row, column);
            }
        }
    }

    private void drawTextKey(Canvas canvas, int row, int column) {
        canvas.drawText(keys[row][column].getTextKey(),
                keys[row][column].getXTextKey(),
                keys[row][column].getYTextKey(), textKeyPaint);
    }

    private void drawLineUnderKey(Canvas canvas, int row, int column) {
        canvas.drawLine(keys[row][column].getLineXStart(),
                keys[row][column].getLineYStart(),
                keys[row][column].getLineXStop(),
                keys[row][column].getLineYStop(), lineKeyPaint);
    }


    private int keyWidth(int viewWidth) {
        return viewWidth / COLUMN_LENGTH;
    }

    private int keyHeight(int viewHeight) {
        return (viewHeight - MARGIN_BOTTOM) / (ROW_LENGTH);
    }

    private int getDesiredWidth() {
        return getPaddingLeft() + getPaddingRight();
    }

    private int getDesiredHeight() {
        return getPaddingTop() + getPaddingBottom();
    }

}
