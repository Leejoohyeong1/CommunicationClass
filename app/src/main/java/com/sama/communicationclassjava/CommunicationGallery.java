package com.sama.communicationclassjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.sama.communicationclassjava.Adapter.GalleryAdapter;
import com.sama.communicationclassjava.Contract.GalleryContract;
import com.sama.communicationclassjava.Data.CommunicationItem;
import com.sama.communicationclassjava.Lisetner.OnItemClickListener;
import com.sama.communicationclassjava.Presenter.GalleryPresenter;

public class CommunicationGallery extends AppCompatActivity
        implements GalleryContract.View, View.OnClickListener, OnItemClickListener {

    private GalleryContract.Presenter presenter;
    private GalleryAdapter galleryAdapter;
    private RecyclerView SubjectRecyclerView;
    private View GalleryaddButten;



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


        LinearLayoutManager manager = new GridLayoutManager(this, 2  );

        this.SubjectRecyclerView.setLayoutManager(manager);
        SubjectRecyclerView.setAdapter(galleryAdapter);



        SubjectRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!SubjectRecyclerView.canScrollVertically(1)) {
                    presenter.ContentsLoading();
                }
            }
        });

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
    public void onItemClickListener(int position, CommunicationItem item) {
        Intent myIntent = new Intent(this, CommunicationDetailActivity.class);
        startActivity(myIntent);
    }
}
