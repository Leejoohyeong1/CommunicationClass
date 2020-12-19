package com.sama.communicationclass.Contract;

import com.sama.communicationclass.Data.UserInfo;
import com.sama.communicationclass.Lisetner.UseChildClickListener;

import java.util.ArrayList;

public interface ChildAdapterContract {
    interface View{
        void notfyAdapter();

    }

    interface Model{
        void addItems(ArrayList<UserInfo> userInfoArrayList);
        void addItems(UserInfo userInfoItem);
        void setUseChildClickListener(UseChildClickListener useChildClickListener);

    }
}
