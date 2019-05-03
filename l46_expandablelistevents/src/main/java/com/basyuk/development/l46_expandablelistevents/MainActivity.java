package com.basyuk.development.l46_expandablelistevents;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final String TAG = "myLogs";

    ExpandableListView expandableListView;
    AdapterHelper adapterHelper;
    SimpleExpandableListAdapter adapter;
    TextView textViewInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewInfo = findViewById(R.id.tvInfo);

        //создаем адаптер
        adapterHelper = new AdapterHelper(this);
        adapter = adapterHelper.getAdapter();

        expandableListView = findViewById(R.id.elvMain);
        expandableListView.setAdapter(adapter);

        // нажатие на элемент

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int i, int i1, long l) {
                Log.d(TAG, "onChildClick: groupPosition = " + i + " childPosition = " + i1 + " id = " + l);
                textViewInfo.setText(adapterHelper.getGroupChildText(i, i1));
                return false;
            }
        });

        // нажатие на группу

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView expandableListView, View view, int i, long l) {
                Log.d(TAG, "onGroupClick: groupPosition = " + i + " id = " + l);

                //блокируем дальнейшую обработку события для группы с позицией 1
                if (i == 1) return true;

                return false;
            }
        });

        //сворачивание группы
        expandableListView.setOnGroupCollapseListener(new ExpandableListView.OnGroupCollapseListener() {
            @Override
            public void onGroupCollapse(int i) {
                Log.d(TAG, "onGroupCollapse: groupPosition = " + i);
                textViewInfo.setText("Свернули " + adapterHelper.getGroupText(i));
            }
        });

        //разворачивание группы
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int i) {
                Log.d(TAG, "onGroupExpand: groupPosition = " + i);
                textViewInfo.setText("Развернули " + adapterHelper.getGroupText(i));
            }
        });

        //разворачиваем группу с позицией 2
        expandableListView.expandGroup(2);
    }
}
