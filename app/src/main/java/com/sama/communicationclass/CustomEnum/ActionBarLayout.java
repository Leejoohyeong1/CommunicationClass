package com.sama.communicationclass.CustomEnum;

import android.text.Layout;

import com.sama.communicationclass.R;

public enum ActionBarLayout {

    choiceLayout(R.layout.toolbar_choice_layout ,R.raw.guide1),
    galleryLayout(R.layout.toolbar_gallery_layout,R.raw.guide2),
    nanuliLayout(R.layout.toolbar_nanuli_layout,R.raw.guide4),
    contentsTypeLayout(R.layout.toolbar_contentstype_layout,R.raw.guide5),
    galleryDetailLayout(R.layout.toolbar_gallery_detail_layout,R.raw.guide3),

    ;

    int layout;
    int guideVideo;

    ActionBarLayout(int layout,int guideVideo){
        this.layout = layout;

        this.guideVideo = guideVideo;
    }

    public int getLayout(){
        return this.layout;
    }

    public int getGuideVideo() {
        return guideVideo;
    }
}
