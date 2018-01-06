package com.example.administrator.ballsfly;

import android.animation.TypeEvaluator;
import android.util.Log;

import java.util.List;

/**
 * Created by Administrator on 2018/1/5.
 */

public class PointEvaluator implements TypeEvaluator {

    public static final String TAG = "PointEvaluator";

    public static int screenHeight;

    public static int screenWidth;

    public static int ballRadius = 30;

    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {

        List<Point> pointList = (List<Point>) startValue;


        for(int i = 0; i < pointList.size(); i++){
            Point point = pointList.get(i);
            point.setX(point.getX() + point.getCount());
            point.setY(point.getB1() + point.getK1() * point.getX());

            if((int)point.getX() <= ballRadius){
                point.setCount(-point.getCount());
                point.setK1(-point.getK1());
                point.setB1(2*ballRadius*(-point.getK1()) + point.getB1());
            }
            //上
            else if((int)point.getY() <= ballRadius){
                point.setK1(-point.getK1());
                point.setB1(2*ballRadius - point.getB1());
            }
            //右
            else if((int)point.getX() >= (screenWidth - ballRadius)){
                //获取对称后的直线方程
                point.setCount(-point.getCount());
                point.setK1(-point.getK1());
                point.setB1(2*(screenWidth - ballRadius)*(-point.getK1()) + point.getB1());
            }
            //下
            else if((int)point.getY() >= (screenHeight - ballRadius)){
                point.setK1(-point.getK1());
                point.setB1((screenHeight - ballRadius)*2 - point.getB1());
            }
        }


        return pointList;
    }

    public static void setScreenHeight(int screenHeight) {
        PointEvaluator.screenHeight = screenHeight;
    }

    public static void setScreenWidth(int screenWidth) {
        PointEvaluator.screenWidth = screenWidth;
    }
}
