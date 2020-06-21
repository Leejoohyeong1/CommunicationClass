package com.sama.communicationclassjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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
import com.sama.communicationclassjava.Adapter.GalleryDatailCommentKeyAdapter;
import com.sama.communicationclassjava.Adapter.GalleryDatailImagesAdapter;
import com.sama.communicationclassjava.Contract.GalleryDetailContract;
import com.sama.communicationclassjava.Data.GalleryDetailData;
import com.sama.communicationclassjava.Presenter.GalleryDetailPresenter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class CommunicationDetailActivity extends AppCompatActivity implements GalleryDetailContract.View,View.OnClickListener{

    private GalleryDetailContract.Presenter presenter;

    private RecyclerView imagesRecyclerView;
    private RecyclerView commentRecyclerView;
    private RecyclerView commentKeyRecyclerView;
    GalleryDetailData detailData;

    ConstraintLayout keyArea;

    ImageView keyPreView;
    ImageView commentCloseBnt;

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
        // 여러게 보이게 위해 놓음
        detailData.getImageUrl().addAll(detailData.getImageUrl());
        detailData.getImageUrl().addAll(detailData.getImageUrl());



        this.keyArea = (ConstraintLayout) findViewById(R.id.detail_comment_key_area);
        this.keyPreView = (ImageView) findViewById(R.id.comment_key_preview);
        this.commentCloseBnt = (ImageView) findViewById(R.id.comment_close_bnt);
        this.commentCloseBnt.setOnClickListener(this);


        presenter = new GalleryDetailPresenter();

        this.imagesRecyclerView = (RecyclerView) findViewById(R.id.detail_images);
        this.commentRecyclerView = (RecyclerView) findViewById(R.id.detail_comment);
        this.commentKeyRecyclerView = (RecyclerView) findViewById(R.id.detail_comment_keyboard);



        GalleryDatailImagesAdapter galleryDatailImagesAdapter = new GalleryDatailImagesAdapter();
        presenter.setDetailImagesAdapterModel(galleryDatailImagesAdapter);
        presenter.setDetailImagesAdapterView(galleryDatailImagesAdapter);
        presenter.setDetailImagesSrc(detailData.getImageUrl());

        int resId = getResources().getIdentifier("comment_key", "array", this.getPackageName());
        String[] keys = this.getResources().getStringArray(resId);

        for(String key : keys){
            Log.d("CommentKeyHolder",key);
        }

        GalleryDatailCommentKeyAdapter commentKeyAdapter = new GalleryDatailCommentKeyAdapter();
        presenter.setDetailCommentsKeyAdapterModel(commentKeyAdapter);
        presenter.setDetailCommentsKeyAdapterView(commentKeyAdapter);
        presenter.setDetailCommentsKey(new ArrayList<String>( Arrays.asList(keys)));




        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1  );
        imagesRecyclerView.setAdapter(galleryDatailImagesAdapter);
        imagesRecyclerView.setLayoutManager(gridLayoutManager);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        commentKeyRecyclerView.setAdapter(commentKeyAdapter);
        commentKeyRecyclerView.setLayoutManager(layoutManager);


        presenter.notfyImagesAdapter();
        presenter.notfyCommentsKeyAdapter();

    }

    @Override
    public void setKeyAreaVisible() {
        keyArea.setVisibility(View.VISIBLE);
    }

    @Override
    public void setKeyAreaGone() {
        keyArea.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.comment_close_bnt:
                //keyArea.getVisibility() 8 안보임 0 보임
                presenter.keyAreaViewActivation(keyArea.getVisibility() > 0 ? true:false);
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
        keyPreView.setBackground(drawable);
    }

    //    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);
//        this.detailData = (GalleryDetailData) savedInstanceState.getSerializable("DetailData");
//    }
//
//    @Override
//    protected void onSaveInstanceState(@NonNull Bundle outState) {
//        super.onSaveInstanceState(outState);
//        outState.putSerializable("DetailData",this.detailData);
//    }
}
