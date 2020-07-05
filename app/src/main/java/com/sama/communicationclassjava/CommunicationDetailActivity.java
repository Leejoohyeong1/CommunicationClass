package com.sama.communicationclassjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.sama.communicationclassjava.Adapter.GalleryDatailCommentAdapter;
import com.sama.communicationclassjava.Adapter.GalleryDatailCommentKeyAdapter;
import com.sama.communicationclassjava.Adapter.GalleryDatailImagesAdapter;
import com.sama.communicationclassjava.Contract.GalleryDetailContract;
import com.sama.communicationclassjava.Data.CommentData;
import com.sama.communicationclassjava.Data.GalleryDetailData;
import com.sama.communicationclassjava.Lisetner.OnCommentDeletePressListener;
import com.sama.communicationclassjava.Lisetner.OnImageKeyboardListener;
import com.sama.communicationclassjava.MassageEnum.Massage;
import com.sama.communicationclassjava.Presenter.GalleryDetailPresenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CommunicationDetailActivity extends AppCompatActivity implements GalleryDetailContract.View
                                                                            , View.OnClickListener
                                                                            , OnImageKeyboardListener
                                                                            , OnCommentDeletePressListener {

    private GalleryDetailContract.Presenter presenter;

    private RecyclerView imagesRecyclerView;
    private RecyclerView commentRecyclerView;
    private RecyclerView commentKeyRecyclerView;

    RecyclerView.SmoothScroller smoothScroller;
    GalleryDetailData detailData;

    ConstraintLayout keyArea;

    ImageView keyPreView;
    ImageView commentCloseBnt;
    private ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communication_detail);

        if(savedInstanceState != null) {
           this.detailData = (GalleryDetailData) savedInstanceState.getSerializable("DetailData");

//            Toast.makeText(this, detailData.toString(), Toast.LENGTH_SHORT).show();
        }else{
            Intent intent = getIntent();
            this.detailData = (GalleryDetailData) intent.getSerializableExtra("DetailData");
        }

        presenter = new GalleryDetailPresenter(this.detailData.getDocumentKey());
        presenter.attachView(this);

        this.keyArea = (ConstraintLayout) findViewById(R.id.detail_comment_key_area);
        this.keyPreView = (ImageView) findViewById(R.id.comment_key_preview);
        this.commentCloseBnt = (ImageView) findViewById(R.id.comment_close_bnt);
        this.commentCloseBnt.setOnClickListener(this);



        this.imagesRecyclerView = (RecyclerView) findViewById(R.id.detail_images);
        this.commentRecyclerView = (RecyclerView) findViewById(R.id.detail_comment);
        this.commentKeyRecyclerView = (RecyclerView) findViewById(R.id.detail_comment_keyboard);


        // 콘텐츠 영역
        GalleryDatailImagesAdapter galleryDatailImagesAdapter = new GalleryDatailImagesAdapter();
        presenter.setDetailImagesAdapterModel(galleryDatailImagesAdapter);
        presenter.setDetailImagesAdapterView(galleryDatailImagesAdapter);
        presenter.setDetailImagesSrc(detailData.getImageUrl());

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1  );
        imagesRecyclerView.setAdapter(galleryDatailImagesAdapter);
        imagesRecyclerView.setLayoutManager(gridLayoutManager);

        //
        GalleryDatailCommentAdapter galleryDatailCommentAdapter = new GalleryDatailCommentAdapter();
        presenter.setDetailCommentsAdapterModel(galleryDatailCommentAdapter);
        presenter.setDetailCommentsAdapterView(galleryDatailCommentAdapter);
        presenter.setOnCommentDeletePressListener(this);
        LinearLayoutManager commentManager = new LinearLayoutManager(this);
        this.commentRecyclerView.setLayoutManager(commentManager);
        this.commentRecyclerView.setAdapter(galleryDatailCommentAdapter);


        // 키입력
        int resId = getResources().getIdentifier("comment_key", "array", this.getPackageName());
        String[] keys = this.getResources().getStringArray(resId);


        GalleryDatailCommentKeyAdapter commentKeyAdapter = new GalleryDatailCommentKeyAdapter();
        presenter.setDetailCommentsKeyAdapterModel(commentKeyAdapter);
        presenter.setDetailCommentsKeyAdapterView(commentKeyAdapter);
        presenter.setOnImageKeyboardListener(this);
        presenter.setDetailCommentsKey(new ArrayList<String>( Arrays.asList(keys)));

        LinearLayoutManager commentKeyManager = new LinearLayoutManager(this);
        commentKeyManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        commentKeyRecyclerView.setAdapter(commentKeyAdapter);
        commentKeyRecyclerView.setLayoutManager(commentKeyManager);

        presenter.notfyImagesAdapter();
        presenter.notfyCommentsKeyAdapter();

        this.mProgressDialog = new ProgressDialog(this);
        this.mProgressDialog.setProgress(ProgressDialog.STYLE_SPINNER);
        this.mProgressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Horizontal);
        this.mProgressDialog.setMessage("로딩중");
        this.mProgressDialog.setCancelable(false);
    }

    @Override
    public void setKeyAreaVisible() {
        if (keyArea.getVisibility() != View.VISIBLE)
            keyArea.setVisibility(View.VISIBLE);
    }

    @Override
    public void setKeyAreaGone() {
        if (keyArea.getVisibility() != View.GONE)
            keyArea.setVisibility(View.GONE);
    }

    @Override
    public void OnDeletePress(CommentData commentData, int position) {
        this.presenter.deleteComment(commentData,position);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.comment_close_bnt:
                presenter.keyAreaViewActivation(false);
                break;
        }
    }

    @Override
    public void setKeyPreView(String fileName) {

        int id = getResources().getIdentifier(fileName, "drawable", getPackageName());
        Drawable drawable;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            drawable = getResources().getDrawable(id,null);
        } else {
            drawable = getResources().getDrawable(id);
        }

        if (keyPreView.getDrawable() == drawable){
            return;
        }
        keyPreView.setBackground(drawable);
    }

    @Override
    public void setLookComment(int position) {
        commentRecyclerView.smoothScrollToPosition(position);
    }

    @Override
    public void OnKeyPress(String imageKey, int position) {
        presenter.keyAreaViewActivation(true);
        this.presenter.setKeyPreView(imageKey,position);
    }

    @Override
    public void OnKeyDoublePress(String imageKey, int position) {
        presenter.keyAreaViewActivation(false);
        presenter.insertComment(imageKey);
    }

    @Override
    public void ToastMassage(Massage massage) {
        Toast.makeText(this, massage.name(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDisplayProgressDialog() {
        this.mProgressDialog.show();
    }

    @Override
    public void noneDisplayProgressDialog() {
        this.mProgressDialog.dismiss();
    }
}
