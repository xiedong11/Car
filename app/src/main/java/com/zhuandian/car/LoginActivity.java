package com.zhuandian.car;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.ed_name)
    EditText edName;
    @BindView(R.id.ed_password)
    EditText edPassword;
    @BindView(R.id.tv_login)
    TextView tvLogin;
    @BindView(R.id.tv_regeist)
    TextView tvRegeist;
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.tv_login, R.id.tv_regeist, R.id.tv_gt_password})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_login:
                login();
                break;
            case R.id.tv_regeist:
                startActivity(new Intent(this, RegeistActivity.class));
                break;
            case R.id.tv_gt_password:
                startActivity(new Intent(this, GetPasswordActivity.class));
                break;
        }
    }

    private void login() {
        username = edName.getText().toString();
        password = edPassword.getText().toString();
        final MyUser user = new MyUser();
        user.setPassword(password);
        user.setUsername(username);
        user.login(new SaveListener<MyUser>() {
            @Override
            public void done(MyUser myUser, BmobException e) {
                if (e == null) {
                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    SharedPreferences sp = getSharedPreferences("user", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("user_id", user.getObjectId());
                    editor.commit();
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
}
