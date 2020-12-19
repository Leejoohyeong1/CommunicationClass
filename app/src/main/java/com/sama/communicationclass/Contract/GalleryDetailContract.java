package com.sama.communicationclass.Contract;


import com.sama.communicationclass.Data.CommentData;
import com.sama.communicationclass.Data.NanuliCard;
import com.sama.communicationclass.Lisetner.OnCommentDeletePressListener;
import com.sama.communicationclass.Lisetner.OnImageKeyboardListener;
import com.sama.communicationclass.CustomEnum.ResultMassage;


import java.util.ArrayList;

public interface GalleryDetailContract {
    interface  View{
        void setKeyAreaVisible();
        void setKeyAreaGone();
        void setKeyPreView(String fileName);
        void setLookComment(int position);
        void ToastMassage(ResultMassage massage);
        void showDisplayProgressDialog();
        void noneDisplayProgressDialog();
        void backPressedGoGallery();
        void finishGoGallery();
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



        void setCardEditAdapterModel(NanuliCardEditAdapterContract.Model Model);
        void setCardEditAdapterView(NanuliCardEditAdapterContract.View View);
        void setCardEditAdapterData(ArrayList<NanuliCard> cardEditAdapterData);
        void notfyCardEdit();


        void keyAreaViewActivation(boolean flag);


        void setKeyPreView(String imageKey , int position);
        void insertComment(String imageKey);



        void deleteComment(CommentData commentData,int position);

        void deleteGallery();

        void attachView(GalleryDetailContract.View view);
        void detachView();
        void notfyImagesAdapter();
        void notfyCommentsAdapter();
        void notfyCommentsKeyAdapter();


        void backPressed();
    }
}
