package com.basyuk.development.l109_listfragment;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Toast;

public class MainList extends ListFragment {

    String data[] = new String[] { "one", "two", "three", "four" };

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, data);
        setListAdapter(arrayAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment, null);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        Toast.makeText(getActivity(), "position = " + position, Toast.LENGTH_SHORT).show();
        switch (position) {
            case 0:
                getView().setBackgroundColor(Color.BLUE);
                break;

            case 1:
                getView().setBackgroundColor(Color.RED);
                break;

            case 2:
                getView().setBackgroundColor(Color.GRAY);
                break;

            case 3:
                getView().setBackgroundColor(Color.GREEN);
        }
    }
}
