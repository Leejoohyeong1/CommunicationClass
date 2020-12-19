package com.sama.communicationclass.Model;


import android.graphics.Bitmap;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sama.communicationclass.Data.GalleryDetailData;
import com.sama.communicationclass.Data.NanuliCard;
import com.sama.communicationclass.Lisetner.OnGalleryUploadListener;
import com.sama.communicationclass.Lisetner.OnFileUploadCompleteListener;
import com.sama.communicationclass.SinglePattern.SelectUserInfo;

import java.util.ArrayList;

public class FireBaseFileUpLoadModule implements OnFileUploadCompleteListener, OnCompleteListener<Void>

{

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private OnGalleryUploadListener listener;
    SelectUserInfo userInfo;
    FireBaseFileUpLoadTask task;
    GalleryDetailData data;
    ArrayList<NanuliCard> deck = null;

    private static FireBaseFileUpLoadModule one;

    public static FireBaseFileUpLoadModule getInstance() {
        if (one == null) {
            one = new FireBaseFileUpLoadModule();
        }
        return one;
    }

    public FireBaseFileUpLoadModule(){
        userInfo =  SelectUserInfo.getInstance();
        task = new FireBaseFileUpLoadTask().addListener(this);
    }



    @Override
    public void OnComplete(ArrayList<String> filePaths) {
        this.insertGallery(filePaths);
    }

    private void insertGallery(ArrayList<String> filePaths){
        CollectionReference collection = db.collection("ContentsGallery");
        String documentKey = collection.document().getId();
        data = new GalleryDetailData();
        data.Merge(userInfo);
        data.addImageUrl(filePaths);
        data.writeProduce();
        data.setDocumentKey(documentKey);
        data.setDecks(this.deck);



        collection
                .document(documentKey)
                .set(data)
        .addOnCompleteListener(this);
    }

    public FireBaseFileUpLoadModule addGalleryUploadListener(OnGalleryUploadListener listener){
        this.listener =listener;
        return this;
    }

    public FireBaseFileUpLoadModule execute(Bitmap[] bitmaps){
        task.execute(bitmaps);
        return this;
    }

    public FireBaseFileUpLoadModule execute(Bitmap[] bitmaps,ArrayList<NanuliCard> deck){
        this.deck = deck;
        task.execute(bitmaps);
        return this;
    }

    @Override
    public void onComplete(@NonNull Task<Void> task) {
        if(task.isSuccessful()){
            listener.OnGalleryUploadListener(true,data);
        }else{
            listener.OnGalleryUploadListener(true,data);
        }
    }
}
