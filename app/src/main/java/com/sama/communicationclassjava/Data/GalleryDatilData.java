package com.sama.communicationclassjava.Data;

import android.net.Uri;

import com.sama.communicationclassjava.SinglePattern.SelectUserInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class GalleryDatilData implements Serializable {



    ArrayList<String> imageUrl = new ArrayList<>();

    String UUID;
    Date writeDay;
//  작성날짜
    String ProfileImage;
//  프로필이미지
    String ProfileName;
//  프리필이름

    ArrayList<DatilcommentData> commentList = new ArrayList<>();

    public void Merge(SelectUserInfo info){
        this.setUUID(info.getUUID());
        this.setProfileImage(info.getProfileImage());
        this.setProfileName(info.getProfileName());

    }


    public void addImageUrl(String url){
        imageUrl.add(url);
    }
    public void writeProduce(){
        this.writeDay = new Date();
    }

    public Date getWriteDay() {
        return writeDay;
    }

    public void setWriteDay(Date writeDay) {
        this.writeDay = writeDay;
    }


    public ArrayList<String> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(ArrayList<String> imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getProfileImage() {
        return ProfileImage;
    }

    public void setProfileImage(String profileImage) {
        ProfileImage = profileImage;
    }

    public String getProfileName() {
        return ProfileName;
    }

    public void setProfileName(String profileName) {
        ProfileName = profileName;
    }

    public ArrayList<DatilcommentData> getCommentList() {
        return commentList;
    }

    public void setCommentList(ArrayList<DatilcommentData> commentList) {
        this.commentList = commentList;
    }

    class DatilcommentData{
        String ProfileImage;
        String ProfileName;
        String writeDay;
        String CommentText;

        public String getWriteDay() {
            return writeDay;
        }

        public void setWriteDay(String writeDay) {
            this.writeDay = writeDay;
        }

        public String getProfileImage() {
            return ProfileImage;
        }

        public void setProfileImage(String profileImage) {
            ProfileImage = profileImage;
        }

        public String getProfileName() {
            return ProfileName;
        }

        public void setProfileName(String profileName) {
            ProfileName = profileName;
        }

        public String getCommentText() {
            return CommentText;
        }

        public void setCommentText(String commentText) {
            CommentText = commentText;
        }
    }
}
