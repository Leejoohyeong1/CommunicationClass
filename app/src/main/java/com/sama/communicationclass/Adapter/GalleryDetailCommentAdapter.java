package com.sama.communicationclass.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sama.communicationclass.Contract.GalleryDetailCommentAdpterContract;
import com.sama.communicationclass.Data.CommentData;
import com.sama.communicationclass.Holder.GalleryDetailCommentHolder;
import com.sama.communicationclass.Lisetner.OnCommentDeletePressListener;
import com.sama.communicationclass.R;

import java.util.ArrayList;

public class GalleryDetailCommentAdapter extends RecyclerView.Adapter<GalleryDetailCommentHolder>
                                                                implements GalleryDetailCommentAdpterContract.View
                                                                         , GalleryDetailCommentAdpterContract.Model{

    OnCommentDeletePressListener onCommentDeletePressListener;
    ArrayList<CommentData> itemsCommentData = new ArrayList<>();
    @NonNull
    @Override
    public GalleryDetailCommentHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_comment_item_layout, parent, false);
        GalleryDetailCommentHolder commentHolder = new GalleryDetailCommentHolder(v);
        return commentHolder;


    }

    @Override
    public void onBindViewHolder(@NonNull GalleryDetailCommentHolder holder, int position) {
        holder.onBind(this.itemsCommentData.get(position),position,onCommentDeletePressListener);
    }

    @Override
    public int getItemCount() {
        return itemsCommentData.size();
    }

    @Override
    public void notfyAdapter() {
        this.notifyDataSetChanged();
    }

    @Override
    public void setOnCommentDeletePressListener(OnCommentDeletePressListener onCommentDeletePressListener) {
        this.onCommentDeletePressListener = onCommentDeletePressListener;
    }

    @Override
    public void setCommentItems(ArrayList<CommentData> items) {
        itemsCommentData.addAll(items);
    }

    @Override
    public void addCommentItem(CommentData item) {
        itemsCommentData.add(item);
    }

    @Override
    public void removeCommentItem(int position) {
        itemsCommentData.remove(position);
    }

    @Override
    public int getLastSize() {
        return itemsCommentData.size();
    }
}
