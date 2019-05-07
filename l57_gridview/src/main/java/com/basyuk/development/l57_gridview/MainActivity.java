package com.basyuk.development.l57_gridview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;

public class MainActivity extends AppCompatActivity {

    String[] data = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"};

    GridView gridView;
    ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        adapter = new ArrayAdapter<>(this, R.layout.item, R.id.tvText, data);
        gridView = findViewById(R.id.gvMain);
        gridView.setAdapter(adapter);
        adjustGridView();
    }

    private void adjustGridView(){
        gridView.setNumColumns(GridView.AUTO_FIT);
        gridView.setColumnWidth(400);
        gridView.setHorizontalSpacing(5);
        gridView.setVerticalSpacing(5);
        gridView.setStretchMode(GridView.STRETCH_SPACING_UNIFORM);
    }
}
