package com.sama.communicationclassjava.Contract;

import com.sama.communicationclassjava.Lisetner.OnItemClickListener;

import java.util.ArrayList;

public interface GalleryDetailImageAdpterContract {

    interface View {
        void imageAdpterNotfyAdaoter();
    }

    interface Model {
        void setImagesSrcList(ArrayList<String> imagesSrc);
        int imageAdpterGetSize();
        String imageAdpterGetItem(int position);
        void imageAdpterClearItems();
    }
}
