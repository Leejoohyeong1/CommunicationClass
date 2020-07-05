package com.sama.communicationclassjava.Model;


import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.sama.communicationclassjava.Data.CommentData;
import com.sama.communicationclassjava.Data.GalleryDetailData;
import com.sama.communicationclassjava.Lisetner.OnCommentInsertListener;
import com.sama.communicationclassjava.Lisetner.OnGallerySelectCommentsListener;
import com.sama.communicationclassjava.Lisetner.OnGallerySelectItemListListener;
import com.sama.communicationclassjava.Lisetner.OnGalleryUploadListener;
import com.sama.communicationclassjava.SinglePattern.SelectUserInfo;

import java.util.ArrayList;

public class FireBaseModel {
    final static String TAG = "FireBaseModel";

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

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

    public void setOnSelectGalleryListener(OnGallerySelectItemListListener selectItemListListener) {
        this.selectItemListListener = selectItemListListener;
    }

    OnFailureListener bitmpaFailureListener = new OnFailureListener() {
        @Override
        public void onFailure(@NonNull Exception e) {
            mountainsRef = null;
        }
    };



    OnCompleteListener onCompleteListener = new OnCompleteListener<QuerySnapshot>(){

        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task) {
            if (task.isSuccessful()) {
                ArrayList<GalleryDetailData> list = new ArrayList<>();
                for (DocumentSnapshot document : task.getResult()) {
                    Log.d(TAG,task.toString());
                    list.add(document.toObject(GalleryDetailData.class));
                }
                selectItemListListener.OnGallerySelectItemList(list);
            }
        }
    };



    OnSuccessListener<UploadTask.TaskSnapshot> bitmapOnSuccessListener = new OnSuccessListener<UploadTask.TaskSnapshot>() {
        @Override
        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
            GalleryDetailData data = new GalleryDetailData();

            CollectionReference collection = db.collection("Gallery");
            String documentKey = collection.document().getId();
            data.Merge(userInfo);
            data.addImageUrl(mountainsRef.getPath());
            data.writeProduce();
            data.setDocumentKey(documentKey);


            db.collection("Gallery").document(documentKey).set(data);

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
        mountainsRef  = storageRef.child("Gallery")
                .child(String.valueOf((userInfo.getArea()))).child(FileName+"_"+userInfo.getUserKey()+".jpg");
        this.uploadTask = mountainsRef.putBytes(DrawingByte);
        uploadTask
                .addOnFailureListener(bitmpaFailureListener)
                .addOnSuccessListener(bitmapOnSuccessListener);
    }




    public void SelectGallerycontents(Long key){

        SelectUserInfo userInfo = SelectUserInfo.getInstance();
        if(key != null){
            db.collection("Gallery")
                    .orderBy("writeDay", Query.Direction.DESCENDING)
                    .whereLessThan("writeDay",key)
                    .whereEqualTo("area",userInfo.getArea())
                    .limit(6).get()
                    .addOnCompleteListener(onCompleteListener);
            return;
        }

        db.collection("Gallery")
                .orderBy("writeDay", Query.Direction.DESCENDING)
                .whereEqualTo("area",userInfo.getArea())
                .limit(6).get()
                .addOnCompleteListener(onCompleteListener);
    }
}
