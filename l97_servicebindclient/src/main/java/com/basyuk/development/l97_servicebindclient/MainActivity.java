package com.basyuk.development.l97_servicebindclient;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    final String TAG = "myLogs";

    boolean bound = false;

    ServiceConnection serviceConnection;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent = new Intent("com.basyuk.development.l97_servicebindserver.MyService");

        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.d(TAG, "MainActivity onServiceConnected");
                bound = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                Log.d(TAG, "MainActivity onServiceDisconnected");
                bound = false;
            }
        };
    }

    public void onClickStart(View view){
        startService(intent);
    }

    public void onClickStop(View view){
        stopService(intent);
    }

    public void onClickBind(View view){
        bindService(intent, serviceConnection, BIND_AUTO_CREATE);
    }

    public void onClickUnBind(View view) {
        if (!bound) return;
        unbindService(serviceConnection);
        bound = false;
    }

    protected void onDestroy() {
        super.onDestroy();
        onClickBind(null);
    }
}
