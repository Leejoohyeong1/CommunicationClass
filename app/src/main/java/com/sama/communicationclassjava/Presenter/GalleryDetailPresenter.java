package com.sama.communicationclassjava.Presenter;

import com.sama.communicationclassjava.Contract.GalleryDetailCommentAdpterContract;
import com.sama.communicationclassjava.Contract.GalleryDetailCommentKeyAdpterContract;
import com.sama.communicationclassjava.Contract.GalleryDetailContract;
import com.sama.communicationclassjava.Contract.GalleryDetailImageAdpterContract;
import com.sama.communicationclassjava.Data.CommentData;
import com.sama.communicationclassjava.Lisetner.OnCommentDeleteListener;
import com.sama.communicationclassjava.Lisetner.OnCommentDeletePressListener;
import com.sama.communicationclassjava.Lisetner.OnCommentInsertListener;
import com.sama.communicationclassjava.Lisetner.OnGallerySelectCommentsListener;
import com.sama.communicationclassjava.Lisetner.OnImageKeyboardListener;
import com.sama.communicationclassjava.Lisetner.OnGallerySelectItemListListener;
import com.sama.communicationclassjava.MassageEnum.Massage;
import com.sama.communicationclassjava.Model.FileBaseCommentDeleteModel;
import com.sama.communicationclassjava.Model.FileBaseCommentInsertModel;
import com.sama.communicationclassjava.Model.FileBaseCommentSelectModel;
import com.sama.communicationclassjava.Model.FireBaseModel;

import java.util.ArrayList;

public class GalleryDetailPresenter implements GalleryDetailContract.Presenter
                                            , OnGallerySelectCommentsListener
                                            , OnCommentInsertListener
                                            , OnCommentDeleteListener {

    GalleryDetailContract.View view;

    GalleryDetailImageAdpterContract.Model imageAdapterModel;
    GalleryDetailImageAdpterContract.View imageAdapterView;

    GalleryDetailCommentAdpterContract.Model commentAdapterModel;
    GalleryDetailCommentAdpterContract.View commentAdapterView;

    GalleryDetailCommentKeyAdpterContract.Model commentKeyAdapterModel;
    GalleryDetailCommentKeyAdpterContract.View commentKeyAdapterView;

    String documentKey;

    public GalleryDetailPresenter(String documentKey) {
        this.documentKey = documentKey;
        FileBaseCommentSelectModel.getInstance().setOnSelectGalleryCommentsListener(this);
        FileBaseCommentSelectModel.getInstance().SelectGalleryDetailComments(this.documentKey);
        FileBaseCommentInsertModel.getInstance().setOnCommentInsertListener(this);
        FileBaseCommentDeleteModel.getInstance().setonCommentDeleteListener(this);
    }

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
    public void setOnImageKeyboardListener(OnImageKeyboardListener onImageKeyboardListener) {
        this.commentKeyAdapterModel.setOnImageKeyboardListener(onImageKeyboardListener);
    }

    @Override
    public void setOnCommentDeletePressListener(OnCommentDeletePressListener onCommentDeletePressListener) {
        this.commentAdapterModel.setOnCommentDeletePressListener(onCommentDeletePressListener);
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
        this.commentKeyAdapterView.CommentKeyAdpterNotfyAdaoter();
    }

    @Override
    public void keyAreaViewActivation(boolean flag) {
        if (flag)
            this.view.setKeyAreaVisible();
        else
            this.view.setKeyAreaGone();

    }

    @Override
    public void insertComment(String imageKey) {
        view.showDisplayProgressDialog();
        FileBaseCommentInsertModel.getInstance().insertComment(imageKey,documentKey);
    }

    @Override
    public void setKeyPreView(String imageKey, int position) {
        this.view.setKeyPreView(imageKey);
    }

    @Override
    public void OnCommentItems(ArrayList<CommentData> list) {
        this.commentAdapterModel.setCommentItems(list);
        this.commentAdapterView.CommentAdpternotfyAdaoter();
    }

    @Override
    public void OnInsertSuccess(CommentData item) {
        this.commentAdapterModel.addCommentItem(item);
        this.commentAdapterView.CommentAdpternotfyAdaoter();
        this.view.setLookComment(this.commentAdapterModel.getLastSize()-1);
        this.view.noneDisplayProgressDialog();
    }
    @Override
    public void OnInsertFailure() {
        this.view.noneDisplayProgressDialog();
        this.view.ToastMassage(Massage.OnInsertFailure);

    }

    @Override
    public void deleteComment(CommentData commentData, int position) {
        view.showDisplayProgressDialog();
        FileBaseCommentDeleteModel.getInstance().deleteComment(commentData,position);
    }

    @Override
    public void OnDeleteSuccess(int position) {
        this.commentAdapterModel.removeCommentItem(position);
        this.commentAdapterView.CommentAdpternotfyAdaoter();
        this.view.noneDisplayProgressDialog();
    }

    @Override
    public void OnDeleteFailure() {
        this.view.noneDisplayProgressDialog();
        this.view.ToastMassage(Massage.OnDeleteFailure);
    }


}
