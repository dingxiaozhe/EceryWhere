package com.example.administrator.ecerywhere.model;



import com.example.administrator.ecerywhere.base.BaseModel;
import com.example.administrator.ecerywhere.bean.LoginInfo;
import com.example.administrator.ecerywhere.net.BaseObserver;
import com.example.administrator.ecerywhere.net.EveryWhereService;
import com.example.administrator.ecerywhere.net.HttpUtils;
import com.example.administrator.ecerywhere.net.ResultCallBack;
import com.example.administrator.ecerywhere.net.RxUtils;

import io.reactivex.disposables.Disposable;

public class LoginFragmentModel extends BaseModel{

    public void loginSina(String uid, final ResultCallBack<LoginInfo> callback){
        EveryWhereService apiserver = HttpUtils.getInstance().getApiserver(EveryWhereService.BASE_URL, EveryWhereService.class);
        apiserver.postWeiboLogin(uid)
                .compose(RxUtils.<LoginInfo>rxObserableSchedulerHelper())
                .subscribe(new BaseObserver<LoginInfo>() {
                    @Override
                    public void onNext(LoginInfo loginInfo) {
                            if (loginInfo!=null){
                                if (loginInfo.getCode()==EveryWhereService.SUCCESS_CODE){
                                        callback.onSuccess(loginInfo);
                                }else {
                                    callback.onFail(loginInfo.getDesc());
                                }
                            }
                    }

                    @Override
                    public void error(String msg) {

                    }

                    @Override
                    protected void subscribe(Disposable d) {
                        addDisposable(d);
                    }
                });

    }
}
