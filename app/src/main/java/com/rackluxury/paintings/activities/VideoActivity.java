package com.rackluxury.paintings.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;

import com.rackluxury.paintings.R;
import com.rackluxury.paintings.activities.models.MediaObject;
import com.rackluxury.paintings.activities.util.Resources;
import com.rackluxury.paintings.activities.util.VerticalSpacingItemDecorator;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;
import java.util.Arrays;

public class VideoActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private VideoPlayerRecyclerView mRecyclerView;
    private ImageView backIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);


        ConnectivityManager manager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = manager.getActiveNetworkInfo();

        if (null == activeNetwork) {
            setNoInternetDialogue();
        }
        setupUIViews();
        initToolbar();
        initRVVideo();
    }
    private void setupUIViews() {
        toolbar = findViewById(R.id.toolbarVideoActivity);
        mRecyclerView = findViewById(R.id.rvVideoActivity);
        backIcon = findViewById(R.id.backIconVideo);
    }
    private void initToolbar() {
        setSupportActionBar(toolbar);
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Animatoo.animateSplit(VideoActivity.this);
            }
        });

    }
    private void setNoInternetDialogue() {
        final NoInternetDialogue noInternetDialogue = new NoInternetDialogue(VideoActivity.this);
        noInternetDialogue.startNoInternetDialogue();
        Handler handler = new Handler();
        int TRANSITION_SCREEN_TIME = 4000;
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                noInternetDialogue.dismissDialogue();
            }
        }, TRANSITION_SCREEN_TIME);
    }



    private void initRVVideo(){
        LinearLayoutManager layoutManager = new LinearLayoutManager(VideoActivity.this);
        mRecyclerView.setLayoutManager(layoutManager);
        VerticalSpacingItemDecorator itemDecorator = new VerticalSpacingItemDecorator(10);
        mRecyclerView.addItemDecoration(itemDecorator);

        ArrayList<MediaObject> mediaObjects = new ArrayList<>(Arrays.asList(Resources.MEDIA_OBJECTS));
        mRecyclerView.setMediaObjects(mediaObjects);
        VideoPlayerRecyclerAdapter adapter = new VideoPlayerRecyclerAdapter(mediaObjects, initGlide());
        mRecyclerView.setAdapter(adapter);
    }

    private RequestManager initGlide(){
        RequestOptions options = new RequestOptions()
                .placeholder(R.drawable.white_background)
                .error(R.drawable.white_background);

        return Glide.with(VideoActivity.this)
                .setDefaultRequestOptions(options);
    }


    @Override
    protected void onDestroy() {
        if(mRecyclerView!=null)
            mRecyclerView.releasePlayer();
        super.onDestroy();
    }
    @Override
    public void onBackPressed() {
        finish();
        Animatoo.animateSplit(VideoActivity.this);

    }


}