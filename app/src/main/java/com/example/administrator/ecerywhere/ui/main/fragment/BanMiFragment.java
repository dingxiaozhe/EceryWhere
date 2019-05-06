package com.example.administrator.ecerywhere.ui.main.fragment;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.administrator.ecerywhere.R;
import com.example.administrator.ecerywhere.base.BaseFragment;
import com.example.administrator.ecerywhere.presenter.main.BanMiPresenter;
import com.example.administrator.ecerywhere.view.main.BanMiView;

/**
 * .
 */
public class BanMiFragment extends BaseFragment<BanMiView,BanMiPresenter> implements BanMiView{


    @Override
    protected BanMiPresenter initPresenter() {
        return new BanMiPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_ban_mi;
    }

    @Override
    public void toastShort(String msg) {

    }
}
