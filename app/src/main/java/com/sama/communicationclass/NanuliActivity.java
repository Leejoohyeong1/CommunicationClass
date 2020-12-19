package com.sama.communicationclass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.sama.communicationclass.Adapter.NanuliCardAdapter;
import com.sama.communicationclass.Adapter.NanuliCardEditAdapter;
import com.sama.communicationclass.Contract.NanuliContract;
import com.sama.communicationclass.CustomActionBar.CustomAction;
import com.sama.communicationclass.CustomEnum.ActionBarLayout;
import com.sama.communicationclass.Data.NanuliCard;
import com.sama.communicationclass.Lisetner.OnNanuliCardClickListener;
import com.sama.communicationclass.Lisetner.OnToolbarActionListener;
import com.sama.communicationclass.Presenter.NanuliPresenter;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class NanuliActivity extends AppCompatActivity implements NanuliContract.View
                                                                , OnToolbarActionListener
                                                                , View.OnClickListener
                                                                , OnNanuliCardClickListener {

    NanuliContract.Presenter presenter;

    ImageView previousBtn, nextBtn;
    TextView catalogName;
    RecyclerView cardKeyBoard;
    RecyclerView cardEditView;
    MediaPlayer mPlayer;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nanuli);


        new CustomAction(getSupportActionBar(), this)
                .setActionBar(ActionBarLayout.nanuliLayout)
                .addToolbarListener(this);



        this.presenter = new NanuliPresenter();
        presenter.attachView(this);

        previousBtn = (ImageButton) findViewById(R.id.nanuli_previous);
        previousBtn.setOnClickListener(this);
        nextBtn = (ImageButton) findViewById(R.id.nanuli_next);
        nextBtn.setOnClickListener(this);
        catalogName = (TextView) findViewById(R.id.nanuli_catalog_name);
        cardKeyBoard = (RecyclerView) findViewById(R.id.nanuli_card_key);
        cardEditView = (RecyclerView) findViewById(R.id.nanuli_inbox);

        //입력 RecyclerView
        NanuliCardAdapter nanuliCardKeyAdapter = new NanuliCardAdapter();
        nanuliCardKeyAdapter.addNanuliCardListener(this);
        LinearLayoutManager nanuliKeyBoardManager = new LinearLayoutManager(this);
        nanuliKeyBoardManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        this.cardKeyBoard.setLayoutManager(nanuliKeyBoardManager);
        this.cardKeyBoard.setAdapter(nanuliCardKeyAdapter);
        this.presenter.setCardKeyAdapterModel(nanuliCardKeyAdapter);
        this.presenter.setCardKeyAdapterView(nanuliCardKeyAdapter);



        //출력 RecyclerView
        NanuliCardEditAdapter nanuliCardEditAdapter = new NanuliCardEditAdapter();

        nanuliCardEditAdapter.setEditingMode(true);
        nanuliCardEditAdapter.addNanuliCardListener(this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,3);
        this.cardEditView.setLayoutManager(gridLayoutManager);
        this.cardEditView.setAdapter(nanuliCardEditAdapter);
        this.presenter.setCardEditAdapterModel(nanuliCardEditAdapter);
        this.presenter.setCardEditAdapterView(nanuliCardEditAdapter);


        this.presenter.notfyCardKey();
        this.presenter.notfyCardEdit();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void setCatalog(String catalog) {
        catalogName.setText(catalog);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.nanuli_previous :
                presenter.previousCatalog();
                break;
            case R.id.nanuli_next :
                presenter.nextCatalog();
                break;
        }
    }



    @Override
    public void OnNanuliCardClick(NanuliCard nanuliCard, boolean EditMode, int position) {

        if(!EditMode){
            this.presenter.addCardEditing(nanuliCard);
        }else{
            this.presenter.setCardEditAdapterRemove(position);
        }
        this.presenter.notfyCardEdit();
    }

    @Override
    public Bitmap loadBitmapFromView() {
        RecyclerView v = cardEditView;
        v.setDrawingCacheEnabled(true);


        v.setDrawingCacheEnabled(true);
        Bitmap b = v.getDrawingCache(true).copy(Bitmap.Config.ARGB_8888, false);
        v.destroyDrawingCache();



//        Bitmap b = Bitmap.createBitmap( v.getLayoutParams().width, v.getLayoutParams().height, Bitmap.Config.ARGB_8888);
//        Canvas c = new Canvas(b);
//        v.layout(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
//        v.draw(c);
        return b;

    }


    @Override
    public void finishActivity(Bitmap bitmap, ArrayList<NanuliCard> editDeck) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream() ;
        bitmap.compress( Bitmap.CompressFormat.JPEG, 100, stream) ;
        byte[] byteArray = stream.toByteArray() ;
        Intent intent = new Intent();
        intent.putExtra("bitmap", byteArray);
        intent.putExtra("deck", editDeck);
        setResult(Activity.RESULT_OK,intent);
        finish();
    }

    @Override
    public void rightButtonAction() {
        presenter.cardEditViewCloseButtonHide();
        cardEditView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                presenter.nanuliUpload();
            }
        });


    }


    @Override
    public void cardSoundPlay(NanuliCard nanuliCard) {
        mPlayer = MediaPlayer.create(this, nanuliCard.getVoice());
        mPlayer.start();
    }
}
