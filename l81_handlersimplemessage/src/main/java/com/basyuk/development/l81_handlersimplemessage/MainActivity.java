package com.basyuk.development.l81_handlersimplemessage;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    final String TAG = "myLogs";

    final int STATUS_NONE = 0; // нет подключения
    final int STATUS_CONNECTING = 1; //подключаемся
    final int STATUS_CONNECTED = 2; // подключено

    Handler handler;

    TextView textViewStatus;
    ProgressBar progressBarConnect;
    Button buttonConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewStatus = findViewById(R.id.tvStatus);
        progressBarConnect = findViewById(R.id.pbConnect);
        buttonConnect = findViewById(R.id.btnConnect);

        handler = new Handler(){
            public void handleMessage(Message message){
                switch (message.what){
                    case STATUS_NONE:
                        buttonConnect.setEnabled(true);
                        textViewStatus.setText("Not connected");
                        break;

                    case STATUS_CONNECTING:
                        buttonConnect.setEnabled(false);
                        progressBarConnect.setVisibility(View.VISIBLE);
                        textViewStatus.setText("Connecting");
                        break;

                    case STATUS_CONNECTED:
                        progressBarConnect.setVisibility(View.GONE);
                        textViewStatus.setText("Connected");
                        break;
                }
            }
        };
        handler.sendEmptyMessage(STATUS_NONE);
    }

    public void onClick(View view){
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    //устанавливаем подключение
                    handler.sendEmptyMessage(STATUS_CONNECTING);
                    TimeUnit.SECONDS.sleep(2);

                    //установлено
                    handler.sendEmptyMessage(STATUS_CONNECTED);

                    //выполняется какая-то работа
                    TimeUnit.SECONDS.sleep(3);

                    //разрываем подключение
                    handler.sendEmptyMessage(STATUS_NONE);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }
}
