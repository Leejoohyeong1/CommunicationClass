package com.sama.communicationclassjava.Data;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class CommunicationItem implements Serializable {
    String data;
    String name;
    String profile;
    String preview;

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public CommunicationItem(String data, String name, String profile, String preview){
        this.data = data;
        this.name = name;
        this.profile = profile;
        this.preview = preview;
    }

    @Override
    public String toString() {
        return "CommunicationItem{" +
                "data='" + data + '\'' +
                ", name='" + name + '\'' +
                ", profile='" + profile + '\'' +
                ", preview='" + preview + '\'' +
                '}';
    }
}
