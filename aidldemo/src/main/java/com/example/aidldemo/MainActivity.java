package com.example.aidldemo;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.example.servicedemo1.IMyAidlInterface;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            IMyAidlInterface imai = IMyAidlInterface.Stub.asInterface(iBinder);
            try {
                imai.showProgress();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void operate(View v){
        int id = v.getId();
        if (id == R.id.start) {//远程启动服务
            //Activity ok
//            Intent it = new Intent(this,com.example.servicedemo1.MainActivity2.class);
//            startActivity(it);

            //Activity ok
            // パーラー１、自身、para2 目標
//            Intent it = new Intent();
//            it.setClassName("com.example.aidldemo","com.example.servicedemo1.MainActivity2");
//            startActivity(it);

            Intent it = new Intent();
            it.setAction("com.example.myservice111");
            it.setPackage("com.example.aidldemo");
            startService(it);

                startService(it);

//            Intent intent = new Intent();
//            ComponentName componentName = new ComponentName("com.example.aidldemo", "com.example.servicedemo1.MyService");
//            intent.setComponent(componentName);
//            startService(intent);
//            Log.i(TAG, "operate: ");

        } else if (id == R.id.stop) {

            Intent intent = new Intent();
            ComponentName componentName = new ComponentName("com.example.aidldemo", "com.example.servicedemo1.MyService");
            intent.setComponent(componentName);
            stopService(intent);
        } else if (id == R.id.bind) {

            Intent intent = new Intent();
            intent.setAction("com.example.myservice111");
            intent.setPackage("com.example.aidldemo");
//            ComponentName componentName = new ComponentName("com.example.aidldemo", "com.example.servicedemo1.MyService");
//            intent.setComponent(componentName);
            bindService(intent, conn, BIND_AUTO_CREATE);
        } else if (id == R.id.unbind) {
            unbindService(conn);
        }
    }
}
