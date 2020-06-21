package com.sama.communicationclassjava.Presenter;


import com.sama.communicationclassjava.Contract.GalleryContract;
import com.sama.communicationclassjava.Contract.GalleryAdapterContract;
import com.sama.communicationclassjava.Data.CommunicationItem;
import com.sama.communicationclassjava.Data.GalleryDetailData;
import com.sama.communicationclassjava.Lisetner.OnGallerySelectItemListListener;
import com.sama.communicationclassjava.Model.FireBaseModel;

import java.util.ArrayList;
import java.util.Date;

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
        this.adapterView.notfyAdaoter();
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
        view.ItemloadingAlertShow();
        Long key = null;
        if(adapterModel.LastItem() != null){
            key = adapterModel.LastItem().getWriteDay();
        }
        fireBaseModel.SelectGallerycontents(key);
    }

    @Override
    public void OnGallerySelectItemList(ArrayList<GalleryDetailData> list) {
        view.ItemloadingAlertDisabled();
        this.adapterModel.addItems(list);
        this.notfyAdapter();
    }
}

