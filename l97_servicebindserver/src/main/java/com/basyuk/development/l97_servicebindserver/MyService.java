package com.basyuk.development.l97_servicebindserver;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    final String TAG = "myLogs";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "MyService onCreate");
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "MyServce onBind");
        return new Binder();
    }

    @Override
    public void onRebind(Intent intent) {
        super.onRebind(intent);
        Log.d(TAG, "MyService onRebind");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "MyService onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "MyService onDestroy");
    }
}
