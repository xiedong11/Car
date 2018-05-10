package com.zhuandian.car;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.zhuandian.car.entity.CarEntity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadFileListener;

/**
 * Created by xiedong
 */
public class UploadCarActivity extends Activity {
    @BindView(R.id.rb_new)
    RadioButton rbNew;
    @BindView(R.id.rb_old)
    RadioButton rbOld;
    @BindView(R.id.iv_goods)
    ImageView goodsImageView;
    @BindView(R.id.ed_desc)
    EditText contentEditText;
    @BindView(R.id.ed_title)
    EditText titleEditText;
    @BindView(R.id.ed_price)
    EditText priceEditText;
    @BindView(R.id.ed_name)
    EditText nameEditText;
    @BindView(R.id.ed_phone)
    EditText phoneEditText;
    @BindView(R.id.commit)
    TextView commit;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_right_image)
    ImageView ivRightImage;
    @BindView(R.id.tv_right_text)
    TextView tvRightText;
    @BindView(R.id.ed_invater)
    EditText edInvater;
    private Bitmap mBitmap;
    private boolean isNewCar;
    private CarEntity carEntity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_car);
        ButterKnife.bind(this);
        initView();
    }


    private void initView() {
        if (rbNew.isChecked()) {
            isNewCar = true;
        } else if (rbOld.isChecked()) {
            isNewCar = false;
        }

        tvTitle.setText("上传车信息");
        goodsImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePhoto();
            }
        });

        //给按钮绑定监听事件
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                pDialog.show();
                Toast.makeText(UploadCarActivity.this, "开始上传...", Toast.LENGTH_SHORT).show();
                uploadGoods();
            }
        });
    }

    /**
     * 上传会员用户的商品详情到数据库
     */
    private void uploadGoods() {

        String name = nameEditText.getText().toString();
        String phone = phoneEditText.getText().toString();
        String title = titleEditText.getText().toString();
        String content = contentEditText.getText().toString();
        String price = priceEditText.getText().toString();
        String invaterName = edInvater.getText().toString();
        Bitmap bitmap = mBitmap;

        if ("".equals(name) || "".equals(phone) || "".equals(title) || "".equals(content) || "".equals(price) || mBitmap == null) {
//            new SweetAlertDialog(getActivity(),SweetAlertDialog.ERROR_TYPE)
//                    .setTitleText("请完善商品信息")
//                    .setContentText("商品信息所有条目都为必填项 ！！")
//                    .show();
            //cancle点提交商品的dialog
//            pDialog.cancel();
//            pDialog.dismiss();
            Toast.makeText(UploadCarActivity.this, "请完善所有必填内容", Toast.LENGTH_SHORT).show();
        } else {

            carEntity = new CarEntity();
            carEntity.setPrice(price);
            carEntity.setName(name);
            carEntity.setPhone(phone);
            carEntity.setGoodsTiltle(title);
            carEntity.setGoodsContent(content);
            carEntity.setNewCar(isNewCar);
            carEntity.setInvate(invaterName);
//        goodsBenn.setGoodsBitmap(bitmap);


            //上传图片文件并且返回图片的url
            final String picPath = "/sdcard/qyb/Goods.jpg";
            final BmobFile bmobFile = new BmobFile(new File(picPath));
            bmobFile.uploadblock(new UploadFileListener() {

                @Override
                public void done(BmobException e) {
                    if (e == null) {
                        //bmobFile.getFileUrl()--返回的上传文件的完整地址
                        Log.i("上传文件成功:", bmobFile.getFileUrl().toString());
                        //得到商品存储的url地址之后，代码商品属性加载加载完成，然后把各项数据存进数据库
                        carEntity.setGoodsUrl(bmobFile.getFileUrl() + "");
                        carEntity.save(new SaveListener<String>() {
                            @Override
                            public void done(String s, BmobException e) {
                                if (e == null) {
//                                    pDialog.cancel();
//                                    pDialog.dismiss();
                                    Toast.makeText(UploadCarActivity.this, "上传成功...", Toast.LENGTH_SHORT).show();
//                                    new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
//                                            .setTitleText("好的嘛 !")
//                                            .setContentText("小二已经记下您的商品啦 !")
//                                            .show();
//                                Toast.makeText(getActivity(), "上传成功", Toast.LENGTH_SHORT).show();
                                } else {
                                    Log.i("xie", e.toString());
                                }

                            }

                        });

                    } else {
                        Log.i("上传文件失败：", e.getMessage().toString());
                    }

                }


                @Override
                public void onProgress(Integer value) {
                    // 返回的上传进度（百分比）
                }
            });

        }

    }

    //调用摄像机拍照并存储在指定位置
    private void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 1);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            String sdStatus = Environment.getExternalStorageState();
            if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
                Log.i("TestFile",
                        "SD card is not avaiable/writeable right now.");
                return;
            }

            String name = "Goods" + ".jpg";
            Toast.makeText(UploadCarActivity.this, name, Toast.LENGTH_LONG).show();
            Bundle bundle = data.getExtras();
            mBitmap = (Bitmap) bundle.get("data");// 获取相机返回的数据，并转换为Bitmap图片格式

            FileOutputStream b = null;
            File file = new File("/sdcard/qyb/");
            file.mkdirs();// 创建文件夹
            String fileName = "/sdcard/qyb/" + name;

            try {
                b = new FileOutputStream(fileName);
                mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } finally {
                try {
                    b.flush();
                    b.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            try {
                goodsImageView.setImageBitmap(mBitmap);// 将图片显示在ImageView里
            } catch (Exception e) {
                Log.e("error", e.getMessage());
            }

        }
    }


    @OnClick({R.id.rb_new, R.id.rb_old})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rb_new:
                isNewCar = true;
                break;
            case R.id.rb_old:
                isNewCar = false;
                break;
        }
    }
}
