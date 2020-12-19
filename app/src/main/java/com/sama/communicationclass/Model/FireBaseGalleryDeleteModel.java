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
import com.sama.communicationclass.Lisetner.OnGalleryDeleteListener;
import com.sama.communicationclass.Lisetner.OnGallerySelectItemListListener;
import com.sama.communicationclass.SinglePattern.SelectUserInfo;

import java.util.ArrayList;

public class FireBaseGalleryDeleteModel {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private SelectUserInfo userInfo;
    private OnGalleryDeleteListener onGalleryDeleteListener;

    private static FireBaseGalleryDeleteModel one;

    public static FireBaseGalleryDeleteModel getInstance() {
        if (one == null) {
            one = new FireBaseGalleryDeleteModel();
        }
        return one;
    }

    public FireBaseGalleryDeleteModel setOnGalleryDeleteListener(OnGalleryDeleteListener onGalleryDeleteListener) {
        this.onGalleryDeleteListener = onGalleryDeleteListener;
        return this;
    }

    OnCompleteListener onCompleteListener = new OnCompleteListener<QuerySnapshot>(){

        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task) {
            if (task.isSuccessful()) {
                Log.d("DeleteModel","됨");
                onGalleryDeleteListener.OnGalleryDelete();
            }else {
                Log.d("DeleteModel","안됨 됩");
            }
        }
    };

    public FireBaseGalleryDeleteModel(){
        userInfo =  SelectUserInfo.getInstance();

    }



    public void deleteGalleryContents(String documentKey){
        Log.d("DeleteModel",documentKey);
        db.collection("ContentsGallery")
                .document(documentKey)
                .delete()
        .addOnCompleteListener(onCompleteListener);


    }
}
