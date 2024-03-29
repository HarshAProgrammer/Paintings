package com.rackluxury.paintings.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import com.rackluxury.paintings.R;

class TwitterShareDialogue {
    private AlertDialog dialogue;
    private final Activity activity;

    TwitterShareDialogue(Activity myActivity){
        activity = myActivity;
    }

    void startTwitterShareDialogue(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflate = activity.getLayoutInflater();
        builder.setView(inflate.inflate(R.layout.twitter_share_dialogue, null));
        builder.setCancelable(true);

        dialogue = builder.create();
        dialogue.show();
    }

    void dismissDialogue(){
        dialogue.dismiss();
    }
}
