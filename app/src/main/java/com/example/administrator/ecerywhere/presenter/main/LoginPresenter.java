package com.example.administrator.ecerywhere.presenter.main;


import com.example.administrator.ecerywhere.base.BasePresenter;
import com.example.administrator.ecerywhere.bean.VerifyCodeBean;
import com.example.administrator.ecerywhere.model.LoginModel;
import com.example.administrator.ecerywhere.net.ResultCallBack;
import com.example.administrator.ecerywhere.view.main.LoginView;

/**
 * @author xts
 *         Created by asus on 2019/4/29.
 */

public class LoginPresenter extends BasePresenter<LoginView> {
    private LoginModel mLoginModel;

    @Override
    protected void initModel() {
        mLoginModel = new LoginModel();
        mModels.add(mLoginModel);
    }

    public void getVerifyCode() {
        mLoginModel.getVerifyCode(new ResultCallBack<VerifyCodeBean>() {
            @Override
            public void onSuccess(VerifyCodeBean bean) {

            }

            @Override
            public void onFail(String msg) {

            }
        });
    }
}
