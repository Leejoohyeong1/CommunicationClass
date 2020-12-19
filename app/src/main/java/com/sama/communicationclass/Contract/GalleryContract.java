package com.sama.communicationclass.Contract;

import com.sama.communicationclass.Data.GalleryDetailData;

public interface GalleryContract {
    interface  View{
        void changeContentsTypeChoiceActivity();
        void changeDetailActivity(GalleryDetailData detailData);
        void ItemLoadingAlertShow();
        void ItemLoadingAlertDisabled();
        void refreshStop();






    }

    interface Presenter{
        void setOptionAdapterModel(GalleryAdapterContract.Model Model);
        void setOptionAdapterView(GalleryAdapterContract.View View);
        void changeContentsTypeChoiceActivity();
        void attachView(View view);
        void detachView();
        void ContentsLoading();

        void refreshContentsLoading();
        void recyclerViewRefresh();

        void changeDetailActivity(GalleryDetailData item);



        void notfyAdapter();
    }
}
