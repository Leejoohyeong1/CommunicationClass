package com.sama.communicationclassjava.Contract;


import com.sama.communicationclassjava.Data.CommentData;
import com.sama.communicationclassjava.Lisetner.OnCommentDeletePressListener;
import com.sama.communicationclassjava.Lisetner.OnImageKeyboardListener;
import com.sama.communicationclassjava.MassageEnum.Massage;

import java.util.ArrayList;

public interface GalleryDetailContract {
    interface  View{
        void setKeyAreaVisible();
        void setKeyAreaGone();
        void setKeyPreView(String fileName);
        void setLookComment(int position);
        void ToastMassage(Massage massage);
        void showDisplayProgressDialog();
        void noneDisplayProgressDialog();


    }

    interface Presenter{
        void setDetailImagesAdapterModel(GalleryDetailImageAdpterContract.Model Model);
        void setDetailImagesAdapterView(GalleryDetailImageAdpterContract.View View);

        void setDetailCommentsAdapterModel(GalleryDetailCommentAdpterContract.Model Model);
        void setDetailCommentsAdapterView(GalleryDetailCommentAdpterContract.View View);

        void setDetailCommentsKeyAdapterModel(GalleryDetailCommentKeyAdpterContract.Model Model);
        void setDetailCommentsKeyAdapterView(GalleryDetailCommentKeyAdpterContract.View View);
        void setOnImageKeyboardListener(OnImageKeyboardListener onImageKeyboardListener);
        void setOnCommentDeletePressListener(OnCommentDeletePressListener onCommentDeletePressListener);
        void setDetailImagesSrc(ArrayList<String> srcs);
        void setDetailCommentsKey(ArrayList<String> keys);


        void keyAreaViewActivation(boolean flag);


        void setKeyPreView(String imageKey , int position);
        void insertComment(String imageKey);



        void deleteComment(CommentData commentData,int position);




        void attachView(GalleryDetailContract.View view);
        void detachView();
        void notfyImagesAdapter();
        void notfyCommentsAdapter();
        void notfyCommentsKeyAdapter();
    }
}
