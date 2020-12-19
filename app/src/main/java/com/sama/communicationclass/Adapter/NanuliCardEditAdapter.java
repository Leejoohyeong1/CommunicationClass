package com.sama.communicationclass.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sama.communicationclass.Contract.NanuliCardEditAdapterContract;
import com.sama.communicationclass.Data.NanuliCard;
import com.sama.communicationclass.Holder.NanuliCardKeyHolder;
import com.sama.communicationclass.Lisetner.OnNanuliCardClickListener;
import com.sama.communicationclass.R;

import java.util.ArrayList;

public class NanuliCardEditAdapter extends RecyclerView.Adapter<NanuliCardKeyHolder> implements NanuliCardEditAdapterContract.Model
        , NanuliCardEditAdapterContract.View {

    ArrayList<NanuliCard> nanuliEditDeck = new ArrayList<>();
    boolean editMode = false;
    OnNanuliCardClickListener listener;



    public NanuliCardEditAdapter(){
        nanuliEditDeck.add(new NanuliCard(0,0,""));
        nanuliEditDeck.add(new NanuliCard(0,0,""));
        nanuliEditDeck.add(new NanuliCard(0,0,""));
        nanuliEditDeck.add(new NanuliCard(0,0,""));
        nanuliEditDeck.add(new NanuliCard(0,0,""));
        nanuliEditDeck.add(new NanuliCard(0,0,""));
        nanuliEditDeck.add(new NanuliCard(0,0,""));
        nanuliEditDeck.add(new NanuliCard(0,0,""));
        nanuliEditDeck.add(new NanuliCard(0,0,""));

    }

    @NonNull
    @Override
    public NanuliCardKeyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.nanuli_card_layout, parent, false);
        GridLayoutManager.LayoutParams lp = (GridLayoutManager.LayoutParams) v.getLayoutParams();
        lp.height = parent.getMeasuredHeight() / 3;
        lp.width = parent.getMeasuredWidth() / 3;
        v.setLayoutParams(lp);
        NanuliCardKeyHolder nanuliCardKeyHolder = new NanuliCardKeyHolder(v,editMode);
        return nanuliCardKeyHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NanuliCardKeyHolder holder, int position) {
        holder.onBind(this.nanuliEditDeck.get(position),position,editMode,listener);
    }

    @Override
    public int getItemCount() {
        return this.nanuliEditDeck.size();
    }

    @Override
    public void notfy() {
        this.notifyDataSetChanged();

    }


    @Override
    public int KeyAdapterGetSize()
    {


        int size = 0;
        for (NanuliCard nanuliCard:this.nanuliEditDeck){
            if(!nanuliCard.isEmpty()){
                size++;
            }
        }
        Log.d("씨밥",String.valueOf(size));
        return size;
    }

    @Override
    public void addNanuliCards(NanuliCard nanuliCard) {
        if(nanuliEditDeck.size() > 9){ return;}
        for (int i = 0; i < 9; i++) {
            if(this.nanuliEditDeck.get(i).isEmpty()){
                this.nanuliEditDeck.set(i,nanuliCard);
                break;
            }
        }
    }

    @Override
    public void setNanuliCards(ArrayList<NanuliCard> nanuliCardDeck) {
        this.nanuliEditDeck = nanuliCardDeck;
    }

    @Override
    public void nanuliCardsClear() {
        nanuliEditDeck.clear();
    }

    @Override
    public void removeNanuliCard(int position) {
        nanuliEditDeck.set(position,new NanuliCard(0,0,""));
    }

    @Override
    public void setEditingMode(boolean flag) {
        this.editMode = flag;
    }

    @Override
    public void addNanuliCardListener(OnNanuliCardClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void closeBtnHide() {
        this.editMode = false;
    }

    @Override
    public ArrayList<NanuliCard> getCardDeck() {
        return nanuliEditDeck;
    }
}
