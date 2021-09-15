package com.rackluxury.rolex.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rackluxury.rolex.R;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.ramotion.foldingcell.FoldingCell;

import tyrantgit.explosionfield.ExplosionField;

public class FactsDetailActivity extends AppCompatActivity implements
        GestureDetector.OnGestureListener{
    private Toolbar toolbar;
    private ExplosionField explosionField;
    public static final int SWIPE_THRESHOLD = 100;
    public static final int SWIPE_VELOCITY_THRESHOLD = 100;
    private GestureDetector gestureDetector;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facts_detail);

        toolbar = findViewById(R.id.toolbarFactDetailActivity);
        explosionField = ExplosionField.attach2Window(FactsDetailActivity.this);
        gestureDetector = new GestureDetector(FactsDetailActivity.this, this);

        final FoldingCell fcFacts = findViewById(R.id.folding_cell_facts);


        TextView FactDescription = findViewById(R.id.tvFactsDescription);
        TextView FactTitle1 = findViewById(R.id.tvFactsTitle1);
        TextView FactTitle2 = findViewById(R.id.tvFactsTitle2);
        final ImageView FactImage = findViewById(R.id.ivDetailFacts);
        FactDescription.setText(getIntent().getStringExtra("description"));
        FactTitle1.setText(getIntent().getStringExtra("title"));
        FactTitle2.setText(getIntent().getStringExtra("title"));
        FactImage.setImageResource(getIntent().getIntExtra("image",1));

        initToolbar();
        fcFacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                explosionField.explode(FactImage);
                fcFacts.toggle(false);

            }
        });

    }
    @Override
    public void onBackPressed() {
        finish();
        Animatoo.animateSlideDown(FactsDetailActivity.this);

    }
    private void initToolbar(){
        setSupportActionBar(toolbar);

        if(getSupportActionBar() != null){
            getSupportActionBar().setTitle("Facts About us");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
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

            if (Math.abs(diffX) > SWIPE_THRESHOLD && Math.abs(velocityX) > SWIPE_VELOCITY_THRESHOLD) {

                result = true;
            }
        } else {

            if (Math.abs(diffY) > SWIPE_THRESHOLD && Math.abs(velocityY) > SWIPE_VELOCITY_THRESHOLD) {
                if (diffY > 0) {
                    onSwipeBottom();
                }
                result = true;
            }
        }

        return result;
    }


    private void onSwipeBottom() {
        finish();
        Animatoo.animateSlideDown(FactsDetailActivity.this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gestureDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }
}