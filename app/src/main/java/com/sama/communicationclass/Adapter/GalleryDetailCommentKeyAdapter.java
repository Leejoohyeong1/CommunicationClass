package com.sama.communicationclass.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sama.communicationclass.Contract.GalleryDetailCommentKeyAdpterContract;
import com.sama.communicationclass.Holder.GalleryDetailCommentKeyHolder;
import com.sama.communicationclass.Lisetner.OnImageKeyboardListener;
import com.sama.communicationclass.R;

import java.util.ArrayList;

public class GalleryDetailCommentKeyAdapter extends RecyclerView.Adapter<GalleryDetailCommentKeyHolder>
                                            implements GalleryDetailCommentKeyAdpterContract.Model
                                                     , GalleryDetailCommentKeyAdpterContract.View {
    OnImageKeyboardListener onImageKeyboardListener;
    ArrayList<String> commentKeys;
    @NonNull
    @Override
    public GalleryDetailCommentKeyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_comment_keyitem_layout, parent, false);
        GalleryDetailCommentKeyHolder commentKeyHolder = new GalleryDetailCommentKeyHolder(v);
        return commentKeyHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryDetailCommentKeyHolder holder, int position) {
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
    public void notfyAdapter() {
        this.notifyDataSetChanged();
    }
    @Override
    public void setOnImageKeyboardListener(OnImageKeyboardListener onImageKeyboardListener) {
        this.onImageKeyboardListener = onImageKeyboardListener;
    }


    @Override
    public int commentKeyAdapterGetSize() {
        return this.commentKeys.size();
    }

    @Override
    public String commentKeyAdapterGetItem(int position) {
        return this.commentKeys.get(position);
    }

    @Override
    public void commentKeyAdapterClearItems() {
        this.commentKeys.clear();
    }
}
