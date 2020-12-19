package com.sama.communicationclass.Model;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.sama.communicationclass.Data.UserInfo;
import com.sama.communicationclass.Lisetner.OnChildSelectListener;

import java.util.ArrayList;

public class FireBaseUserSelectModule {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private static FireBaseUserSelectModule one;

    private OnChildSelectListener onChildSelectListener;

    private OnCompleteListener onUseChildListener =  new OnCompleteListener<QuerySnapshot>(){

        @Override
        public void onComplete(@NonNull Task<QuerySnapshot> task) {
            ArrayList<UserInfo> list = new ArrayList<>();
            if (task.isSuccessful()) {

                for (DocumentSnapshot document : task.getResult()) {
                    list.add(document.toObject(UserInfo.class));
                }

            }
            onChildSelectListener.onChildData(list);
        }
    };


    public void setOnChildSelectListener(OnChildSelectListener onChildSelectListener) {
        this.onChildSelectListener = onChildSelectListener;
    }

    public static FireBaseUserSelectModule getInstance() {
        if (one == null) {
            one = new FireBaseUserSelectModule();
        }
        return one;
    }

    public void SelectUserData(ArrayList<String> userKeys){
        if(userKeys.size() <= 0){
            return;
        }
        db.collection("Users")
                .whereIn("userKey",userKeys)
                .get()
                .addOnCompleteListener(onUseChildListener);
    }


    public void SelectSingleUserData(String userKeys){
        db.collection("Users")
                .whereEqualTo("userKey",userKeys)
                .get()
                .addOnCompleteListener(onUseChildListener);
    }


}
