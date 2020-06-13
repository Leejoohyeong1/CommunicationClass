package com.sama.communicationclassjava.Contract;

import com.sama.communicationclassjava.Data.CommunicationItem;
import com.sama.communicationclassjava.Lisetner.OnItemClickListener;

import java.util.ArrayList;

public interface GalleryAdapterContract {
    interface View {
        void notfyAdaoter();
        void setOnClickLisetner(OnItemClickListener clickLisetner);

    }

    interface Model {
        void addItems(ArrayList<CommunicationItem> CommunicationItemList);
        void addItem(CommunicationItem item);
        CommunicationItem getItem(int position);
        void clearItems();
    }
}
