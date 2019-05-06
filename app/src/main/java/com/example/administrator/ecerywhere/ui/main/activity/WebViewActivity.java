package com.example.administrator.ecerywhere.ui.main.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.administrator.ecerywhere.R;
import com.example.administrator.ecerywhere.base.BaseActivity;
import com.example.administrator.ecerywhere.presenter.main.EmptyPresenter;
import com.example.administrator.ecerywhere.view.main.EmptyView;
import com.jaeger.library.StatusBarUtil;
import com.just.agentweb.AgentWeb;
import com.just.agentweb.WebChromeClient;
import com.umeng.commonsdk.debug.I;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WebViewActivity extends BaseActivity<EmptyView, EmptyPresenter> implements EmptyView {


    @BindView(R.id.toolbar_te)
    TextView mToolbarTe;
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.linear)
    LinearLayout linear;
    private AgentWeb mAgentWeb;

public  static  void startAct(Context context){
    Intent intent = new Intent(context, WebViewActivity.class);
    context.startActivity(intent);
}
    @Override
    protected EmptyPresenter initPresenter() {
        return new EmptyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_web_view;
    }

    @Override
    protected void initView() {
        //亮色的模式,会讲状态栏文字修改为黑色的
        StatusBarUtil.setLightMode(this);

        mToolbar.setTitle("");
        mToolbar.setNavigationIcon(R.mipmap.back_white);
        setSupportActionBar(mToolbar);
        mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(linear, new LinearLayout.LayoutParams(-1, -1))
                .closeIndicator()
                .createAgentWeb()
                .ready()
                .go("https://api.banmi.com/app2017/agreement.html");
        mAgentWeb.getWebCreator().getWebView().setWebChromeClient(new WebChromeClient(){
            @Override
            public void onReceivedTitle(WebView view, String title) {
                if (!TextUtils.isEmpty(title)){
                    mToolbarTe.setText(title);
                }
                super.onReceivedTitle(view, title);
            }
        });
    }

    @Override
    public void toastShort(String msg) {

    }
}
