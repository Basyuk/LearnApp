package com.kirill_basyuk.l11_resvalues;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout llButtom = findViewById(R.id.llBottom);
        TextView tvButtom = findViewById(R.id.tvBottom);
        Button btnButtom = findViewById(R.id.btnBottom);

        llButtom.setBackgroundResource(R.color.llBottomColor);
        tvButtom.setText(R.string.tvBottomText);
        btnButtom.setText(R.string.btnBottomText);
    }
}
