package com.sama.communicationclass.Holder;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.sama.communicationclass.R;

public class GalleryDetailImageHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    ImageView contentsImageView;
    String imageSrc;
    int position;

    public GalleryDetailImageHolder(@NonNull View itemView) {
        super(itemView);

        contentsImageView = (ImageView) itemView.findViewById(R.id.detail_contents);
    }

    public void onBind(final String imageSrc, final int position) {
        this.position = position;
        this.imageSrc = imageSrc;

        final FirebaseStorage storage = FirebaseStorage.getInstance();
        final StorageReference storageRef = storage
                .getReferenceFromUrl(contentsImageView.getResources().getString(R.string.storage))
                .child(this.imageSrc);

        Glide.with(contentsImageView.getContext())
                .load(storageRef)
                .dontTransform()
                .into(contentsImageView);

        contentsImageView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

    }
}
