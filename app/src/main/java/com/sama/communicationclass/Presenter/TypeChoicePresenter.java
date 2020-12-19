package com.sama.communicationclass.Presenter;

import android.graphics.Bitmap;
import android.util.Log;

import com.sama.communicationclass.Contract.TypeChoiceContract;
import com.sama.communicationclass.Data.GalleryDetailData;
import com.sama.communicationclass.Data.NanuliCard;
import com.sama.communicationclass.Lisetner.OnGalleryUploadListener;
import com.sama.communicationclass.Model.FireBaseFileUpLoadModule;

import java.util.ArrayList;

public class TypeChoicePresenter implements TypeChoiceContract.Presenter, OnGalleryUploadListener {

    TypeChoiceContract.View view;

    @Override
    public void attachView(TypeChoiceContract.View view) {
        this.view = view;
        this.view.cameraPermissions();
    }


    @Override
    public void detachView() {
        if(this.view != null){
            view = null;
        }
    }

    @Override
    public void bitmapUpload(Bitmap[] upLoadBitMap) {
        new FireBaseFileUpLoadModule().addGalleryUploadListener(this).execute(upLoadBitMap);

    }

    @Override
    public void bitmapUpload(Bitmap[] upLoadBitMap, ArrayList<NanuliCard> deck) {
        new FireBaseFileUpLoadModule().addGalleryUploadListener(this).execute(upLoadBitMap,deck);
    }

    @Override
    public void openImagePicker() {
        view.openImagePicker();
    }

    @Override
    public void openDrawing() {
        view.openDrawing();
    }

    @Override
    public void openNanuli() {
        view.openNanuli();
    }

    @Override
    public void OnGalleryUploadListener(boolean flag, GalleryDetailData item) {
        Log.d("씨발","OnGalleryUploadListener="+flag);
        if(flag){
            view.goDetailActivity(item);
        }else{
            view.upLoadFail();
        }
    }
}
