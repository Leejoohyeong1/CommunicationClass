package com.sama.communicationclass.SinglePattern;

import com.sama.communicationclass.Data.UserInfo;

public class SelectUserInfo {

    String userKey = "7nmN5vW9kk9dzMDLHxEz";
    String child = "temp_child";
    String className = "temp_ClassName";
    String parents = "temp_Parents";
    String group = "temp_Group";
    String area = "temp_area11";
    //  작성날짜
    String profileImage = "/Users/temp_area/ProfileImage1.jpg";
    //  프로필이미지
    String profileName= "성영현";
//
//    1.성영현 ProfileImage1.jpg
//2.석도유
//3.김민재 ProfileImage2.jpg
//4.박지아 ProfileImage3.jpg


    public SelectUserInfo(String area,String group, String className,  String profileName,String profileImage) {
        this.className = className;
        this.group = group;
        this.area = area;
        this.profileImage = "/Users/"+group+"/"+className+"/"+profileImage;
        this.profileName = profileName;
    }

    public SelectUserInfo() { }

    private static SelectUserInfo one;

    public static SelectUserInfo getInstance() {
        if (one == null) {
            one = new SelectUserInfo();
        }
        return one;
    }

    public String getUserKey() {
        return userKey;
    }

    public void setUserKey(String userKey) {
        this.userKey = userKey;
    }

    public String getChild() {
        return child;
    }

    public void setChild(String child) {
        this.child = child;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getParents() {
        return parents;
    }

    public void setParents(String parents) {
        this.parents = parents;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
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


    public void Merge(UserInfo info){
        this.setUserKey(info.getUserKey());
        this.setChild(info.getChild());
        this.setClassName(info.getClassName());
        this.setGroup(info.getGroup());
        this.setArea(info.getArea());
        this.setProfileImage(info.getProfileImage());
        this.setProfileName(info.getProfileName());
    }


    public String insetLog(){
        return userKey+"\t\t"+area+"\t"+group+"\t"+className+"\t"+profileName+"\t"+profileImage;
    }

    @Override
    public String toString() {

        return "SelectUserInfo{" +
                "userKey='" + userKey + '\'' +
                ", child='" + child + '\'' +
                ", className='" + className + '\'' +
                ", parents='" + parents + '\'' +
                ", group='" + group + '\'' +
                ", area='" + area + '\'' +
                ", profileImage='" + profileImage + '\'' +
                ", profileName='" + profileName + '\'' +
                '}';
    }
}
