package com.sama.communicationclass.Data;

import androidx.annotation.NonNull;

import com.sama.communicationclass.SinglePattern.SelectUserInfo;

import java.io.Serializable;
import java.util.Date;

public class CommunicationItem implements Serializable {

    String documentKey;
    String profileImage;
    String profileName;
    Long writeDay;
    String imageKey;

    public CommunicationItem(String documentKey, String imageKey) {
        this.documentKey = documentKey;
        this.imageKey = imageKey;
    }


    public void Merge(SelectUserInfo info){
        this.setProfileImage(info.getProfileImage());
        this.setProfileName(info.getProfileName());
        this.writeDay = new Date().getTime();
    }

    public String getDocumentKey() {
        return documentKey;
    }

    public void setDocumentKey(String documentKey) {
        this.documentKey = documentKey;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public Long getWriteDay() {
        return writeDay;
    }

    public void setWriteDay() {
        this.writeDay = new Date().getTime();
    }

    public String getImageKey() {
        return imageKey;
    }

    public void setImageKey(String imageKey) {
        this.imageKey = imageKey;
    }
}
