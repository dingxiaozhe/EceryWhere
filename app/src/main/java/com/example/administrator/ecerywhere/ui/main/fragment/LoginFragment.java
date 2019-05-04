package com.example.administrator.ecerywhere.ui.main.fragment;


import android.annotation.SuppressLint;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;
import android.text.Html;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.ecerywhere.R;
import com.example.administrator.ecerywhere.base.BaseFragment;
import com.example.administrator.ecerywhere.presenter.main.LoginFragmentPresenter;
import com.example.administrator.ecerywhere.view.main.LoginFragmentView;

import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;

import com.umeng.socialize.bean.SHARE_MEDIA;


import java.util.Map;


import butterknife.BindView;
import butterknife.OnClick;

/**
 * A simple {@link Fragment} subclass.
 */
public class LoginFragment extends BaseFragment<LoginFragmentView,LoginFragmentPresenter> implements LoginFragmentView {
    @BindView(R.id.iv_back)
    ImageView mIvBack;
    TextView mTvLogin;
    @BindView(R.id.tv_coutry_code)
    TextView mTvCoutryCode;
    @BindView(R.id.et_phone)
    EditText mEtPhone;
    @BindView(R.id.btn_send_verify)
    Button mBtnSendVerify;
    @BindView(R.id.ll_container)
    LinearLayout mLlContainer;
    @BindView(R.id.view)
    View mView;
    @BindView(R.id.ll_or)
    LinearLayout mLlOr;
    @BindView(R.id.iv_wechat)
    ImageView mIvWechat;
    @BindView(R.id.iv_qq)
    ImageView mIvQq;
    @BindView(R.id.iv_sina)
    ImageView mIvSina;
    @BindView(R.id.tv6)
    TextView tv6;


    private static final String TAG = "LoginFragment";


    //appkey:5ccc19ca3fc1952d15000e1a
    @Override
    protected LoginFragmentPresenter initPresenter() {
        return new LoginFragmentPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initView() {
        tv6.setText(Html.fromHtml("<u>"+"用户协议"+"<u/>"));
    }





    @SuppressLint("ResourceType")
    @OnClick({R.id.iv_wechat,R.id.iv_qq,R.id.iv_sina,R.id.btn_send_verify})
    public  void onClick(View v){
        switch (v.getId()) {
            case R.id.iv_wechat:
                login(SHARE_MEDIA.WEIXIN);
                break;
            case R.id.iv_qq:
                login(SHARE_MEDIA.QQ);
                break;
            case R.id.iv_sina:
                login(SHARE_MEDIA.SINA);
                break;

            case R.id.btn_send_verify:
                addVerifyFragment();
                break;
        }

    }

    private void addVerifyFragment() {
        if (TextUtils.isEmpty(getPhone())){
            return;
        }
        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        //添加会回退栈
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.add(R.id.fl_container,new VerifyFragment()).commit();
    }

    @Override
    protected void initListener() {
        mEtPhone.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                switchBtnState(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    /**
     * 根据输入框中是否有内容,切换发送验证码的背景
     *
     */
    private void switchBtnState(CharSequence charSequence) {
        if (TextUtils.isEmpty(charSequence)){
            mBtnSendVerify.setBackgroundResource(R.drawable.bg_btn_ea_r15);
        }else {
            mBtnSendVerify.setBackgroundResource(R.drawable.bg_btn_fa6a13_r15);

        }

    }

    public  String getPhone() {
        return mEtPhone.getText().toString().trim();
    }

    private void login(SHARE_MEDIA type) {
        UMShareAPI umShareAPI = UMShareAPI.get(getContext());
        umShareAPI.getPlatformInfo(getActivity(), type, umAuthListener);
    }

    UMAuthListener umAuthListener = new UMAuthListener() {
        /**
         * @desc 授权开始的回调
         * @param platform 平台名称
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @desc 授权成功的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param data 用户资料返回
         */
        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            for (Map.Entry<String,String> entry:data.entrySet()){
                String key = entry.getKey();
                String value = entry.getValue();
                Log.d(TAG, "key: "+key+",value:"+value);
            }
            Toast.makeText(getContext(), "成功了", Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权失败的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {

            Toast.makeText(getContext(), "失败：" + t.getMessage(),
                    Toast.LENGTH_LONG).show();
        }

        /**
         * @desc 授权取消的回调
         * @param platform 平台名称
         * @param action 行为序号，开发者用不上
         */
        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(getContext(), "取消了", Toast.LENGTH_LONG).show();
        }
    };


}
