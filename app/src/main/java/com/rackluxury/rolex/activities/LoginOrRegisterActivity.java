package com.rackluxury.rolex.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;

import com.rackluxury.rolex.R;
import com.rackluxury.rolex.databinding.ActivityLoginOrRegisterBinding;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;


import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginOrRegisterActivity extends AppCompatActivity implements
    GestureDetector.OnGestureListener{
    ActivityLoginOrRegisterBinding binding;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;



    public static final int SWIPE_THRESHOLD = 100;
    public static final int SWIPE_VELOCITY_THRESHOLD = 100;
    private GestureDetector gestureDetector;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();
        gestureDetector = new GestureDetector(LoginOrRegisterActivity.this, this);


        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {

                } else {
                }
            }
        };


        if (user != null) {
            finish();
            Intent openHomeActivityFromLoginOrRegister = new Intent(LoginOrRegisterActivity.this, HomeActivity.class);
            startActivity(openHomeActivityFromLoginOrRegister);
            Animatoo.animateSlideUp(LoginOrRegisterActivity.this);
        } else {
            binding = DataBindingUtil.setContentView(LoginOrRegisterActivity.this, R.layout.activity_login_or_register);

        }


    }

    @Override
    protected void onStart() {
        super.onStart();
        firebaseAuth.addAuthStateListener(authStateListener);
    }

    @Override
    protected void onStop() {
        super.onStop();

        if (authStateListener != null) {
            firebaseAuth.removeAuthStateListener(authStateListener);
        }
    }

    public void login(View view) {
        finish();
        startActivity(new Intent(LoginOrRegisterActivity.this, LoginActivity.class));
        Animatoo.animateSlideRight(LoginOrRegisterActivity.this);

    }

    public void getStarted(View view) {
        finish();
        startActivity(new Intent(LoginOrRegisterActivity.this, RegistrationActivity.class));
        Animatoo.animateSlideLeft(LoginOrRegisterActivity.this);
    }


    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent downEvent, MotionEvent moveEvent, float velocityX, float velocityY) {
        boolean result = false;
        float diffY = moveEvent.getY() - downEvent.getY();
        float diffX = moveEvent.getX() - downEvent.getX();

        if (Math.abs(diffX) > Math.abs(diffY)) {

            if (Math.abs(diffX)> SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffX > 0) {
                    onSwipeRight();
                } else {
                    onSwipeLeft();
                }
                result = true;
            }
        }

        return result;
    }

    private void onSwipeLeft() {
        finish();
        startActivity(new Intent(LoginOrRegisterActivity.this, RegistrationActivity.class));
        Animatoo.animateSlideLeft(LoginOrRegisterActivity.this);
    }

    private void onSwipeRight() {
        finish();
        startActivity(new Intent(LoginOrRegisterActivity.this, LoginActivity.class));
        Animatoo.animateSlideRight(LoginOrRegisterActivity.this);

    }
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

}