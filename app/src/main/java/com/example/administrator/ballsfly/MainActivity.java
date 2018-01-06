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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        LeakCanary.install(getApplication());
        super.onCreate(savedInstanceState);

        //初始化构建们
        setContentView(R.layout.activity_main);
        //获取屏幕宽高
//        WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
        FrameLayout frameLayout = findViewById(R.id.linear);
        Log.d(TAG, "onCreate: width: " + frameLayout.getLayoutParams().width + " height: " + frameLayout.getLayoutParams().height);
        PointEvaluator.setScreenHeight(frameLayout.getLayoutParams().height);
        PointEvaluator.setScreenWidth(frameLayout.getLayoutParams().width);

    }


}
