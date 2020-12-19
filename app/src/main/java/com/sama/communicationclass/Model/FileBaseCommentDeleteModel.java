package com.sama.communicationclass.Model;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sama.communicationclass.Data.CommentData;
import com.sama.communicationclass.Lisetner.OnCommentDeleteListener;
import com.sama.communicationclass.SinglePattern.SelectUserInfo;

public class FileBaseCommentDeleteModel {

    private static FileBaseCommentDeleteModel one;
    OnCommentDeleteListener onCommentDeleteListener;
    SelectUserInfo userInfo;
    int position;
    OnCompleteListener OnremoveCompleteListener = new OnCompleteListener() {
        @Override
        public void onComplete(@NonNull Task task) {
            if(task.isSuccessful()){
                onCommentDeleteListener.OnDeleteSuccess(position);
                return;
            }
            onCommentDeleteListener.OnDeleteFailure();


        }
    };
    private FirebaseFirestore db = FirebaseFirestore.getInstance();


    public FileBaseCommentDeleteModel() {
        userInfo =  SelectUserInfo.getInstance();
    }

    public static FileBaseCommentDeleteModel getInstance() {
        if (one == null) {
            one = new FileBaseCommentDeleteModel();
        }
        return one;
    }

    public void setonCommentDeleteListener(OnCommentDeleteListener onCommentDeleteListener){
        this.onCommentDeleteListener = onCommentDeleteListener;
    }

    public void deleteComment(CommentData commentData, int position){
        this.position = position;
        CollectionReference collection = db.collection("Comments");
        collection
                .document(commentData.getCommentKey())
                .delete()
                .addOnCompleteListener(OnremoveCompleteListener);
    }

}
