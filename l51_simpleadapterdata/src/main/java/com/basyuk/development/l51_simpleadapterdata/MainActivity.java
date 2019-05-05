package com.basyuk.development.l51_simpleadapterdata;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private static final int CM_DELETE_ID = 1;

    //имена атрибутов для Map
    final String ATTRIBUTE_NAME_TEXT = "text";
    final String ATTRIBUTE_NAME_IMAGE = "image";

    ListView listViewSimple;
    SimpleAdapter simpleAdapter;
    ArrayList<Map<String, Object>> data;
    Map<String, Object> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //упаковываем данные в понятную для адаптера структуру
        data = new ArrayList<>();
        for (int i = 1; i < 5; i++){
            map = new HashMap<>();
            map.put(ATTRIBUTE_NAME_TEXT, "Sometext " + i);
            map.put(ATTRIBUTE_NAME_IMAGE, R.mipmap.ic_launcher);
            data.add(map);
        }

        //массив имен атрибутов, из которых будут читаться данные
        String[] from = { ATTRIBUTE_NAME_TEXT, ATTRIBUTE_NAME_TEXT };
        int[] to = { R.id.tvText, R.id.ivImg };

        //создаем адаптер
        simpleAdapter = new SimpleAdapter(this, data, R.layout.item, from, to);

        //определяем список и присваиваем ему адаптер
        listViewSimple = findViewById(R.id.lvSimple);
        listViewSimple.setAdapter(simpleAdapter);
        registerForContextMenu(listViewSimple);
    }

    public void onButtonClick(View view){
        //создаем новый Map
        map = new HashMap<>();
        map.put(ATTRIBUTE_NAME_TEXT, "Sometext " + (data.size() + 1));
        map.put(ATTRIBUTE_NAME_IMAGE, R.mipmap.ic_launcher);
        //добавляем его в коллекцию
        data.add(map);
        //уведомляем, что данные изменились
        simpleAdapter.notifyDataSetChanged();
    }

    public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo){
        super.onCreateContextMenu(contextMenu, view, contextMenuInfo);
        contextMenu.add(0, CM_DELETE_ID, 0, "Удалить запись");
    }

    public boolean onContextItemSelected(MenuItem menuItem){
        if (menuItem.getItemId() == CM_DELETE_ID) {
            //получаем инфу о пункте списка
            AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo) menuItem.getMenuInfo();
            data.remove(adapterContextMenuInfo.position);
            simpleAdapter.notifyDataSetChanged();
            return true;
        }
        return super.onContextItemSelected(menuItem);
    }
}
