package com.sama.communicationclassjava.Data;

import android.net.Uri;
import android.util.Log;

import com.sama.communicationclassjava.SinglePattern.SelectUserInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class GalleryDatilData implements Serializable {

    ArrayList<String> imageUrl = new ArrayList<>();
    String documentKey;

    String useruuid;
    Long writeDay;
//  작성날짜
    String ProfileImage;
//  프로필이미지
    String ProfileName;
//  프리필이름
    int Area;
//  지역

//    ArrayList<DatilcommentData> commentList = new ArrayList<>();

    public void Merge(SelectUserInfo info){
        this.setProfileImage(info.getProfileImage());
        this.setProfileName(info.getProfileName());
        this.setArea(info.getArea());
    }

    public String getDocumentKey() {
        return documentKey;
    }

    public void setDocumentKey(String documentKey) {
        this.documentKey = documentKey;
    }

    public void addImageUrl(String url){
        imageUrl.add(url);
    }
    public void writeProduce(){
        this.writeDay = new Date().getTime();
    }

    public ArrayList<String> getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(ArrayList<String> imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Long getWriteDay() {
        return writeDay;
    }

    public void setWriteDay(Long writeDay) {
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

    public int getArea() {
        return Area;
    }

    public void setArea(int area) {
        Area = area;
    }

    //    class DatilcommentData{
//        String ProfileImage;
//        String ProfileName;
//        String writeDay;
//        String CommentText;
//
//        public String getWriteDay() {
//            return writeDay;
//        }
//
//        public void setWriteDay(String writeDay) {
//            this.writeDay = writeDay;
//        }
//
//        public String getProfileImage() {
//            return ProfileImage;
//        }
//
//        public void setProfileImage(String profileImage) {
//            ProfileImage = profileImage;
//        }
//
//        public String getProfileName() {
//            return ProfileName;
//        }
//
//        public void setProfileName(String profileName) {
//            ProfileName = profileName;
//        }
//
//        public String getCommentText() {
//            return CommentText;
//        }
//
//        public void setCommentText(String commentText) {
//            CommentText = commentText;
//        }
//    }
}
