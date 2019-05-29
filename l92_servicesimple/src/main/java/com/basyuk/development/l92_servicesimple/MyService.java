package com.basyuk.development.l92_servicesimple;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.TimeUnit;

public class MyService extends Service {

    final String TAG = "myLogs";

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand");
        someTask();
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy");
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return null;
    }

    void someTask(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1; i<=5; ++i){
                    Log.d(TAG, "i = " + i);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                }
                stopSelf();
            }
        }).start();
    }
}
