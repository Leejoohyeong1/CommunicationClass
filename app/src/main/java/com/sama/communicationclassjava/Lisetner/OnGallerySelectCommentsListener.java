package com.sama.communicationclassjava.Lisetner;

import com.sama.communicationclassjava.Data.CommentData;
import com.sama.communicationclassjava.Data.GalleryDetailData;

import java.util.ArrayList;

public interface OnGallerySelectCommentsListener {
    void OnCommentItems(ArrayList<CommentData> list);
}
