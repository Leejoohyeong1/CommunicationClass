package com.sama.communicationclassjava.Contract;

import com.sama.communicationclassjava.Data.CommentData;
import com.sama.communicationclassjava.Lisetner.OnCommentDeletePressListener;

import java.util.ArrayList;

public interface GalleryDetailCommentAdpterContract {
    interface View {
        void CommentAdpternotfyAdaoter();
    }

    interface Model {
        void setOnCommentDeletePressListener(OnCommentDeletePressListener onCommentDeletePressListener);
        void setCommentItems(ArrayList<CommentData> items);
        void addCommentItem(CommentData item);
        void removeCommentItem(int position);
        int getLastSize();
    }
}
