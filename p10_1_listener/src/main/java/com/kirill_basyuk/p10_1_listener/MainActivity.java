package com.kirill_basyuk.p10_1_listener;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    TextView tvOut;
    Button btnOk;
    Button btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvOut = findViewById(R.id.tvOut);
        btnOk = findViewById(R.id.btnOk);
        btnCancel = findViewById(R.id.btnCancel);

        View.OnClickListener oclBtn = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               switch (view.getId()){
                   //Кнопка ОК
                   case R.id.btnOk:
                       tvOut.setText("Нажата кнопка ОК");
                       btnOk.setEnabled(false);
                       btnCancel.setEnabled(true);
                       break;
                   //Кнопка Cancel
                   case R.id.btnCancel:
                       tvOut.setText("Нажата кнопка Cancel");
                       btnCancel.setEnabled(false);
                       btnOk.setEnabled(true);
                       break;
               }
            }
        };
        btnOk.setOnClickListener(oclBtn);
        btnCancel.setOnClickListener(oclBtn);
    }
}
