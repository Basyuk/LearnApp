package com.kirill_basyuk.l12_logandmessage;

import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvOut;
    Button btnOk;
    Button btnCancel;

    private static final String TAG = "MyLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d(TAG, "Найдем View-элементы");
        tvOut = findViewById(R.id.tvOut);
        btnOk = findViewById(R.id.btnOk);
        btnCancel = findViewById(R.id.btnCancel);

        Log.d(TAG, "Присваиваем обработчик кнопкам");
        btnOk.setOnClickListener(this);
        btnCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Log.d(TAG, "По ID определяем кнопку, вызвавшую обработчик");
        switch (view.getId()) {
            //Кнопка ОК
            case R.id.btnOk:
                Log.d(TAG, "Кнопка ОК");
                tvOut.setText("Нажата кнопка ОК");
                btnOk.setEnabled(false);
                btnCancel.setEnabled(true);
                Toast.makeText(this,"Нажата кнопка ОК", Toast.LENGTH_SHORT).show();
                break;
            //Кнопка Cancel
            case R.id.btnCancel:
                Log.d(TAG,"Кнопка Cancel");
                tvOut.setText("Нажата кнопка Cancel");
                btnCancel.setEnabled(false);
                btnOk.setEnabled(true);
                Toast.makeText(this, "Нажата кнопка Cancel",Toast.LENGTH_LONG).show();
                break;
        }
    }
}
