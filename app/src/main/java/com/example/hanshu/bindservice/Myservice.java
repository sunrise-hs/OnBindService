package com.example.hanshu.bindservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import android.util.Log;
import android.widget.Toast;

/**
 * Created by HanShu on 2016/7/18.
 */
public class Myservice extends Service {
    private static final String TAG = "Myservice";

    class MyBind extends Binder{
       public void callMethod(){
           method();

       }
    }
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind: 服务被绑定！！");
        return new MyBind();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate: 服务被创建！");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onCreate: 服务被销毁！");
    }
    public  void method(){
        Toast.makeText(this,"我是服务器中的方法，我被调用了！！",Toast.LENGTH_SHORT).show();
    }
}
