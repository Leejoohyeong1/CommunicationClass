package com.sama.communicationclass.Model;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sama.communicationclass.Data.CommentData;
import com.sama.communicationclass.Lisetner.OnCommentInsertListener;
import com.sama.communicationclass.SinglePattern.SelectUserInfo;

public class FileBaseCommentInsertModel {

    private static FileBaseCommentInsertModel one;
    CommentData commentData;
    SelectUserInfo userInfo;
    OnCommentInsertListener onCommentInsertListener;
    OnCompleteListener onInsertCommentsListener = new OnCompleteListener<DocumentSnapshot>(){
        @Override
        public void onComplete(@NonNull Task<DocumentSnapshot> task) {
            if (task.isSuccessful()) {
                onCommentInsertListener.OnInsertSuccess(commentData);
                return;
            }
            onCommentInsertListener.OnInsertFailure();
        }
    };
    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    public FileBaseCommentInsertModel() {
        userInfo =  SelectUserInfo.getInstance();
    }

    public static FileBaseCommentInsertModel getInstance() {
        if (one == null) {
            one = new FileBaseCommentInsertModel();
        }
        return one;
    }

    public void setOnCommentInsertListener(OnCommentInsertListener onCommentInsertListener){
        this.onCommentInsertListener = onCommentInsertListener;
    }

    public void removeOnCommentInsertListener(){
        this.onCommentInsertListener = null;
    }

    public void insertComment(String imageKey,String galleryDocumentKey){

        CollectionReference collection = db.collection("Comments");
        String commentKey = collection.document().getId();

        this.commentData = new CommentData();
        this.commentData.setInfo(galleryDocumentKey,imageKey,commentKey);
        this.commentData.Merge(userInfo);
        collection
                .document(commentKey)
                .set(commentData)
                .addOnCompleteListener(onInsertCommentsListener);
    }



}
