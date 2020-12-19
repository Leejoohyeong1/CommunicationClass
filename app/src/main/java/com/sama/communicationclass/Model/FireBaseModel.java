package com.sama.communicationclass.Model;


import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.sama.communicationclass.Data.GalleryDetailData;
import com.sama.communicationclass.Lisetner.OnGallerySelectItemListListener;
import com.sama.communicationclass.SinglePattern.SelectUserInfo;

import java.util.ArrayList;

public class FireBaseModel {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private SelectUserInfo userInfo;
    private OnGallerySelectItemListListener selectItemListListener;

    private static FireBaseModel one;

    public static FireBaseModel getInstance() {
        if (one == null) {
            one = new FireBaseModel();
        }
        return one;
    }

    public void setOnSelectGalleryListener(OnGallerySelectItemListListener selectItemListListener) {
        this.selectItemListListener = selectItemListListener;
    }

    OnCompleteListener onCompleteListener = new OnCompleteListener<QuerySnapshot>(){

        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task) {
            if (task.isSuccessful()) {
                ArrayList<GalleryDetailData> list = new ArrayList<>();
                for (DocumentSnapshot document : task.getResult()) {
                    if (document.exists()) {
                        GalleryDetailData galleryDetailData = document.toObject(GalleryDetailData.class);
                        list.add(galleryDetailData);
                    }
                }
                selectItemListListener.OnGallerySelectItemList(list);
            }
        }
    };

    public FireBaseModel(){
        userInfo =  SelectUserInfo.getInstance();

    }

    public void deleteGalleryContents(String documentKey){


        db.collection("ContentsGallery").document(documentKey).delete().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

            }
        });
    }

    public void SelectGalleryContents(Long key){

        SelectUserInfo userInfo = SelectUserInfo.getInstance();

        if(key != null){
            db.collection("ContentsGallery")
                    .orderBy("writeDay", Query.Direction.DESCENDING)
                    .whereLessThan("writeDay",key)
                    .whereEqualTo("area",userInfo.getArea())
                    .whereEqualTo("group",userInfo.getGroup())
                    .whereEqualTo("className",userInfo.getClassName())
                    .limit(10).get()
                    .addOnCompleteListener(onCompleteListener);
            return;
        }


        db.collection("ContentsGallery")
                .orderBy("writeDay", Query.Direction.DESCENDING)
                .whereEqualTo("area",userInfo.getArea())
                .whereEqualTo("group",userInfo.getGroup())
                .whereEqualTo("className",userInfo.getClassName())
                .limit(10).get()
                .addOnCompleteListener(onCompleteListener);
    }
}
