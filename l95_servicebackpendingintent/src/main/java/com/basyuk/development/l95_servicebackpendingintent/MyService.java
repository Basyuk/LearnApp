package com.basyuk.development.l95_servicebackpendingintent;

import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MyService extends Service {

    final String TAG = "myLogs";

    ExecutorService executorService;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "MyService onCreate");
        executorService = Executors.newFixedThreadPool(2);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "MyService onDestroy");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "MyService onStartCommand");

        int time = intent.getIntExtra(MainActivity.PARAM_TIME, 1);
        PendingIntent pendingIntent = intent.getParcelableExtra(MainActivity.PARAM_PINTENT);

        MyRun myRun = new MyRun(time, startId, pendingIntent);
        executorService.execute(myRun);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    class MyRun implements Runnable {

        int time;
        int startId;
        PendingIntent pendingIntent;

        public MyRun(int time, int startId, PendingIntent pendingIntent) {
            this.time = time;
            this.startId = startId;
            this.pendingIntent = pendingIntent;
            Log.d(TAG, "MyRun#" + startId + " create");
        }

        @Override
        public void run() {
            Log.d(TAG, "MyRun#" + startId + " start, time = " + time);
            try {
                //сообщаем о старте задачи
                pendingIntent.send(MainActivity.STATUS_START);

                //начинаем выполнение задачи
                TimeUnit.SECONDS.sleep(time);

                //сообщаем об окончании задачи
                Intent intent = new Intent().putExtra(MainActivity.PARAM_RESULT, time * 100);
                pendingIntent.send(MyService.this, MainActivity.STATUS_FINISH, intent);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (PendingIntent.CanceledException e){
                e.printStackTrace();
            }
            stop();
        }

        void stop() {
            Log.d(TAG, "MyRun#" + startId + " end, stopSelfResult(" + startId + ") = " + stopSelfResult(startId));
        }
    }
}
