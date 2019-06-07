package com.basyuk.development.l105_fragment_dynamic;

import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

public class MainActivity extends AppCompatActivity {

    final String TAG = "myLogs";
    Fragment1 fragment1;
    Fragment2 fragment2;
    FragmentTransaction fragmentTransaction;
    CheckBox checkBoxStack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fragment1 = new Fragment1();
        fragment2 = new Fragment2();

        checkBoxStack = findViewById(R.id.chbStack);

    }

    public void onClick(View view) {
        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        switch (view.getId()) {
            case R.id.btnAdd:
                fragmentTransaction.add(R.id.frgmCont, fragment1);
                break;

            case R.id.btnRemove:
                fragmentTransaction.remove(fragment1);
                break;

            case R.id.btnReplace:
                fragmentTransaction.replace(R.id.frgmCont, fragment2);
                break;

                default:
                    break;
        }

        if (checkBoxStack.isChecked()) fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}
