package com.sama.communicationclassjava.Contract;

import com.sama.communicationclassjava.Data.GalleryDetailData;

import java.util.ArrayList;

public interface GalleryContract {
    interface  View{
        void changeContentsTypeChoiceActivity();
        void changeDetailActivity(GalleryDetailData datilData);
        void ItemloadingAlertShow();
        void ItemloadingAlertDisabled();

    }

    interface Presenter{
        void setOptionAdapterModel(GalleryAdapterContract.Model Model);
        void setOptionAdapterView(GalleryAdapterContract.View View);
        void changeContentsTypeChoiceActivity();
        void attachView(View view);
        void detachView();
        void ContentsLoading();
        void changeDetailActivity(GalleryDetailData item);
        void notfyAdapter();
    }
}
