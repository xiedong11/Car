package com.zhuandian.car;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhuandian.car.entity.MyUser;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class RegeistActivity extends AppCompatActivity {
    @BindView(R.id.ed_name)
    EditText edName;
    @BindView(R.id.ed_password)
    EditText edPassword;
    @BindView(R.id.ed_invate)
    EditText edInvate;
    @BindView(R.id.tv_regeist)
    TextView tvRegeist;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    private String userName;
    private String passWord;
    private String invateName;
    private String mima;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regeist);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_regeist, R.id.tv_login})
    public void onViewClicked(View view) {

        switch (view.getId()) {
            case R.id.tv_regeist:
                regisitNewUser();
                break;
            case R.id.tv_login:
                startActivity(new Intent(RegeistActivity.this, LoginActivity.class));
                finish();
                break;
        }
    }

    /**
     * 注册新用户
     */
    private void regisitNewUser() {
        userName = edName.getText().toString();
        passWord = edPassword.getText().toString();
        invateName = edInvate.getText().toString();
        final MyUser bu = new MyUser();
        bu.setUsername(userName);
        bu.setPassword(passWord);
        bu.setInvateName(invateName);
        bu.setMima(passWord);

        bu.signUp(new SaveListener<MyUser>() {
            @Override
            public void done(MyUser myUser, BmobException e) {
                Toast.makeText(RegeistActivity.this, "注册成功，请返回登录页登录", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
