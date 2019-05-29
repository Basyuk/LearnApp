package com.basyuk.development.l91_asynctaskrotate;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    MyTask myTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("myLogs", "create MainActivity: " + this.hashCode());

        textView = findViewById(R.id.tv);

        myTask = (MyTask) getLastNonConfigurationInstance();
        if (myTask == null){
            myTask = new MyTask();
            myTask.execute();
        }
        Log.d("myLogs", "create MyTask: " + myTask.hashCode());
    }

    class MyTask extends AsyncTask<String, Integer, Void> {

        @Override
        protected Void doInBackground(String... strings) {
            try {
                for (int i = 1; i <= 10; i++){
                    TimeUnit.SECONDS.sleep(1);
                    publishProgress(i);
                    Log.d("myLogs", "i = " + i + ", MyTask: " + this.hashCode() + ", MainActivity: " + MainActivity.this.hashCode());
                }
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            textView.setText("i = " + values[0]);
        }
    }

}
