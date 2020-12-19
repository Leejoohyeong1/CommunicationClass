package com.sama.communicationclass.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sama.communicationclass.Contract.GalleryDetailImageAdpterContract;
import com.sama.communicationclass.Holder.GalleryDetailImageHolder;
import com.sama.communicationclass.R;

import java.util.ArrayList;

public class GalleryDetailImagesAdapter extends RecyclerView.Adapter<GalleryDetailImageHolder>
        implements GalleryDetailImageAdpterContract.Model
                ,  GalleryDetailImageAdpterContract.View {

    ArrayList<String> imagesSrcList;

    @NonNull
    @Override
    public GalleryDetailImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.detail_contents_image_item_layout, parent, false);
        GalleryDetailImageHolder holder = new GalleryDetailImageHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryDetailImageHolder holder, int position) {
        holder.onBind(this.imagesSrcList.get(position),position);
    }


    @Override
    public void notfyAdapter() {
        this.notifyDataSetChanged();
    }

    @Override
    public int imageAdapterGetSize() {
        return this.imagesSrcList.size();
    }

    @Override
    public String imageAdapterGetItem(int position) {
        return this.imagesSrcList.get(position);
    }

    @Override
    public void imageAdapterClearItems() {
        this.imagesSrcList.clear();
    }

    @Override
    public int getItemCount() {
        return this.imagesSrcList.size();
    }
    @Override
    public void setImagesSrcList(ArrayList<String> imagesSrc) {
        this.imagesSrcList = imagesSrc;
    }
}
