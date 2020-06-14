package com.sama.communicationclassjava.Model;


import android.provider.SyncStateContract;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.sama.communicationclassjava.Data.GalleryDatilData;
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

    public void setSelectItemListListener(OnGallerySelectItemListListener selectItemListListener) {
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
                ArrayList<GalleryDatilData> list = new ArrayList<>();
                for (DocumentSnapshot document : task.getResult()) {
                    Log.d(TAG,task.toString());
                    list.add(document.toObject(GalleryDatilData.class));
                }
                selectItemListListener.OnGallerySelectItemList(list);
            }
        }
    };

    OnSuccessListener<UploadTask.TaskSnapshot> bitmapOnSuccessListener = new OnSuccessListener<UploadTask.TaskSnapshot>() {
        @Override
        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
            GalleryDatilData data = new GalleryDatilData();

            CollectionReference collection= db.collection("Gallery_"+userInfo.getArea());
            String documentKey = collection.document().getId();
            data.Merge(userInfo);
            data.addImageUrl(mountainsRef.getPath());
            data.writeProduce();
            data.setDocumentKey(documentKey);


            db.collection("Gallery_"+userInfo.getArea()).document(documentKey).set(data);

//            DocumentReference document= db.collection("Gallery")
//                    .document(userInfo.getArea());
//
//            String collectionKey = document.getId();
//            data.Merge(userInfo);
//            data.addImageUrl(mountainsRef.getPath());
//            data.writeProduce();
//            data.setDocumentKey(collectionKey);
//            document.collection(collectionKey).add(data);







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

        mountainsRef  = storageRef.child("Gallery_"+userInfo.getArea())
                .child(String.valueOf((userInfo.getArea()))).child(FileName+"_"+userInfo.getUUID()+".jpg");
        this.uploadTask = mountainsRef.putBytes(DrawingByte);
        uploadTask
                .addOnFailureListener(bitmpaFailureListener)
                .addOnSuccessListener(bitmapOnSuccessListener);
    }


    public void SelectGallerycontents(Long key){

        SelectUserInfo userInfo = SelectUserInfo.getInstance();
        if(key != null){
            db.collection("Gallery_"+userInfo.getArea())
                    .whereLessThan("writeDay",key)
                    .orderBy("writeDay", Query.Direction.DESCENDING)
                    .limit(2).get()
                    .addOnCompleteListener(onCompleteListener);
            return;
        }


        db.collection("Gallery_"+userInfo.getArea())
                .orderBy("writeDay", Query.Direction.DESCENDING)
                .limit(2).get()
                .addOnCompleteListener(onCompleteListener);


    }
}
