package com.sama.communicationclassjava.Data;

import androidx.annotation.NonNull;

import com.sama.communicationclassjava.SinglePattern.SelectUserInfo;

import java.io.Serializable;
import java.util.Date;

public class CommentData implements Serializable {

    String galleryDocumentKey;
    String profileImage;
    String profileName;
    String writeUserKey;
    Long writeDay;
    String imageKey;
    String commentKey;

    public CommentData() {} //Needed for Firebase

    public void setInfo(String galleryDocumentKey, String imageKey,String commentKey) {
        this.setGalleryDocumentKey(galleryDocumentKey);
        this.setImageKey(imageKey);
        this.setCommentKey(commentKey);
    }


    public void Merge(SelectUserInfo info){
        this.setWriteUserKey(info.getUserKey());
        this.setProfileImage(info.getProfileImage());
        this.setProfileName(info.getProfileName());
        this.writeDay = new Date().getTime();
    }


    public String getCommentKey() {
        return commentKey;
    }

    public void setCommentKey(String commentKey) {
        this.commentKey = commentKey;
    }

    public String getWriteUserKey() {
        return writeUserKey;
    }

    public void setWriteUserKey(String writeUserKey) {
        this.writeUserKey = writeUserKey;
    }

    public String getGalleryDocumentKey() {
        return galleryDocumentKey;
    }

    public void setGalleryDocumentKey(String galleryDocumentKey) {
        this.galleryDocumentKey = galleryDocumentKey;
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
