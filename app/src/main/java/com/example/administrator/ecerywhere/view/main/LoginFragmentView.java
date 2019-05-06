package com.example.administrator.ecerywhere.view.main;

import android.app.Activity;

import com.example.administrator.ecerywhere.base.BaseMvpView;

public interface LoginFragmentView extends BaseMvpView{
    String getPhone();
    Activity getAct();

    void go2MainActivity();

}
