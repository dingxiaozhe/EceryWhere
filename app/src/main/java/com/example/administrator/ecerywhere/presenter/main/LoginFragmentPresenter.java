package com.example.administrator.ecerywhere.presenter.main;

import android.util.Log;

import com.example.administrator.ecerywhere.R;
import com.example.administrator.ecerywhere.base.BaseApp;
import com.example.administrator.ecerywhere.base.BasePresenter;
import com.example.administrator.ecerywhere.base.Constants;
import com.example.administrator.ecerywhere.bean.LoginInfo;
import com.example.administrator.ecerywhere.model.LoginFragmentModel;
import com.example.administrator.ecerywhere.net.ResultCallBack;
import com.example.administrator.ecerywhere.util.SpUtil;
import com.example.administrator.ecerywhere.util.ToastUtil;
import com.example.administrator.ecerywhere.view.main.LoginFragmentView;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.Base;

import java.util.Map;

public class LoginFragmentPresenter extends BasePresenter<LoginFragmentView>{

    private LoginFragmentModel loginFragmentModel;
    private static final String TAG = "LoginFragmentPresenter";
    @Override
    protected void initModel() {
        loginFragmentModel = new LoginFragmentModel();
        mModels.add(loginFragmentModel);
    }
    public  void  oauthLogin(SHARE_MEDIA type){
        UMShareAPI umShareAPI = UMShareAPI.get(mMvpView.getAct());
        //media,三方平台
        //authListener,登录回调
        umShareAPI.getPlatformInfo(mMvpView.getAct(), type, authListener);
    }

    UMAuthListener authListener=new UMAuthListener(){
        /**
         * @desc 授权开始的回调
         *
         */
        @Override
        public void onStart(SHARE_MEDIA share_media) {

        }
        /**
         * @desc 授权成功的回调
         *
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int i, Map<String, String> data) {
                logMap(data);
                if (platform==SHARE_MEDIA.SINA){
                    loginSina(data.get("uid"));
                }
        }
        /**
         * @desc 授权失败的回调
         *
         */
        @Override
        public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
            ToastUtil.showShort(BaseApp.getRes().getString(R.string.oauth_error));
        }
        /**
         * @desc 授权取消的回调
         *
         */
        @Override
        public void onCancel(SHARE_MEDIA share_media, int i) {
            ToastUtil.showShort(BaseApp.getRes().getString(R.string.oauth_cancel));
        }
    };

    private void loginSina(String uid) {
        loginFragmentModel.loginSina(uid, new ResultCallBack<LoginInfo>() {
            @Override
            public void onSuccess(LoginInfo bean) {
                //登录成功了,需要做什么
                //1.跳转主页面
                //2.保存用户信息
                if (bean.getResult()!=null){
                    saveUserInfo(bean.getResult());
                    if (mMvpView!=null){
                        mMvpView.toastShort(BaseApp.getRes().getString(R.string.login_success));
                        mMvpView.go2MainActivity();
                    }
                }else {
                    if (mMvpView!=null){
                        mMvpView.toastShort(BaseApp.getRes().getString(R.string.login_fail));
                    }
                }
            }

            @Override
            public void onFail(String msg) {
                if (mMvpView != null){
                    mMvpView.toastShort(msg);
                }
            }
        });
    }

    private void saveUserInfo(LoginInfo.ResultBean result) {
        SpUtil.setParam(Constants.TOKEN,result.getToken());
        SpUtil.setParam(Constants.DESC,result.getDescription());
        SpUtil.setParam(Constants.USERNAME,result.getUserName());
        SpUtil.setParam(Constants.GENDER,result.getGender());
        SpUtil.setParam(Constants.EMAIL,result.getEmail());
        SpUtil.setParam(Constants.PHOTO,result.getPhoto());
        SpUtil.setParam(Constants.PHONE,result.getPhone());
    }

    private void logMap(Map<String, String> map) {
        for (Map.Entry<String ,String> entry :map.entrySet()) {
            Log.e(TAG, "logMap: " +entry.getKey()+","+entry.getValue());

        }

    }
}
