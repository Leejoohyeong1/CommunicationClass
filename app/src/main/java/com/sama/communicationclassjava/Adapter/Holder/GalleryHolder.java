package com.sama.communicationclassjava.Adapter.Holder;

import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.storage.images.FirebaseImageLoader;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.sama.communicationclassjava.Data.CommunicationItem;
import com.sama.communicationclassjava.Data.GalleryDatilData;
import com.sama.communicationclassjava.Lisetner.OnItemClickListener;
import com.sama.communicationclassjava.R;

public class GalleryHolder extends RecyclerView.ViewHolder{

    OnItemClickListener listener;
    ImageView PreviewImageView;
    ImageView profileImageView;
    TextView ChildName;
    TextView BeginDate;
    StorageReference storageRef = FirebaseStorage.getInstance().getReference();

    public GalleryHolder(@NonNull View itemView,OnItemClickListener listener) {
        super(itemView);
        this.listener = listener;
        this.PreviewImageView = (ImageView) itemView.findViewById(R.id.item_preview);
//        this.profileImageView =(ImageView) itemView.findViewById(R.id.profile_image);
//        this.ChildName = (TextView) itemView.findViewById(R.id.childname);
//        this.BeginDate = (TextView) itemView.findViewById(R.id.begin_date);
    }

    public void onBind(final GalleryDatilData optionItem, final int position) {
//        FirebaseStorage firebaseStorage = FirebaseStorage.getInstance();
//        StorageReference storageReference = firebaseStorage.getReference().child();

        final FirebaseStorage storage = FirebaseStorage.getInstance();
        final StorageReference storageRef = storage.getReferenceFromUrl("gs://communicationclass-e9cef.appspot.com").child(optionItem.getImageUrl().get(0));



        Glide.with(PreviewImageView.getContext())
                .load(storageRef)
                .into(PreviewImageView);


        itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (listener != null) {
                    listener.onItemClickListener(position,optionItem);
                }
            }
        });
    }
}
