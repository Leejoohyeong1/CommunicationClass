package com.sama.communicationclass.Holder;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sama.communicationclass.Lisetner.OnImageKeyboardListener;
import com.sama.communicationclass.R;

import java.util.Date;

public class GalleryDetailCommentKeyHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    String CommentKeyfileName;
    int position;
    ImageView commentKeyView;
    OnImageKeyboardListener onImageKeyboardListener;
    long doublePressTime;


    public GalleryDetailCommentKeyHolder(@NonNull View itemView) {
        super(itemView);
        commentKeyView = itemView.findViewById(R.id.comment_key);

    }

    public void onBind(final String CommentKeyfileName, final int position,OnImageKeyboardListener onImageKeyboardListener) {
        this.position = position;
        this.CommentKeyfileName = CommentKeyfileName;
        this.onImageKeyboardListener = onImageKeyboardListener;

        int id = commentKeyView.getResources().getIdentifier(CommentKeyfileName, "drawable", itemView.getContext().getPackageName());
        Drawable drawable;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            drawable = commentKeyView.getResources().getDrawable(id,null);
        } else {
            drawable = commentKeyView.getResources().getDrawable(id);
        }
        doublePressTime = new Date().getTime();
        commentKeyView.setBackground(drawable);
        commentKeyView.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(new Date().getTime() - doublePressTime < 500){
            onImageKeyboardListener.OnKeyDoublePress(this.CommentKeyfileName, position);
        }else{
            onImageKeyboardListener.OnKeyPress(this.CommentKeyfileName, position);
        }
        doublePressTime = new Date().getTime();





    }
}
