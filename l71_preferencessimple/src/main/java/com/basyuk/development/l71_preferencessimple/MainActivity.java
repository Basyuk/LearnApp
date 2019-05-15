package com.basyuk.development.l71_preferencessimple;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textViewInfo;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewInfo = findViewById(R.id.tvInfo);

        //получаем SharedPreferences, которое работает с файлом настроек
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        //полная очистка настроек
        //sharedPreferences.clear().commit();

    }

    @Override
    protected void onResume() {
        Boolean notif = sharedPreferences.getBoolean("notif", false);
        String address = sharedPreferences.getString("address", "");
        String text = "Notification are " + ((notif) ? " enabled, address = " + address : "disabled");
        textViewInfo.setText(text);
        super.onResume();
    }

    public boolean onCreateOptionsMenu(Menu menu){
        MenuItem menuItem = menu.add(0, 1, 0, "Preferences");
        menuItem.setIntent(new Intent(this, PrefActivity.class));
        return super.onCreateOptionsMenu(menu);
    }
}
