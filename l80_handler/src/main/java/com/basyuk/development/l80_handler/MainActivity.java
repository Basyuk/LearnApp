package com.basyuk.development.l80_handler;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.TimeUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    final String TAG = "myLogs";

    Handler handler;
    TextView textViewInfo;
    Button buttonStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewInfo = findViewById(R.id.tvInfo);
        handler = new Handler(){
            public void handlerMessage(Message message){
                //обновляем TextView
                textViewInfo.setText("Закачано файлов: " + message.what);
                if (message.what == 10) buttonStart.setEnabled(true);
            }
        };
    }

    public void onClick(View view){
        switch(view.getId()){
            case R.id.btnStart:

               // buttonStart.setEnabled(false);
                Thread thread = new Thread(new Runnable() {

                    @Override
                    public void run() {
                        for (int i = 1; i <= 10; i++){
                            //долгий процесс
                            downloadFile();
                            //обновляем TextView
                            handler.sendEmptyMessage(i);
                            //пишем лог
                            Log.d(TAG, "i = " + i);
                        }
                    }
                });
                thread.start();
                break;

            case R.id.btnTest:
                Log.d(TAG, "test");
                break;

                default:
                    break;
        }
    }

    void downloadFile(){
        try{
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
