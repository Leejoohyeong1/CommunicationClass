package com.sama.communicationclassjava.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sama.communicationclassjava.Adapter.Holder.GalleryHolder;
import com.sama.communicationclassjava.Contract.GalleryAdapterContract;
import com.sama.communicationclassjava.Data.CommunicationItem;
import com.sama.communicationclassjava.Data.GalleryDatilData;
import com.sama.communicationclassjava.Lisetner.OnItemClickListener;
import com.sama.communicationclassjava.R;

import java.util.ArrayList;
import java.util.List;

public class GalleryAdapter extends RecyclerView.Adapter<GalleryHolder> implements GalleryAdapterContract.Model, GalleryAdapterContract.View{

    ArrayList<GalleryDatilData> ListItem;


    OnItemClickListener onItemClickListener = null;


    public GalleryAdapter() {
        super();
        this.ListItem = new ArrayList<>();
    }

    @NonNull
    @Override
    public GalleryHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.gallerylistitem, parent, false);
        GalleryHolder holder = new GalleryHolder(v,onItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull GalleryHolder holder, int position) {
        holder.onBind(this.ListItem.get(position),position);
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
    public GalleryDatilData LastItem() {
        if(this.ListItem.size() - 1 < 0){
            return null;
        }
        return this.ListItem.get(this.ListItem.size() - 1);
    }

    @Override
    public void addItems(ArrayList<GalleryDatilData> galleryDatilData) {
        this.ListItem.addAll(galleryDatilData);
    }

    @Override
    public int getSize() {
        return this.ListItem.size();
    }

    @Override
    public void addItem(GalleryDatilData item) {
        this.ListItem.add(item);
    }

    @Override
    public GalleryDatilData getItem(int position) {
        return this.ListItem.get(position);
    }

    @Override
    public void clearItems() {
        this.ListItem.clear();
    }
}
