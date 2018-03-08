package com.clj.module.b.ui;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.clj.module.b.R;
import com.clj.module.b.router.ModuleBGetter;

public class ModuleBActivity extends AppCompatActivity {

    public static final String KEY_INTENT_PARAM = "key_intent_param";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_b);

        String param = getIntent().getStringExtra(KEY_INTENT_PARAM);
        Toast.makeText(ModuleBActivity.this, "页面跳转携带参数： " + param, Toast.LENGTH_LONG).show();

        findViewById(R.id.btn_id).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int userId = ModuleBGetter.getUserId(ModuleBActivity.this);
                Toast.makeText(ModuleBActivity.this, "取得userId： " + userId, Toast.LENGTH_LONG).show();
            }
        });
    }
}
