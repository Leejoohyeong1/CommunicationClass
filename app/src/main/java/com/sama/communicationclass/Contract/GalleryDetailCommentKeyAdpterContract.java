package com.sama.communicationclass.Contract;

import com.sama.communicationclass.Lisetner.OnImageKeyboardListener;

import java.util.ArrayList;

public interface GalleryDetailCommentKeyAdpterContract {
    interface View {
        void notfyAdapter();


    }

    interface Model {
        void setOnImageKeyboardListener(OnImageKeyboardListener onImageKeyboardListener);
        void setCommentIconList(ArrayList<String> commentKeys);
        int commentKeyAdapterGetSize();
        String commentKeyAdapterGetItem(int position);
        void commentKeyAdapterClearItems();
    }
}
