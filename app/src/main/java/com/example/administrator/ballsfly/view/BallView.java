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

import com.example.administrator.ballsfly.Point;
import com.example.administrator.ballsfly.PointEvaluator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by PYF on 2018/1/4.
 */

public class BallView extends View {

    public static final String TAG = "BallView";

    private List<Point> pointList = new ArrayList<>();

    private Paint mPaint;// 绘图画笔



    public BallView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(Color.BLACK);
        Random random = new Random();
        for(int i = 0; i < 50; i++){
            Point temp = new Point(random.nextInt(1080 - 2*PointEvaluator.ballRadius) + PointEvaluator.ballRadius,
                    random.nextInt(1800 - 2*PointEvaluator.ballRadius) + PointEvaluator.ballRadius,
                    random.nextInt(10) + 1, random.nextFloat()/1);
            pointList.add(temp);
        }
        //初始化的操作 一般都有构造函数这个时候作为时机 调用！
        setupAnimator();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);


        long start = System.currentTimeMillis();


        for (Point point : pointList) {
            canvas.drawCircle(point.getX(), point.getY(), 30, mPaint);
            drawLine(canvas, point);
        }
        long end = System.currentTimeMillis();
        Log.d(TAG, "onDraw: time = " + (end - start));



    }

    public void setupAnimator(){
        ValueAnimator animator = ValueAnimator.ofObject(new PointEvaluator(), pointList, null);

        //肯定有设置动画无限的地方的
        animator.setDuration(1000);

        //动画的刷新效果 是看自己在updateListener里面的更新操作，更新操作不一定要放在Evaluator里，也可以直接在Listener中完成
        animator.setRepeatCount(ValueAnimator.INFINITE);

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
