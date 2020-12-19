package com.sama.communicationclass.CustomActionBar;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.source.hls.HlsMediaSource;
import com.google.android.exoplayer2.ui.PlayerControlView;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.RawResourceDataSource;
import com.google.android.exoplayer2.util.Util;
import com.google.firebase.storage.StorageReference;
import com.sama.communicationclass.CustomEnum.ActionBarLayout;
import com.sama.communicationclass.Lisetner.OnToolbarActionListener;
import com.sama.communicationclass.Model.FireBaseImageLoaderModule;
import com.sama.communicationclass.R;
import com.sama.communicationclass.SinglePattern.SelectUserInfo;

public class CustomAction implements View.OnClickListener{

    private ActionBar actionBar;
    private Activity activity;
    private View actionLayout;
    private OnToolbarActionListener listener;
    private ActionBarLayout actionBarLayout;
    private SimpleExoPlayer player;
    private PlayerView pv;
    private PlayerControlView pcv;
    private PopupWindow popupWindow;

    public CustomAction(ActionBar actionBar, Activity activity) {
        this.actionBar = actionBar;
        this.activity = activity;
    }

    private void init() {
        this.actionBar.setDisplayShowCustomEnabled(true);
        this.actionBar.setDisplayHomeAsUpEnabled(false);
        this.actionBar.setDisplayShowTitleEnabled(false);
        this.actionBar.setDisplayShowHomeEnabled(false);
    }

    private void setToolbarLayout(View view) {
        actionBar.setCustomView(view);
        Toolbar Parent = (Toolbar) view.getParent();
        Parent.setContentInsetsAbsolute(0, 0);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(ActionBar.LayoutParams.MATCH_PARENT
                , ActionBar.LayoutParams.MATCH_PARENT);
        actionBar.setCustomView(view, params);
    }


    public CustomAction setActionBar(ActionBarLayout actionBar) {
        this.init();

        this.actionBarLayout = actionBar;
        this.actionLayout = LayoutInflater.from(activity.getApplicationContext()).inflate(actionBar.getLayout(), null);
        if(actionBar == ActionBarLayout.galleryLayout){
            this.tooBarGalleryInit(actionLayout);
        }

        this.setToolbarLayout(actionLayout);
        this.setChoiceEvent(this.actionLayout);
        return this;
    }


    private void tooBarGalleryInit(View view){
        TextView textView =  view.findViewById(R.id.actionbar_name);
        ImageView childProfileImage =  view.findViewById(R.id.profile_image);

        final StorageReference storageRef = FireBaseImageLoaderModule.getInstance().getImageLoader(SelectUserInfo.getInstance().getProfileImage());

        Glide.with(view.getContext())
                .load(storageRef)
                .into(childProfileImage);

        textView.setText(SelectUserInfo.getInstance().getProfileName());
    }

    private void setChoiceEvent(View view){
        switch (this.actionBarLayout){
            case choiceLayout :
                view.findViewById(R.id.toolbar_choice_left_icon).setOnClickListener(this);
                view.findViewById(R.id.toolbar_choice_right_icon).setOnClickListener(this);
                break;
            case nanuliLayout :
                view.findViewById(R.id.toolbar_nanuli_right_icon).setOnClickListener(this);
                view.findViewById(R.id.toolbar_nanuli_left_icon).setOnClickListener(this);
                break;
            case contentsTypeLayout:
                view.findViewById(R.id.toolbar_contents_left_icon).setOnClickListener(this);
                break;
            case galleryDetailLayout:
                view.findViewById(R.id.toolbar_detail_right_icon).setOnClickListener(this);
                view.findViewById(R.id.toolbar_detail_left_icon).setOnClickListener(this);
                break;
            case galleryLayout:
                view.findViewById(R.id.toolbar_gallery_left_icon).setOnClickListener(this);
                break;
        }
    }

    public void setNoneActionBar(){
        this.actionBar.hide();
    }

    public CustomAction addToolbarListener(OnToolbarActionListener listener){
        this.listener = listener;
        return this;
    }

    private void guideVideoPlay() {

        if(player != null && player.isPlaying()){
            exoPlayStop();
            CustomAction.this.popupWindow.dismiss();
        }


        LayoutInflater inflater = (LayoutInflater)
                activity.getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.guide_play_layout, null);


        pv= popupView.findViewById(R.id.pv);
        pv.setVisibility(View.VISIBLE);
        pcv=popupView.findViewById(R.id.pcv);

        player= new SimpleExoPlayer.Builder(popupView.getContext()).build();
        pcv.setPlayer(player);

        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(popupView.getContext(),
                Util.getUserAgent(popupView.getContext(), activity.getString(R.string.app_name)));

        //비디오데이터를 Uri로 부터 추출해서 DataSource객체 (CD or LP판 같은 ) 생성
        ProgressiveMediaSource mediaSource= new ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(RawResourceDataSource.buildRawResourceUri(actionBarLayout.getGuideVideo()));

//        HlsMediaSource videoSource = new HlsMediaSource.Factory(dataSourceFactory)
//                .createMediaSource(RawResourceDataSource.buildRawResourceUri(actionBarLayout.getGuideVideo()));

        //만들어진 비디오데이터 소스객체인 mediaSource를
        //플레이어 객체에게 전당하여 준비하도록!![ 로딩하도록 !!]

        pv.setPlayer(player);

        pcv.setPlayer(player);

        player.prepare(mediaSource);
        player.setPlayWhenReady(true);
        pcv.hide();




        popupWindow = new PopupWindow(popupView, LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout .LayoutParams.WRAP_CONTENT,true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setFocusable(false);

        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_OUTSIDE) { // popupview영역 외의 바깥부분을 터치할 시
                    pv.setPlayer(null);
                    player.release();
                    player=null;
                    CustomAction.this.popupWindow.dismiss();
                    return true;
                }
                return false;
            }
        });
        popupWindow.showAtLocation(activity.getWindow().getDecorView(), Gravity.CENTER, 0, 0);

    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.toolbar_nanuli_right_icon:
            case R.id.toolbar_choice_right_icon:
            case R.id.toolbar_detail_right_icon :
                if(this.listener == null){
                    return;
                }
                listener.rightButtonAction();
                break;
            case R.id.toolbar_detail_left_icon :
            case R.id.toolbar_choice_left_icon:
            case R.id.toolbar_nanuli_left_icon:
            case R.id.toolbar_contents_left_icon:
            case R.id.toolbar_gallery_left_icon:
                guideVideoPlay();

                break;
        }

    }
    public void exoPlayStop(){
        if(!player.isPlaying()){
            pv.setPlayer(null);
            player.release();
            player=null;
        }
    }

    public void deleteGalleryBtn(Boolean aBoolean){
        actionLayout.findViewById(R.id.toolbar_detail_right_icon).setVisibility(aBoolean ? View.VISIBLE:View.INVISIBLE);
    }
}
