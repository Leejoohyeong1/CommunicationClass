package com.sama.communicationclass.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sama.communicationclass.Contract.ChildAdapterContract;
import com.sama.communicationclass.Data.UserInfo;
import com.sama.communicationclass.Holder.ChildHolder;
import com.sama.communicationclass.Lisetner.UseChildClickListener;
import com.sama.communicationclass.R;

import java.util.ArrayList;
import java.util.HashSet;

public class ChildAdapter extends RecyclerView.Adapter<ChildHolder> implements ChildAdapterContract.View,ChildAdapterContract.Model {

    ArrayList<UserInfo> userInfoArrayList = new ArrayList<>();
    UseChildClickListener useChildClickListener;

    @NonNull
    @Override
    public ChildHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.use_child_choice_layout, parent, false);
        GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) v.getLayoutParams();
        lp.height = parent.getMeasuredHeight() / 2;

        v.setLayoutParams(lp);

        ChildHolder childHolder = new ChildHolder(v);

        return childHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ChildHolder holder, int position) {
        holder.onBind(userInfoArrayList.get(position),position,this.useChildClickListener);
    }

    @Override
    public int getItemCount() {
        return userInfoArrayList.size();
    }

    @Override
    public void notfyAdapter() {
        this.notifyDataSetChanged();
    }

    @Override
    public void addItems(ArrayList<UserInfo> userInfoArrayList) {
        HashSet<UserInfo>  hashSet = new HashSet<UserInfo>(userInfoArrayList);
        for(UserInfo userInfo: this.userInfoArrayList){
            hashSet.add(userInfo);
        }
        this.userInfoArrayList.addAll(hashSet);
//        this.userInfoArrayList.addAll(userInfoArrayList);
    }

    @Override
    public void addItems(UserInfo userInfoItem) {
        this.userInfoArrayList.add(userInfoItem);
    }

    @Override
    public void setUseChildClickListener(UseChildClickListener useChildClickListener) {
        this.useChildClickListener = useChildClickListener;
    }
}
