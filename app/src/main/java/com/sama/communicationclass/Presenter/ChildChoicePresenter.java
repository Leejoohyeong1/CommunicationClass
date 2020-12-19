package com.sama.communicationclass.Presenter;

import android.util.Log;

import com.sama.communicationclass.Contract.ChildAdapterContract;
import com.sama.communicationclass.Contract.ChildChoiceContract;
import com.sama.communicationclass.Data.UserInfo;
import com.sama.communicationclass.Lisetner.OnChildSelectListener;
import com.sama.communicationclass.Lisetner.UseChildClickListener;
import com.sama.communicationclass.Model.FireBaseUserSelectModule;

import java.util.ArrayList;

public class ChildChoicePresenter implements ChildChoiceContract.Presenter , OnChildSelectListener {
    ChildChoiceContract.View view;


    ChildAdapterContract.Model adapterModel;
    ChildAdapterContract.View adapterView;

    @Override
    public void setAdapterModel(ChildAdapterContract.Model Model) {
        this.adapterModel = Model;
    }

    @Override
    public void setAdapterView(ChildAdapterContract.View View) {
        adapterView = View;
    }

    @Override
    public void attachView(ChildChoiceContract.View view) {
        this.view = view;
    }

    @Override
    public void setUseChildClickListener(UseChildClickListener useChildClickListener) {
        this.adapterModel.setUseChildClickListener(useChildClickListener);
    }


    @Override
    public void detachView() {
        if(this.view != null){return;}
        this.view = null;
    }

//    @Override
//    public void userLoader(String token) {
//        ArrayList<String> strings = new ArrayList<>();
//        strings.add("6mQbWLksBMyNvEU0sp6O");
//        strings.add("7nmN5vW9kk9dzMDLHxEz");
//        strings.add("Q5V6kxI9ekaQutZm7QCZ");
//        strings.add("cOJqK09MGxjZ91U13agH");
//
//
//    }

    @Override
    public void userLoader() {
//        ArrayList<String> tokens = new ArrayList<>();
        ArrayList<String> tokens = view.loadingUserData();
        Log.d("loadingUserData tokens",tokens.toString());
        FireBaseUserSelectModule.getInstance().setOnChildSelectListener(this);
        FireBaseUserSelectModule.getInstance().SelectUserData(tokens);
    }

    @Override
    public void saveUser(String token) {
        boolean flag = view.saveUserData(token);
        if (flag) {
            FireBaseUserSelectModule.getInstance().setOnChildSelectListener(this);
            FireBaseUserSelectModule.getInstance().SelectSingleUserData(token);
        }

    }

    @Override
    public void moveGalleryActivity(UserInfo userInfo) {
        this.view.moveGalleryActivity(userInfo);
    }


    @Override
    public void onChildData(ArrayList<UserInfo> userInfoList) {
        adapterModel.addItems(userInfoList);
        adapterView.notfyAdapter();
    }
}
