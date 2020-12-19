package com.sama.communicationclass.Contract;

import com.sama.communicationclass.Data.NanuliCard;
import com.sama.communicationclass.Lisetner.OnNanuliCardClickListener;

import java.util.ArrayList;

public interface NanuliCardEditAdapterContract {
    interface View {
        void notfy();
        void closeBtnHide();
    }

    interface Model {

        void setNanuliCards(ArrayList<NanuliCard> nanuliCardDeck);
        void addNanuliCards(NanuliCard nanuliCard);
        void nanuliCardsClear();
        int KeyAdapterGetSize();
        ArrayList<NanuliCard> getCardDeck();
        void removeNanuliCard(int position);
        void setEditingMode(boolean flag);
        void addNanuliCardListener(OnNanuliCardClickListener listener);
    }
}
