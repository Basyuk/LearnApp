package com.basyuk.development.l49_simpleadaptercustom1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //имена атрибутов для Map
    final String ATTRIBUTE_NAME_TEXT = "text";
    final String ATTRIBUTE_NAME_VALUE = "value";
    final String ATTRIBUTE_NAME_IMAGE = "image";

    //картинки для отображения динамики
    final int positive = android.R.drawable.arrow_up_float;
    final int negative = android.R.drawable.arrow_down_float;

    ListView listViewSimple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //массив данных
        int[] values = { 8, 4, -3, 2, -5, 0, 3, -6, 1, -1};

        //упаковываем данные в понятную для адаптера структуру
        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(values.length);
        Map<String, Object> map;
        int img = 0;
        for (int i = 0; i < values.length; i++){
            map = new HashMap<String, Object>();
            map.put(ATTRIBUTE_NAME_TEXT, "Day " + (i + 1));
            map.put(ATTRIBUTE_NAME_VALUE, values[i]);
            if (values[i] == 0) img = 0;
            else img = (values[i] > 0) ? positive : negative;
            map.put(ATTRIBUTE_NAME_IMAGE, img);
            data.add(map);
        }

        //массив имен атрибутов, из которых будут читаться данные
        String[] from = {ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_VALUE, ATTRIBUTE_NAME_IMAGE};
        //массив ID View-компонентов, к которые будут вставлятся данные
        int[] to = { R.id.tvText, R.id.tvValue, R.id.ivImg };

        //Создаем адаптер
        MySimpleAdapter simpleAdapter = new MySimpleAdapter(this, data, R.layout.item, from, to);

        //определяем список и присваиваем ему адаптер
        listViewSimple = findViewById(R.id.lvSimple);
        listViewSimple.setAdapter(simpleAdapter);


    }
}
