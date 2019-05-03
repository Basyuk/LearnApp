package com.kirill_basyuk.l14_menuadv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    CheckBox chb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.textView);
        chb = findViewById(R.id.chbExtMenu);

    }

    //создание меню
    public boolean onCreateOptionsMenu(Menu menu){
        //добавляем пункты меню
        getMenuInflater().inflate(R.menu.mymenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onPrepareOptionsMenu(Menu menu){
        //пункты меню с ID группы = 1 видны, если в CheckBox стоит галка
        menu.setGroupVisible(R.id.group1, chb.isChecked());
        return super.onPrepareOptionsMenu(menu);
    }

    //обработка нажатий
    public boolean onOptionsItemSelected(MenuItem item){

        StringBuilder sb = new StringBuilder();
        //Выведем в TextView информацию о нажатом пункте меню

        sb.append("Item Menu");
        sb.append("\r\n groupId: " + String.valueOf(item.getGroupId()));
        sb.append("\r\n ItemId: " + String.valueOf(item.getItemId()));
        sb.append("\r\n Order: " + String.valueOf(item.getOrder()));
        sb.append("\r\n Title: " + String.valueOf(item.getTitle()));
        tv.setText(sb.toString());

        return super.onOptionsItemSelected(item);
    }
}
