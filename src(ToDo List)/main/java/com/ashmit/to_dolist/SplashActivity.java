package com.ashmit.to_dolist;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        final Intent i = new Intent(this,MainActivity.class);

        new Handler().postDelayed(() -> { //Anonymous Function - describe a function in  parameters and dont have any name
            startActivity(i);
            finish();
        },2000);
    }
}
//new Handler().postDelayed(new Runnable() {
//@Override
//public void run() {
//        startActivity(i);
//        finish();
//        }
//        },2000);