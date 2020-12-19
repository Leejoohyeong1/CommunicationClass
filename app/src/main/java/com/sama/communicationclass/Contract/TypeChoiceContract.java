package com.sama.communicationclass.Contract;

import android.graphics.Bitmap;

import com.sama.communicationclass.Data.GalleryDetailData;
import com.sama.communicationclass.Data.NanuliCard;

import java.util.ArrayList;

public interface TypeChoiceContract {
    interface View{
        void openImagePicker();
        void openDrawing();
        void openNanuli();
        void upLoadFail();
        void goDetailActivity(GalleryDetailData item);
        void cameraPermissions();
    }

    interface Presenter{
        void attachView(View view);
        void detachView();
        void bitmapUpload(Bitmap[] upLoadBitMap);
        void bitmapUpload(Bitmap[] upLoadBitMap,ArrayList<NanuliCard> deck);
        void openImagePicker();
        void openDrawing();
        void openNanuli();
    }
}
