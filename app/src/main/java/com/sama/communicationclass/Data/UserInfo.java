package com.sama.communicationclass.Data;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class UserInfo implements Serializable {
    String userKey = "7nmN5vW9kk9dzMDLHxEz";
    String child = "temp_child";
    String className = "temp_ClassName";
    String parents = "temp_Parents";
    String group = "temp_Group";
    String area = "temp_area";
    //  작성날짜
    String profileImage = "/Users/temp_area/ProfileImage.jpg";
    //  프로필이미지
    String profileName = "안원재";

    public UserInfo() {}

    public UserInfo(String userKey, String child, String className, String parents, String group, String area, String profileImage, String profileName) {
        this.userKey = userKey;
        this.child = child;
        this.className = className;
        this.parents = parents;
        this.group = group;
        this.area = area;
        this.profileImage = profileImage;
        this.profileName = profileName;
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

    @Override
    public String toString() {
        return "UserInfo{" +
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
