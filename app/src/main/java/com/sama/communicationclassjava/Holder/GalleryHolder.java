package com.sama.communicationclassjava.Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.sama.communicationclassjava.Data.GalleryDetailData;
import com.sama.communicationclassjava.Lisetner.OnItemClickListener;
import com.sama.communicationclassjava.R;

public class GalleryHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    OnItemClickListener listener;
    ImageView PreviewImageView;
    ImageView profileImageView;
    TextView ChildName;
    TextView BeginDate;
    StorageReference storageRef = FirebaseStorage.getInstance().getReference();
    GalleryDetailData optionItem;
    int position;


    public GalleryHolder(@NonNull View itemView) {
        super(itemView);
        this.PreviewImageView = (ImageView) itemView.findViewById(R.id.item_preview);
//        this.profileImageView =(ImageView) itemView.findViewById(R.id.profile_image);
//        this.ChildName = (TextView) itemView.findViewById(R.id.childname);
//        this.BeginDate = (TextView) itemView.findViewById(R.id.begin_date);
    }

    public void onBind(final GalleryDetailData optionItem, final int position,OnItemClickListener listener) {
        this.listener = listener;
        final FirebaseStorage storage = FirebaseStorage.getInstance();
        final StorageReference storageRef = storage
                .getReferenceFromUrl(PreviewImageView.getResources().getString(R.string.storage))
                .child(optionItem.getImageUrl().get(0));

        this.optionItem = optionItem;
        this.position = position;
        this.itemView.setOnClickListener(this);

        Glide.with(PreviewImageView.getContext())
                .load(storageRef)
                .into(PreviewImageView);
    }

    @Override
    public void onClick(View v) {
        if (this.listener != null) {
            this.listener.onItemClickListener(this.position,this.optionItem);
        }
    }
}
