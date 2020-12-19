package com.sama.communicationclass.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.sama.communicationclass.Contract.NanuliCardKeyAdapterContract;
import com.sama.communicationclass.Data.NanuliCardDeck;
import com.sama.communicationclass.Holder.NanuliCardKeyHolder;
import com.sama.communicationclass.Lisetner.OnNanuliCardClickListener;
import com.sama.communicationclass.R;

public class NanuliCardAdapter extends RecyclerView.Adapter<NanuliCardKeyHolder> implements NanuliCardKeyAdapterContract.Model
        , NanuliCardKeyAdapterContract.View {


    NanuliCardDeck nanuliCardDeck;
    OnNanuliCardClickListener listener;

    @NonNull
    @Override
    public NanuliCardKeyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.nanuli_card_layout, parent, false);
        NanuliCardKeyHolder nanuliCardKeyHolder = new NanuliCardKeyHolder(v,false);
        return nanuliCardKeyHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NanuliCardKeyHolder holder, int position) {
        holder.onBind(this.nanuliCardDeck.getDeck().get(position),position,false,this.listener);
    }

    @Override
    public int getItemCount() {
        return this.nanuliCardDeck.getDeck().size();
    }

    @Override
    public void notfy() {
        this.notifyDataSetChanged();
    }

    @Override
    public void setNanuliCard(NanuliCardDeck NanuliCards) {
        this.nanuliCardDeck = NanuliCards;
    }

    @Override
    public int KeyAdapterGetSize() {
        return this.nanuliCardDeck.getDeck().size();
    }

    @Override
    public void addNanuliCardListener(OnNanuliCardClickListener listener) {
        this.listener = listener;
    }
}
