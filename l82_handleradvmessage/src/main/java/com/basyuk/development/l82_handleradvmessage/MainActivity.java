package com.basyuk.development.l82_handleradvmessage;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    final String TAG = "myLogs";

    final int STATUS_NONE = 0; // нет подключения
    final int STATUS_CONNECTING = 1; // подключаемся
    final int STATUS_CONNECTED = 2; // подключено
    final int STATUS_DOWNLOAD_START = 3; // загрузка началась
    final int STATUS_DOWNLOAD_FILE = 4; // файл загружен
    final int STATUS_DOWNLOAD_END = 5; // загрузка закончена
    final int STATUS_DOWNLOAD_NONE = 6; // нет файлов для загрузки

    Handler handler;

    TextView textViewStatus;
    ProgressBar progressBarDownload;
    Button buttonConnect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewStatus = findViewById(R.id.tvStatus);
        buttonConnect = findViewById(R.id.btnConnect);
        progressBarDownload = findViewById(R.id.pbDownload);

        handler = new Handler(){
            public void handleMessage(Message message){
                switch (message.what){
                    case STATUS_NONE:
                        buttonConnect.setEnabled(true);
                        textViewStatus.setText("Not connected");
                        progressBarDownload.setVisibility(View.GONE);
                        break;

                    case STATUS_CONNECTING:
                        buttonConnect.setEnabled(false);
                        textViewStatus.setText("Connecting");
                        break;

                    case STATUS_CONNECTED:
                        textViewStatus.setText("Connected");
                        break;

                    case STATUS_DOWNLOAD_START:
                        textViewStatus.setText("Start download " + message.arg1 + "files");
                        progressBarDownload.setMax(message.arg1);
                        progressBarDownload.setProgress(0);
                        progressBarDownload.setVisibility(View.VISIBLE);
                        break;

                    case STATUS_DOWNLOAD_FILE:
                        textViewStatus.setText("Downloading. Left " + message.arg2 + " files");
                        progressBarDownload.setProgress(message.arg1);
                        saveFile((byte[]) message.obj);
                        break;

                    case STATUS_DOWNLOAD_END:
                        textViewStatus.setText("Download complete!");
                        break;

                    case STATUS_DOWNLOAD_NONE:
                        textViewStatus.setText("No files for download");
                        break;
                }
            }
        };
        handler.sendEmptyMessage(STATUS_NONE);
    }

    public void onClick(View view){

        Log.d(TAG, "onClick: ");
        Thread thread = new Thread(new Runnable() {
            Message message;
            byte[] file;
            Random random = new Random();

            @Override
            public void run() {
                try{
                    Log.d(TAG, "run()");
                    //устанавливаем подключение
                    handler.sendEmptyMessage(STATUS_CONNECTING);
                    TimeUnit.SECONDS.sleep(1);

                    //подключение установлено
                    handler.sendEmptyMessage(STATUS_CONNECTED);

                    //определяем кол-во файлов
                    TimeUnit.SECONDS.sleep(1);
                    int filesCount = random.nextInt(5);

                    if (filesCount == 0){
                        //сообщаем, что файлов для загрузки нет
                        handler.sendEmptyMessage(STATUS_DOWNLOAD_NONE);
                        //и отключаемся
                        TimeUnit.MILLISECONDS.sleep(1500);
                        handler.sendEmptyMessage(STATUS_NONE);
                        return;
                    }

                    //загрузка начинается
                    //создаем сообщение с информацией о кол-ве файлов

                    message = handler.obtainMessage(STATUS_DOWNLOAD_START, filesCount, 0);

                    //отправляем
                    handler.sendMessage(message);

                    for (int i = 1; i <= filesCount; i++){
                        //загружается файл
                        file = downloadFile();
                        //создаем сообщение с информацией о порядковом номере файла, кол-вом оставшихся и самим файлом
                        message = handler.obtainMessage(STATUS_DOWNLOAD_FILE, i, filesCount - 1, file);
                        //отправляем
                        handler.sendMessage(message);
                    }


                    //загрузка завершена
                    handler.sendEmptyMessage(STATUS_DOWNLOAD_END);

                    //отключаемся
                    TimeUnit.MILLISECONDS.sleep(1500);
                    handler.sendEmptyMessage(STATUS_DOWNLOAD_NONE);
                } catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        });
        thread.start();
    }


    byte[] downloadFile() throws InterruptedException{
        TimeUnit.SECONDS.sleep(2);
        return new byte[1024];
    }

    void saveFile(byte[] file){

    }
}
