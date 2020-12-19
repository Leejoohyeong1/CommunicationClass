package com.sama.communicationclass.Contract;

import com.sama.communicationclass.Data.NanuliCardDeck;
import com.sama.communicationclass.Lisetner.OnNanuliCardClickListener;

public interface NanuliCardKeyAdapterContract {
    interface View {
        void notfy();
    }

    interface Model {
        void setNanuliCard(NanuliCardDeck NanuliCards);
        int KeyAdapterGetSize();
        void addNanuliCardListener(OnNanuliCardClickListener listener);
    }
}
