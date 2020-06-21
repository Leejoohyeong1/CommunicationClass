package com.sama.communicationclassjava.Contract;

import com.sama.communicationclassjava.Data.GalleryDetailData;
import com.sama.communicationclassjava.Lisetner.OnItemClickListener;

import java.util.ArrayList;

public interface GalleryDetailCommentKeyAdpterContract {
    interface View {
        void CommentKeyAdpterNotfyAdaoter();
    }

    interface Model {
        void setCommentIconList(ArrayList<String> commentKeys);
        int commentKeyAdpterGetSize();
        String commentKeyAdpterGetItem(int position);
        void commentKeyAdpterClearItems();
    }
}
