package com.sama.communicationclass.Model;

import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class FireBaseImageLoaderModule {


    final FirebaseStorage storage = FirebaseStorage.getInstance();

    private static FireBaseImageLoaderModule one;

    public static FireBaseImageLoaderModule getInstance() {
        if (one == null) {
            one = new FireBaseImageLoaderModule();
        }
        return one;
    }


    public StorageReference getImageLoader(String url){
        StorageReference storageRef = storage
                .getReferenceFromUrl("gs://communicationclass-e9cef.appspot.com")
                .child(url);
        return storageRef;
    }

}
