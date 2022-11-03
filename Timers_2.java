package com.example.timer;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Timers_2 extends AppCompatActivity {
    public int hr1;
    public int m1;
    public int s1;
    public int origHr1;
    public int origM1;
    public int origS1;
    public String hrs1;
    public String min1;
    public String sec1;
    public boolean finish1 = true;
    public boolean ready1 = true;
    Button start1;
    Button pause1;
    Button cancel1;
    Button restart1;
    TextView timer1;
    EditText setHrs1;
    EditText setMin1;
    EditText setSec1;
    CountDownTimer t1;

    public int hr2;
    public int m2;
    public int s2;
    public int origHr2;
    public int origM2;
    public int origS2;
    public String hrs2;
    public String min2;
    public String sec2;
    public boolean finish2 = true;
    public boolean ready2 = true;
    Button start2;
    Button pause2;
    Button cancel2;
    Button restart2;
    TextView timer2;
    EditText setHrs2;
    EditText setMin2;
    EditText setSec2;
    CountDownTimer t2;
    Handler time1 = new Handler();
    Handler time2 = new Handler();


    Runnable updateTimer1 = new Runnable() {
        @Override
        public void run() {
            if(finish1) {
                if (setHrs1.getText().toString().isEmpty()) {
                    setHrs1.setText("0");
                }

                if (setMin1.getText().toString().isEmpty()) {
                    setMin1.setText("0");
                }

                if(setSec1.getText().toString().isEmpty()){
                    setSec1.setText("0");
                }


                if (setHrs1.getText().toString().contains(":")) {
                    setHrs1.setText("");
                    setHrs1.setHint("Input a valid hour!");
                    ready1 = false;
                }

                if (setMin1.getText().toString().contains(":")) {
                    setMin1.setText("");
                    setMin1.setHint("Input a valid minute!");
                    ready1 = false;
                }

                if (setSec1.getText().toString().contains(":")) {
                    setSec1.setText("");
                    setSec1.setHint("Input a valid second!");
                    ready1 = false;
                }



                if(ready1){
                    hr1 = Integer.parseInt(setHrs1.getText().toString());
                    m1 = Integer.parseInt(setMin1.getText().toString());
                    s1 = Integer.parseInt(setSec1.getText().toString());

                    if(s1 > 60){
                        int t = s1%60;
                        s1 -= t;
                        int i = 0;
                        if(s1 != 0){
                            i = s1 / 60;
                        }
                        s1 = t;
                        m1 += i;
                    }

                    if(m1 > 60){
                        int t = m1%60;
                        m1 -= t;
                        int i = 0;
                        if(m1 != 0){
                            i = m1 / 60;
                        }
                        m1 = t;
                        hr1 += i;
                    }
                    if(start1.getText().equals("Start")){
                        origHr1 = hr1;
                        origM1 = m1;
                        origS1 = s1;
                    }
                    start1.setText("Start");


                    finish1 = false;

                    int time = (hr1 * 3600) + (m1 * 60) + s1;

                    t1 = new CountDownTimer((time * 1000), 1000) {
                        public void onTick(long millisUntilFinished) {
                            if (hr1 < 10) {
                                hrs1 = "0" + hr1;
                            } else {
                                hrs1 = "" + hr1;
                            }
                            if (m1 < 10) {
                                min1 = "0" + m1;
                            } else {
                                min1 = "" + m1;
                            }
                            if (s1 < 10) {
                                sec1 = "0" + s1;
                            } else {
                                sec1 = "" + s1;
                            }
                            timer1.setText(hrs1 + ":" + min1 + ":" + sec1);
                            s1--;
                            if (s1 < 0) {
                                s1 = 59;
                                m1--;
                                if (m1 < 0) {
                                    m1 = 59;
                                    hr1--;
                                }
                            }

                        }

                        public void onFinish() {
                            timer1.setText("00:00:00");
                            finish1 = true;
                        }
                    }.start();
                }
            }
        }
    };

    Runnable updateTimer2 = new Runnable() {
        @Override
        public void run() {
            if(finish2) {
                if (setHrs2.getText().toString().isEmpty()) {
                    setHrs2.setText("0");
                }

                if (setMin2.getText().toString().isEmpty()) {
                    setMin2.setText("0");
                }

                if(setSec2.getText().toString().isEmpty()){
                    setSec2.setText("0");
                }


                if (setHrs2.getText().toString().contains(":")) {
                    setHrs2.setText("");
                    setHrs2.setHint("Input a valid hour!");
                    ready2 = false;
                }

                if (setMin2.getText().toString().contains(":")) {
                    setMin2.setText("");
                    setMin2.setHint("Input a valid minute!");
                    ready2 = false;
                }

                if (setSec2.getText().toString().contains(":")) {
                    setSec2.setText("");
                    setSec2.setHint("Input a valid second!");
                    ready2 = false;
                }



                if(ready2){
                    hr2 = Integer.parseInt(setHrs2.getText().toString());
                    m2 = Integer.parseInt(setMin2.getText().toString());
                    s2 = Integer.parseInt(setSec2.getText().toString());

                    if(s2 > 60){
                        int t = s2%60;
                        s2 -= t;
                        int i = 0;
                        if(s2 != 0){
                            i = s2 / 60;
                        }
                        s2 = t;
                        m2 += i;
                    }

                    if(m2 > 60){
                        int t = m2%60;
                        m2 -= t;
                        int i = 0;
                        if(m2 != 0){
                            i = m2 / 60;
                        }
                        m2 = t;
                        hr2 += i;
                    }
                    if(start2.getText().equals("Start")){
                        origHr2 = hr2;
                        origM2 = m2;
                        origS2 = s2;
                    }
                    start2.setText("Start");


                    finish2 = false;

                    int time = (hr2 * 3600) + (m2 * 60) + s2;

                    t2 = new CountDownTimer((time * 1000), 1000) {
                        public void onTick(long millisUntilFinished) {
                            if (hr2 < 10) {
                                hrs2 = "0" + hr2;
                            } else {
                                hrs2 = "" + hr2;
                            }
                            if (m2 < 10) {
                                min2 = "0" + m2;
                            } else {
                                min2 = "" + m2;
                            }
                            if (s2 < 10) {
                                sec2 = "0" + s2;
                            } else {
                                sec2 = "" + s2;
                            }
                            timer2.setText(hrs2 + ":" + min2 + ":" + sec2);
                            s2--;
                            if (s2 < 0) {
                                s2 = 59;
                                m2--;
                                if (m2 < 0) {
                                    m2 = 59;
                                    hr2--;
                                }
                            }

                        }

                        public void onFinish() {
                            timer2.setText("00:00:00");
                            finish2 = true;
                        }
                    }.start();
                }
            }
        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.timers_2);
        start1 = (Button) findViewById(R.id.start1);
        pause1 = (Button) findViewById(R.id.pause1);
        cancel1 = (Button) findViewById(R.id.cancel1);
        restart1 = (Button) findViewById(R.id.restart1);
        timer1 = (TextView) findViewById(R.id.timer1);
        setHrs1 = (EditText) findViewById(R.id.setHour1);
        setMin1 = (EditText) findViewById(R.id.setMin1);
        setSec1 = (EditText) findViewById(R.id.setSec1);

        start1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time1.postDelayed(updateTimer1, 0);
            }
        });


        pause1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!finish1) {
                    t1.cancel();
                    setHrs1.setText("" + hr1);
                    setMin1.setText("" + m1);
                    setSec1.setText("" + s1);
                    timer1.setText("Paused");
                    start1.setText("Resume");
                    finish1 = true;
                }
            }
        });


        cancel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!finish1) {
                    t1.cancel();
                    finish1 = true;
                }
                start1.setText("Start");

                setHrs1.setHint("Set Hours");
                setHrs1.setText("");

                setMin1.setHint("Set Minutes");
                setMin1.setText("");

                setSec1.setHint("Set Seconds");
                setSec1.setText("");

                timer1.setText("00:00:00");
            }
        });


        restart1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!finish1) {
                    t1.cancel();
                    setHrs1.setText("" + origHr1);
                    setMin1.setText("" + origM1);
                    setSec1.setText("" + origS1);
                    hr1 = origHr1;
                    m1 = origM1;
                    s1 = origS1;
                    finish1 = false;
                    start1.setText("Start");
                    t1.start();
                }
            }
        });


        start2 = (Button) findViewById(R.id.start2);
        pause2 = (Button) findViewById(R.id.pause2);
        cancel2 = (Button) findViewById(R.id.cancel2);
        restart2 = (Button) findViewById(R.id.restart2);
        timer2 = (TextView) findViewById(R.id.timer2);
        setHrs2 = (EditText) findViewById(R.id.setHour2);
        setMin2 = (EditText) findViewById(R.id.setMin2);
        setSec2 = (EditText) findViewById(R.id.setSec2);
        start2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                time2.postDelayed(updateTimer2, 0);
            }
        });


        pause2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!finish2) {
                    t2.cancel();
                    setHrs2.setText("" + hr2);
                    setMin2.setText("" + m2);
                    setSec2.setText("" + s2);
                    timer2.setText("Paused");
                    start2.setText("Resume");
                    finish2 = true;
                }
            }
        });


        cancel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!finish2) {
                    t2.cancel();
                    finish2 = true;
                }
                start2.setText("Start");

                setHrs2.setHint("Set Hours");
                setHrs2.setText("");

                setMin2.setHint("Set Minutes");
                setMin2.setText("");

                setSec2.setHint("Set Seconds");
                setSec2.setText("");

                timer2.setText("00:00:00");
            }
        });


        restart2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!finish2) {
                    t2.cancel();
                    setHrs2.setText("" + origHr2);
                    setMin2.setText("" + origM2);
                    setSec2.setText("" + origS2);
                    hr2 = origHr2;
                    m2 = origM2;
                    s2 = origS2;
                    finish2 = false;
                    start2.setText("Start");
                    t2.start();
                }
            }
        });

    }
}
