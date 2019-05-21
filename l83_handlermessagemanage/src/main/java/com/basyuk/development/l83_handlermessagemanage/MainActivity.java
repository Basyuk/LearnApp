package com.basyuk.development.l83_handlermessagemanage;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    final String TAG = "myLogs";

    Handler handler;

    Handler.Callback handlerCallback = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message message) {
            Log.d(TAG, "what = " + message.what);
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler(handlerCallback);
        sendMessage();
    }

    void sendMessage(){
        Log.d(TAG, "sendMessage");
        handler.sendEmptyMessageDelayed(1, 1000);
        handler.sendEmptyMessageDelayed(2, 2000);
        handler.sendEmptyMessageDelayed(3, 3000);
        handler.sendEmptyMessageDelayed(2, 4000);
        handler.sendEmptyMessageDelayed(5, 5000);
        handler.sendEmptyMessageDelayed(2, 6000);
        handler.sendEmptyMessageDelayed(7, 7000);
        handler.removeMessages(2);
    }
}
