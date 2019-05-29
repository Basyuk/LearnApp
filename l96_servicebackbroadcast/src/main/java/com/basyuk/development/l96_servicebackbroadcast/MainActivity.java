package com.basyuk.development.l96_servicebackbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final String TAG = "myLogs";

    final int TASK1_CODE = 1;
    final int TASK2_CODE = 2;
    final int TASK3_CODE = 3;

    public final static int STATUS_START = 100;
    public final static int STATUS_FINISH = 200;

    public final static String PARAM_TIME = "time";
    public final static String PARAM_TASK = "task";
    public final static String PARAM_RESULT = "result";
    public final static String PARAM_STATUS = "status";

    public final static String BROADCAST_ACTION = "com.basyuk.development.l96_servicebackbroadcast";

    TextView textViewTask1;
    TextView textViewTask2;
    TextView textViewTask3;
    BroadcastReceiver broadcastReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewTask1 = findViewById(R.id.tvTask1);
        textViewTask1.setText("Task1");
        textViewTask2 = findViewById(R.id.tvTask2);
        textViewTask2.setText("Task2");
        textViewTask3 = findViewById(R.id.tvTask3);
        textViewTask3.setText("Task3");

        //создаем BroadcastReceiver
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int task = intent.getIntExtra(PARAM_TASK, 0);
                int status = intent.getIntExtra(PARAM_STATUS, 0);
                Log.d(TAG, "onReceive: task = " + task + ", status = " + status);

                //ловим сообщения о старте
                if (status == STATUS_START) {
                    switch (task) {
                        case TASK1_CODE:
                            textViewTask1.setText("Task1 start");
                            break;

                        case TASK2_CODE:
                            textViewTask2.setText("Task2 start");
                            break;

                        case TASK3_CODE:
                            textViewTask3.setText("Task3 start");
                            break;
                    }
                }

                //ловим сообщения об окончании задач
                if (status == STATUS_FINISH) {
                    int result = intent.getIntExtra(PARAM_RESULT, 0);
                    switch (task) {
                        case TASK1_CODE:
                            textViewTask1.setText("Task1 finish, result = " + result);
                            break;

                        case TASK2_CODE:
                            textViewTask2.setText("Task finish, result = " + result);
                            break;

                        case TASK3_CODE:
                            textViewTask3.setText("Task3 finish, result = " + result);
                            break;
                    }
                }
            }
        };
        //создаем фильтр для BroadcastReceiver
        IntentFilter intentFilter = new IntentFilter(BROADCAST_ACTION);
        // регистрируем (включаем) BroadcastReceiver
        registerReceiver(broadcastReceiver, intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //дерегистрируем BroadcastReceiver
        unregisterReceiver(broadcastReceiver);
    }

    public void onClickStart(View view) {
        Intent intent;

        //Создаем Intent для вызова сервиса, кладем туда параметр времени и код задачи
        intent = new Intent(this, MyService.class).putExtra(PARAM_TIME, 7).putExtra(PARAM_TASK, TASK1_CODE);
        //стартуем сервис
        startService(intent);

        intent = new Intent(this, MyService.class).putExtra(PARAM_TIME, 4).putExtra(PARAM_TASK, TASK2_CODE);
        startService(intent);

        intent = new Intent(this, MyService.class).putExtra(PARAM_TIME, 6).putExtra(PARAM_TASK, TASK3_CODE);
        startService(intent);

    }
}
