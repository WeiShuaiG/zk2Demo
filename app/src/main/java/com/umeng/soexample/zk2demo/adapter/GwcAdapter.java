package com.umeng.soexample.zk2demo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;


import com.bumptech.glide.Glide;
import com.umeng.soexample.zk2demo.CountView;
import com.umeng.soexample.zk2demo.R;
import com.umeng.soexample.zk2demo.entity.Querygwc;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by W on 2019/1/11 10:57.
 */

public class GwcAdapter extends RecyclerView.Adapter<GwcAdapter.ViewHolder> {
    private List<Querygwc.ResultBean> list = new ArrayList<>();
    private Context context;

    public GwcAdapter(List<Querygwc.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder holder = null;
        View view = LayoutInflater.from(context).inflate(R.layout.item_gwc,parent,false);
        holder = new ViewHolder(view);


        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Glide.with(context).load(list.get(position).getPic()).into(holder.gou_item_img);
        holder.gou_item_name.setText(list.get(position).getCommodityName());
        holder.gou_item_price.setText("￥"+list.get(position).getPrice()+".00");
        holder.item_count.setNumber(list.get(position).getCount());
        holder.gou_item_check.setChecked(list.get(position).isChildCheck());


        holder.gou_item_check.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClick != null) {
                    onItemClick.onClick(view, position);
                }
            }
        });
        holder.item_count.setOnChangeNumber(new CountView.OnChangeNumber() {
            @Override
            public void getNumber(int number) {
                if (onItemClick != null) {
                    onItemClick.onNumber(position, number);
                }
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClick != null) {
                    onItemClick.onDelete(view, position);
                }
            }
        });



    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public void setCheckStatus(int position, boolean status) {
        list.get(position).setChildCheck(status);
    }

    public boolean thisCheckStatus(int position) {
        boolean childCheck = list.get(position).isChildCheck();
        if (childCheck) {
            return true;
        } else {
            return false;
        }
    }

    public boolean thisShopStatus() {
        for (int i = 0; i < list.size(); i++) {
            boolean childCheck = list.get(i).isChildCheck();
            if (childCheck) {
                return true;
            }
        }
        return false;
    }

    //所有的checkbox的状态
    public boolean allCheckStatus() {
        for (int i = 0; i < list.size(); i++) {
            boolean childCheck = list.get(i).isChildCheck();
            if (!childCheck) {
                return false;
            }
        }
        return true;
    }

    public void setAllCheckStatus(boolean status) {
        for (int i = 0; i < list.size(); i++) {
            list.get(i).setChildCheck(status);
        }
    }

    public double getAllPrice() {
        int mprice = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isChildCheck()) {
                mprice += list.get(i).getPrice() * list.get(i).getCount();
            }
        }
        return mprice;
    }

    public int getAllCount() {
        int mcount = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).isChildCheck()) {
                mcount += list.get(i).getCount();
            }
        }
        return mcount;
    }

    public void setShopCount(int position, int number) {
        list.get(position).setCount(number);
    }

    public interface OnItemClick {
        void onClick(View view, int position);

        void onDelete(View view, int position);

        void onNumber(int position, int number);
    }

    private OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private CheckBox gou_item_check;
        private ImageView gou_item_img;
        private TextView gou_item_name;
        private TextView gou_item_price;
        private Button delete;
        CountView item_count;
        public ViewHolder(View itemView) {
            super(itemView);
            gou_item_check = itemView.findViewById(R.id.gou_item_check);
            gou_item_img = itemView.findViewById(R.id.gou_item_img);
            gou_item_name = itemView.findViewById(R.id.gou_item_name);
            gou_item_price = itemView.findViewById(R.id.gou_item_price);
            item_count = itemView.findViewById(R.id.item_count);
            delete = itemView.findViewById(R.id.item_delete);

        }
    }
}
