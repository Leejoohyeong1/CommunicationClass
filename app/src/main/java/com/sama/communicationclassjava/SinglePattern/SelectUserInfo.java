package com.sama.communicationclassjava.SinglePattern;

import androidx.annotation.NonNull;

public class SelectUserInfo {

    String userKey = "7nmN5vW9kk9dzMDLHxEz";
    String child = "temp_child";
    String className = "temp_ClassName";
    String parents = "temp_Parents";
    String group = "temp_Group";
    String area = "temp_area";
    //  작성날짜
    String profileImage = "/Users/temp_area/ProfileImage.jpg";
    //  프로필이미지
    String profileName= "안원재";


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
}
