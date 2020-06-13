package com.sama.communicationclassjava.Contract;

public interface TypeChoiceContract {
    interface View{
        void moveGalleryDetail();
        void openImagePicker();
        void openDrawing();
    }

    interface Presenter{
        void attachView(View view);
        void detachView();
        void bitmapUpload(byte[] DrawingByte);
        void imagePickerUpload();
        void openImagePicker();
        void openDrawing();
    }
}
