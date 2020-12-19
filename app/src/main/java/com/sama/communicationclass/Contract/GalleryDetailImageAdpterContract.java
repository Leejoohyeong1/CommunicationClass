package com.sama.communicationclass.Contract;

import com.sama.communicationclass.Lisetner.OnItemClickListener;

import java.util.ArrayList;

public interface GalleryDetailImageAdpterContract {

    interface View {
        void notfyAdapter();
    }

    interface Model {
        void setImagesSrcList(ArrayList<String> imagesSrc);
        int imageAdapterGetSize();
        String imageAdapterGetItem(int position);
        void imageAdapterClearItems();
    }
}
