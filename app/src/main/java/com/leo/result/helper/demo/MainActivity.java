package com.leo.result.helper.demo;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.leo.result.helper.OnActivityResultListener;
import com.leo.result.helper.ResultHelper;


/**
 * @Author : yongxu.lv
 * @Date : 2019/12/10 10:52
 * @Desc : 功能演示页面
 */
public class MainActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.context = this;

        Button btnRequestPermission = findViewById(R.id.btn_click_permission);
        Button btnStartResult = findViewById(R.id.btn_click_test_start_result);

        btnRequestPermission.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //申请相机权限
//                ResultHelper.with(MainActivity.this).requestPermissions(new String[]{Manifest.permission.CAMERA}, new OnPermissionResultListener() {
//                    @Override
//                    public void onResult(boolean isGrant) {
//                        if (isGrant) {
//                            //所申请的权限已经获取成功，可进行下一步操作
//                            Toast.makeText(context, "相机权限已经获取", Toast.LENGTH_LONG).show();
//                        } else {
//                            Toast.makeText(context, "权限获取失败，请去应用权限管理界面手动开启", Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });

                //Java 8 lambda code
                ResultHelper.with(MainActivity.this).requestPermissions(new String[]{Manifest.permission.CAMERA}, isGrant -> {
                    if (isGrant) {
                        //所申请的权限已经获取成功，可进行下一步操作
                        Toast.makeText(context, "相机权限已经获取", Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(context, "权限获取失败，请去应用权限管理界面手动开启", Toast.LENGTH_LONG).show();
                    }
                });

            }
        });

        btnStartResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //跳转页面并回传数据
//                ResultHelper.with(MainActivity.this).startForResult(TestActivity.class, new OnActivityResultListener() {
//                    @Override
//                    public void onResult(int resultCode, Intent data) {
//                        //处理测试页面回传的数据
//                        if (resultCode == Activity.RESULT_OK && data != null) {
//                            String resultStr = data.getStringExtra("testData");
//                            Toast.makeText(context, "返回的数据为：" + resultStr, Toast.LENGTH_LONG).show();
//                        }
//                    }
//                });

                //Java 8 lambda code
                ResultHelper.with(MainActivity.this).startForResult(TestActivity.class, (resultCode, data) -> {
                    //处理测试页面回传的数据
                    if (resultCode == Activity.RESULT_OK && data != null) {
                        String resultStr = data.getStringExtra("testData");
                        Toast.makeText(context, "返回的数据为：" + resultStr, Toast.LENGTH_LONG).show();
                    }
                });

                //数据用 Intent 包装传递
                Intent intent = new Intent();
                intent.putExtra("type", "1");
                ResultHelper.with(MainActivity.this).startForResult(TestActivity.class, intent, (resultCode, data) -> {
                    String resultStr = data.getStringExtra("testData");
                    Toast.makeText(context, "返回的数据为：" + resultStr, Toast.LENGTH_LONG).show();
                });

                //数据用 Bundle 包装传递
                Bundle bundle = new Bundle();
                bundle.putString("type", "2");
                ResultHelper.with(MainActivity.this).startForResult(TestActivity.class, bundle, (resultCode, data) -> {
                    String resultStr = data.getStringExtra("testData");
                    Toast.makeText(context, "返回的数据为：" + resultStr, Toast.LENGTH_LONG).show();
                });
            }
        });

    }
}
