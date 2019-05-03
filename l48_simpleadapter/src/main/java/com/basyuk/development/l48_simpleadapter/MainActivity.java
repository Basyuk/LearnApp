package com.basyuk.development.l48_simpleadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //имена атрибутов для Map
    final String ATTRIBUTE_NAME_TEXT = "text";
    final String ATTRIBUTE_NAME_CHECKED = "checked";
    final String ATTRIBUTE_NAME_IMAGE = "image";

    ListView listViewSimple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //массивы данных
        String[] texts = { "sometext_1", "sometext_2", "sometext_3", "sometext_4", "sometext_5" };
        boolean[] checked = { true, false, false, true, false };
        int image = R.mipmap.ic_launcher;

        //упаковываем данные в понятную для адаптера структуру
        ArrayList<Map<String, Object>> data = new ArrayList<Map<String, Object>>(texts.length);
        Map<String, Object> map;
        for (int i = 0; i < texts.length; i++){
            map = new HashMap<String, Object>();
            map.put(ATTRIBUTE_NAME_TEXT, texts[i]);
            map.put(ATTRIBUTE_NAME_CHECKED, checked[i]);
            map.put(ATTRIBUTE_NAME_IMAGE, image);
            data.add(map);
        }

        //массив имен атрибутов, из которых будут читаться данные
        String[] from = { ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_CHECKED, ATTRIBUTE_NAME_IMAGE, ATTRIBUTE_NAME_TEXT };
        //массив ID View-компонентов, в которые будут выставлять данные
        int[] to = { R.id.tvText, R.id.cbChecked, R.id.ivImg, R.id.cbChecked };

        //Создаем адаптер
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, data, R.layout.item, from, to);

        //определяем список и присваиваем ему адаптер
        listViewSimple = findViewById(R.id.lvSimple);
        listViewSimple.setAdapter(simpleAdapter);

    }
}
