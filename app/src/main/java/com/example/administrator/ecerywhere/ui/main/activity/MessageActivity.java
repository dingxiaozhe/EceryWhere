package com.example.administrator.ecerywhere.ui.main.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.ecerywhere.R;
import com.example.administrator.ecerywhere.base.BaseActivity;
import com.example.administrator.ecerywhere.bean.InformBean;
import com.example.administrator.ecerywhere.presenter.main.InformationPresenter;
import com.example.administrator.ecerywhere.ui.main.adapter.NotificationAdapter;
import com.example.administrator.ecerywhere.view.main.InformationView;
import com.umeng.commonsdk.debug.I;

import java.util.ArrayList;

import butterknife.BindView;

public class MessageActivity extends BaseActivity<InformationView,InformationPresenter> implements InformationView {
    @BindView(R.id.mImg_T1)
    ImageView mImgT1;
    @BindView(R.id.mTv_Clear1)
    TextView mTvClear1;
    @BindView(R.id.mTools1)
    Toolbar mTools1;
    @BindView(R.id.Mrv1)
    RecyclerView Mrv1;
    private ArrayList<InformBean> list1;
    private NotificationAdapter adapter;

    @Override
    protected InformationPresenter initPresenter() {
        return new InformationPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message;
    }

    @Override
    protected void initView() {
        mTools1.setTitle("");
        setSupportActionBar(mTools1);
        Mrv1.setLayoutManager(new LinearLayoutManager(this));
        list1 = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            InformBean bean = new InformBean();
            bean.setTitle("系统通知");
            bean.setTime("2017/10/21");
            if (i == 0) {
                bean.setText("用户注册成功");
            } else {
                bean.setText("通过分享获得2米粒");
            }
            list1.add(bean);
        }
        adapter=new NotificationAdapter(this,list1);
        Mrv1.setAdapter(adapter);
        mImgT1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MessageActivity.this,InformActivity.class));
            }
        });

    }

    @Override
    public void toastShort(String msg) {

    }
}
