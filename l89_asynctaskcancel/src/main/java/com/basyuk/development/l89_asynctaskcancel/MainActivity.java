package com.basyuk.development.l89_asynctaskcancel;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.sql.Time;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    final String TAG = "myLogs";

    MyTask myTask;
    TextView textViewInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewInfo = findViewById(R.id.tvInfo);
    }

    public void onClick(View view){
        switch (view.getId()){
            case R.id.btnStart:
                myTask = new MyTask();
                myTask.execute();
                break;

            case R.id.btnCancel:
                cancelTask();
                break;

                default:
                    break;
        }
    }

    private void cancelTask() {
        if (myTask == null) return;
        Log.d(TAG, "cancel result: " + myTask.cancel(true));
    }

    class MyTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            textViewInfo.setText("Begin");
            Log.d(TAG, "Begin");
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                for (int i = 0; i < 5; i++){
                    TimeUnit.SECONDS.sleep(1);
                    //if (isCancelled() == true) return null;
                    Log.d(TAG, "isCancelled " + isCancelled());
                }
            } catch (InterruptedException e){
                Log.d(TAG, "Interrupted");
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            textViewInfo.setText("End");
            Log.d(TAG, "End");
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            textViewInfo.setText("Canceled");
            Log.d(TAG, "Canceled");
        }
    }
}
