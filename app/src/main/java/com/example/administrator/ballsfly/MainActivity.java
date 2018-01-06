package com.example.administrator.ballsfly;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.administrator.ballsfly.view.AnotherBall;
import com.example.administrator.ballsfly.view.BallView;
import com.squareup.leakcanary.LeakCanary;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "MainActivity";

    private static List<BallView> ballViews;

    private static boolean flag = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LeakCanary.install(getApplication());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ballViews = new ArrayList<>();
        FrameLayout frameLayout = findViewById(R.id.linear);
        Random random = new Random();

//        for(int i = 0; i < 20; i++){
//            BallView ballView1 = new BallView(this,
//                    random.nextInt(690), random.nextInt(1250), Color.BLACK);
//            frameLayout.addView(ballView1);
//            ballViews.add(ballView1);
//            ballView1.invalidate();
//        }



    }

    //暴力法 判断距离远近
    public static void caculate(){
         new Thread(new Runnable() {
            @Override
            public void run() {
                //不能永久
//                List<List<BallView>> lists = new ArrayList<>();
//                for(int i = 0; i < ballViews.size(); i++){
//                    lists.add(new CopyOnWriteArrayList<BallView>());
//                }
                while (flag){
                    for(int i = 0; i < ballViews.size(); i++){
                        BallView ballView = ballViews.remove(i);

//                        List<BallView> list = lists.get(i);
                        List<BallView> list = new ArrayList<>();
                        list.clear();
                        for(BallView temp : ballViews){
                            double distance = distance( ballView.getCurrentPoint().getX(), ballView.getCurrentPoint().getY(),
                                    temp.getCurrentPoint().getX(), temp.getCurrentPoint().getY());
                            if(distance <= 200){
                                list.add(temp);
                            }
                        }
                        ballView.setNearBall(list);

                        ballViews.add(i, ballView);

                    }
                }
            }
        }).start();

    }

    @Override
    protected void onStop() {
        super.onStop();
        flag = false;
    }

    @Override
    protected void onStart() {
        super.onStart();
        flag = true;
//        caculate();
    }

    public static double distance(float x1, float y1, float x2, float y2){
        return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    }

}
