package com.sama.communicationclassjava.Holder;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.storage.FirebaseStorage;
import com.sama.communicationclassjava.R;

public class GalleryDatailCommentKeyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    String CommentKeyfileName;
    int position;
    ImageView commentKeyView;

    public GalleryDatailCommentKeyHolder(@NonNull View itemView) {
        super(itemView);
        commentKeyView = itemView.findViewById(R.id.comment_key);

    }

    public void onBind(final String CommentKeyfileName, final int position) {
        this.position = position;
        this.CommentKeyfileName = CommentKeyfileName;

        int id = commentKeyView.getResources().getIdentifier(CommentKeyfileName, "drawable", itemView.getContext().getPackageName());
        Drawable drawable;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            drawable = commentKeyView.getResources().getDrawable(id,null);
        } else {
            drawable = commentKeyView.getResources().getDrawable(id);
        }

        commentKeyView.setBackground(drawable);
        commentKeyView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

    }
}
