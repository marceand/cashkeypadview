package com.marceme.cashkeypadview;

/**
 * @author Marcelino Yax-marce7j@gmail.com-Android Developer
 *         Created on 4/28/2017.
 */

public class UIKey {
    private final String textKey;
    private int xTextKey;
    private int yTextKey;
    private float startXLine;
    private float startYLine;
    private float stopXLine;
    private float stopYLine;


    public UIKey(String textKey) {
        this.textKey = textKey;
    }

    public void setTextKeyCenterAxis(int xCenter, int yCenter) {
        xTextKey = xCenter;
        yTextKey = yCenter;
    }

    public void setLineKeyAxis(float startX, float startY, float stopX, float stopY) {
        startXLine = startX;
        startYLine = startY;
        stopXLine = stopX;
        stopYLine = stopY;
    }

    public String getTextKey() {
        return textKey;
    }

    public int getXTextKey() {
        return xTextKey;
    }

    public int getYTextKey() {
        return yTextKey;
    }

    public float getLineXStart() {
        return startXLine;
    }

    public float getLineYStart() {
        return startYLine;
    }

    public float getLineXStop() {
        return stopXLine;
    }

    public float getLineYStop() {
        return stopYLine;
    }
}
