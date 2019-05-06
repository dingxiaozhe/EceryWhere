package com.example.administrator.ecerywhere.ui.main.fragment;


import android.annotation.SuppressLint;


import android.app.Activity;
import android.support.design.internal.ForegroundLinearLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.Editable;

import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
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
import com.example.administrator.ecerywhere.ui.main.activity.MainActivity;
import com.example.administrator.ecerywhere.ui.main.activity.WebViewActivity;
import com.example.administrator.ecerywhere.util.ToastUtil;
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
    @BindView(R.id.tv5)
    TextView tv5;


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
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(getResources().getString(R.string.agree_protocol));
       //点击事件
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void onClick(View view) {
                //ToastUtil.showShort("点击");
                WebViewActivity.startAct(getActivity());
            }
        };
        spannableStringBuilder.setSpan(clickableSpan,12,16, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        //下划线
        UnderlineSpan underlineSpan = new UnderlineSpan();
        spannableStringBuilder.setSpan(underlineSpan,12,16,Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        //文本yanse
        ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(getResources().getColor(R.color.c_FA6A13));
        spannableStringBuilder.setSpan(foregroundColorSpan,12,16,Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        //需要设置这个ClickableSpan才会有效果
        tv5.setMovementMethod(LinkMovementMethod.getInstance());
        tv5.setText(spannableStringBuilder);

    }







    @SuppressLint("ResourceType")
    @OnClick({R.id.iv_wechat,R.id.iv_qq,R.id.iv_sina,R.id.btn_send_verify})
    public  void onClick(View v){
        switch (v.getId()) {
            case R.id.iv_wechat:
                mPresenter.oauthLogin(SHARE_MEDIA.WEIXIN);
                break;
            case R.id.iv_qq:
              mPresenter.oauthLogin(SHARE_MEDIA.QQ);
                break;
            case R.id.iv_sina:
                mPresenter.oauthLogin(SHARE_MEDIA.SINA);
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
        //添加回退栈
        //fragmentTransaction.addToBackStack(null);
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

    @Override
    public Activity getAct() {

        return getActivity();
    }

    @Override
    public void go2MainActivity() {
        MainActivity.startAct(getContext());

    }


    @Override
    public void toastShort(String msg) {

    }
}
