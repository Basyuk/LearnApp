package com.basyuk.development.l94_servicekillserver;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.TimeUnit;

public class MyService extends Service {

    final String TAG = "myLogs";

    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "MyService onCreate");
    }

    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "MyService onDestroy");
    }

    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "MyService onStartCommand");
        readFlags(flags);
        MyRun mr = new MyRun(startId);
        new Thread(mr).start();
        return START_NOT_STICKY;
    }

    public IBinder onBind(Intent arg0) {
        return null;
    }

    void readFlags(int flags) {
        if ((flags&START_FLAG_REDELIVERY) == START_FLAG_REDELIVERY)
            Log.d(TAG, "START_FLAG_REDELIVERY");
        if ((flags&START_FLAG_RETRY) == START_FLAG_RETRY)
            Log.d(TAG, "START_FLAG_RETRY");
    }

    class MyRun implements Runnable {

        int startId;

        public MyRun(int startId) {
            this.startId = startId;
            Log.d(TAG, "MyRun#" + startId + " create");
        }

        public void run() {
            Log.d(TAG, "MyRun#" + startId + " start");
            try {
                TimeUnit.SECONDS.sleep(15);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            stop();
        }

        void stop() {
            Log.d(TAG, "MyRun#" + startId + " end, stopSelfResult("
                    + startId + ") = " + stopSelfResult(startId));
        }
    }
}
