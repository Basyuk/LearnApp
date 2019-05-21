package com.basyuk.development.l84_handlerrunnable;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBarCount;
    TextView textViewInfo;
    CheckBox checkBoxInfo;
    int count;

    final String TAG = "myLogs";
    final int MAX = 100;

    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler();

        progressBarCount = findViewById(R.id.pbCount);
        progressBarCount.setMax(MAX);
        progressBarCount.setProgress(0);

        textViewInfo = findViewById(R.id.tvInfo);

        checkBoxInfo = findViewById(R.id.chbInfo);
        checkBoxInfo.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    textViewInfo.setVisibility(View.VISIBLE);
                    //показываем информацию
                    handler.post(showInfo);
                } else {
                    textViewInfo.setVisibility(View.GONE);
                    //отменяем показ информации
                    handler.removeCallbacks(showInfo);
            }
            }
        });

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    for(count = 1; count < MAX; count++) {
                        TimeUnit.MILLISECONDS.sleep(100);
                        //обновляем ProgressBar
                        handler.post(updateProgress);
                    }
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }

    //обновление ProgressBar
    Runnable updateProgress = new Runnable() {
        @Override
        public void run() {
            progressBarCount.setProgress(count);
        }
    };

    //показ информации
    Runnable showInfo = new Runnable() {
        @Override
        public void run() {
            Log.d(TAG, "showInfo");
            textViewInfo.setText("Count = " + count);
            //планирует сам себя через 1000 милисекунд
            handler.postDelayed(showInfo, 1000);
        }
    };
}
