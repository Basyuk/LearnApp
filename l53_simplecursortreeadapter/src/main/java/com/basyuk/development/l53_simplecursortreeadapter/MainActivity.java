package com.basyuk.development.l53_simplecursortreeadapter;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ExpandableListView;

public class MainActivity extends AppCompatActivity {

    ExpandableListView expandableListView;
    DB db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //подключаемся к БД
        db = new DB(this);
        db.open();

        //готовим данные по групам для адаптера
        Cursor cursor = db.getCompanyData();
        startManagingCursor();
    }

    private void startManagingCursor() {
    }
}
