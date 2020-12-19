package com.sama.communicationclass;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sama.communicationclass.SinglePattern.SelectUserInfo;

import org.junit.Test;

import java.util.ArrayList;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {




        SelectUserInfo.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        CollectionReference collection = db.collection("Users");
//        String documentKey = collection.document().getId();


        ArrayList<SelectUserInfo> list = new ArrayList<>();
        list.add(new SelectUserInfo("yecheon","yecheonkindergarten","star","이민성","1.jpg"));
        list.add(new SelectUserInfo("yecheon","yecheonkindergarten","star","박용훈","2.jpg"));
        list.add(new SelectUserInfo("yecheon","yecheonkindergarten","star","성영현","3.jpg"));
        list.add(new SelectUserInfo("yecheon","yecheonkindergarten","star","엄하준","4.jpg"));
        list.add(new SelectUserInfo("yecheon","yecheonkindergarten","star","엄태영","5.jpg"));
        list.add(new SelectUserInfo("yecheon","yecheonkindergarten","star","권한나","6.jpg"));
        list.add(new SelectUserInfo("yecheon","yecheonkindergarten","star","석도윤","7.jpg"));
        list.add(new SelectUserInfo("yecheon","yucheonelementary school","love","권도경","8.jpg"));




        for(SelectUserInfo userInfo : list){
            String documentKey = collection.document().getId();
            userInfo.setUserKey(documentKey);
            System.out.println(userInfo.insetLog());
            collection
                    .document(documentKey)
                    .set(userInfo);

        }



//
//        1.성영현 ProfileImage1.jpg
//        2.석도유
//        3.김민재 ProfileImage2.jpg
//        4.박지아 ProfileImage3.jpg



//
//        collection
//                .document(documentKey)
//                .set(info)
//                .addOnCompleteListener(new OnCompleteListener<Void>() {
//                    @Override
//                    public void onComplete(@NonNull Task<Void> task) {
//
//                    }
//                });
    }

    @Test
    public void NanuliCardDecksTest() {









    }
}