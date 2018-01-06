package com.example.administrator.ballsfly.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.view.animation.OvershootInterpolator;

import com.example.administrator.ballsfly.Point;
import com.example.administrator.ballsfly.PointEvaluator;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Random;

/**
 * Created by PYF on 2018/1/4.
 */

public class BallView extends View {

    public static final String TAG = "BallView";

    private Point currentPoint;// 当前点坐标

    private List<Point> pointList = new ArrayList<>();

    private Paint mPaint;// 绘图画笔
    private boolean flag;

    private List<BallView> nearBall;

    public BallView(Context context, float startX, float startY, int color) {
        super(context);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(color);
        Random random = new Random();
//        this.currentPoint = new Point(startX, startY, random.nextInt(10), random.nextFloat()/1);
        Point temp = new Point(random.nextInt(690), random.nextInt(1250),
                random.nextInt(10), random.nextFloat()/1);
        pointList.add(temp);
        this.flag = true;
    }


    public BallView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
        Random random = new Random();
//        this.currentPoint = new Point(startX, startY, random.nextInt(10), random.nextFloat()/1);
        for(int i = 0; i < 20; i++){
            Point temp = new Point(random.nextInt(660) + 30, random.nextInt(1220) + 30,
                    random.nextInt(10) + 1, random.nextFloat()/1);
            pointList.add(temp);
        }
        flag = true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(flag){
            setupAnimator();
            flag = false;
        }else {
            long start = System.currentTimeMillis();


            for(Point point : pointList){
                canvas.drawCircle(point.getX(), point.getY(), 30, mPaint);
                drawLine(canvas, point);
            }
            long end = System.currentTimeMillis();
//            if((end - start) > 10){
                Log.d(TAG, "onDraw: time = " + (end - start));
//                Log.d(TAG, "onDraw: size = " + nearBall.size());
//            }

        }
    }


    public void setNearBall(List<BallView> nearBall) {
        this.nearBall = nearBall;
    }

    public Point getCurrentPoint() {
        return currentPoint;
    }

    public void setupAnimator(){
        ValueAnimator animator = ValueAnimator.ofObject(new PointEvaluator(), pointList, null);

        animator.setDuration(Long.MAX_VALUE);

        Interpolator linearInterpolator = new LinearInterpolator();

        animator.setInterpolator(linearInterpolator);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                pointList = (List<Point>) animation.getAnimatedValue();

                invalidate();
            }
        });

        animator.start();
    }

    public void drawLine(Canvas canvas, Point point){
        for(int i = 0; i < pointList.size(); i++){
            Point temp = pointList.get(i);
            if(point == temp){
                continue;
            }
            double distance = distance( point.getX(), point.getY(),
                    temp.getX(), temp.getY());
            if(distance <= 150){
                canvas.drawLine(point.getX(), point.getY(), temp.getX(), temp.getY(), mPaint);
            }
        }
    }

    public static double distance(float x1, float y1, float x2, float y2){
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

}
