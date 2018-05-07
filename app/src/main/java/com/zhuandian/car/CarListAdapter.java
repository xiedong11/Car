package com.zhuandian.car;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhuandian.car.entity.CarEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * desc :
 * author：xiedong
 * data：2018/3/10
 */

public class CarListAdapter extends RecyclerView.Adapter<CarListAdapter.MyViewHolder> {

    private List<CarEntity> mdata;
    private Context context;



    private ItemClickListener clickListener;

    public CarListAdapter(List<CarEntity> mdata, Context context) {
        this.mdata = mdata;
        this.context = context;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_car_list, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(inflate);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        final CarEntity carEntity = mdata.get(position);
        viewHolder.tvTitle.setText(carEntity.getGoodsTiltle() + "");
        viewHolder.tvContent.setText(carEntity.getGoodsContent() + "");
        viewHolder.tvPrice.setText("￥" + carEntity.getPrice());

        //正则切割时间字符串 2016-10-06 14:58:06
        String time[] = carEntity.getCreatedAt().split(" ");
        viewHolder.tvTime.setText(time[0]);
        Glide.with(context).load(carEntity.getGoodsUrl()).error(R.drawable.ic_launcher).into(viewHolder.ivGoods);
        viewHolder.linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickListener!=null){
                    clickListener.itemClick(carEntity);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mdata.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_goods)
        ImageView ivGoods;
        @BindView(R.id.tv_time)
        TextView tvTime;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_content)
        TextView tvContent;
        @BindView(R.id.tv_price)
        TextView tvPrice;
        @BindView(R.id.ll_container)
        LinearLayout linearLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public void setClickListener(ItemClickListener clickListener) {
        this.clickListener = clickListener;
    }
    public interface ItemClickListener{
        void itemClick(CarEntity entity);
    }
}
