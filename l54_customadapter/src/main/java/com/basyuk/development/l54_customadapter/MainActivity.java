package com.basyuk.development.l54_customadapter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Product> products = new ArrayList<>();
    BoxAdapter boxAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // создаем адаптер
        fillData();
        boxAdapter = new BoxAdapter(this, products);

        // настраиваем список
        ListView listView = findViewById(R.id.lvMain);
        listView.setAdapter(boxAdapter);
    }

    void fillData(){
        for (int i = 1; i < 20; i++){
            products.add(new Product("Product " + i, i*1000, R.mipmap.ic_launcher, false));
        }
    }

    // выводим информацию о корзине
    public void showResult(View view){
        String result = "Товары в корзине:";
        for(Product product : boxAdapter.getBox()){
            if (product.box)
                result += "\n" + product.name;
        }
        Toast.makeText(this, result, Toast.LENGTH_LONG).show();
    }
}
