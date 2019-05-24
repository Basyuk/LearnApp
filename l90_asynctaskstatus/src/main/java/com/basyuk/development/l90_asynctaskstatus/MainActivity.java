package com.basyuk.development.l90_asynctaskstatus;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.BreakIterator;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    TextView textViewInfo;
    MyTask myTask;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewInfo = findViewById(R.id.tvInfo);
    }

    public void onClick(View view){
        switch (view.getId()) {
            case R.id.btnStart:
                startTask();
                break;

            case R.id.btnStatus:
                statusTask();
                break;

                default:
                    break;
        }
    }

    private void startTask(){
        myTask = new MyTask();
        myTask.execute();
        myTask.cancel(false);
    }

    private void statusTask(){
        if (myTask != null)
            if (myTask.isCancelled())
                Toast.makeText(this, "CANCELLED", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(this, myTask.getStatus().toString(), Toast.LENGTH_SHORT).show();
    }

    class MyTask extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            textViewInfo.setText("Begin");
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                for (int i = 0; i < 5; i ++){
                    TimeUnit.SECONDS.sleep(1);
                    if (isCancelled()) return null;
                }
            } catch (InterruptedException e){
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
        protected void onCancelled() {
            super.onCancelled();
            textViewInfo.setText("Cancelled");
        }
    }
}
