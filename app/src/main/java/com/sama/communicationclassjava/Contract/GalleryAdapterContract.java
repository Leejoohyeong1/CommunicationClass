package com.sama.communicationclassjava.Contract;

import com.sama.communicationclassjava.Data.GalleryDetailData;
import com.sama.communicationclassjava.Lisetner.OnItemClickListener;

import java.util.ArrayList;

public interface GalleryAdapterContract {
    interface View {
        void notfyAdaoter();
        void setOnClickLisetner(OnItemClickListener clickLisetner);

    }

    interface Model {
        void addItems(ArrayList<GalleryDetailData> galleryDatilData);
        int getSize();
        GalleryDetailData LastItem();
        void addItem(GalleryDetailData item);
        GalleryDetailData getItem(int position);
        void clearItems();
    }
}
