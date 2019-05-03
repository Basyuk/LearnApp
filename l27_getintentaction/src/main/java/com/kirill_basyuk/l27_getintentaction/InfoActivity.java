package com.kirill_basyuk.l27_getintentaction;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        //Получаем Intent, который вызвал Activity
        Intent intent = getIntent();

        //читаем из него action
        String action = intent.getAction();
        String format = "", textInfo = "";

        //в зависимости от action заполняем переменные
        if (action.equals("com.kirill_basyuk.intent.action.showtime")) {
            format = "HH:mm:ss";
            textInfo = "Time: ";
        }
        else if (action.equals("com.kirill_basyuk.intent.action.showdate")) {
            format = "dd.MM.yyyy";
            textInfo = "Date";
        }

        //в зависимости от содержимого прееменной format получаем дату или время в переменную datatime
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        String datetime = simpleDateFormat.format(new Date(System.currentTimeMillis()));

        TextView tvDate = findViewById(R.id.tvInfo);
        tvDate.setText(textInfo + datetime);

    }
}
