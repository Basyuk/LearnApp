package com.basyuk.development.l76_tab;

import android.support.v4.content.ContextCompat;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TabHost;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabHost tabHost = findViewById(android.R.id.tabhost);
        //инициализация
        tabHost.setup();

        TabHost.TabSpec tabSpec;

        //создаем вкладку и указываем тег
        tabSpec = tabHost.newTabSpec("tag1");
        //название вкладки
        tabSpec.setIndicator("Вкладка 1");
        //указываем if компонента из FrameLayout, он и станет содержимым
        tabSpec.setContent(R.id.tvTab1);
        //добавляем в корневой элемент
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag2");
        //указываем название и картинку
        //в нашем случае вместо картнки идет xml-файл
        //который определяет картинку по сотоянию вкладки
        tabSpec.setIndicator("Вкладка 2", ContextCompat.getDrawable(this, android.R.drawable.star_on));
        tabSpec.setContent(R.id.tvTab2);
        tabHost.addTab(tabSpec);

        tabSpec = tabHost.newTabSpec("tag3");
        //создаем View из layout-файла
        View view = getLayoutInflater().inflate(R.layout.tab_header, null);
        //и устанавливаем его, как заголовок
        tabSpec.setIndicator(view);
        tabSpec.setContent(R.id.tvTab3);
        tabHost.addTab(tabSpec);

        //вторая вкладка будет выбрана по умолчанию
        tabHost.setCurrentTabByTag("tag2");

        //обработчик переключения вкладок
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String s) {
                Toast.makeText(getBaseContext(), "tabID = " + s, Toast.LENGTH_LONG).show();
            }
        });
    }
}
