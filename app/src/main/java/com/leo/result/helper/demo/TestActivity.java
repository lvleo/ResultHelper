package com.leo.result.helper.demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * @Author : yongxu.lv
 * @Date : 2019/12/10 11:22
 * @Desc : 测试 ResultHelper 的 startForResult 功能
 */
public class TestActivity extends AppCompatActivity {

    private static final String TAG = TestActivity.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        String type = getIntent().getExtras().getString("type");
        Log.e(TAG, "onCreate: type======" + type);

        Button btnSetResultData = findViewById(R.id.btn_set_result_data);
        btnSetResultData.setOnClickListener(v -> {
            Intent data = new Intent();
            data.putExtra("testData", "测试 startForResult 功能");
            setResult(Activity.RESULT_OK, data);
            finish();
        });

    }
}
