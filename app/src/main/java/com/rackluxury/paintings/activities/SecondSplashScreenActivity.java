package com.rackluxury.paintings.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

import androidx.appcompat.app.AppCompatActivity;

import com.rackluxury.paintings.R;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;

public class SecondSplashScreenActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_second_splash_screen);


        openLoginOrRegisterActivityFromSecondSplash();


    }


    private void openLoginOrRegisterActivityFromSecondSplash() {
        int SECOND_SPLASH_SCREEN_TIME = 2800;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent openLoginOrRegisterActivityFromSecondSplash = new Intent(SecondSplashScreenActivity.this, LoginOrRegisterActivity.class);
                startActivity(openLoginOrRegisterActivityFromSecondSplash);
                finish();
                Animatoo.animateSlideUp(SecondSplashScreenActivity.this);
            }
        }, SECOND_SPLASH_SCREEN_TIME);
    }

    @Override
    public void onBackPressed() {

    }
}
