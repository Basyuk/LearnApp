package com.basyuk.development.l88_asynctaskresult;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

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

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnStart:
                myTask = new MyTask();
                myTask.execute();
                break;

            case R.id.btnGet:
                showResult();
                break;

                default:
                    break;
        }
    }

    private void showResult() {
        if (myTask == null) return;
        int result = -1;

        try {
            Log.d(TAG, "Try to get result");
            result = myTask.get(1, TimeUnit.SECONDS);
            Log.d(TAG, "get returns " + result);
            Toast.makeText(this, "get returns + " + result, Toast.LENGTH_LONG).show();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e){
            e.printStackTrace();
        } catch (TimeoutException e) {
            Log.d(TAG, "get timeout, result = " + result + " " + e.getMessage());
            e.printStackTrace();
        }
    }

    class MyTask extends AsyncTask<Void, Void, Integer> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            textViewInfo.setText("Begin");
            Log.d(TAG, "Begin");
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            try{
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            return 100500;
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            textViewInfo.setText("End. Result = " + integer);
            Log.d(TAG, "End. Result = " + integer);
        }
    }
}
