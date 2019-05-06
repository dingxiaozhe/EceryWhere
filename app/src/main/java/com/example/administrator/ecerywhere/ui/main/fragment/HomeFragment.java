package com.example.administrator.ecerywhere.ui.main.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.administrator.ecerywhere.R;
import com.example.administrator.ecerywhere.base.BaseFragment;
import com.example.administrator.ecerywhere.bean.HomeBean;
import com.example.administrator.ecerywhere.net.EveryWhereService;
import com.example.administrator.ecerywhere.presenter.main.HomePresenter;
import com.example.administrator.ecerywhere.ui.main.adapter.HomeAdapter;
import com.example.administrator.ecerywhere.view.main.HomeView;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends BaseFragment<HomeView, HomePresenter> implements HomeView {

    private static final String TAG = "HomeFragment";
    @BindView(R.id.recyclerview)
    RecyclerView recyclerview;
    @BindView(R.id.refreshlayout)
    SmartRefreshLayout refreshlayout;

    private ArrayList<HomeBean.ResultBean.BannersBean> bannerList;
    private ArrayList<HomeBean.ResultBean.RoutesBean> list;
    private HomeAdapter adapter;
    private int page = 2;

    @Override
    protected HomePresenter initPresenter() {

        return new HomePresenter();
    }

    @Override
    protected int getLayoutId() {

        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        list = new ArrayList<>();
        bannerList = new ArrayList<>();
        adapter = new HomeAdapter(bannerList, list, getContext());
        recyclerview.setAdapter(adapter);
        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl(EveryWhereService.homeUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        Observable<HomeBean> home1 = retrofit1.create(EveryWhereService.class).getHome(page);
        home1.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeBean homeBean) {
                        Log.e(TAG, "onNexts: " + homeBean.getResult().getBanners());
                        if (homeBean.getResult().getRoutes() != null) {
                            bannerList.addAll(homeBean.getResult().getBanners());
                            if (bannerList.size() > 0) {
                                adapter.setBannerList(bannerList);
                                adapter.notifyDataSetChanged();
                            }

                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(EveryWhereService.homeUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
        final Observable<HomeBean> home = retrofit.create(EveryWhereService.class).getHome(page);
        home.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<HomeBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(HomeBean homeBean) {
                        Log.e(TAG, "onNext: " + homeBean.getResult().getRoutes());
                        if (homeBean.getResult().getRoutes() != null) {
                            list.addAll(homeBean.getResult().getRoutes());
                            if (list.size() > 0) {
                                adapter.setList(list);
                                adapter.notifyDataSetChanged();
                            }

                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });

        refreshlayout.setEnableRefresh(true);
        refreshlayout.setEnableLoadmore(true);
        refreshlayout.finishRefresh();
        refreshlayout.finishLoadmore();
    }

    @Override
    protected void initView() {
        recyclerview.setLayoutManager(new LinearLayoutManager(getContext()));

        refreshlayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                page=2;
                bannerList.clear();
                list.clear();
                adapter.notifyDataSetChanged();
                refreshlayout.finishRefresh();
            }
        });
        refreshlayout.setOnLoadmoreListener(new OnLoadmoreListener() {
            @Override
            public void onLoadmore(RefreshLayout refreshlayout) {
                ++page;
                Log.e(TAG, "onLoadmore: "+page);
                adapter.notifyDataSetChanged();
                refreshlayout.finishLoadmore();
            }
        });

    }

    @Override
    public void toastShort(String msg) {

    }



}
