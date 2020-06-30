package com.sama.communicationclassjava.Lisetner;

import com.sama.communicationclassjava.Data.GalleryDetailData;
import com.sama.communicationclassjava.Data.GalleryDetailCommentData;

import java.util.ArrayList;

public interface OnGallerySelectCommentsListener {
    void OnGallerySelectCommentsListener(ArrayList<GalleryDetailCommentData> list);
}
