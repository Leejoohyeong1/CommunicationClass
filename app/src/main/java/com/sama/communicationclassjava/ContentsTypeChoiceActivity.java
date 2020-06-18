package com.sama.communicationclassjava;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.divyanshu.draw.activity.DrawingActivity;
import com.sama.communicationclassjava.Contract.TypeChoiceContract;
import com.sama.communicationclassjava.Data.CommunicationItem;
import com.sama.communicationclassjava.Lisetner.OnItemClickListener;
import com.sama.communicationclassjava.Presenter.TypeChoicePresenter;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class ContentsTypeChoiceActivity extends AppCompatActivity implements View.OnClickListener , TypeChoiceContract.View {

    View DrawingButton;
    static final int REQUEST_CODE_DRAW = 1;
    InputStream stream = null;
    TypeChoiceContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contents_type_choice);

        DrawingButton = findViewById(R.id.drawType);
        DrawingButton.setOnClickListener(this);

        presenter = new TypeChoicePresenter();
        presenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.drawType:
                presenter.openDrawing();
                break;
            case R.id.phototypeicon:
                presenter.openImagePicker();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && resultCode == Activity.RESULT_OK) {
            switch (requestCode){
                case REQUEST_CODE_DRAW :
                    byte[] DrawingByte =  data.getByteArrayExtra("bitmap");
                    presenter.bitmapUpload(DrawingByte);
                    break;
                }
            }
        }



    @Override
    public void moveGalleryDetail() {

    }

    @Override
    public void openImagePicker() {
        Toast.makeText(this, "openImagePicker", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void openDrawing() {
        Intent myIntent = new Intent(this, DrawingActivity.class);
        startActivityForResult(myIntent, REQUEST_CODE_DRAW);
    }
}
