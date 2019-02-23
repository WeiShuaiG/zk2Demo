package com.umeng.soexample.zk2demo.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.umeng.soexample.zk2demo.R;
import com.umeng.soexample.zk2demo.entity.Gjz;

import java.util.List;


public class GjzAdapter extends RecyclerView.Adapter<GjzAdapter.ViewHolder>{
    private List<Gjz.ResultBean> list;
    private Context context;



    public GjzAdapter(List<Gjz.ResultBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder holder = null;
        View view = LayoutInflater.from(context).inflate(R.layout.item_gjz,parent,false);
        holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        
        Uri uri = Uri.parse(list.get(position).getMasterPic());
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build();
        holder.img.setController(controller);
        holder.name.setText(list.get(position).getCommodityName());
        holder.price.setText("￥"+list.get(position).getPrice()+".00");
        holder.yishou.setText(list.get(position).getSaleNum()+"件");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener!=null){
                    listener.onItemClick(view,position,list.get(position).getCommodityId());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private SimpleDraweeView img;
        private TextView name;
        private TextView price;
        private TextView yishou;
        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.shou_gjz_img);
            name = itemView.findViewById(R.id.shou_gjz_name);
            price = itemView.findViewById(R.id.shou_gjz_price);
            yishou = itemView.findViewById(R.id.shou_gjz_yishou);
        }
    }
    public OnItemClickListener listener;
    public interface OnItemClickListener{
        void onItemClick(View v, int positon, int id);
    }

    public void setClick(OnItemClickListener listener){
        this.listener = listener;
    }
}
