package com.example.linux.muscleapp;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * @author Salvador Muñoz
 * @version 1.0
 */

public class SplashScreenActivity extends AppCompatActivity {
    private static int TIME_OUT= 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreenActivity.this,LogInActivity.class);
                startActivity(intent);
                finish();
            }
        },TIME_OUT);
    }

}
