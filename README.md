# ResultHelper

To request permissions and startActivityForResult by Fragment without UI

#### Step 1. Add the JitPack repository in your root build.gradle
```
allprojects {
    repositories {
    ...
    maven { url 'https://jitpack.io' }
    }
}
```
#### Step 2. Add the dependency in you app module build.gradle dependencies
```
dependencies {
    implementation 'com.github.lvleo:ResultHelper:1.0.1'
}
```

#### Step 3.1. Copy this code into you class where you need for Request Permissions
```
//申请相机权限
ResultHelper.with(MainActivity.this).requestPermissions(new String[]{Manifest.permission.CAMERA}, new OnPermissionResultListener() {
    @Override
    public void onResult(boolean isGrant) {
        if (isGrant) {
            //所申请的权限已经获取成功，可进行下一步操作
            Toast.makeText(context, "相机权限已经获取", Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(context, "权限获取失败，请去应用权限管理界面手动开启", Toast.LENGTH_LONG).show();
        }
    }
});
```

#### Step 3.2. Copy this code into you class where you need for onStartActivityForResult
```
//不传递任何数据
ResultHelper.with(this).startForResult(TestActivity.class, new OnActivityResultListener() {
    @Override
    public void onResult(int resultCode, Intent data) {
        //处理测试页面回传的数据
        if (resultCode == Activity.RESULT_OK && data != null) {
            String resultStr = data.getStringExtra("testData");
            Toast.makeText(context, "返回的数据为：" + resultStr, Toast.LENGTH_LONG).show();
        }
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

//跳转系统 Intent（比如拍照）
Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
intent.addCategory(Intent.CATEGORY_DEFAULT);
//...其他相关参数 参见 app module 里 MainActivity 类中 takePhoto() 示例代码
ResultHelper.with((FragmentActivity) activity).startForResult(intent, (resultCode, data) -> {
    if (resultCode == RESULT_OK) {
        Log.e(TAG, "onResult: data------" + data.toString());
    }
});

```