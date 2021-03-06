package com.basyuk.development.l41_layoutinflaterlist;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    final String TAG = "myLogs";
    String[] name = { "Иван", "Марья", "Петр", "Антон", "Даша", "Борис", "Костя", "Игорь" };
    String[] position = { "Программер", "Бухгалтер", "Программер", "Программе", "Бухгалтер", "Директор", "Программер", "Охранник" };
    int salary[] = { 13000, 10000, 13000, 13000, 10000, 15000, 13000, 8000 };

    int[] colors = new int[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colors[0] = Color.parseColor("#559966CC");
        colors[1] = Color.parseColor("#55336699");

        LinearLayout linearLayout = findViewById(R.id.linLayout);

        LayoutInflater layoutInflater = getLayoutInflater();

        for (int i = 0; i < name.length; i++){
            Log.d(TAG, "i = " + i);
            View item = layoutInflater.inflate(R.layout.item, linearLayout, false);

            TextView tvName = item.findViewById(R.id.tvName);
            tvName.setText(name[i]);

            TextView tvPosition = item.findViewById(R.id.tvPosition);
            tvPosition.setText("Должность: " + position[i]);

            TextView tvSalary = item.findViewById(R.id.tvSalary);
            tvSalary.setText("Оклад: " + salary[i]);

            item.getLayoutParams().width = LinearLayout.LayoutParams.MATCH_PARENT;
            item.setBackgroundColor(colors[i % 2]);

            linearLayout.addView(item);
        }
    }
}
