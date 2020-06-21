package com.sama.communicationclassjava.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sama.communicationclassjava.Holder.GalleryHolder;
import com.sama.communicationclassjava.Contract.GalleryAdapterContract;
import com.sama.communicationclassjava.Data.GalleryDetailData;
import com.sama.communicationclassjava.Lisetner.OnItemClickListener;
import com.sama.communicationclassjava.R;

import java.util.ArrayList;
import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryHolder> implements GalleryAdapterContract.Model, GalleryAdapterContract.View{

    ArrayList<GalleryDetailData> ListItem;


    OnItemClickListener onItemClickListener = null;


    public GalleryAdapter() {
        super();
        this.ListItem = new ArrayList<>();
    }

    @NonNull
    @Override
    public GalleryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallerylistitem, parent, false);
        GalleryHolder holder = new GalleryHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryHolder holder, int position) {
        holder.onBind(this.ListItem.get(position),position,onItemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryHolder holder, int position, @NonNull List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemCount() {
        return ListItem.size();
    }

    @Override
    public void notfyAdaoter() {
//        this.notify();
        this.notifyDataSetChanged();
    }

    @Override
    public void setOnClickLisetner(OnItemClickListener clickLisetner) {
        onItemClickListener = clickLisetner;
    }


    @Override
    public GalleryDetailData LastItem() {
        if(this.ListItem.size() - 1 < 0){
            return null;
        }
        return this.ListItem.get(this.ListItem.size() - 1);
    }

    @Override
    public void addItems(ArrayList<GalleryDetailData> galleryDatilData) {
        this.ListItem.addAll(galleryDatilData);
    }

    @Override
    public int getSize() {
        return this.ListItem.size();
    }

    @Override
    public void addItem(GalleryDetailData item) {
        this.ListItem.add(item);
    }

    @Override
    public GalleryDetailData getItem(int position) {
        return this.ListItem.get(position);
    }

    @Override
    public void clearItems() {
        this.ListItem.clear();
    }
}
