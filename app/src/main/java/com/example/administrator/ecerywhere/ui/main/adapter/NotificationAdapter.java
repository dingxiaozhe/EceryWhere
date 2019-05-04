package com.example.administrator.ecerywhere.ui.main.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.ecerywhere.R;
import com.example.administrator.ecerywhere.bean.InformBean;
import com.example.administrator.ecerywhere.ui.main.activity.DetailsActivity;

import java.util.ArrayList;

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.ViewHolder> {
    private Context context;
    private ArrayList<InformBean> list;


    public NotificationAdapter(Context context, ArrayList<InformBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_noti, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        InformBean bean = list.get(i);
        if (bean != null) {
            viewHolder.tv_title_inform.setText(bean.getTitle());
            viewHolder.tv_time_inform.setText(bean.getTime());
            viewHolder.tv_text_inform.setText(bean.getText());
        }

        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context,DetailsActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_title_inform;
        private TextView tv_time_inform;
        private TextView tv_text_inform;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_title_inform = itemView.findViewById(R.id.tv_title_inform);
            tv_time_inform = itemView.findViewById(R.id.tv_time_inform);
            tv_text_inform = itemView.findViewById(R.id.tv_text_inform);
        }
    }
}
