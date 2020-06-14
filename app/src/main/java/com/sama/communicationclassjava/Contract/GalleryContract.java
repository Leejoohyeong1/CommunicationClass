package com.sama.communicationclassjava.Contract;

import com.sama.communicationclassjava.Data.CommunicationItem;

public interface GalleryContract {
    interface  View{
        void TestView();
        void Histortview();
        void DetailActivity(CommunicationItem item);
        void ItemloadingAlertShow();
        void ItemloadingAlertDisabled();

    }

    interface Presenter{
        void setOptionAdapterModel(GalleryAdapterContract.Model Model);
        void setOptionAdapterView(GalleryAdapterContract.View View);
        void attachView(View view);
        void detachView();
        void ContentsLoading();
        void DetailActivity(CommunicationItem item);
        void notfyAdaoter();
    }
}
