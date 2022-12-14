package com.project.discoverskool.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.project.discoverskool.Auth.SignInActivity;
import com.project.discoverskool.R;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
    }

    public void sign(View view) {
        startActivity(new Intent(SplashScreenActivity.this, SignInActivity.class));
    }

    public void finder(View view) {
        startActivity(new Intent(SplashScreenActivity.this, SmartFinderActivity.class));
    }
}