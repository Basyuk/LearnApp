package com.basyuk.development.l49_simpleadaptercustom1;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;


import java.util.List;
import java.util.Map;

public class MySimpleAdapter extends SimpleAdapter {

    //картинки для отображения динамики
    final int positive = android.R.drawable.arrow_up_float;
    final int negative = android.R.drawable.arrow_down_float;

    public MySimpleAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
        super(context, data, resource, from, to);
    }

    @Override
    public void setViewText(TextView v, String text) {
        //метод супер-класса, который вставляет текст
        super.setViewText(v, text);
        // если нужный нам TextView, то разрисовываем
        if (v.getId() == R.id.tvValue) {
            int i = Integer.parseInt(text);
            if (i < 0) v.setTextColor(Color.RED);
            else if (i > 0) v.setTextColor(Color.GREEN);
        }
    }

    @Override
    public void setViewImage(ImageView v, int value){
        //метод супер-класса
        super.setViewImage(v, value);
        //разрисовываем ImageView
        if (value == negative) v.setBackgroundColor(Color.RED);
        else if (value == positive) v.setBackgroundColor(Color.GREEN);
    }
}
