package com.sama.communicationclass.Model;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.sama.communicationclass.Lisetner.OnFileUploadCompleteListener;
import com.sama.communicationclass.SinglePattern.SelectUserInfo;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.ExecutionException;


public class FireBaseFileUpLoadTask extends AsyncTask<Bitmap, Void, Boolean> implements
                                                                                    OnFailureListener
                                                                                    , OnSuccessListener<UploadTask.TaskSnapshot> {
    private StorageReference storageRef = FirebaseStorage.getInstance().getReference();
    private StorageReference mountainsRef = null;
    private OnFileUploadCompleteListener onFileUploadCompleteListener;

    private int cursor = 0;
    private SelectUserInfo userInfo;
    private int fileCount;

    private ArrayList<String> completeList = new ArrayList<>();

    FireBaseFileUpLoadTask(){
        this.userInfo = SelectUserInfo.getInstance();
    }

    @Override
    protected Boolean doInBackground(Bitmap... bitmaps) {
        final ArrayList<UploadTask> tasks = new ArrayList<>();

        fileCount = bitmaps.length;
        while(cursor < bitmaps.length){
            Bitmap bitmap = bitmapReSize(bitmaps[cursor]);
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] data = baos.toByteArray();

            mountainsRef  = storageRef.child("Gallery")
                    .child((userInfo.getArea())).child(new Date().getTime() +"_"+userInfo.getUserKey()+".jpg");
            UploadTask uploadTask = mountainsRef.putBytes(data);
            uploadTask.addOnFailureListener(this);
            uploadTask.addOnSuccessListener(this);
            tasks.add(uploadTask);
            cursor++;
        }

        try {
            Tasks.await(Tasks.whenAll(tasks));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return Tasks.forResult(tasks).isSuccessful();

    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);
        Log.d("Upload","Success");
        onFileUploadCompleteListener.OnComplete(completeList);
    }

    @Override
    public void onFailure(@NonNull Exception e) {

    }

    @Override
    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
        completeList.add(taskSnapshot.getStorage().getPath());
    }

    private Bitmap bitmapReSize(Bitmap bitmap){

        int width = bitmap.getWidth();
        int height = bitmap.getHeight();

        while ((width * height * 4) >= 2000000){
            width *= 0.95;
            height *= 0.95;
        }

        Bitmap resized = Bitmap.createScaledBitmap(bitmap, width, height, true);

        return resized;
    }

    public FireBaseFileUpLoadTask addListener(OnFileUploadCompleteListener onFileUploadCompleteListener){
        this.onFileUploadCompleteListener = onFileUploadCompleteListener;
        return this;
    }
}
