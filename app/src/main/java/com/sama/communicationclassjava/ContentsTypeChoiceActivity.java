package com.sama.communicationclassjava;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.divyanshu.draw.activity.DrawingActivity;
import com.sama.communicationclassjava.Contract.TypeChoiceContract;
import com.sama.communicationclassjava.Presenter.TypeChoicePresenter;
import java.io.InputStream;
import java.util.List;

import gun0912.tedimagepicker.builder.TedImagePicker;
import gun0912.tedimagepicker.builder.listener.OnMultiSelectedListener;
import gun0912.tedimagepicker.builder.type.MediaType;

public class ContentsTypeChoiceActivity extends AppCompatActivity implements OnMultiSelectedListener
                                                                            , View.OnClickListener
                                                                            , TypeChoiceContract.View {

    View drawingTypeButton;
    View photoTypeButton;

    static final int REQUEST_CODE_DRAW = 1;
    InputStream stream = null;
    TypeChoiceContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contents_type_choice);

        this.drawingTypeButton = findViewById(R.id.drawType);
        this.drawingTypeButton.setOnClickListener(this);

        this.photoTypeButton = findViewById(R.id.PhotoType);
        this.photoTypeButton.setOnClickListener(this);

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
            case R.id.PhotoType:
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
        TedImagePicker.with(this)
                .mediaType(MediaType.IMAGE)
                .max(5, "5장까지 공유가능합니다 ")
                .startMultiImage(this);
    }

    @Override
    public void openDrawing() {
        Intent myIntent = new Intent(this, DrawingActivity.class);
        startActivityForResult(myIntent, REQUEST_CODE_DRAW);
    }

    @Override
    public void onSelected(List<? extends Uri> list) {
        Toast.makeText(this, list.toString(), Toast.LENGTH_SHORT).show();
    }
}
