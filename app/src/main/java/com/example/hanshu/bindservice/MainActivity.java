package com.example.hanshu.bindservice;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

public class MainActivity extends AppCompatActivity {
    Button bind,method,unbind;
    Myservice.MyBind binder;
    Myconn myconn;
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind=(Button)findViewById(R.id.open);
        method=(Button)findViewById(R.id.method);
        unbind=(Button)findViewById(R.id.unbind);

       
        bind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myconn==null){
                    myconn=new Myconn();
                }
                Intent intent=new Intent(MainActivity.this,Myservice.class);
                bindService(intent,myconn,BIND_AUTO_CREATE);
                Toast.makeText(MainActivity.this,"绑定并开启服务",Toast.LENGTH_SHORT).show();
                Log.i(TAG, "onClick: 绑定并开启服务");
            }
        });
        method.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binder.callMethod();
            }
        });
        unbind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myconn!=null){
                    unbindService(myconn);
                    Toast.makeText(MainActivity.this,"解除绑定服务",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    class Myconn implements ServiceConnection{

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            binder=(Myservice.MyBind)iBinder;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    }
}
