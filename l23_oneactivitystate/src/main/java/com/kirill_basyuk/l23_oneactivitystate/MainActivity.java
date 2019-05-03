package com.kirill_basyuk.l23_oneactivitystate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    final String TAG = "States";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "MainActivity: onCreate()");
    }

    protected void onStart(){
        super.onStart();
        Log.d(TAG, "MainActivity: onStart()");
    }

    protected void onResume(){
        super.onResume();
        Log.d(TAG, "MainActivity: onResume");
    }

    protected void onPause(){
        super.onPause();
        Log.d(TAG, "MainActivity: onPause");
    }

    protected void onStop(){
        super.onStop();
        Log.d(TAG, "MainActivity: onStop");
    }

    protected void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "MainActivity: onDestroy");
    }
}
