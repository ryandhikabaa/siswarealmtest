package com.example.learnrealm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        int SPLASH_TIME_OUT = 2500;
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                    Intent intent = new Intent(getApplication(), SiswaActivity.class);
                    startActivity(intent);

                finish();
            }
        }, SPLASH_TIME_OUT);
    }
}
