package com.sama.communicationclassjava.Data;

import java.io.Serializable;
import java.util.Date;

public class GalleryDetailCommentData implements Serializable {
        String documentKey;
        String profileImage;
        String profileName;
        Long writeDay;
        String comment;


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
    public void writeProduce(){
        this.writeDay = new Date().getTime();
    }

    public Long getWriteDay() {
        return writeDay;
    }

    public void setWriteDay(Long writeDay) {
        this.writeDay = writeDay;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
