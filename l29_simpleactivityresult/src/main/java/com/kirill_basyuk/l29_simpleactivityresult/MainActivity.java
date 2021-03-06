package com.kirill_basyuk.l29_simpleactivityresult;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tvName;
    Button btnName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvName = findViewById(R.id.tvName);
        btnName = findViewById(R.id.btnName);
        btnName.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(this, NameActvity.class);
        startActivityForResult(intent, 1);

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (data == null){
            return;
        }
            String name = data.getStringExtra("name");
            tvName.setText("Your name is " + name);
    }
}
