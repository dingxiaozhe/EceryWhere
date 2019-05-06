package com.example.administrator.ecerywhere.ui.main.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.administrator.ecerywhere.R;
import com.example.administrator.ecerywhere.base.BaseFragment;
import com.example.administrator.ecerywhere.presenter.main.VerifyPresenter;
import com.example.administrator.ecerywhere.view.main.VerifyView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * A simple {@link Fragment} subclass.
 */
public class VerifyFragment extends BaseFragment<VerifyView, VerifyPresenter> implements VerifyView {


    @BindView(R.id.img_back)
    ImageView mImgBack;


    public VerifyFragment() {
        // Required empty public constructor
    }


    @Override
    protected VerifyPresenter initPresenter() {

        return new VerifyPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_verify;
    }

    @OnClick(R.id.img_back)
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.img_back:
                FragmentManager manager =getActivity().getSupportFragmentManager();
                manager.beginTransaction().addToBackStack(null).add(R.id.fl_container,new LoginFragment()).commit();
                break;
        }
    }


    @Override
    public void toastShort(String msg) {

    }
}
