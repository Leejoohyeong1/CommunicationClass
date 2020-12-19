package com.sama.communicationclass.Presenter;


import android.util.Log;

import com.sama.communicationclass.Contract.GalleryContract;
import com.sama.communicationclass.Contract.GalleryAdapterContract;
import com.sama.communicationclass.Data.GalleryDetailData;
import com.sama.communicationclass.Lisetner.OnGallerySelectItemListListener;
import com.sama.communicationclass.Model.FireBaseModel;

import java.util.ArrayList;

public class GalleryPresenter implements GalleryContract.Presenter ,OnGallerySelectItemListListener{

    GalleryContract.View view;
    GalleryAdapterContract.Model adapterModel;
    GalleryAdapterContract.View adapterView;
    FireBaseModel fireBaseModel = FireBaseModel.getInstance();


    public GalleryPresenter() {
        super();
        fireBaseModel.setOnSelectGalleryListener(this);
    }

    @Override
    public void attachView(GalleryContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }



    @Override
    public void changeDetailActivity(GalleryDetailData item) {
        this.view.changeDetailActivity(item);
    }



    @Override
    public void notfyAdapter() {
        this.adapterView.notfyAdapter();
    }

    @Override
    public void setOptionAdapterModel(GalleryAdapterContract.Model Model) {
        this.adapterModel = Model;
    }

    @Override
    public void setOptionAdapterView(GalleryAdapterContract.View View) {
        this.adapterView = View;
    }

    @Override
    public void changeContentsTypeChoiceActivity() {
        this.view.changeContentsTypeChoiceActivity();
    }


    @Override
    public void ContentsLoading() {
        Log.d("DeleteModel","ContentsLoading");
        view.ItemLoadingAlertShow();
        Long key = null;
        if(adapterModel.LastItem() != null){
            key = adapterModel.LastItem().getWriteDay();
        }
        fireBaseModel.SelectGalleryContents(key);
    }

    @Override
    public void refreshContentsLoading() {
        Log.d("DeleteModel","refreshContentsLoading");
        this.adapterModel.clearItems();
        fireBaseModel.SelectGalleryContents(null);
        view.refreshStop();

    }

    @Override
    public void OnGallerySelectItemList(ArrayList<GalleryDetailData> list) {
        Log.d("DeleteModel","OnGallerySelectItemList");
        view.ItemLoadingAlertDisabled();
        this.adapterModel.addItems(list);
        this.notfyAdapter();
    }

    @Override
    public void recyclerViewRefresh() {
        this.adapterModel.clearItems();
        Log.d("DeleteModel","recyclerViewRefresh");
        this.notfyAdapter();
        fireBaseModel.SelectGalleryContents(null);

    }
}

