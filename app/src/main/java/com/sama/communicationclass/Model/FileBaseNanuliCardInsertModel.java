package com.sama.communicationclass.Model;

import com.google.firebase.firestore.FirebaseFirestore;

public class FileBaseNanuliCardInsertModel {

    private FirebaseFirestore db = FirebaseFirestore.getInstance();

    private static FileBaseNanuliCardInsertModel one;

    public static FileBaseNanuliCardInsertModel getInstance() {
        if (one == null) {
            one = new FileBaseNanuliCardInsertModel();
        }
        return one;
    }





}
