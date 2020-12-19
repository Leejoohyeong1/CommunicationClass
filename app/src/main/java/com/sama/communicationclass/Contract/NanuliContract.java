package com.sama.communicationclass.Contract;

import android.graphics.Bitmap;

import com.sama.communicationclass.Data.NanuliCard;

import java.util.ArrayList;

public interface NanuliContract {
    interface View{
        void setCatalog(String catalog);
        void finishActivity(Bitmap bitmap, ArrayList<NanuliCard> editDeck);
        Bitmap loadBitmapFromView();
        void cardSoundPlay(NanuliCard nanuliCard);
    }

    interface Presenter{
        void attachView(View view);
        void detachView();


        void nanuliUpload();

        void cardEditViewCloseButtonHide();
        void setCardKeyAdapterModel(NanuliCardKeyAdapterContract.Model Model);
        void setCardKeyAdapterView(NanuliCardKeyAdapterContract.View View);
        void nextCatalog();
        void previousCatalog();
        void notfyCardKey();


        void setCardEditAdapterModel(NanuliCardEditAdapterContract.Model Model);
        void setCardEditAdapterView(NanuliCardEditAdapterContract.View View);
        void setCardEditAdapterRemove(int position);
        void notfyCardEdit();

        void addCardEditing(NanuliCard nanuliCard);



    }


}
