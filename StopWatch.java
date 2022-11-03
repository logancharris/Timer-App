package com.example.timer;
/**
 * This stopwatch method is a modified version of the code in this video:
 * https://www.youtube.com/watch?v=Dr-VtCbev10
 */

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StopWatch extends AppCompatActivity {

    public int m = 0;
    public int s = 0;
    public int ms = 0;
    public boolean paused = false;
    String min;
    String sec;
    String milliSec;
    TextView watch;
    Button start;
    Button pause;
    Button reset;
    CountDownTimer t;
    Handler customHandler = new Handler();

    long startTime=0L;
    long timeInMs=0L;
    long timeSwapBuff=0L;
    long updateTime=0L;

    Runnable updateTimer = new Runnable() {
        @Override
        public void run() {
            timeInMs = SystemClock.uptimeMillis()-startTime;
            updateTime = timeSwapBuff+timeInMs;
            s =(int)(updateTime/1000);
            m = s/60;
            s %= 60;
            if (m < 10) {
                min = "0" + m;
            } else {
                min = "" + m;
            }
            if (s < 10) {
                sec = "0" + s;
            } else {
                sec = "" + s;
            }
            if(ms < 100){
                if(ms < 10){
                    milliSec = "00" + ms;
                }else {
                    milliSec = "0" + ms;
                }
            }else{
                milliSec = "" + ms;
            }
            ms = (int)(updateTime%1000);
            watch.setText(min + ":" + sec + "." + milliSec);
            customHandler.postDelayed(this,0);
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.stopwatch);
        watch = (TextView) findViewById(R.id.watch);
        start = (Button) findViewById(R.id.strt);
        pause = (Button) findViewById(R.id.stp);
        reset = (Button) findViewById(R.id.reset);

        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startTime = SystemClock.uptimeMillis();
                customHandler.postDelayed(updateTimer, 0);
            }
        });

            pause.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!paused) {
                        timeSwapBuff += timeInMs;
                        customHandler.removeCallbacks(updateTimer);
                    }
                }
            });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                watch.setText("00:00.00");
                customHandler.removeCallbacks(updateTimer);
                ms =0;
                m = 0;
                s = 0;
                startTime=0L;
                timeInMs=0L;
                timeSwapBuff=0L;
                updateTime=0L;
            }
        });
    }



}
