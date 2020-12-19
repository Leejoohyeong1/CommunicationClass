package com.sama.communicationclass.Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.sama.communicationclass.Data.GalleryDetailData;
import com.sama.communicationclass.Lisetner.OnItemClickListener;
import com.sama.communicationclass.R;

public class GalleryHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    OnItemClickListener listener;
    ImageView PreviewImageView;
    ImageView profileImageView;
    TextView ChildName;
    GalleryDetailData optionItem;
    int position;


    public GalleryHolder(@NonNull View itemView) {
        super(itemView);
        this.PreviewImageView = (ImageView) itemView.findViewById(R.id.item_preview);
        this.profileImageView =(ImageView) itemView.findViewById(R.id.card_profile_image);
        this.ChildName = (TextView) itemView.findViewById(R.id.card_profile_name);


    }

    public void onBind(final GalleryDetailData optionItem, final int position,OnItemClickListener listener) {
        this.listener = listener;
        FirebaseStorage storage = FirebaseStorage.getInstance();

        final StorageReference storageRef = storage
                .getReferenceFromUrl(PreviewImageView.getResources().getString(R.string.storage))
                .child(optionItem.getImageUrl().get(0));



        final StorageReference profileImageStorageRef = storage
                .getReferenceFromUrl(PreviewImageView.getResources().getString(R.string.storage))
                .child(optionItem.getProfileImage());

        this.optionItem = optionItem;
        this.position = position;
        this.itemView.setOnClickListener(this);


        this.ChildName.setText(optionItem.getProfileName());


        Glide.with(profileImageView.getContext())
                .load(profileImageStorageRef)
                .dontTransform()
                .into(profileImageView);

        Glide.with(PreviewImageView.getContext())
                .load(storageRef)
                .dontTransform()
                .into(PreviewImageView);
    }

    @Override
    public void onClick(View v) {
        if (this.listener != null) {
            this.listener.onItemClickListener(this.position,this.optionItem);
        }
    }
}
