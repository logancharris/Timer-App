/**
* Author: Logan C. Harris
* Email: logancharris12@gmail.com
* Date: 8th March, 2022
*/



package com.example.timer;

import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class Timer_1 extends AppCompatActivity {

    public int hr;
    public int m;
    public int s;
    public int origHr;
    public int origM;
    public int origS;
    public String hrs;
    public String min;
    public String sec;
    public boolean finish = true;
    public boolean ready = true;
    Button start;
    Button pause;
    Button cancel;
    Button restart;
    TextView textView;
    EditText setHrs;
    EditText setMin;
    EditText setSec;
    CountDownTimer t;
    Handler customHandler = new Handler();
    NotificationCompat.Builder notification;



    Runnable updateTimer = new Runnable() {
        @Override
        public void run() {
            if(finish) {
                if (setHrs.getText().toString().isEmpty()) {
                    setHrs.setText("0");
                }

                if (setMin.getText().toString().isEmpty()) {
                    setMin.setText("0");
                }

                if(setSec.getText().toString().isEmpty()){
                    setSec.setText("0");
                }


                if (setHrs.getText().toString().contains(":")) {
                    setHrs.setText("");
                    setHrs.setHint("Input a valid hour!");
                    ready = false;
                }

                if (setMin.getText().toString().contains(":")) {
                    setMin.setText("");
                    setMin.setHint("Input a valid minute!");
                    ready = false;
                }

                if (setSec.getText().toString().contains(":")) {
                    setSec.setText("");
                    setSec.setHint("Input a valid second!");
                    ready = false;
                }



                if(ready){

                    hr = Integer.parseInt(setHrs.getText().toString());
                    m = Integer.parseInt(setMin.getText().toString());
                    s = Integer.parseInt(setSec.getText().toString());



                    if(s > 60){
                        int t = s%60;
                        s -= t;
                        int i = 0;
                        if(s != 0){
                            i = s / 60;
                        }
                        s = t;
                        m += i;
                    }

                    if(m > 60){
                        int t = m%60;
                        m -= t;
                        int i = 0;
                        if(m != 0){
                            i = m / 60;
                        }
                        m = t;
                        hr += i;
                    }
                    if(start.getText().equals("Start")){
                        origHr = hr;
                        origM = m;
                        origS = s;
                    }

                    String nt = hr + ":" + m + ":" + s;
                    String textTitle = "Timer";
                    notification = new NotificationCompat.Builder(Timer_1.this, "Timer Notification")
                            .setContentTitle(textTitle)
                            .setContentText(nt)
                            .setPriority(NotificationCompat.PRIORITY_DEFAULT);
                    start.setText("Start");


                    finish = false;

                    int time = (hr * 3600) + (m * 60) + s;

                    t = new CountDownTimer((time * 1000), 1000) {
                        public void onTick(long millisUntilFinished) {
                            if (hr < 10) {
                                hrs = "0" + hr;
                            } else {
                                hrs = "" + hr;
                            }
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
                            String time = hrs + ":" + min + ":" + sec;
                            textView.setText(time);
                            notification.setContentText(time);
                            s--;
                            if (s < 0) {
                                s = 59;
                                m--;
                                if (m < 0) {
                                    m = 59;
                                    hr--;
                                }
                            }

                        }

                        public void onFinish() {
                            textView.setText("00:00:00");
                            finish = true;
                        }
                    }.start();
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timer_1);
        start = (Button) findViewById(R.id.startTimer);
        pause = (Button) findViewById(R.id.pause);
        cancel = (Button) findViewById(R.id.cancel);
        restart = (Button) findViewById(R.id.restartTimer);
        textView = (TextView) findViewById(R.id.textView2);
        setHrs = (EditText) findViewById(R.id.setHour);
        setMin = (EditText) findViewById(R.id.setMin);
        setSec = (EditText) findViewById(R.id.setSec);


        /**
        When the start button is clicked sends to the customHandler class with the updateTimer method as a parameter.
         This starts the timer and creates notification at the same time.
         */
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                customHandler.postDelayed(updateTimer, 0);


            }
        });


        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!finish) {
                    t.cancel();
                    setHrs.setText("" + hr);
                    setMin.setText("" + m);
                    setSec.setText("" + s);
                    textView.setText("Paused");
                    start.setText("Resume");
                    finish = true;
                }
            }
        });


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!finish) {
                    t.cancel();
                    finish = true;

                }
                start.setText("Start");

                setHrs.setHint("Set Hours");
                setHrs.setText("");

                setMin.setHint("Set Minutes");
                setMin.setText("");

                setSec.setHint("Set Seconds");
                setSec.setText("");

                textView.setText("00:00:00");
            }
        });


        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!finish) {
                    t.cancel();

                    setHrs.setText("" + origHr);
                    setMin.setText("" + origM);
                    setSec.setText("" + origS);
                    hr = origHr;
                    m = origM;
                    s = origS;
                    finish = false;
                    start.setText("Start");
                    t.start();
                }
            }
        });
    }
}
