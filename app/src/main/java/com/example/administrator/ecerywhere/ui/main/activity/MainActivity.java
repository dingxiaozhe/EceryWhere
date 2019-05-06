package com.example.administrator.ecerywhere.ui.main.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.RadioGroup;

import com.bumptech.glide.request.RequestOptions;
import com.example.administrator.ecerywhere.R;
import com.example.administrator.ecerywhere.base.BaseActivity;
import com.example.administrator.ecerywhere.presenter.main.MainPresenter;
import com.example.administrator.ecerywhere.ui.main.adapter.MainFragmentAdapter;
import com.example.administrator.ecerywhere.ui.main.fragment.BanMiFragment;
import com.example.administrator.ecerywhere.ui.main.fragment.HomeFragment;
import com.example.administrator.ecerywhere.view.main.MainView;
import com.example.administrator.ecerywhere.widget.GlideApp;
import com.jaeger.library.StatusBarUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity<MainView, MainPresenter> implements MainView {
    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.img_ca)
    ImageView mImgCa;
    @BindView(R.id.radiogroup)
    RadioGroup radiogroup;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    private ArrayList<Fragment> list;
    private MainFragmentAdapter adapter;

    public static void startAct(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        initToolbar();
        list=new ArrayList<>();
        list.add(new HomeFragment());
        list.add(new BanMiFragment());
        adapter = new MainFragmentAdapter(getSupportFragmentManager(), list);
        viewpager.setAdapter(adapter);

       viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
           @Override
           public void onPageScrolled(int i, float v, int i1) {

           }

           @Override
           public void onPageSelected(int i) {
               switch (i) {
                   case 0:
                       radiogroup.check(R.id.home);
                       break;
                   case 1:
                       radiogroup.check(R.id.banmi);
                       break;
               }
           }

           @Override
           public void onPageScrollStateChanged(int i) {

           }
       });
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.home:
                        viewpager.setCurrentItem(0);
                        break;
                    case R.id.banmi:
                        viewpager.setCurrentItem(1);
                        break;
                }
            }
        });

    }

    private void initToolbar() {
        //亮色的模式,会讲状态栏文字修改为黑色的
        StatusBarUtil.setLightMode(this);

        mToolbar.setTitle("");
        setSupportActionBar(mToolbar);
        RequestOptions mRequestOptions = RequestOptions.circleCropTransform()
                .skipMemoryCache(true);//不做内存缓存
        GlideApp.with(this).load(R.mipmap.zhanweitu_home_kapian).apply(mRequestOptions).into(mImgCa);
    }

    @Override
    public void toastShort(String msg) {

    }



}
