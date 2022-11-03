/**
 * Author: Logan C. Harris
 * Date: 3th March, 2022
 * Email: logancharris12@gmail.com
 */


package com.example.timer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button test;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * Test about creating a notification
         */
        test = (Button) findViewById(R.id.NTest);
        test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NotificationCompat.Builder nTest = new NotificationCompat.Builder(MainActivity.this, "Test Notification")
                        .setSmallIcon(R.drawable.ic_launcher_background)
                        .setContentTitle("Test Notification")
                        .setContentText("This is a test")
                        .setPriority(NotificationCompat.PRIORITY_HIGH);
            }
        });
    }

    public void goToTimer1(View view){
        Intent intent = new Intent(this, Timer_1.class);
        startActivity(intent);
    }

    public void goToTimers2(View view){
        Intent intent = new Intent(this, Timers_2.class);
        startActivity(intent);
    }

    public void goToStopWatch(View view){
        Intent intent = new Intent(this, StopWatch.class);
        startActivity(intent);
    }


}