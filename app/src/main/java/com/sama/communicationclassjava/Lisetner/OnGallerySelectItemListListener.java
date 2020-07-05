package com.sama.communicationclassjava.Lisetner;


import com.sama.communicationclassjava.Data.CommentData;
import com.sama.communicationclassjava.Data.GalleryDetailData;

import java.util.ArrayList;

public interface OnGallerySelectItemListListener {
    void OnGallerySelectItemList(ArrayList<GalleryDetailData> list);
}
