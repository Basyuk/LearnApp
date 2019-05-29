package com.basyuk.development.l95_servicebackpendingintent;

import android.app.PendingIntent;
import android.content.Intent;
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
    public final static String PARAM_PINTENT = "pendingIntent";
    public final static String PARAM_RESULT = "result";

    TextView textViewTask1;
    TextView textViewTask2;
    TextView textViewTask3;

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
    }

    public void onClickStart(View view){

        PendingIntent pendingIntent;
        Intent intent;

        //Создаем PendingIntent для Task1
        pendingIntent = createPendingResult(TASK1_CODE, new Intent(), 0);
        //Создаем Intent для вызова сервиса, кладем туда параментр времени и созданный PendingIntent
        intent = new Intent(this, MyService.class).putExtra(PARAM_TIME, 7).putExtra(PARAM_PINTENT, pendingIntent);
        //стартуем сервис
        startService(intent);

        pendingIntent = createPendingResult(TASK2_CODE, new Intent(), 0);
        intent = new Intent(this, MyService.class).putExtra(PARAM_TIME, 4).putExtra(PARAM_PINTENT, pendingIntent);
        startService(intent);

        pendingIntent = createPendingResult(TASK3_CODE, new Intent(), 0);
        intent = new Intent(this, MyService.class).putExtra(PARAM_TIME, 6).putExtra(PARAM_PINTENT, pendingIntent);
        startService(intent);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "requestCode = " + requestCode + ", resultCode = " + requestCode);

        //ловим сообщения о старте задач
        if (resultCode == STATUS_START){
            switch (requestCode){
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
        if (resultCode == STATUS_FINISH){
            int result = data.getIntExtra(PARAM_RESULT, 0);
            switch (requestCode) {
                case TASK1_CODE:
                    textViewTask1.setText("Task1 finish, result = " + result);
                    break;

                case TASK2_CODE:
                    textViewTask2.setText("Task2 finish, result = " + result);
                    break;

                case TASK3_CODE:
                    textViewTask3.setText("Task3 finish, result = " + result);
                    break;
            }
        }
    }
}
