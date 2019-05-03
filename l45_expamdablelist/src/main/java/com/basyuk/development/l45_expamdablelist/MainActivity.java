package com.basyuk.development.l45_expamdablelist;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    //название компаний (групп)
    String[] groups = new String[]{ "HTC", "Samsung", "Xiaomi"};

    //название моделей (элементов)
    String[] phonesHTC = new String[]{ "Sensation", "Desire", "Wildfire", "Hero" };
    String[] phonesSamsung = new String[]{ "Galaxy S9", "Galaxy Fold", "Wave" };
    String[] phonesXiaomi = new String[]{ "MI A1", "MI 6", "Mi Flex", "MI 8"};

    //коллекция для групп
    ArrayList<Map<String, String>> groupData;

    //коллекция для элементов одной группы
    ArrayList<Map<String, String>> childDataItem;

    //общая коллекция для коллекций элементов
    ArrayList<ArrayList<Map<String, String>>> childData;
    //в итоге получится childData = ArrayList<childData>

    //список атрибутов группы или элемента
    Map<String, String> map;

    ExpandableListView expandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //заполняем коллекцию групп из массива с названиями групп
        groupData = new ArrayList<Map<String, String>>();
        for (String group : groups){
            // заполняем список атрибутов для каждой группы
            map = new HashMap<String, String>();
            map.put("groupName", group);
            groupData.add(map);
        }

        //список атрибутов групп для чтения
        String groupFrom[] = new String[]{"groupName"};
        // список ID view-элементов, в которые будут помещены атрибуты групп
        int groupTo[] = new int[]{android.R.id.text1};

        //создаем коллекцию для коллекций элементов
        childData = new ArrayList<ArrayList<Map<String, String>>>();

        //создаем коллекцию элементов для первой группы
        childDataItem = new ArrayList<Map<String, String>>();
        //заполняем список атрибутов для каждого элемента
        for (String phone : phonesHTC){
            map = new HashMap<String, String>();
            map.put("phoneName", phone); //название телефона
            childDataItem.add(map);
        }
        //добавляем коллекцию коллекций
        childData.add(childDataItem);

        //создаем коллекцию элементов для второй группы
        childDataItem = new ArrayList<Map<String, String>>();
        for (String phone : phonesSamsung){
            map = new HashMap<String, String>();
            map.put("phoneName", phone);
            childDataItem.add(map);
        }

        childData.add(childDataItem);

        //создаем коллекцию элементов для третьей группы
        childDataItem = new ArrayList<Map<String, String>>();
        for (String phone : phonesXiaomi){
            map = new HashMap<String, String>();
            map.put("phoneName", phone);
            childDataItem.add(map);
        }
        childData.add(childDataItem);

        // Список атрибутов элементов для чтения
        String childFrom[] = new String[]{"phoneName"};
        // список ID view-элементов, в которые будут помещены атрибуты элементов
        int childTo[] = new int[]{android.R.id.text1};

        SimpleExpandableListAdapter adapter = new SimpleExpandableListAdapter(this, groupData, android.R.layout.simple_expandable_list_item_1, groupFrom, groupTo, childData, android.R.layout.simple_list_item_1, childFrom, childTo);

        expandableListView = findViewById(R.id.elvMain);
        expandableListView.setAdapter(adapter);
    }
}
