package com.sama.communicationclass.Contract;

import android.content.Context;

import com.sama.communicationclass.Data.UserInfo;
import com.sama.communicationclass.Lisetner.UseChildClickListener;

import java.util.ArrayList;

public interface ChildChoiceContract {
    interface View{
        Context getContext();
        void moveGalleryActivity(UserInfo userInfo);
        ArrayList<String> loadingUserData();
        boolean saveUserData(String token);
    }

    interface Presenter{
        void setAdapterModel(ChildAdapterContract.Model Model);
        void setAdapterView(ChildAdapterContract.View View);
        void userLoader();

        void attachView(ChildChoiceContract.View view);
        void detachView();
        void saveUser(String token);
        void moveGalleryActivity(UserInfo userInfo);
        void setUseChildClickListener(UseChildClickListener useChildClickListener);

    }
}



