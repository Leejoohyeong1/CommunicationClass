package com.sama.communicationclass.Holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.StorageReference;
import com.sama.communicationclass.Data.UserInfo;
import com.sama.communicationclass.Lisetner.UseChildClickListener;
import com.sama.communicationclass.Model.FireBaseImageLoaderModule;
import com.sama.communicationclass.R;

public class ChildHolder extends RecyclerView.ViewHolder implements View.OnClickListener {


    private UserInfo userInfo;
    private TextView childName;
    private ImageView childProfileImage;
    private UseChildClickListener useChildClickListener;
    private View view;

    private int position;

    public ChildHolder(@NonNull View itemView) {
        super(itemView);
        this.view = itemView.findViewById(R.id.use_child_choice_view);
        this.childName = (TextView) itemView.findViewById(R.id.use_child_choice_textView);
        this.childProfileImage = (ImageView) itemView.findViewById(R.id.use_child_choice_imageView);
        this.view.setOnClickListener(this);
    }

    public void onBind(UserInfo userInfo, int position,UseChildClickListener useChildClickListener){
        this.userInfo = userInfo;
        this.position = position;
        this.useChildClickListener = useChildClickListener;

        final StorageReference storageRef = FireBaseImageLoaderModule.getInstance().getImageLoader(userInfo.getProfileImage());

        Glide.with(this.childProfileImage.getContext())
                .load(storageRef)
                .into(this.childProfileImage);
        childName.setText(this.userInfo.getProfileName());

        this.itemView.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        useChildClickListener.OnChildClickListener(this.userInfo,this.position);
    }
}
