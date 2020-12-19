package com.sama.communicationclass.Presenter;

import android.util.Log;

import com.sama.communicationclass.Contract.GalleryDetailCommentAdpterContract;
import com.sama.communicationclass.Contract.GalleryDetailCommentKeyAdpterContract;
import com.sama.communicationclass.Contract.GalleryDetailContract;
import com.sama.communicationclass.Contract.GalleryDetailImageAdpterContract;
import com.sama.communicationclass.Contract.NanuliCardEditAdapterContract;
import com.sama.communicationclass.Data.CommentData;
import com.sama.communicationclass.Data.NanuliCard;
import com.sama.communicationclass.Lisetner.OnCommentDeleteListener;
import com.sama.communicationclass.Lisetner.OnCommentDeletePressListener;
import com.sama.communicationclass.Lisetner.OnCommentInsertListener;
import com.sama.communicationclass.Lisetner.OnGalleryDeleteListener;
import com.sama.communicationclass.Lisetner.OnGallerySelectCommentsListener;
import com.sama.communicationclass.Lisetner.OnImageKeyboardListener;
import com.sama.communicationclass.CustomEnum.ResultMassage;
import com.sama.communicationclass.Model.FileBaseCommentDeleteModel;
import com.sama.communicationclass.Model.FileBaseCommentInsertModel;
import com.sama.communicationclass.Model.FileBaseCommentSelectModel;
import com.sama.communicationclass.Model.FireBaseGalleryDeleteModel;

import java.util.ArrayList;

public class GalleryDetailPresenter implements GalleryDetailContract.Presenter
                                            , OnGalleryDeleteListener
                                            , OnGallerySelectCommentsListener
                                            , OnCommentInsertListener
                                            , OnCommentDeleteListener {

    GalleryDetailContract.View view;

    private GalleryDetailImageAdpterContract.Model imageAdapterModel;
    private GalleryDetailImageAdpterContract.View imageAdapterView;

    private NanuliCardEditAdapterContract.Model cardEditAdapterModel;
    private NanuliCardEditAdapterContract.View cardEditAdapterView;



    private GalleryDetailCommentAdpterContract.Model commentAdapterModel;
    private GalleryDetailCommentAdpterContract.View commentAdapterView;

    private GalleryDetailCommentKeyAdpterContract.Model commentKeyAdapterModel;
    private GalleryDetailCommentKeyAdpterContract.View commentKeyAdapterView;

    private String documentKey;

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
        this.imageAdapterView.notfyAdapter();
    }

    @Override
    public void notfyCommentsAdapter() {
        this.commentAdapterView.notfyAdapter();
    }

    @Override
    public void notfyCommentsKeyAdapter() {
        this.commentKeyAdapterView.notfyAdapter();
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
        this.commentAdapterView.notfyAdapter();
    }

    @Override
    public void OnInsertSuccess(CommentData item) {
        this.commentAdapterModel.addCommentItem(item);
        this.commentAdapterView.notfyAdapter();
        this.view.setLookComment(this.commentAdapterModel.getLastSize()-1);
        this.view.noneDisplayProgressDialog();
    }
    @Override
    public void OnInsertFailure() {
        this.view.noneDisplayProgressDialog();
        this.view.ToastMassage(ResultMassage.OnInsertFailure);
    }

    @Override
    public void deleteComment(CommentData commentData, int position) {
        view.showDisplayProgressDialog();
        FileBaseCommentDeleteModel.getInstance().deleteComment(commentData,position);
    }

    @Override
    public void OnDeleteSuccess(int position) {
        this.commentAdapterModel.removeCommentItem(position);
        this.commentAdapterView.notfyAdapter();
        this.view.noneDisplayProgressDialog();
    }

    @Override
    public void OnDeleteFailure() {
        this.view.noneDisplayProgressDialog();
        this.view.ToastMassage(ResultMassage.OnDeleteFailure);
    }


    @Override
    public void setCardEditAdapterModel(NanuliCardEditAdapterContract.Model Model) {
        this.cardEditAdapterModel = Model;
    }

    @Override
    public void setCardEditAdapterView(NanuliCardEditAdapterContract.View View) {
        this.cardEditAdapterView = View;
    }

    public void notfyCardEdit() {
        cardEditAdapterView.notfy();
    }

    @Override
    public void setCardEditAdapterData(ArrayList<NanuliCard> cardEditAdapterData) {
        this.cardEditAdapterModel.setNanuliCards(cardEditAdapterData);
    }

    @Override
    public void backPressed() {
        view.backPressedGoGallery();
    }


    @Override
    public void deleteGallery() {
        FireBaseGalleryDeleteModel.getInstance().setOnGalleryDeleteListener(this).deleteGalleryContents(documentKey);
    }

    @Override
    public void OnGalleryDelete() {
        Log.d("DeleteModel","OnGalleryDelete");
        view.finishGoGallery();
    }
}
