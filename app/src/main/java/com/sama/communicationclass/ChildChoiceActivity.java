package com.sama.communicationclass;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.sama.communicationclass.Adapter.ChildAdapter;
import com.sama.communicationclass.Contract.ChildChoiceContract;
import com.sama.communicationclass.CustomActionBar.CustomAction;
import com.sama.communicationclass.CustomDialog.EditDialog;
import com.sama.communicationclass.CustomEnum.ActionBarLayout;
import com.sama.communicationclass.Data.UserInfo;
import com.sama.communicationclass.Lisetner.OnEditDialogListener;
import com.sama.communicationclass.Lisetner.OnToolbarActionListener;
import com.sama.communicationclass.Lisetner.UseChildClickListener;
import com.sama.communicationclass.Presenter.ChildChoicePresenter;
import com.sama.communicationclass.SinglePattern.SelectUserInfo;

import java.util.ArrayList;

public class ChildChoiceActivity extends AppCompatActivity implements ChildChoiceContract.View
                                                                    , UseChildClickListener
                                                                    , OnToolbarActionListener
                                                                    , OnEditDialogListener {

    RecyclerView childRecyclerView;
    ChildChoiceContract.Presenter presenter;
    EditDialog editDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child_choice);
        this.editDialog = new EditDialog(this);
        this.editDialog.setEditDialogListener(this);


        CustomAction customAction = new CustomAction(getSupportActionBar(), this);
        customAction.setActionBar(ActionBarLayout.choiceLayout);
        customAction.addToolbarListener(this);

        presenter = new ChildChoicePresenter();
        presenter.attachView(this);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);

        ChildAdapter childAdapter = new ChildAdapter();
        childRecyclerView = (RecyclerView) findViewById(R.id.use_child_choice_RecyclerView);
        childRecyclerView.setLayoutManager(gridLayoutManager);
        childRecyclerView.setAdapter(childAdapter);
        presenter.setAdapterModel(childAdapter);
        presenter.setAdapterView(childAdapter);
        presenter.setUseChildClickListener(this);
        presenter.userLoader();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.presenter.detachView();
    }

    @Override
    public ArrayList<String> loadingUserData() {
        ArrayList<String> UserDataList = new ArrayList<>();
        SharedPreferences UserData = getSharedPreferences("UserData", MODE_PRIVATE);
        int count = UserData.getInt("UserDataCount", 0);
        for (int index = 0 ;index < count ; index++){

            UserDataList.add(UserData.getString("UserData"+index,""));
            Log.d("loadingUserData",UserData.getString("UserData"+index,""));
        }
        Log.d("loadingUserData return",UserDataList.toString());
        return UserDataList;
    }

    @Override
    public boolean saveUserData(String token){
        SharedPreferences UserData = getSharedPreferences("UserData", MODE_PRIVATE);
        SharedPreferences.Editor editor = UserData.edit();
        int count = UserData.getInt("UserDataCount", 0);
        if(count >=4 ){
            Toast.makeText(this, "4명이상 등록할수 없습니다", Toast.LENGTH_SHORT).show();
            return false;
        }
        for (int index = 0 ;index < count ; index++){
            if(UserData.getString("UserData"+index,"") .equals(token)){
                Toast.makeText(this, "중복입니다", Toast.LENGTH_SHORT).show();
                return false;
            }

        }
        editor.putString("UserData"+count,token);
        count++;
        editor.putInt("UserDataCount", count);
        editor.commit();

        return true;
    }

    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void OnChildClickListener(UserInfo userInfo, int position) {
        this.presenter.moveGalleryActivity(userInfo);
    }


    @Override
    public void moveGalleryActivity(UserInfo userInfo) {
        SelectUserInfo.getInstance().Merge(userInfo);
        Intent intent = new Intent(this, CommunicationGalleryActivity.class);
        startActivity(intent);
    }

    @Override
    public void rightButtonAction() {
        editDialog.show();
    }

    @Override
    public void OnDialogConfirm(String editText) {
        presenter.saveUser(editText);
    }
}
