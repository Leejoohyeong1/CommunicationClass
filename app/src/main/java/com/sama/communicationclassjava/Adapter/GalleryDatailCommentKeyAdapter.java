package com.sama.communicationclassjava.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sama.communicationclassjava.Contract.GalleryDetailCommentKeyAdpterContract;
import com.sama.communicationclassjava.Holder.GalleryDatailCommentKeyHolder;
import com.sama.communicationclassjava.Lisetner.OnImageKeyboardListener;
import com.sama.communicationclassjava.R;

import java.util.ArrayList;

public class GalleryDatailCommentKeyAdapter extends RecyclerView.Adapter<GalleryDatailCommentKeyHolder>
                                            implements GalleryDetailCommentKeyAdpterContract.Model
                                                     , GalleryDetailCommentKeyAdpterContract.View {
    OnImageKeyboardListener onImageKeyboardListener;
    ArrayList<String> commentKeys;
    @NonNull
    @Override
    public GalleryDatailCommentKeyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_comment_keyitem_layout, parent, false);
        GalleryDatailCommentKeyHolder commentKeyHolder = new GalleryDatailCommentKeyHolder(v);
        return commentKeyHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryDatailCommentKeyHolder holder, int position) {
        holder.onBind(this.commentKeys.get(position),position,onImageKeyboardListener);
    }

    @Override
    public int getItemCount() {
        return commentKeys.size();
    }

    @Override
    public void setCommentIconList(ArrayList<String> commentKeys) {
        this.commentKeys = commentKeys;
    }

    @Override
    public void CommentKeyAdpterNotfyAdaoter() {
        this.notifyDataSetChanged();
    }

    @Override
    public void setOnImageKeyboardListener(OnImageKeyboardListener onImageKeyboardListener) {
        this.onImageKeyboardListener = onImageKeyboardListener;
    }

    @Override
    public int commentKeyAdpterGetSize() {
        return this.commentKeys.size();
    }

    @Override
    public String commentKeyAdpterGetItem(int position) {
        return this.commentKeys.get(position);
    }

    @Override
    public void commentKeyAdpterClearItems() {
        this.commentKeys.clear();
    }
}
