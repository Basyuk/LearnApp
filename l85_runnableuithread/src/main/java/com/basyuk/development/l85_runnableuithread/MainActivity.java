package com.basyuk.development.l85_runnableuithread;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    final String TAG = "myLogs";

    TextView textViewInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewInfo = findViewById(R.id.tvInfo);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    runOnUiThread(runn1);
                    TimeUnit.SECONDS.sleep(1);
                    textViewInfo.postDelayed(runn3, 2000);
                    textViewInfo.post(runn2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    Runnable runn1 = new Runnable() {
        @Override
        public void run() {
            textViewInfo.setText("runn1");
        }
    };

    Runnable runn2 = new Runnable() {
        @Override
        public void run() {
            textViewInfo.setText("runn2");
        }
    };

    Runnable runn3 = new Runnable() {
        @Override
        public void run() {
            textViewInfo.setText("runn3");
        }
    };
}
