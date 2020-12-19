package com.sama.communicationclass.Contract;

import com.sama.communicationclass.Data.GalleryDetailData;
import com.sama.communicationclass.Lisetner.OnItemClickListener;

import java.util.ArrayList;

public interface GalleryAdapterContract {
    interface View {
        void notfyAdapter();
        void setOnClickListener(OnItemClickListener clickListener);

    }

    interface Model {
        void addItems(ArrayList<GalleryDetailData> galleryDetailData);
        int getSize();
        GalleryDetailData LastItem();
        void addItem(GalleryDetailData item);
        GalleryDetailData getItem(int position);
        void clearItems();
    }
}
