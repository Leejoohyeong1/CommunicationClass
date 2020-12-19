package com.sama.communicationclass.Data;

import java.util.ArrayList;

public class NanuliCardDeck {

    String catalogName;
    ArrayList<NanuliCard> deck;


    public NanuliCardDeck(String catalogName, ArrayList<NanuliCard> deck) {
        this.deck = deck;
        this.catalogName = catalogName;
    }

    public ArrayList<NanuliCard> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<NanuliCard> deck) {
        this.deck = deck;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }


    @Override
    public String toString() {

        return "NanuliCardDeck{" +
                "deck=" + deck.toString() +
                ", catalogName='" + catalogName + '\'' +
                '}';
    }
}
