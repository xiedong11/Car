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

import com.youth.banner.Banner;
import com.zhuandian.car.CarDetailActivity;
import com.zhuandian.car.GlideImageLoader;
import com.zhuandian.car.MainActivity;
import com.zhuandian.car.R;
import com.zhuandian.car.UploadCarActivity;
import com.zhuandian.car.adapter.CarListAdapter;
import com.zhuandian.car.entity.CarEntity;

import java.util.ArrayList;
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
public class HomeFragment extends Fragment {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_right_image)
    ImageView ivRightImage;
    @BindView(R.id.tv_right_text)
    TextView tvRightText;
    @BindView(R.id.banner)
    Banner bannerGuideContent;
    @BindView(R.id.rv_car_list)
    RecyclerView rvCarList;
    private View mView;
    private List<String> mDatas;
    private CarListAdapter adapter;
    private MainActivity activity;
    List<Integer> images = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_home, null);
        ButterKnife.bind(this, mView);
        tvTitle.setText("首页");
        ivRightImage.setVisibility(View.VISIBLE);
        ivRightImage.setImageResource(R.drawable.ic_upload_car);
        activity = (MainActivity) getActivity();
        rvCarList.setNestedScrollingEnabled(false);  //禁止Ryecyclerview滑动
        initBanner();
        initData();
        return mView;
    }

    private void initBanner() {
        images.add(R.drawable.ic_banner_one);
        images.add(R.drawable.ic_banner_two);
        images.add(R.drawable.ic_banner_three);

        bannerGuideContent.setImageLoader(new GlideImageLoader())
                .setImages(images)
//                .startAutoPlay();
                .start();


    }


    private void initData() {
        //查询数据库中的具体商品信息
        final CarEntity carEntity = new CarEntity();
        BmobQuery<CarEntity> query = new BmobQuery<CarEntity>();
        query.order("-updatedAT");
        query.setLimit(5);
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


    @OnClick({R.id.ll_old_car, R.id.ll_new_car, R.id.iv_right_image})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_new_car:
                getFragmentManager()
                        .beginTransaction()
                        .add(R.id.fl_container, CarShopFragment.getInstance(true, false), "f")
                        .commit();
                Toast.makeText(getActivity(), "切换为新车列表", Toast.LENGTH_SHORT).show();
                activity.initTabState(R.id.ll_car_shop);
                break;
            case R.id.ll_old_car:
                getFragmentManager()
                        .beginTransaction()
                        .add(R.id.fl_container, CarShopFragment.getInstance(false, false), "f")
                        .commit();
                Toast.makeText(getActivity(), "切换为二手车列表", Toast.LENGTH_SHORT).show();
                activity.initTabState(R.id.ll_car_shop);
                break;
            case R.id.iv_right_image:
                startActivity(new Intent(getActivity(), UploadCarActivity.class));
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        bannerGuideContent.stopAutoPlay();
    }
}
