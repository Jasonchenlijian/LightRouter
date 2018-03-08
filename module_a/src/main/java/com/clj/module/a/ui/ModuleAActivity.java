package com.clj.module.a.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.clj.module.a.R;
import com.clj.module.a.router.ModuleAGetter;

public class ModuleAActivity extends AppCompatActivity {

    public static int USER_ID = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_a);

        // 在模块A里面模拟登陆
        findViewById(R.id.btn_login).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                USER_ID = (int) (Math.random() * 100);
                Toast.makeText(ModuleAActivity.this, "登陆成功，userId为： " + USER_ID, Toast.LENGTH_LONG).show();
            }
        });

        // 从模块A页面跳转到模块B页面，并携带参数
        findViewById(R.id.btn_navigate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ModuleAGetter.navigateToModuleBUI(ModuleAActivity.this, "test");
            }
        });

    }


}
