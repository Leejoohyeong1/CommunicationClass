package com.sama.communicationclass.Presenter;

import android.graphics.Bitmap;

import com.sama.communicationclass.Contract.NanuliCardEditAdapterContract;
import com.sama.communicationclass.Contract.NanuliCardKeyAdapterContract;
import com.sama.communicationclass.Contract.NanuliContract;
import com.sama.communicationclass.Data.NanuliCard;
import com.sama.communicationclass.Data.NanuliCardDecks;

public class NanuliPresenter implements NanuliContract.Presenter {

    private NanuliCardKeyAdapterContract.Model cardKeyAdapterModel;
    private NanuliCardKeyAdapterContract.View cardKeyAdapterView;

    private NanuliCardEditAdapterContract.Model cardEditAdapterModel;
    private NanuliCardEditAdapterContract.View cardEditAdapterView;

    private NanuliCardDecks nanuliCardDecks = NanuliCardDecks.getInstance();
    private NanuliContract.View view;


    @Override
    public void attachView(NanuliContract.View view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        if(this.view != null){ this.view = null; }
    }

    @Override
    public void setCardKeyAdapterModel(NanuliCardKeyAdapterContract.Model Model) {
        this.cardKeyAdapterModel = Model;
        this.cardKeyAdapterModel.setNanuliCard(nanuliCardDecks.nextCatalog());
        this.view.setCatalog(this.nanuliCardDecks.getDeck().getCatalogName());
    }

    @Override
    public void setCardKeyAdapterView(NanuliCardKeyAdapterContract.View View) {
        this.cardKeyAdapterView = View;
    }


    @Override
    public void nextCatalog() {
        this.cardKeyAdapterModel.setNanuliCard(nanuliCardDecks.nextCatalog());
        this.view.setCatalog(this.nanuliCardDecks.getDeck().getCatalogName());
        this.cardKeyAdapterView.notfy();
    }

    @Override
    public void previousCatalog() {
        this.cardKeyAdapterModel.setNanuliCard(nanuliCardDecks.previousCatalog());
        this.view.setCatalog(this.nanuliCardDecks.getDeck().getCatalogName());
        this.cardKeyAdapterView.notfy();
    }

    @Override
    public void notfyCardKey() {
        this.cardKeyAdapterView.notfy();
    }

    @Override
    public void setCardEditAdapterView(NanuliCardEditAdapterContract.View View) {
        this.cardEditAdapterView = View;
    }

    @Override
    public void setCardEditAdapterModel(NanuliCardEditAdapterContract.Model Model) {
        this.cardEditAdapterModel = Model;
    }

    @Override
    public void addCardEditing(NanuliCard nanuliCard) {
        view.cardSoundPlay(nanuliCard);
        this.cardEditAdapterModel.addNanuliCards(nanuliCard);
    }

    @Override
    public void setCardEditAdapterRemove(int position) {
        cardEditAdapterModel.removeNanuliCard(position);
    }

    @Override
    public void notfyCardEdit() {
        cardEditAdapterView.notfy();
    }


    @Override
    public void nanuliUpload() {
        Bitmap bitmap = view.loadBitmapFromView();

        view.finishActivity(bitmap,cardEditAdapterModel.getCardDeck());
    }

    @Override
    public void cardEditViewCloseButtonHide() {
        cardEditAdapterView.closeBtnHide();
        cardEditAdapterView.notfy();
    }
}
