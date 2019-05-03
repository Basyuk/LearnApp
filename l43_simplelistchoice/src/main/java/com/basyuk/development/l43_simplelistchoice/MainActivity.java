package com.basyuk.development.l43_simplelistchoice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final String TAG = "myLogs";

    ListView listView;
    String names[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.lvMain);
        //устанавливаем режим выбора пунктов списка
        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        //Создаем адаптер, используя массиы из файла ресурсов
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.names, android.R.layout.simple_list_item_multiple_choice);
        listView.setAdapter(adapter);

        Button buttonChecked = findViewById(R.id.btnChecked);
        buttonChecked.setOnClickListener(this);

        //получаем массив из файла ресурсов
        names = getResources().getStringArray(R.array.names);
    }

    @Override
    public void onClick(View view) {
        //в лог пишем выделенный элемент
        Log.d(TAG, "Checked " );

        SparseBooleanArray sparseBooleanArray = listView.getCheckedItemPositions();
        for (int i = 0; i < sparseBooleanArray.size(); i++){
            int key = sparseBooleanArray.keyAt(i);
            if(sparseBooleanArray.get(key))
                Log.d(TAG, names[key]);
        }

    }
}
