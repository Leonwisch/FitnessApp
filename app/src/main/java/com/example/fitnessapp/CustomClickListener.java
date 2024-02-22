package com.example.fitnessapp;

import android.content.DialogInterface;
import android.view.View;

public class CustomClickListener implements View.OnClickListener {

    String fitnessId;
    public CustomClickListener(String fitnessId){
        this.fitnessId = fitnessId;
    }

    @Override
    public void onClick(View v) {

    }
}
