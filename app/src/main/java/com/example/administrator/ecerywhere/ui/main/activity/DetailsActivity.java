package com.example.administrator.ecerywhere.ui.main.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ImageButton;

import com.example.administrator.ecerywhere.R;
import com.example.administrator.ecerywhere.base.BaseActivity;
import com.example.administrator.ecerywhere.presenter.main.InformationPresenter;
import com.example.administrator.ecerywhere.ui.main.adapter.DetailsBean;
import com.example.administrator.ecerywhere.ui.main.adapter.DetailsRecyclerViewAdapter;
import com.example.administrator.ecerywhere.view.main.InformationView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.OnClick;

public class DetailsActivity extends BaseActivity<InformationView,InformationPresenter> implements  InformationView {

    @BindView(R.id.back)
    ImageButton back;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    private ArrayList<DetailsBean> recyclerviewList;
    private DetailsRecyclerViewAdapter detailsRecyclerViewAdapter;
    @Override
    protected InformationPresenter initPresenter() {
        return new InformationPresenter();
    }

    @Override
    protected int getLayoutId() {
        return  R.layout.activity_details;
    }

    @Override
    protected void initView() {
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        recyclerview.setLayoutManager(new LinearLayoutManager(this));
        recyclerviewList = new ArrayList<>();
        recyclerviewList.add(new DetailsBean("2017/10/21", "请问日本的西瓜卡怎么办理？", 1));
        recyclerviewList.add(new DetailsBean("2017/10/21", "在个地铁口都可以买到，有一个西瓜卡的自动售卖机", 2));
        detailsRecyclerViewAdapter = new DetailsRecyclerViewAdapter(recyclerviewList, this);
        recyclerview.setAdapter(detailsRecyclerViewAdapter);
    }
    @OnClick(R.id.back)
    public void onViewClicked() {
        finish();
    }

    @Override
    public void toastShort(String msg) {

    }
}
