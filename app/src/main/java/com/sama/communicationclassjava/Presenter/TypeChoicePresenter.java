package com.sama.communicationclassjava.Presenter;

import com.sama.communicationclassjava.Contract.TypeChoiceContract;
import com.sama.communicationclassjava.Data.GalleryDatilData;
import com.sama.communicationclassjava.Lisetner.OnGalleryUploadListener;
import com.sama.communicationclassjava.Model.FireBaseModel;

import java.util.Date;

public class TypeChoicePresenter implements TypeChoiceContract.Presenter, OnGalleryUploadListener {

    TypeChoiceContract.View view;

    FireBaseModel fireBaseStorageModel = FireBaseModel.getInstance();

    @Override
    public void attachView(TypeChoiceContract.View view) {
        this.view = view;
    }


    @Override
    public void detachView() {
        if(this.view != null){
            view = null;
        }
    }

    @Override
    public void bitmapUpload(byte[] DrawingByte) {
        fireBaseStorageModel.setOnGalleryUploadListener(this);
        fireBaseStorageModel.FireBaseBitmpaUpload(Long.toString(new Date().getTime()),DrawingByte);
    }

    @Override
    public void imagePickerUpload() {

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
    public void OnGalleryUploadListener(boolean flag, GalleryDatilData item) {

    }
}
