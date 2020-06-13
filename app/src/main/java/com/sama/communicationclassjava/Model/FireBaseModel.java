package com.sama.communicationclassjava.Model;


import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.sama.communicationclassjava.Data.CommunicationItem;
import com.sama.communicationclassjava.Data.GalleryDatilData;
import com.sama.communicationclassjava.Lisetner.OnGallerySelectItemListListener;
import com.sama.communicationclassjava.Lisetner.OnGalleryUploadListener;
import com.sama.communicationclassjava.SinglePattern.SelectUserInfo;

import java.util.ArrayList;

public class FireBaseModel {

    DatabaseReference database = FirebaseDatabase.getInstance().getReference();
    StorageReference storageRef = FirebaseStorage.getInstance().getReference();

    StorageReference mountainsRef = null;
    UploadTask uploadTask;

    SelectUserInfo userInfo;

    OnGallerySelectItemListListener selectItemListListener;
    OnGalleryUploadListener OnGalleryUpload;


    private static FireBaseModel one;

    public static FireBaseModel getInstance() {
        if (one == null) {
            one = new FireBaseModel();
        }
        return one;
    }


    public void setOnGalleryUploadListener(OnGalleryUploadListener OnGalleryUpload) {
        this.OnGalleryUpload = OnGalleryUpload;
    }

    public void setSelectItemListListener(OnGallerySelectItemListListener selectItemListListener) {
        this.selectItemListListener = selectItemListListener;
    }

    OnFailureListener bitmpaFailureListener = new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            mountainsRef = null;
        }
    };

    ValueEventListener itemListListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            selectItemListListener.OnGallerySelectItemList((ArrayList<CommunicationItem>) dataSnapshot.getValue());
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    };

    OnSuccessListener<UploadTask.TaskSnapshot> bitmapOnSuccessListener = new OnSuccessListener<UploadTask.TaskSnapshot>() {
        @Override
        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
            GalleryDatilData data = new GalleryDatilData();

            DatabaseReference galleryTableArea = database.child("Gallery").child(userInfo.getArea());

            data.Merge(userInfo);
            data.addImageUrl(mountainsRef.getPath());
            data.writeProduce();
            String Key = galleryTableArea.push().getKey();
            galleryTableArea.child(Key).setValue(data);
            mountainsRef = null;

            uploadTask
                    .removeOnFailureListener(bitmpaFailureListener)
                    .removeOnSuccessListener(bitmapOnSuccessListener);
        }
    };


    public FireBaseModel(){
        userInfo =  SelectUserInfo.getInstance();

    }

    public void FireBaseBitmpaUpload(String FileName,byte[] DrawingByte){

        mountainsRef  = storageRef.child(FileName+"_"+userInfo.getUUID()+".jpg");
        this.uploadTask = mountainsRef.putBytes(DrawingByte);
        uploadTask
                .addOnFailureListener(bitmpaFailureListener)
                .addOnSuccessListener(bitmapOnSuccessListener);
    }


    public void SelectGallerycontents(int page){
        int countPage = 2;
        int startindex = (page - 1) * countPage + 1;
        int endindex = page * countPage;

        SelectUserInfo userInfo = SelectUserInfo.getInstance();
        DatabaseReference galleryTableArea = database.child("Gallery").child(userInfo.getArea());
        galleryTableArea.orderByChild("writeDay").startAt(startindex).endAt(endindex).addValueEventListener(itemListListener);
    }
}
