package com.sama.communicationclass.Model;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.sama.communicationclass.Data.CommentData;
import com.sama.communicationclass.Lisetner.OnGallerySelectCommentsListener;
import com.sama.communicationclass.SinglePattern.SelectUserInfo;

import java.util.ArrayList;

public class FileBaseCommentSelectModel {

    private static FileBaseCommentSelectModel one;
    SelectUserInfo userInfo;
    OnGallerySelectCommentsListener onGallerySelectCommentsListener;
    OnCompleteListener onDetailCommentsListener = new OnCompleteListener<QuerySnapshot>(){

        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task) {

            if (task.isSuccessful()) {
                ArrayList<CommentData> list = new ArrayList<>();
                for (DocumentSnapshot document : task.getResult()) {
                    list.add(document.toObject(CommentData.class));
                }
                onGallerySelectCommentsListener.OnCommentItems(list);
            }
        }
    };
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public FileBaseCommentSelectModel() {
        userInfo =  SelectUserInfo.getInstance();
    }

    public static FileBaseCommentSelectModel getInstance() {
        if (one == null) {
            one = new FileBaseCommentSelectModel();
        }
        return one;
    }

    public void setOnSelectGalleryCommentsListener(OnGallerySelectCommentsListener commentsListener) {
        this.onGallerySelectCommentsListener = commentsListener;
    }

    public void SelectGalleryDetailComments(String galleryDocumentKey){
        db.collection("Comments")
                .whereEqualTo("galleryDocumentKey",galleryDocumentKey)
                .get()
                .addOnCompleteListener(onDetailCommentsListener);
    }


}
