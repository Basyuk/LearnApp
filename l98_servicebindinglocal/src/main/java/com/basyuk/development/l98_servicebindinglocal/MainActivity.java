package com.basyuk.development.l98_servicebindinglocal;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final String TAG = "myLogs";

    boolean bound = false;
    ServiceConnection serviceConnection;
    Intent intent;
    MyService myService;
    TextView textViewInterval;
    long interval;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewInterval = findViewById(R.id.tvInterval);
        intent = new Intent(this, MyService.class);
        serviceConnection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                Log.d(TAG, "MainActivity onServiceConnected");
                myService = ((MyService.MyBinder) iBinder).getService();
                bound = true;
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                Log.d(TAG, "MainActivity onServiceDisconnected");
                bound = false;
            }
        };
    }

    @Override
    protected void onStart() {
        super.onStart();
        bindService(intent, serviceConnection, 0);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (!bound) return;
        unbindService(serviceConnection);
        bound = false;
    }

    public void onClickStart(View view){
        startService(intent);
    }

    public void onClickUp(View view) {
        if (!bound) return;
        interval = myService.upInterval(500);
        textViewInterval.setText("Interval = " + interval);
    }

    public void onClickDown(View view) {
        if (!bound) return;
        interval = myService.downInterval(500);
        textViewInterval.setText("Interval = " + interval);
    }
}
