package com.example.administrator.ballsfly;

/**
 * Created by Administrator on 2018/1/5.
 */

public class Point {

    private float x;
    private float y;
    private float count;
    private float k1;
    private float b1;

    public Point(float x, float y, float count, float k1) {
        this.x = x;
        this.y = y;
        this.k1 = k1;
        this.count = count;
        this.b1 = y - x;
    }

    public float getX() {
        return x;
    }

    public float getY() {
        return y;
    }

    public float getCount() {
        return count;
    }

    public float getK1() {
        return k1;
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setCount(float count) {
        this.count = count;
    }

    public void setK1(float k1) {
        this.k1 = k1;
    }

    public float getB1() {
        return b1;
    }

    public void setB1(float b1) {
        this.b1 = b1;
    }
}
