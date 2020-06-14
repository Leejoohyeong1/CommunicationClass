package com.sama.communicationclassjava.Presenter;


import com.sama.communicationclassjava.Contract.GalleryContract;
import com.sama.communicationclassjava.Contract.GalleryAdapterContract;
import com.sama.communicationclassjava.Data.CommunicationItem;
import com.sama.communicationclassjava.Data.GalleryDatilData;
import com.sama.communicationclassjava.Lisetner.OnGallerySelectItemListListener;
import com.sama.communicationclassjava.Model.FireBaseModel;

import java.util.ArrayList;
import java.util.Date;

public class GalleryPresenter implements GalleryContract.Presenter ,OnGallerySelectItemListListener{

    GalleryContract.View view;
    ArrayList<CommunicationItem> communicationItemArrayList;
    GalleryAdapterContract.Model adapterModel;
    GalleryAdapterContract.View adapterView;
    FireBaseModel fireBaseModel = FireBaseModel.getInstance();
    public GalleryPresenter() {
        super();
        fireBaseModel.setSelectItemListListener(this);

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
    public void notfyAdaoter() {
        this.adapterView.notfyAdaoter();
    }

    @Override
    public void DetailActivity(CommunicationItem item) {

    }

    @Override
    public void setOptionAdapterModel(GalleryAdapterContract.Model Model) {
        this.adapterModel = Model;
//        this.communicationItemArrayList = new ArrayList<>();
//        this.communicationItemArrayList.add(new CommunicationItem("1","1","1","1"));
//        this.communicationItemArrayList.add(new CommunicationItem("2","2","2","2"));
//        this.communicationItemArrayList.add(new CommunicationItem("3","3","3","3"));
//        this.adapterModel.addItems(this.communicationItemArrayList);
    }

    @Override
    public void setOptionAdapterView(GalleryAdapterContract.View View) {
        this.adapterView = View;
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
    public void OnGallerySelectItemList(ArrayList<GalleryDatilData> list) {
        view.ItemloadingAlertDisabled();
        this.adapterModel.addItems(list);
        this.notfyAdaoter();
    }
}

