package com.sama.communicationclass.Data;

import android.net.Uri;
import android.util.Log;

import com.sama.communicationclass.SinglePattern.SelectUserInfo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class GalleryDetailData implements Serializable {

    ArrayList<String> imageUrl = new ArrayList<>();
    ArrayList<NanuliCard> decks = new ArrayList<>();

    String writeUserKey;
    Long writeDay;
    //  작성날짜
    String ProfileImage;
    //  프로필이미지
    String ProfileName;
    //  프리필이름
    String area;
//    지역
    String group;
//    유치원
    String className;
//    반

    String documentKey;

    public GalleryDetailData(){

    }

    public void Merge(SelectUserInfo info){
        this.setWriteUserKey(info.getUserKey());

        this.setArea(info.getArea());
        this.setGroup(info.getGroup());
        this.setClassName(info.getClassName());

        this.setProfileImage(info.getProfileImage());
        this.setProfileName(info.getProfileName());

    }


    public String getWriteUserKey() {
        return writeUserKey;
    }

    public void setWriteUserKey(String writeUserKey) {
        this.writeUserKey = writeUserKey;
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
    public void addImageUrl(ArrayList<String> url){
        imageUrl = url;
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

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public ArrayList<NanuliCard> getDecks() {
        return decks;
    }

    public void setDecks(ArrayList<NanuliCard> decks) {
        this.decks = decks;
    }

    @Override
    public String toString() {
        return "GalleryDetailData{" +
                "imageUrl=" + imageUrl +
                ", decks=" + decks +
                ", writeUserKey='" + writeUserKey + '\'' +
                ", writeDay=" + writeDay +
                ", ProfileImage='" + ProfileImage + '\'' +
                ", ProfileName='" + ProfileName + '\'' +
                ", area='" + area + '\'' +
                ", group='" + group + '\'' +
                ", className='" + className + '\'' +
                ", documentKey='" + documentKey + '\'' +
                '}';
    }
}
