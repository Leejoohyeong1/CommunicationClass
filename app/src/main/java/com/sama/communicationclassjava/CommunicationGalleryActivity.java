package com.sama.communicationclassjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.sama.communicationclassjava.Adapter.GalleryAdapter;
import com.sama.communicationclassjava.Contract.GalleryContract;
import com.sama.communicationclassjava.CustomActionBar.CustomAction;
import com.sama.communicationclassjava.Data.GalleryDetailData;
import com.sama.communicationclassjava.Lisetner.OnItemClickListener;
import com.sama.communicationclassjava.Presenter.GalleryPresenter;

public class CommunicationGalleryActivity extends AppCompatActivity
        implements GalleryContract.View, View.OnClickListener, OnItemClickListener {

    private GalleryContract.Presenter presenter;
    private GalleryAdapter galleryAdapter;
    private RecyclerView SubjectRecyclerView;
    private View GalleryaddButten;
    private ProgressDialog mProgressDialog;
    private LinearLayoutManager manager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communication_gallery);

        CustomAction customAction = new CustomAction(getSupportActionBar(), this);
        customAction.setActionBar();
        getSupportActionBar().setCustomView(R.layout.custom_toolbarlayout);

        GalleryaddButten = findViewById(R.id.Gallery_add_layout);
        SubjectRecyclerView = (RecyclerView) findViewById(R.id.CommunicationGalleryRecyclerView);

        GalleryaddButten.setOnClickListener(this);

        this.galleryAdapter = new GalleryAdapter();
        this.presenter = new GalleryPresenter();
        this.presenter.attachView(this);
        this.presenter.setOptionAdapterModel(galleryAdapter);
        this.presenter.setOptionAdapterView(galleryAdapter);
        this.galleryAdapter.setOnClickLisetner(this);


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
    public void changeDetailActivity(GalleryDetailData datilData) {
        Intent  Intent = new Intent(this, CommunicationDetailActivity.class);
        startActivity(Intent);
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
    public void onItemClickListener(int position, GalleryDetailData item) {
        Intent intent = new Intent(this, CommunicationDetailActivity.class);
        intent.putExtra("DetailData", item);
        startActivity(intent);
    }

    @Override
    public void ItemloadingAlertShow() {
        this.mProgressDialog.show();
    }

    @Override
    public void ItemloadingAlertDisabled() {
        this.mProgressDialog.dismiss();
    }

}
