package com.sama.communicationclass;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;


import com.sama.communicationclass.Adapter.GalleryAdapter;
import com.sama.communicationclass.Contract.GalleryContract;
import com.sama.communicationclass.CustomActionBar.CustomAction;
import com.sama.communicationclass.CustomEnum.ActionBarLayout;
import com.sama.communicationclass.Data.GalleryDetailData;
import com.sama.communicationclass.Data.NanuliCard;
import com.sama.communicationclass.Lisetner.OnItemClickListener;
import com.sama.communicationclass.Presenter.GalleryPresenter;

import java.util.ArrayList;

public class CommunicationGalleryActivity extends AppCompatActivity
        implements GalleryContract.View, View.OnClickListener, OnItemClickListener , SwipeRefreshLayout.OnRefreshListener{

    private GalleryContract.Presenter presenter;
    private GalleryAdapter galleryAdapter;
    private RecyclerView SubjectRecyclerView;
    private View GalleryAddButton;
    private ProgressDialog mProgressDialog;
    private LinearLayoutManager manager;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onStart() {
        super.onStart();
        if(SubjectRecyclerView != null){

        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communication_gallery);

        new CustomAction(getSupportActionBar(), this).setActionBar(ActionBarLayout.galleryLayout);



        GalleryAddButton = findViewById(R.id.Gallery_add_layout);
        SubjectRecyclerView = (RecyclerView) findViewById(R.id.CommunicationGalleryRecyclerView);

        GalleryAddButton.setOnClickListener(this);

        this.galleryAdapter = new GalleryAdapter();
        this.presenter = new GalleryPresenter();
        this.presenter.attachView(this);
        this.presenter.setOptionAdapterModel(galleryAdapter);
        this.presenter.setOptionAdapterView(galleryAdapter);
        this.galleryAdapter.setOnClickListener(this);


        this.manager = new GridLayoutManager(this, 2  );
        this.SubjectRecyclerView.setLayoutManager(manager);
        SubjectRecyclerView.setAdapter(galleryAdapter);

        SubjectRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int lastPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
                int totalCount = recyclerView.getAdapter().getItemCount()-1;
                if(lastPosition == totalCount){
                    //아이템 추가 ! 입맛에 맞게 설정하시면됩니다.
                    if(!mProgressDialog.isShowing()){
                        presenter.ContentsLoading();
                    }

                }
            }
        });

        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.Gallery_swipe_refresh);
        swipeRefreshLayout.setOnRefreshListener(this);

        this.mProgressDialog = new ProgressDialog(CommunicationGalleryActivity.this);
        this.mProgressDialog.setProgress(ProgressDialog.STYLE_SPINNER);
        this.mProgressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Horizontal);
        this.mProgressDialog.setMessage("로딩중");
        this.mProgressDialog.setCancelable(false);
        this.presenter.ContentsLoading();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.presenter.detachView();

    }

    @Override
    public void changeContentsTypeChoiceActivity() {
        Intent  Intent = new Intent(this, ContentsTypeChoiceActivity.class);
        startActivity(Intent);
    }

    @Override
    public void changeDetailActivity(GalleryDetailData detailData) {
        Intent  Intent = new Intent(this, CommunicationDetailActivity.class);
        startActivity(Intent);
    }

    @Override
    public void onRefresh() {
        presenter.refreshContentsLoading();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.Gallery_add_layout :
                presenter.changeContentsTypeChoiceActivity();
                break;
        }
    }


    @Override
    public void refreshStop() {
        swipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onItemClickListener(int position, GalleryDetailData item) {
        Intent intent = new Intent(this, CommunicationDetailActivity.class);
        intent.putExtra("DetailData", item);
        startActivityForResult(intent,1);
    }

    @Override
    public void ItemLoadingAlertShow() {
        this.mProgressDialog.show();
    }

    @Override
    public void ItemLoadingAlertDisabled() {
        this.mProgressDialog.dismiss();
    }



        @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d("DeleteModel","onActivityResult");
        if (data != null && resultCode == Activity.RESULT_OK) {
            Log.d("DeleteModel","onActivityResult 1");
            int flag = data.getIntExtra("job",0);
            Log.d("DeleteModel flag",String.valueOf(flag));

            presenter.recyclerViewRefresh();

        }
    }
}
