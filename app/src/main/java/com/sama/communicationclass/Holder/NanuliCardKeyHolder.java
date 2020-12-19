package com.sama.communicationclass.Holder;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.sama.communicationclass.Data.NanuliCard;
import com.sama.communicationclass.Lisetner.OnNanuliCardClickListener;
import com.sama.communicationclass.R;

public class NanuliCardKeyHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    ImageView nanuliCard_Add_Btn;
    ImageView nanuliCardRemove_Btn;
    TextView nanuliCard_text;
    NanuliCard nanuliCard;
    int position;
    OnNanuliCardClickListener listener;
    boolean editMode;


    public NanuliCardKeyHolder(@NonNull View itemView,boolean editMode) {
        super(itemView);
        this.editMode = editMode;
        this.nanuliCard_Add_Btn = (ImageView) itemView.findViewById(R.id.nanuli_card_add_btn);
        this.nanuliCard_text = (TextView) itemView.findViewById(R.id.nanuli_card_text);
        if(editMode){
            this.nanuliCardRemove_Btn = (ImageView) itemView.findViewById(R.id.nanuli_card_remove_btn);
            this.nanuliCardRemove_Btn.setOnClickListener(this);
        }else{
            itemView.setOnClickListener(this);
        }
    }

    public void onBind(final NanuliCard nanuliCard,int position, boolean editMode, OnNanuliCardClickListener Listener) {
        this.editMode = editMode;
        if(nanuliCard.isEmpty()){
            nanuliCard_Add_Btn.setBackground(null);
            nanuliCard_text.setText("");
            if(this.nanuliCardRemove_Btn != null){
                this.nanuliCardRemove_Btn.setVisibility(View.GONE);
            }
            return;
        }

        this.nanuliCard = nanuliCard;

        Drawable drawable;

        this.listener = Listener;
        this.position = position;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            drawable = nanuliCard_Add_Btn.getResources().getDrawable(nanuliCard.getImageSrc(),null);
        } else {
            drawable = nanuliCard_Add_Btn.getResources().getDrawable(nanuliCard.getImageSrc());
        }

        if(this.editMode){
            this.nanuliCardRemove_Btn.setVisibility(View.VISIBLE);
        }else {
            if(this.nanuliCardRemove_Btn != null){
                this.nanuliCardRemove_Btn.setVisibility(View.GONE);
            }
        }
        nanuliCard_Add_Btn.setBackground(drawable);
        nanuliCard_text.setText(nanuliCard.getPrintText());
    }

    @Override
    public void onClick(View v) {
        if(listener == null){return;}
        listener.OnNanuliCardClick(nanuliCard,this.editMode,position);
    }

}
