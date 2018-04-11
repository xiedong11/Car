package com.zhuandian.car;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.zhuandian.car.entity.CarEntity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by xiedong
 */
public class CarListFragment extends Fragment {

    @BindView(R.id.tv_new)
    TextView tvNew;
    @BindView(R.id.tv_old)
    TextView tvOld;
    private RecyclerView rvCarList;
    private View mView;
    private List<String> mDatas;
    private CarListAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView = inflater.inflate(R.layout.fragment_car_list, null);
        rvCarList = (RecyclerView) mView.findViewById(R.id.rv_car_list);
        ButterKnife.bind(this, mView);
        initData(true);
        return mView;
    }

    private void initData(Boolean isNewCar) {
        //查询数据库中的具体商品信息
        final CarEntity carEntity = new CarEntity();
        BmobQuery<CarEntity> query = new BmobQuery<CarEntity>();
        query.order("-updatedAT");
        query.addWhereEqualTo("isNewCar",isNewCar);
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


    @OnClick({R.id.tv_new, R.id.tv_old})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_new:
                initData(true);
                Toast.makeText(getActivity(), "切换为新车列表", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                break;
            case R.id.tv_old:
                initData(false);
                Toast.makeText(getActivity(), "切换为二手车列表", Toast.LENGTH_SHORT).show();
                adapter.notifyDataSetChanged();
                break;
        }
    }
}
