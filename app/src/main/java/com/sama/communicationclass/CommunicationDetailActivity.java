package com.sama.communicationclass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.sama.communicationclass.Adapter.GalleryDetailCommentAdapter;
import com.sama.communicationclass.Adapter.GalleryDetailCommentKeyAdapter;
import com.sama.communicationclass.Adapter.GalleryDetailImagesAdapter;
import com.sama.communicationclass.Adapter.NanuliCardEditAdapter;
import com.sama.communicationclass.Contract.GalleryDetailContract;
import com.sama.communicationclass.CustomActionBar.CustomAction;
import com.sama.communicationclass.CustomEnum.ActionBarLayout;
import com.sama.communicationclass.Data.CommentData;
import com.sama.communicationclass.Data.GalleryDetailData;
import com.sama.communicationclass.Data.NanuliCard;
import com.sama.communicationclass.Lisetner.OnCommentDeletePressListener;
import com.sama.communicationclass.Lisetner.OnImageKeyboardListener;
import com.sama.communicationclass.CustomEnum.ResultMassage;
import com.sama.communicationclass.Lisetner.OnNanuliCardClickListener;
import com.sama.communicationclass.Lisetner.OnToolbarActionListener;
import com.sama.communicationclass.Presenter.GalleryDetailPresenter;
import com.sama.communicationclass.SinglePattern.SelectUserInfo;

import java.util.ArrayList;
import java.util.Arrays;

public class CommunicationDetailActivity extends AppCompatActivity implements GalleryDetailContract.View
                                                                            , View.OnClickListener
                                                                            , OnImageKeyboardListener
                                                                            , OnCommentDeletePressListener
                                                                            , OnToolbarActionListener
                                                                            , OnNanuliCardClickListener {

    private GalleryDetailContract.Presenter presenter;

    private RecyclerView detailContentsRecyclerView;
    private RecyclerView commentRecyclerView;
    private RecyclerView commentKeyRecyclerView;

    MediaPlayer mPlayer;
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
        }else{
            Intent intent = getIntent();
            this.detailData = (GalleryDetailData) intent.getSerializableExtra("DetailData");
        }
        new CustomAction(getSupportActionBar(), this)
                .setActionBar(ActionBarLayout.galleryDetailLayout)
                .addToolbarListener(this).deleteGalleryBtn((this.detailData.getWriteUserKey().equals(SelectUserInfo.getInstance().getUserKey())) ? true:false);

        presenter = new GalleryDetailPresenter(this.detailData.getDocumentKey());
        presenter.attachView(this);

        this.keyArea = (ConstraintLayout) findViewById(R.id.detail_comment_key_area);
        this.keyPreView = (ImageView) findViewById(R.id.comment_key_preview);
        this.commentCloseBnt = (ImageView) findViewById(R.id.comment_close_bnt);
        this.commentCloseBnt.setOnClickListener(this);


        this.detailContentsRecyclerView = (RecyclerView) findViewById(R.id.detail_contents);



        this.commentRecyclerView = (RecyclerView) findViewById(R.id.detail_comment);
        this.commentKeyRecyclerView = (RecyclerView) findViewById(R.id.detail_comment_keyboard);

        if(this.detailData.getDecks() != null && this.detailData.getDecks().size() > 0){
            // 콘텐츠 영역
            NanuliCardEditAdapter nanuliCardEditAdapter = new NanuliCardEditAdapter();
            nanuliCardEditAdapter.addNanuliCardListener(this);
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
            this.detailContentsRecyclerView.setLayoutManager(gridLayoutManager);
            this.detailContentsRecyclerView.setAdapter(nanuliCardEditAdapter);
            this.presenter.setCardEditAdapterModel(nanuliCardEditAdapter);
            this.presenter.setCardEditAdapterView(nanuliCardEditAdapter);
            this.presenter.setCardEditAdapterData(this.detailData.getDecks());
            presenter.notfyCardEdit();
        }else{
            GalleryDetailImagesAdapter galleryDetailImagesAdapter = new GalleryDetailImagesAdapter();
            presenter.setDetailImagesAdapterModel(galleryDetailImagesAdapter);
            presenter.setDetailImagesAdapterView(galleryDetailImagesAdapter);
            presenter.setDetailImagesSrc(detailData.getImageUrl());

            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1  );
            detailContentsRecyclerView.setAdapter(galleryDetailImagesAdapter);
            detailContentsRecyclerView.setLayoutManager(gridLayoutManager);
            presenter.notfyImagesAdapter();
        }


        //
        GalleryDetailCommentAdapter galleryDetailCommentAdapter = new GalleryDetailCommentAdapter();
        presenter.setDetailCommentsAdapterModel(galleryDetailCommentAdapter);
        presenter.setDetailCommentsAdapterView(galleryDetailCommentAdapter);
        presenter.setOnCommentDeletePressListener(this);
        LinearLayoutManager commentManager = new LinearLayoutManager(this);
        this.commentRecyclerView.setLayoutManager(commentManager);
        this.commentRecyclerView.setAdapter(galleryDetailCommentAdapter);


        // 키입력
        int resId = getResources().getIdentifier("comment_key", "array", this.getPackageName());
        String[] keys = this.getResources().getStringArray(resId);


        GalleryDetailCommentKeyAdapter commentKeyAdapter = new GalleryDetailCommentKeyAdapter();
        presenter.setDetailCommentsKeyAdapterModel(commentKeyAdapter);
        presenter.setDetailCommentsKeyAdapterView(commentKeyAdapter);
        presenter.setOnImageKeyboardListener(this);
        presenter.setDetailCommentsKey(new ArrayList<String>( Arrays.asList(keys)));

        LinearLayoutManager commentKeyManager = new LinearLayoutManager(this);
        commentKeyManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        commentKeyRecyclerView.setAdapter(commentKeyAdapter);
        commentKeyRecyclerView.setLayoutManager(commentKeyManager);


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
    public void ToastMassage(ResultMassage massage) {
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


    @Override
    public void rightButtonAction() {
        presenter.deleteGallery();

    }

    @Override
    public void backPressedGoGallery() {
        Intent intent = new Intent(this,CommunicationGalleryActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        intent.putExtra("job", 0);
        startActivityForResult(intent, Activity.RESULT_OK);
        finish();
    }

    @Override
    public void finishGoGallery() {
        Log.d("DeleteModel","finishGoGallery");
        Intent returnIntent = new Intent();
        returnIntent.putExtra("job", 1);
        returnIntent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }

    @Override
    public void OnNanuliCardClick(NanuliCard nanuliCard, boolean EditMode, int position) {
        mPlayer = MediaPlayer.create(this, nanuliCard.getVoice());
        mPlayer.start();
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        presenter.backPressed();
    }
}
