package com.sama.communicationclassjava.Contract;


import java.util.ArrayList;

public interface GalleryDetailContract {
    interface  View{
        void setKeyAreaVisible();
        void setKeyAreaGone();
        void setKeyPreView(String fileName);
    }

    interface Presenter{
        void setDetailImagesAdapterModel(GalleryDetailImageAdpterContract.Model Model);
        void setDetailImagesAdapterView(GalleryDetailImageAdpterContract.View View);

        void setDetailCommentsAdapterModel(GalleryDetailCommentAdpterContract.Model Model);
        void setDetailCommentsAdapterView(GalleryDetailCommentAdpterContract.View View);

        void setDetailCommentsKeyAdapterModel(GalleryDetailCommentKeyAdpterContract.Model Model);
        void setDetailCommentsKeyAdapterView(GalleryDetailCommentKeyAdpterContract.View View);


        void setDetailImagesSrc(ArrayList<String> srcs);
        void setDetailCommentsKey(ArrayList<String> keys);


        void keyAreaViewActivation(boolean flag);




        void attachView(GalleryDetailContract.View view);
        void detachView();
        void notfyImagesAdapter();
        void notfyCommentsAdapter();
        void notfyCommentsKeyAdapter();
    }
}
