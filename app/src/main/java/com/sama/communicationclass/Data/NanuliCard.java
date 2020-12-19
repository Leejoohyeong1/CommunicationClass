package com.sama.communicationclass.Data;


import android.graphics.drawable.Drawable;

import com.sama.communicationclass.R;

import java.io.Serializable;

public class NanuliCard implements Serializable {

    int imageSrc = 0;
    String printText = "";
    int voice = 0;
    boolean empty =false;

    public NanuliCard() {}

    public NanuliCard(int imageSrc, int voice, String printText) {
        this.imageSrc = imageSrc;
        this.voice = voice;
        this.printText = printText;
    }


    public int getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(int imageSrc) {
        this.imageSrc = imageSrc;
    }

    public String getPrintText() {
        return printText;
    }

    public void setPrintText(String printText) {
        this.printText = printText;
    }

    public int getVoice() {
        return voice;
    }

    public void setVoice(int voice) {
        this.voice = voice;
    }

    public boolean isEmpty(){
        if(imageSrc == 0  && printText.equals("")){
            return true;
        }
        return false;

    }

    @Override
    public String toString() {
        return "NanuliCard{" +
                "imageSrc='" + imageSrc + '\'' +
                ", printText='" + printText + '\'' +
                '}';
    }
}
