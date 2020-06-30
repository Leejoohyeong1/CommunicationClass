package com.sama.communicationclassjava.Presenter;

import com.sama.communicationclassjava.Contract.GalleryDetailCommentAdpterContract;
import com.sama.communicationclassjava.Contract.GalleryDetailCommentKeyAdpterContract;
import com.sama.communicationclassjava.Contract.GalleryDetailContract;
import com.sama.communicationclassjava.Contract.GalleryDetailImageAdpterContract;
import com.sama.communicationclassjava.Data.GalleryDetailData;

import java.util.ArrayList;

public class GalleryDetailPresenter implements GalleryDetailContract.Presenter {
    GalleryDetailContract.View view;

    GalleryDetailImageAdpterContract.Model imageAdapterModel;
    GalleryDetailImageAdpterContract.View imageAdapterView;

    GalleryDetailCommentAdpterContract.Model commentAdapterModel;
    GalleryDetailCommentAdpterContract.View commentAdapterView;

    GalleryDetailCommentKeyAdpterContract.Model commentKeyAdapterModel;
    GalleryDetailCommentKeyAdpterContract.View commentKeyAdapterView;

    @Override
    public void attachView(GalleryDetailContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }

    @Override
    public void setDetailImagesAdapterModel(GalleryDetailImageAdpterContract.Model Model) {
        this.imageAdapterModel = Model;
    }

    @Override
    public void setDetailImagesAdapterView(GalleryDetailImageAdpterContract.View View) {
        this.imageAdapterView = View;
    }

    @Override
    public void setDetailCommentsAdapterModel(GalleryDetailCommentAdpterContract.Model Model) {
        this.commentAdapterModel = Model;
    }

    @Override
    public void setDetailCommentsAdapterView(GalleryDetailCommentAdpterContract.View View) {
        this.commentAdapterView =View;
    }

    @Override
    public void setDetailCommentsKeyAdapterModel(GalleryDetailCommentKeyAdpterContract.Model Model) {
        this.commentKeyAdapterModel = Model;
    }

    @Override
    public void setDetailCommentsKeyAdapterView(GalleryDetailCommentKeyAdpterContract.View View) {
        this.commentKeyAdapterView = View;
    }


    @Override
    public void setDetailImagesSrc(ArrayList<String> srcs) {
        this.imageAdapterModel.setImagesSrcList(srcs);
    }

    @Override
    public void setDetailCommentsKey(ArrayList<String> keys) {
        this.commentKeyAdapterModel.setCommentIconList(keys);
    }

    @Override
    public void notfyImagesAdapter() {
        this.imageAdapterView.imageAdpterNotfyAdaoter();
    }

    @Override
    public void notfyCommentsAdapter() {
        this.commentAdapterView.CommentAdpternotfyAdaoter();
    }

    @Override
    public void notfyCommentsKeyAdapter() {

    }

    @Override
    public void keyAreaViewActivation(boolean flag) {
        if (flag)
            this.view.setKeyAreaGone();
        else
            this.view.setKeyAreaVisible();
    }
}
