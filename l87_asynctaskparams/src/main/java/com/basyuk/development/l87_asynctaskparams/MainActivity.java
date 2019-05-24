package com.basyuk.development.l87_asynctaskparams;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    TextView textViewInfo;
    MyTask myTask;
    String[] data = {"file_path_1", "file_path_2", "file_path_3", "file_path_4", "file_path_5", "file_path_6", "file_path_7", "file_path_8" };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewInfo = findViewById(R.id.tvInfo);
    }

    public void onClick(View view){
        myTask = new MyTask();
        myTask.execute(data);
    }

    class MyTask extends AsyncTask<String, Integer, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            textViewInfo.setText("Begin");
        }

        @Override
        protected Void doInBackground(String... strings) {
            try {
                int count = 0;
                for (String url : strings) {
                    //загружаем файлы
                    downloadFile(url);
                    //выводим промежуточные результаты
                    publishProgress(++count);
                }
                //разъединяемся
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            textViewInfo.setText("End");
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            textViewInfo.setText("Downloaded " + values[0] + "files");
        }
    }

    private void downloadFile(String url) throws InterruptedException {
        TimeUnit.SECONDS.sleep(5);
    }
}
