package com.sama.communicationclassjava.Holder;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.sama.communicationclassjava.Data.CommentData;
import com.sama.communicationclassjava.Lisetner.OnCommentDeletePressListener;
import com.sama.communicationclassjava.R;
import com.sama.communicationclassjava.SinglePattern.SelectUserInfo;


public class GalleryDetailCommentHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private int position;
    private CommentData commentData;
    private ImageView commentIcon;
    private ImageView commentProfileImage;
    private TextView commentName;
    private ImageView deleteBtn;
    private OnCommentDeletePressListener onCommentDeletePressListener;



    public GalleryDetailCommentHolder(@NonNull View itemView) {
        super(itemView);

        this.commentProfileImage = (ImageView) itemView.findViewById(R.id.detail_comment_profile);
        this.commentName = (TextView) itemView.findViewById(R.id.detail_comment_name);
        this.deleteBtn =  (ImageView) itemView.findViewById(R.id.comment_delete);
        this.commentIcon = (ImageView)  itemView.findViewById(R.id.comment_icon);

    }

    public void onBind(final CommentData commentData, final int position,OnCommentDeletePressListener onCommentDeletePressListener) {
        this.position = position;
        this.onCommentDeletePressListener = onCommentDeletePressListener;
        this.commentData = commentData;
        int id = commentIcon.getResources().getIdentifier(this.commentData.getImageKey(), "drawable", itemView.getContext().getPackageName());
        Drawable drawable;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            drawable = commentIcon.getResources().getDrawable(id,null);
        } else {
            drawable = commentIcon.getResources().getDrawable(id);
        }

        commentName.setText(commentData.getProfileName());

        final FirebaseStorage storage = FirebaseStorage.getInstance();
        final StorageReference storageRef = storage
                .getReferenceFromUrl(commentProfileImage.getResources().getString(R.string.storage))
                .child(commentData.getProfileImage());
        Log.d("getProfileImage()",commentData.getProfileImage());
        Glide.with(commentProfileImage.getContext())
                .load(storageRef)
                .dontTransform()
                .into(commentProfileImage);

        Glide.with(commentIcon.getContext())
                .load(drawable)
                .dontTransform()
                .into(commentIcon);


        if(!commentData.getWriteUserKey().equals(SelectUserInfo.getInstance().getUserKey())){
            this.deleteBtn.setVisibility(View.GONE);
        }

        this.deleteBtn.setOnClickListener(this);


    }
    @Override
    public void onClick(View v) {
        onCommentDeletePressListener.OnDeletePress(commentData,position);
    }
}
