package com.basyuk.development.l54_customadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class BoxAdapter extends BaseAdapter {

    Context context;
    LayoutInflater layoutInflater;
    ArrayList<Product> products;

    BoxAdapter(Context _context, ArrayList<Product> _products){
        context = _context;
        products = _products;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    //количество элементов
    @Override
    public int getCount() {
        return products.size();
    }

    //элемент по позиции
    @Override
    public Object getItem(int i) {
        return products.get(i);
    }

    //id по позиции
    @Override
    public long getItemId(int i) {
        return i;
    }

    //пункт списка
    @Override
    public View getView(int i, View _view, ViewGroup viewGroup) {
        // используем созданные, но не используемые view
        View view = _view;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.item, viewGroup, false);
        }

        Product product = getProduct(i);

        //заполняем View в пункте списка данными из товаров: наименование, цена, и картинка
        ((TextView)view.findViewById(R.id.tvDescr)).setText(product.name);
        ((TextView) view.findViewById(R.id.tvPrice)).setText(product.price + "");
        ((ImageView)view.findViewById(R.id.ivImage)).setImageResource(product.image);

        CheckBox checkBox = view.findViewById(R.id.cbBox);
        //присваиваем чекбоксу обработчик
        checkBox.setOnCheckedChangeListener(myCheckChangeList);
        //пишем свою позицию
        checkBox.setTag(i);
        //заполняем данными из товаровЖ в корзине или нет
        checkBox.setChecked(product.box);
        return view;
    }

    //содержимое корзиины
    ArrayList<Product> getBox() {
        ArrayList<Product> box = new ArrayList<>();
        for (Product product : products){
            //если в корзине
            if (product.box)
                box.add(product);
        }
        return box;
    }

    // обработчик для чекбоксов
    CompoundButton.OnCheckedChangeListener myCheckChangeList = new CompoundButton.OnCheckedChangeListener() {
        public void onCheckedChanged(CompoundButton buttonView,
                                     boolean isChecked) {
            // меняем данные товара (в корзине или нет)
            getProduct((Integer) buttonView.getTag()).box = isChecked;
        }
    };

    //товар по позиции
    Product getProduct(int position){
        return ((Product) getItem(position));
    }
}
