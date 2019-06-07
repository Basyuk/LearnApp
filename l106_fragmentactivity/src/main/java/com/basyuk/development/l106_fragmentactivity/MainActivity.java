package com.basyuk.development.l106_fragmentactivity;

import com.basyuk.development.l106_fragmentactivity.Fragment2.onSomeEventListener;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements onSomeEventListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragment2 = new Fragment2();
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.fragment2, fragment2);
        fragmentTransaction.commit();
    }

    public void onClick(View view) {
        Fragment fragment1 = getSupportFragmentManager().findFragmentById(R.id.fragment1);
        ((TextView)fragment1.getView().findViewById(R.id.textView)).setText("Access to Fragment 1 from Activity");

        Fragment fragment2 = getSupportFragmentManager().findFragmentById(R.id.fragment2);
        ((TextView)fragment2.getView().findViewById(R.id.textView)).setText("Access to Fragment 2 from Activity");
    }

    @Override
    public void someEvent(String s) {
        Fragment fragment1 = getSupportFragmentManager().findFragmentById(R.id.fragment1);
        ((TextView)fragment1.getView().findViewById(R.id.textView)).setText("Text from Fragment 2: " + s);
    }
}
