package com.sama.communicationclassjava.SinglePattern;

import androidx.annotation.NonNull;

public class SelectUserInfo {

    String UUID = "temp_UUID";
    String Child = "temp_child";
    String ClassName = "temp_ClassName";
    String Parents = "temp_Parents";
    String Group = "temp_Group";
    int Area = 1;
    //  작성날짜
    String ProfileImage = "temp_ProfileImage";
    //  프로필이미지
    String ProfileName= "temp_ProfileName";


    private static SelectUserInfo one;

    public static SelectUserInfo getInstance() {
        if (one == null) {
            one = new SelectUserInfo();
        }
        return one;
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

    public String getChild() {
        return Child;
    }

    public void setChild(String child) {
        Child = child;
    }

    public String getClassName() {
        return ClassName;
    }

    public void setClassName(String className) {
        ClassName = className;
    }

    public String getParents() {
        return Parents;
    }

    public void setParents(String parents) {
        Parents = parents;
    }

    public String getGroup() {
        return Group;
    }

    public void setGroup(String group) {
        Group = group;
    }

    public int getArea() {
        return Area;
    }

    public void setArea(int area) {
        Area = area;
    }
}
