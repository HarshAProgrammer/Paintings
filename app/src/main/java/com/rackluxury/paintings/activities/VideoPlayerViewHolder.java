package com.rackluxury.paintings.activities;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rackluxury.paintings.R;
import com.rackluxury.paintings.activities.models.MediaObject;
import com.bumptech.glide.RequestManager;

public class VideoPlayerViewHolder extends RecyclerView.ViewHolder {

    final FrameLayout media_container;
    final TextView title;
    final ImageView thumbnail;
    final ImageView volumeControl;
    final ProgressBar progressBar;
    final View parent;
    RequestManager requestManager;

    public VideoPlayerViewHolder(@NonNull View itemView) {
        super(itemView);
        parent = itemView;
        media_container = itemView.findViewById(R.id.media_container_video_player_item);
        thumbnail = itemView.findViewById(R.id.thumbnail_video_player_item);
        title = itemView.findViewById(R.id.title_video_player_item);
        progressBar = itemView.findViewById(R.id.progressBar_video_player_item);
        volumeControl = itemView.findViewById(R.id.volume_control_video_player_item);
    }

    public void onBind(MediaObject mediaObject, RequestManager requestManager) {
        this.requestManager = requestManager;
        parent.setTag(this);
        title.setText(mediaObject.getTitle());
        this.requestManager
                .load(mediaObject.getThumbnail())
                .into(thumbnail);
    }

}