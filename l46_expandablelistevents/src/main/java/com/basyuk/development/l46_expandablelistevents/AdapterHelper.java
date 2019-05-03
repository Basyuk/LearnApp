package com.basyuk.development.l46_expandablelistevents;

import android.content.Context;
import android.widget.SimpleExpandableListAdapter;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.Map;

public class AdapterHelper {

    final String ATTR_GROUP_NAME = "groupName";
    final String ATTR_PHONE_NAME = "phoneName";

    // названия компаний (групп)
    String[] groups = new String[] {"HTC", "Samsung", "LG"};

    // название телефонов (элементов)
    String[] phonesHTC = new String[] {"Sensation", "Desire", "Wildfire", "Hero"};
    String[] phonesSams = new String[] {"Galaxy S II", "Galaxy Nexus", "Wave"};
    String[] phonesLG = new String[] {"Optimus", "Optimus Link", "Optimus Black", "Optimus One"};

    //коллекция для групп
    ArrayList<Map<String, String>> groupData;

    //коллекция для элементов одной группы
    ArrayList<Map<String, String>> childDataItem;

    //общая коллекция для коллекций элементов
    ArrayList<ArrayList<Map<String, String>>> childData;
    // в итоге получится childData = ArrayList<childDataItem>

    //список атрибутов группы или элемента
    Map<String, String> map;

    Context context;

    AdapterHelper(Context _context) {
        context = _context;
    }

    SimpleExpandableListAdapter adapter;

    SimpleExpandableListAdapter getAdapter(){
        // заполняем коллекцию групп из массива с названиями групп
        groupData = new ArrayList<Map<String, String>>();
        for (String group : groups){
            //заплдгяем список атрибутов для каждой группы
            map = new HashMap<String, String>();
            map.put(ATTR_GROUP_NAME, group);
            groupData.add(map);
        }

        //список атрибутов групп для чтения
        String groupFrom[] = new String[]{ATTR_GROUP_NAME};
        // список ID view-элементов, в которые будут помещены атрибуты групп
        int groupTo[] = new int[]{android.R.id.text1};

        //создаем коллецию для коллекций элементов
        childData = new ArrayList<ArrayList<Map<String, String>>>();

        // создаем коллекцию для элементов первой группы
        childDataItem = new ArrayList<Map<String, String>>();
        //заполняем список атрибутов для каждого элемента
        for (String phone : phonesHTC){
            map = new HashMap<String, String>();
            map.put(ATTR_PHONE_NAME, phone);
            childDataItem.add(map);
        }
        childData.add(childDataItem);

        // создаем коллекцию для элементов второй группы
        childDataItem = new ArrayList<Map<String, String>>();
        //заполняем список атрибутов для каждого элемента
        for (String phone : phonesSams){
            map = new HashMap<String, String>();
            map.put(ATTR_PHONE_NAME, phone);
            childDataItem.add(map);
        }
        childData.add(childDataItem);

        // создаем коллекцию для элементов второй группы
        childDataItem = new ArrayList<Map<String, String>>();
        //заполняем список атрибутов для каждого элемента
        for (String phone : phonesLG){
            map = new HashMap<String, String>();
            map.put(ATTR_PHONE_NAME, phone);
            childDataItem.add(map);
        }
        childData.add(childDataItem);

        //список атрибутов элементов для чтения
        String childFrom[] = new String[]{ATTR_PHONE_NAME};
        // список ID view-элементов, в которые будут помещены атрибуты элементов
        int childTo[] = new int[] {android.R.id.text1};

        adapter = new SimpleExpandableListAdapter(context, groupData, android.R.layout.simple_expandable_list_item_1, groupFrom, groupTo, childData, android.R.layout.simple_list_item_1, childFrom, childTo);
        return adapter;
    }

    String getGroupText(int groupPos){
        return ((Map<String, String>)(adapter.getGroup(groupPos))).get(ATTR_GROUP_NAME);
    }

    String getChildText(int groupPos, int childPos){
        return ((Map<String, String>)(adapter.getChild(groupPos, childPos))).get(ATTR_PHONE_NAME);
    }

    String getGroupChildText(int groupPos, int childPos){
        return getGroupText(groupPos) + " " + getChildText(groupPos, childPos);
    }
}
