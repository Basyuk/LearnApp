package com.basyuk.development.l98_servicebindinglocal;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.util.Timer;
import java.util.TimerTask;

public class MyService extends Service {

    final String TAG = "myLogs";

    MyBinder binder = new MyBinder();

    Timer timer;
    TimerTask timerTask;
    long interval = 1000;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "MyService onCreate");
        timer = new Timer();
        schedule();
    }

    void schedule() {
        if (timerTask != null) timerTask.cancel();
        if (interval > 0) {
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    Log.d(TAG, "run");
                }
            };
            timer.schedule(timerTask, 1000, interval);
        }
    }

    long upInterval(long gap) {
        interval = interval + gap;
        if (interval < 0) interval = 0;
        schedule();
        return interval;
    }

    long downInterval(long gap) {
        interval = interval - gap;
        if (interval < 0) interval = 0;
        schedule();
        return interval;
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "MyService onBind");
        return binder;
    }

    class MyBinder extends Binder {
        MyService getService(){
            return MyService.this;
        }
    }
}
