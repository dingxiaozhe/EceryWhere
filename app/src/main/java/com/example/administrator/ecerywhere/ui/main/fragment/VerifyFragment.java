package com.example.administrator.ecerywhere.ui.main.fragment;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.administrator.ecerywhere.R;
import com.example.administrator.ecerywhere.base.BaseFragment;
import com.example.administrator.ecerywhere.presenter.main.VerifyPresenter;
import com.example.administrator.ecerywhere.view.main.VerifyView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class VerifyFragment extends BaseFragment<VerifyView,VerifyPresenter> implements VerifyView {


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







}
