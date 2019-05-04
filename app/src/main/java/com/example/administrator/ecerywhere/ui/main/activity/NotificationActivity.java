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

public class NotificationActivity extends BaseActivity<InformationView,InformationPresenter> implements InformationView {
    @BindView(R.id.mImg_T)
    ImageView mImgT;
    @BindView(R.id.mTv_Clear)
    TextView mTvClear;
    @BindView(R.id.mTools)
    Toolbar mTools;
    @BindView(R.id.rlv)
    RecyclerView mRlv;
    private ArrayList<InformBean> list;
    private NotificationAdapter adapter;

    @Override
    protected InformationPresenter initPresenter() {
        return new InformationPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_notification;
    }

    @Override
    protected void initView() {
        mTools.setTitle("");
        setSupportActionBar(mTools);
        mRlv.setLayoutManager(new LinearLayoutManager(this));
        list=new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            InformBean bean = new InformBean();

            bean.setTime("2017/10/21");
            if (i == 0) {
                bean.setTitle("消息回复");
                bean.setText("阚锁回复了你的留言");
            } else {
                bean.setTitle("系统通知");
                bean.setText("林竹回复了你的留言");
            }
            list.add(bean);
        }
        adapter = new NotificationAdapter(this, list);
        mRlv.setAdapter(adapter);
        mImgT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(NotificationActivity.this,InformActivity.class));
            }
        });
    }
}
