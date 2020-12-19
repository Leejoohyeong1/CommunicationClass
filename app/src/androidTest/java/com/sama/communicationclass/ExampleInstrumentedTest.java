package com.sama.communicationclass;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.sama.communicationclass.SinglePattern.SelectUserInfo;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
    @Test
    public void useAppContext() {

        SelectUserInfo.getInstance();
        FirebaseFirestore db = FirebaseFirestore.getInstance();

        CollectionReference collection = db.collection("Users");


        ArrayList<SelectUserInfo> list = new ArrayList<>();
        list.add(new SelectUserInfo("yecheon","yecheonkindergarten","star","이민성","1.jpg"));
        list.add(new SelectUserInfo("yecheon","yecheonkindergarten","star","박용훈","2.jpg"));
        list.add(new SelectUserInfo("yecheon","yecheonkindergarten","star","성영현","3.jpg"));
        list.add(new SelectUserInfo("yecheon","yecheonkindergarten","star","엄하준","4.jpg"));
        list.add(new SelectUserInfo("yecheon","yecheonkindergarten","star","엄태영","5.jpg"));
        list.add(new SelectUserInfo("yecheon","yecheonkindergarten","star","권한나","6.jpg"));
        list.add(new SelectUserInfo("yecheon","yecheonkindergarten","star","석도윤","7.jpg"));
        list.add(new SelectUserInfo("yecheon","yucheonelementary_school","love","권도경","8.jpg"));




        for(SelectUserInfo userInfo : list){
            String documentKey = collection.document().getId();
            userInfo.setUserKey(documentKey);
            System.out.println(userInfo.insetLog());
            collection
                    .document(documentKey)
                    .set(userInfo)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {

                    }
            });

        }

        assertEquals("com.sama.communicationclass", appContext.getPackageName());
    }
}
