package com.basyuk.development.l69_parcelable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class SecondActivity extends AppCompatActivity {

    final String TAG = "myLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Log.d(TAG, "getParcelableExtra");

        MyObject myObject = getIntent().getParcelableExtra(MyObject.class.getCanonicalName());
        Log.d(TAG, "MyObject: " + myObject.s + ", " + myObject.i);
    }
}
