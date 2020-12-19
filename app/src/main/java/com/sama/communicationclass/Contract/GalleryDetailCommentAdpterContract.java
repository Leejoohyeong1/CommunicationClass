package com.sama.communicationclass.Contract;

import com.sama.communicationclass.Data.CommentData;
import com.sama.communicationclass.Lisetner.OnCommentDeletePressListener;

import java.util.ArrayList;

public interface GalleryDetailCommentAdpterContract {
    interface View {
        void notfyAdapter();
    }

    interface Model {
        void setOnCommentDeletePressListener(OnCommentDeletePressListener onCommentDeletePressListener);
        void setCommentItems(ArrayList<CommentData> items);
        void addCommentItem(CommentData item);
        void removeCommentItem(int position);
        int getLastSize();
    }
}
