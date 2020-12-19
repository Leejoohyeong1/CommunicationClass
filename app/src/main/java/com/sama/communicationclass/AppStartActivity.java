package com.sama.communicationclass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.sama.communicationclass.CustomActionBar.CustomAction;

import java.io.IOException;

public class AppStartActivity extends AppCompatActivity implements View.OnClickListener {

    MediaPlayer mediaPlayer;
    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appstart);
        mediaPlayer = new MediaPlayer();
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        CustomAction customAction = new CustomAction(getSupportActionBar(), this);
        customAction.setNoneActionBar();
        findViewById(R.id.start_view).setOnClickListener(this);

        playSound();

    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this,ChildChoiceActivity.class);
        startActivity(intent);
    }

    public void playSound(){

        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/raw/open");



        try {
            mediaPlayer.setDataSource(getApplicationContext(), uri);
            mediaPlayer.prepare();
            mediaPlayer.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
