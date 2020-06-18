package com.sama.communicationclassjava.SinglePattern;

import androidx.annotation.NonNull;

public class SelectUserInfo {

    String uuID = "temp_UUID";
    String child = "temp_child";
    String className = "temp_ClassName";
    String parents = "temp_Parents";
    String group = "temp_Group";
    String area = "temp_area";
    //  작성날짜
    String profileImage = "temp_ProfileImage";
    //  프로필이미지
    String profileName= "temp_ProfileName";


    private static SelectUserInfo one;

    public static SelectUserInfo getInstance() {
        if (one == null) {
            one = new SelectUserInfo();
        }
        return one;
    }

    public String getUUID() {
        return uuID;
    }

    public void setUuID(String uuID) {
        this.uuID = uuID;
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
