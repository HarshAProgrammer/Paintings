package com.rackluxury.paintings.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.LayoutInflater;

import com.rackluxury.paintings.R;

class NoInternetDialogue {
    private AlertDialog dialogue;
    private final Activity activity;

    NoInternetDialogue(Activity myActivity){
        activity = myActivity;
    }

    void startNoInternetDialogue(){
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflate = activity.getLayoutInflater();
        builder.setView(inflate.inflate(R.layout.no_internet_dialogue, null));
        builder.setCancelable(true);

        dialogue = builder.create();
        dialogue.show();
    }

    void dismissDialogue(){
        dialogue.dismiss();
    }
}
