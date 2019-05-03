package com.basyuk.development.l50_simpleadaptercustom2;

import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //имена атрибутов для Map
    final String ATTRIBUTE_NAME_TEXT = "text";
    final String ATTRIBUTE_NAME_PB = "pb";
    final String ATTRIBUTE_NAME_LL = "ll";

    ListView listViewSimple;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //массив данных
        int load[] = { 41, 48, 22, 35, 50, 67, 51, 88 };

        //упаковываем данные в понятную адаптеру структуру
        ArrayList<Map<String, Object>> data = new ArrayList<>(load.length);
        Map<String,Object> map;
        for (int i = 0; i < load.length; i++){
            map = new HashMap<>();
            map.put(ATTRIBUTE_NAME_TEXT, "Day " + (i + 1) + ". Load " + load[i] + "%");
            map.put(ATTRIBUTE_NAME_PB, load[i]);
            map.put(ATTRIBUTE_NAME_LL, load[i]);
            data.add(map);
        }

        //массив имен атрибутов, из которых будут читаться данные
        String[] from = { ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_PB, ATTRIBUTE_NAME_LL};
        //массив ID View-компонентов, в которые будут вставляться данные
        int[] to = { R.id.tvLoad, R.id.pbLoad, R.id.llLoad };

        //создаем адаптер
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, data, R.layout.item, from, to);
        //указываем адаптеру свой биндер
        simpleAdapter.setViewBinder(new MyViewBinder());

        // определяем список и присваиваем ему адаптер
        listViewSimple = findViewById(R.id.lvSimple);
        listViewSimple.setAdapter(simpleAdapter);
    }

    public class MyViewBinder implements SimpleAdapter.ViewBinder {
        int red = getResources().getColor(R.color.Red);
        int orange = getResources().getColor(R.color.Orange);
        int green = getResources().getColor(R.color.Green);

        @Override
        public boolean setViewValue(View view, Object data,
                                    String textRepresentation) {
            int i;
            switch (view.getId()) {
                // LinearLayout
                case R.id.llLoad:
                    i = ((Integer) data).intValue();
                    if (i < 40) view.setBackgroundColor(green); else
                    if (i < 70) view.setBackgroundColor(orange); else
                        view.setBackgroundColor(red);
                    return true;
                // ProgressBar
                case R.id.pbLoad:
                    i = ((Integer) data).intValue();
                    ((ProgressBar)view).setProgress(i);
                    return true;
            }
            return false;
        }
    }
}
