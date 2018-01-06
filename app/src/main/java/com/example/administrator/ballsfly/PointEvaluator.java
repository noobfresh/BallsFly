package com.example.administrator.ballsfly;

import android.animation.TypeEvaluator;
import android.util.Log;

import java.util.List;

/**
 * Created by Administrator on 2018/1/5.
 */

public class PointEvaluator implements TypeEvaluator {

    public static final String TAG = "PointEvaluator";

//    float k1;
//    float b1;
//    float count;
//    float x;
//    float y;

    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {

        List<Point> pointList = (List<Point>) startValue;


        for(int i = 0; i < pointList.size(); i++){
            Point point = pointList.get(i);
//            float b1 = point.getY() - point.getX();
            point.setX(point.getX() + point.getCount());
            point.setY(point.getB1() + point.getK1() * point.getX());

            if((int)point.getX() <= 30){
//                count = -count;
//                k1 = -k1;
//                b1 = 60*(-k1) + b1;
                point.setCount(-point.getCount());
                point.setK1(-point.getK1());
                point.setB1(60*(-point.getK1()) + point.getB1());
            }
            //上
            else if((int)point.getY() <= 30){
//                k1 = -k1;
//                b1 = 60 - b1;
//
                point.setK1(-point.getK1());
                point.setB1(60 - point.getB1());
            }
            //右
            else if((int)point.getX() >= 690){
                //获取对称后的直线方程
//                k1 = -k1;
//                b1 = 1380*(-k1) + b1;
//                count = -count;

                point.setCount(-point.getCount());
                point.setK1(-point.getK1());
                point.setB1(1380*(-point.getK1()) + point.getB1());
            }
            //下
            else if((int)point.getY() >= 1250){
//                k1 = -k1;
//                b1 = 2500 - b1;

                point.setK1(-point.getK1());
                point.setB1(2500 - point.getB1());
            }
        }


//        if(x == 0 && y == 0){
//            Point start = (Point)startValue;
//            x = start.getX();
//            y = start.getY();
//            k1 = start.getK1();
//            count = start.getCount();
//            b1 = y - x;
//        }
//
//        x += count;
//        y = b1 + k1 * x;
//
//        //触碰边界条件，反弹相当于求直线方程的关于某轴的对称的直线方程
//        //数值 可以自动根据屏幕大小获取
//        //左
//        if((int)x <= 30){
//            count = -count;
//            k1 = -k1;
//            b1 = 60*(-k1) + b1;
//        }
//        //上
//        else if((int)y <= 30){
//            k1 = -k1;
//            b1 = 60 - b1;
//        }
//        //右
//        else if((int)x >= 690){
//            //获取对称后的直线方程
//            k1 = -k1;
//            b1 = 1380*(-k1) + b1;
//            count = -count;
//        }
//        //下
//        else if((int)y >= 1250){
//            k1 = -k1;
//            b1 = 2500 - b1;
//        }
//        Point point = new Point(x, y, count, k1);
        return pointList;
    }

}
