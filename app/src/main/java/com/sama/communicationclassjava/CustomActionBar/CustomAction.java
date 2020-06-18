package com.sama.communicationclassjava.CustomActionBar;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.sama.communicationclassjava.R;

public class CustomAction {
    private ActionBar actionBar;
    private Activity activity;


    public CustomAction(ActionBar actionBar, Activity activity) {
        this.actionBar = actionBar;
        this.activity = activity;
    }

    public void setActionBar(){
        this.actionBar.setDisplayShowCustomEnabled(true);
        this.actionBar.setDisplayHomeAsUpEnabled(false);
        this.actionBar.setDisplayShowTitleEnabled(false);
        this.actionBar.setDisplayShowHomeEnabled(false);



        View mCustomAction = LayoutInflater.from(activity).inflate(R.layout.custom_toolbarlayout,null);
        actionBar.setCustomView(mCustomAction);

        Toolbar Parent = (Toolbar) mCustomAction.getParent();
        Parent.setContentInsetsAbsolute(0,0);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT
                , ActionBar.LayoutParams.MATCH_PARENT);
        actionBar.setCustomView(mCustomAction,params);

    }


}
