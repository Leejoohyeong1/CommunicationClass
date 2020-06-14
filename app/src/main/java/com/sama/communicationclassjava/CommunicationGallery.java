package com.sama.communicationclassjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.sama.communicationclassjava.Adapter.GalleryAdapter;
import com.sama.communicationclassjava.Contract.GalleryContract;
import com.sama.communicationclassjava.Data.CommunicationItem;
import com.sama.communicationclassjava.Data.GalleryDatilData;
import com.sama.communicationclassjava.Lisetner.OnItemClickListener;
import com.sama.communicationclassjava.Presenter.GalleryPresenter;

public class CommunicationGallery extends AppCompatActivity
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
                Log.d("CommunicationGallery","addOnScrollListener");
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

        this.mProgressDialog = new ProgressDialog(CommunicationGallery.this);
        this.mProgressDialog.setProgress(ProgressDialog.STYLE_SPINNER);
        this.mProgressDialog.setProgressStyle(android.R.style.Widget_ProgressBar_Horizontal);
        this.mProgressDialog.setMessage("로딩중");
        this.mProgressDialog.setCancelable(false);
        presenter.ContentsLoading();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.presenter.detachView();

    }

    @Override
    public void TestView() {

    }

    @Override
    public void Histortview() {

    }

    @Override
    public void DetailActivity(CommunicationItem item) {
        Toast.makeText(this, item.toString(), Toast.LENGTH_SHORT).show();
//        Intent myIntent = new Intent(this, CommunicationDetailActivity.class);
//        startActivity(myIntent);
    }

    @Override
    public void onClick(View v) {
        Intent myIntent;
        switch (v.getId()){
            case R.id.Gallery_add_layout :
                myIntent = new Intent(this, ContentsTypeChoiceActivity.class);
                startActivity(myIntent);
                break;
            case R.id.Gallery_add_icon:

                break;
            default:

                break;
        }


    }

    @Override
    public void onItemClickListener(int position, GalleryDatilData item) {
//        Intent myIntent = new Intent(this, CommunicationDetailActivity.class);
//        startActivity(myIntent);
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
