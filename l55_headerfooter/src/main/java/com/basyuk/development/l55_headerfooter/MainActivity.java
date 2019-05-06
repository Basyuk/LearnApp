package com.basyuk.development.l55_headerfooter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.HeaderViewListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final String TAG = "myLogs";

    String[] data = {"one", "two", "three", "four", "five"};
    ListView listView;
    ArrayAdapter<String> adapter;

    View header1;
    View header2;

    View footer1;
    View footer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.lvMain);
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);

        //создаем Header и Footer
        header1 = createHeader("Header 1");
        header2 = createHeader("Header 2");
        footer1 = createFooter("Footer 1");
        footer2 = createFooter("Footer 2");

        fillList();

    }

    //формирование списка
    void fillList(){
        try{
            listView.addHeaderView(header1);
            listView.addHeaderView(header2, "Some text for header 2", false);
            listView.addFooterView(footer1);
            listView.addFooterView(footer2, "some text for footer 2", false);
            listView.setAdapter(adapter);
        } catch (Exception ex){
            Log.e(TAG, ex.getMessage());
        }
    }

    //нажатие кнопки
    public void onClick(View view){

        Object object;

        HeaderViewListAdapter headerViewListAdapter = (HeaderViewListAdapter) listView.getAdapter();
        object = headerViewListAdapter.getItem(1);
        Log.d(TAG, "headerViewListAdapter.getItem(1) = " + object.toString());
        object = headerViewListAdapter.getItem(4);
        Log.d(TAG, "headerViewListAdapter.getItem(4) = " + object.toString());

        ArrayAdapter<String> arrayAdapter = (ArrayAdapter<String>) headerViewListAdapter.getWrappedAdapter();
        object = arrayAdapter.getItem(1);
        Log.d(TAG, "arrayAdapter.getItem(1) = " + object.toString());
        object = arrayAdapter.getItem(4);
        Log.d(TAG, "arrayAdapter.getItem(4) = " + object.toString());
    }

    //создание Header
    View createHeader(String text) {
        View view = getLayoutInflater().inflate(R.layout.header, null);
        ((TextView)view.findViewById(R.id.tvText)).setText(text);
        return view;
    }

    View createFooter(String text){
        View view = getLayoutInflater().inflate(R.layout.footer, null);
        ((TextView)view.findViewById(R.id.tvText)).setText(text);
        return view;
    }
}
