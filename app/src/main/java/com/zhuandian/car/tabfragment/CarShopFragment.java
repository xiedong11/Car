package com.zhuandian.car.tabfragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zhuandian.car.CarDetailActivity;
import com.zhuandian.car.R;
import com.zhuandian.car.UploadCarActivity;
import com.zhuandian.car.adapter.CarListAdapter;
import com.zhuandian.car.entity.CarEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by xiedong
 */
public class CarShopFragment extends Fragment {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_right_image)
    ImageView ivRightImage;
    @BindView(R.id.tv_right_text)
    TextView tvRightText;
    @BindView(R.id.tv_all)
    TextView tvAll;
    @BindView(R.id.tv_new)
    TextView tvNew;
    @BindView(R.id.tv_old)
    TextView tvOld;
    @BindView(R.id.rv_car_list)
    RecyclerView rvCarList;
    private View mView;
    private List<String> mDatas;
    private CarListAdapter adapter;
    private boolean isNewCar;
    private boolean isShowAll;

    public static CarShopFragment getInstance(Boolean isNewCar, Boolean showAll) {
        CarShopFragment fragment = new CarShopFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean("is_new_car", isNewCar);
        bundle.getBoolean("show_all", showAll);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_car_shop, null);
        ButterKnife.bind(this, mView);
        isNewCar = getArguments().getBoolean("is_new_car");
        isShowAll = getArguments().getBoolean("show_all");
        tvTitle.setText("车城");
        initTopTab(isShowAll ? R.id.tv_all : isNewCar ? R.id.tv_new : R.id.tv_old);
        ivRightImage.setVisibility(View.VISIBLE);
        ivRightImage.setImageResource(R.drawable.ic_upload_car);
        initData(isNewCar);
        return mView;
    }

    private void initData(Boolean isNewCar) {
        //查询数据库中的具体商品信息
        final CarEntity carEntity = new CarEntity();
        BmobQuery<CarEntity> query = new BmobQuery<CarEntity>();
        query.order("-updatedAT");
        if (!isShowAll) {
            query.addWhereEqualTo("isNewCar", isNewCar);
        }
        query.setLimit(50);
        //执行查询方法
        query.findObjects(new FindListener<CarEntity>() {
            @Override
            public void done(final List<CarEntity> data, BmobException e) {
                if (e == null) {
                    if (data.size() == 0) {
                        //数据全部被加载，，已经没有可再加载的数据时，对footview的操作
                        return;
                    }
                    adapter = new CarListAdapter(data, getActivity());
                    rvCarList.setLayoutManager(new LinearLayoutManager(getActivity()));
                    rvCarList.setAdapter(adapter);
                    adapter.setClickListener(new CarListAdapter.ItemClickListener() {
                        @Override
                        public void itemClick(CarEntity entity) {
                            Intent intent = new Intent(getActivity(), CarDetailActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("entity", entity);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });
                }
            }
        });

    }


    @OnClick({R.id.tv_all, R.id.tv_new, R.id.tv_old})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_all:
                isShowAll = true;
                initData(true);
                initTopTab(R.id.tv_all);
                Toast.makeText(getActivity(), "切换全部列表", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                break;
            case R.id.tv_new:
                isShowAll = false;
                initData(true);
                initTopTab(R.id.tv_new);
                Toast.makeText(getActivity(), "切换为新车列表", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                break;
            case R.id.tv_old:
                isShowAll = false;
                initTopTab(R.id.tv_old);
                initData(false);
                Toast.makeText(getActivity(), "切换为二手车列表", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void initTopTab(int tabId) {
        initAllTopTab();
        switch (tabId) {
            case R.id.tv_all:
                tvAll.setBackgroundColor(getResources().getColor(R.color.color_white));
                tvAll.setTextColor(getResources().getColor(R.color.app_base_color));
                break;
            case R.id.tv_new:
                tvNew.setBackgroundColor(getResources().getColor(R.color.color_white));
                tvNew.setTextColor(getResources().getColor(R.color.app_base_color));
                break;
            case R.id.tv_old:
                tvOld.setBackgroundColor(getResources().getColor(R.color.color_white));
                tvOld.setTextColor(getResources().getColor(R.color.app_base_color));
                break;
        }
    }

    private void initAllTopTab() {
        tvAll.setBackgroundColor(getResources().getColor(R.color.app_base_color));
        tvNew.setBackgroundColor(getResources().getColor(R.color.app_base_color));
        tvOld.setBackgroundColor(getResources().getColor(R.color.app_base_color));
        tvAll.setTextColor(getResources().getColor(R.color.color_white));
        tvNew.setTextColor(getResources().getColor(R.color.color_white));
        tvOld.setTextColor(getResources().getColor(R.color.color_white));
    }


}
