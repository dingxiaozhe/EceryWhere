package com.example.administrator.ecerywhere.ui.main.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.administrator.ecerywhere.R;
import com.example.administrator.ecerywhere.base.BaseActivity;
import com.example.administrator.ecerywhere.presenter.main.InformationPresenter;
import com.example.administrator.ecerywhere.ui.main.fragment.LoginFragment;
import com.example.administrator.ecerywhere.view.main.InformationView;
import com.umeng.commonsdk.debug.I;

import butterknife.BindView;
import butterknife.OnClick;

public class InformActivity extends BaseActivity<InformationView,InformationPresenter> implements InformationView{

@BindView(R.id.btn)
    Button mBtn;
    @Override
    protected InformationPresenter initPresenter() {
        return new InformationPresenter();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_inform_activity;
    }


    @OnClick(R.id.btn)
    public  void onClick(View v){
        switch (v.getId()) {
            case R.id.btn:
                startActivity(new Intent(InformActivity.this, LoginActivity.class));
                break;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        menu.add(1,100,100,"通知");
        menu.add(2,200,200,"信息");
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case 100:
                startActivity(new Intent(InformActivity.this,MessageActivity.class));
                break;
            case 200:
                startActivity(new Intent(InformActivity.this,NotificationActivity.class));
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
