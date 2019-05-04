package com.example.administrator.ecerywhere.ui.main.activity;

import android.support.v4.app.FragmentManager;

import com.example.administrator.ecerywhere.R;
import com.example.administrator.ecerywhere.base.BaseActivity;
import com.example.administrator.ecerywhere.presenter.main.LoginPresenter;
import com.example.administrator.ecerywhere.ui.main.fragment.LoginFragment;
import com.example.administrator.ecerywhere.view.main.LoginView;

public class LoginActivity extends BaseActivity<LoginView, LoginPresenter> implements LoginView {





    @Override
    protected LoginPresenter initPresenter() {
        return new LoginPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        FragmentManager manager =getSupportFragmentManager();
        manager.beginTransaction().add(R.id.fl_container,new LoginFragment()).commit();
    }






    @Override
    protected void initData() {

        mPresenter.getVerifyCode();
    }





}
