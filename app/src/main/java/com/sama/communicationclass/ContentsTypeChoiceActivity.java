package com.sama.communicationclass;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.divyanshu.draw.activity.DrawingActivity;
import com.sama.communicationclass.Contract.TypeChoiceContract;
import com.sama.communicationclass.CustomActionBar.CustomAction;
import com.sama.communicationclass.CustomEnum.ActionBarLayout;
import com.sama.communicationclass.Data.GalleryDetailData;
import com.sama.communicationclass.Data.NanuliCard;
import com.sama.communicationclass.Presenter.TypeChoicePresenter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import gun0912.tedimagepicker.builder.TedImagePicker;
import gun0912.tedimagepicker.builder.listener.OnMultiSelectedListener;
import gun0912.tedimagepicker.builder.type.MediaType;

public class ContentsTypeChoiceActivity extends AppCompatActivity implements OnMultiSelectedListener
                                                                            , View.OnClickListener
                                                                            , TypeChoiceContract.View
                                                                                {

    static final int REQUEST_CAMERA = 1503;

    ImageButton drawingTypeButton;
    ImageButton photoTypeButton;
    ImageButton nanuliTypeButton;

    static final int REQUEST_CODE_DRAW = 1;
    static final int REQUEST_CODE_Nanuli = 2;
    TypeChoiceContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contents_type_choice);

        new CustomAction(getSupportActionBar(), this).setActionBar(ActionBarLayout.contentsTypeLayout);


        this.drawingTypeButton = (ImageButton) findViewById(R.id.draw_type);
        this.drawingTypeButton.setOnClickListener(this);

        this.photoTypeButton = (ImageButton) findViewById(R.id.photo_type);
        this.photoTypeButton.setOnClickListener(this);

        this.nanuliTypeButton = (ImageButton) findViewById(R.id.nanuli_type);
        this.nanuliTypeButton.setOnClickListener(this);

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
            case R.id.draw_type:
                presenter.openDrawing();
                break;
            case R.id.photo_type:
                presenter.openImagePicker();
                break;
            case R.id.nanuli_type:
                presenter.openNanuli();
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data != null && resultCode == Activity.RESULT_OK) {
                byte[] DrawingByte =  data.getByteArrayExtra("bitmap");

                ArrayList<NanuliCard> deck = (ArrayList<NanuliCard>) data.getSerializableExtra("deck");
                Bitmap[] bitmap = new Bitmap[1];
                bitmap[0] = BitmapFactory.decodeByteArray( DrawingByte, 0, DrawingByte.length) ;
                presenter.bitmapUpload(bitmap,deck);
            }
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
    public void openNanuli() {
        Intent myIntent = new Intent(this, NanuliActivity.class);
        startActivityForResult(myIntent, REQUEST_CODE_Nanuli);
    }

    @Override
    public void onSelected(List<? extends Uri> list) {

        Bitmap[] bitmaps = new Bitmap[list.size()];
        Log.d("Upload", "onSelected Start");
            try {
                for (int index = 0 ; index < list.size() ; index++){
                    bitmaps[index] = MediaStore.Images.Media.getBitmap(this.getContentResolver(), list.get(index));
                }
                presenter.bitmapUpload(bitmaps);

            } catch (IOException e) {
                e.printStackTrace();
            }
    }


    @Override
    public void upLoadFail() {
        Toast.makeText(this, "업로드에 실패 했습니다", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void goDetailActivity(GalleryDetailData item) {
        Intent intent = new Intent(this, CommunicationDetailActivity.class);
        intent.putExtra("DetailData", item);
        startActivity(intent);
    }

    @Override
    public void cameraPermissions() {
        String cameraPermission = Manifest.permission.CAMERA;
        int hasPermission = ActivityCompat.checkSelfPermission(this,cameraPermission);

        String[] permissions = new String[]{ cameraPermission };

        if ( hasPermission != PackageManager.PERMISSION_GRANTED ){
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.READ_CALENDAR)){
                //사용자가 다시 보지 않기에 체크를 하지 않고, 권한 설정을 거절한 이력이 있는 경우

            } else{
                //사용자가 다시 보지 않기에 체크하고, 권한 설정을 거절한 이력이 있는 경우
                //왜 권한이 필요한지 알려주자
            }
            ActivityCompat.requestPermissions(this,permissions,REQUEST_CAMERA);
            //권한 용청
        }
    }
}
