package com.example.administrator.ecerywhere.ui.main.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.administrator.ecerywhere.R;
import com.example.administrator.ecerywhere.bean.HomeBean;
import com.youth.banner.Banner;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<HomeBean.ResultBean.BannersBean> bannerList;
    private ArrayList<HomeBean.ResultBean.RoutesBean> list;
    private Context context;
    private static final int ITEM_HOME_BANNER = 1;
    private static final int ITEM_HOME = 2;



    public HomeAdapter(ArrayList<HomeBean.ResultBean.BannersBean> bannerList, ArrayList<HomeBean.ResultBean.RoutesBean> list, Context context) {
        this.bannerList = bannerList;
        this.list = list;
        this.context = context;
    }

    public void setBannerList(ArrayList<HomeBean.ResultBean.BannersBean> bannerList) {
        this.bannerList = bannerList;
    }

    public void setList(ArrayList<HomeBean.ResultBean.RoutesBean> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (i == ITEM_HOME_BANNER) {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_home_banner, null);
            return new ViewHolder1(inflate);
        } else {
            View inflate = LayoutInflater.from(context).inflate(R.layout.item_home, null);
            return new ViewHolder2(inflate);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        int itemViewType = getItemViewType(i);
        if (itemViewType==ITEM_HOME_BANNER){
            final HomeAdapter.ViewHolder1 viewHolder1= (ViewHolder1) viewHolder;
            viewHolder1.banner.setImages(bannerList);
            viewHolder1.banner.setImageLoader(new ImageLoader() {
                @Override
                public void displayImage(Context context, Object path, ImageView imageView) {
                      final   HomeBean.ResultBean.BannersBean  path1= (HomeBean.ResultBean.BannersBean) path;
                    Glide.with(context).load(path1.getImageURL()).into(imageView);
                }
            });
            viewHolder1.banner.start();
        }else {
            HomeAdapter.ViewHolder2 viewHolder2= (ViewHolder2) viewHolder;
            int newposition=i;
            if (bannerList.size()>0){
                newposition=i-1;
            }
            viewHolder2.tv1.setText(list.get(newposition).getTitle());
            viewHolder2.tv2.setText(list.get(newposition).getCity());
            viewHolder2.tv3.setText(list.get(newposition).getIntro());
            viewHolder2.tv4.setText(list.get(newposition).getPriceInCents()+"");
            Glide.with(context).load(list.get(newposition).getCardURL()).into(viewHolder2.img);
        }
    }

    @Override
    public int getItemCount() {
        if (bannerList.size() > 0) {
            return list.size() + 1;
        }else if (list.size()>0){
            return list.size();
        }
        return 0;
    }

    @Override
    public int getItemViewType(int position) {
        if (bannerList.size() > 0 && position == 0) {
            return ITEM_HOME_BANNER;

        } else  {
            return ITEM_HOME;
        }

    }

    public class ViewHolder1 extends RecyclerView.ViewHolder {
        private Banner banner;

        public ViewHolder1(@NonNull View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
        }
    }

    public class ViewHolder2 extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView tv1;
        private TextView tv2;
        private TextView tv3;
        private TextView tv4;
        private Button btn1;
        public ViewHolder2(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
            tv1=itemView.findViewById(R.id.tv1);
            tv2=itemView.findViewById(R.id.tv2);
            tv3=itemView.findViewById(R.id.tv3);
            tv4=itemView.findViewById(R.id.tv4);
            btn1=itemView.findViewById(R.id.btn1);

        }
    }
}
