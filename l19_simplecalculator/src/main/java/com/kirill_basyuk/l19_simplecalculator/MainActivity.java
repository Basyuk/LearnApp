package com.kirill_basyuk.l19_simplecalculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText etNum1;
    EditText etNum2;

    Button btnAdd;
    Button btnSub;
    Button btnMult;
    Button btnDiv;

    TextView tvResult;

    String oper = "";

    final int MENU_RESET_ID = 1;
    final int MENU_QUIT_ID = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNum1 = findViewById(R.id.etNum1);
        etNum2 = findViewById(R.id.etNum2);

        btnAdd = findViewById(R.id.btnAdd);
        btnDiv = findViewById(R.id.btnDiv);
        btnMult = findViewById(R.id.btnMult);
        btnSub = findViewById(R.id.btnSub);

        tvResult = findViewById(R.id.tvResult);

        btnAdd.setOnClickListener(this);
        btnDiv.setOnClickListener(this);
        btnMult.setOnClickListener(this);
        btnSub.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        float num1 = 0;
        float num2 = 0;
        float result = 0;

        //Проверяем поля на пустоту
        if (TextUtils.isEmpty(etNum1.getText().toString()) || TextUtils.isEmpty(etNum2.getText().toString())){
            return;
        }

        //читаем EditText и заполняем переменные числами
        num1 = Float.parseFloat(etNum1.getText().toString());
        num2 = Float.parseFloat(etNum2.getText().toString());

        switch (view.getId()){
            case R.id.btnAdd:
                oper = " + ";
                result = num1 + num2;
                break;
            case R.id.btnSub:
                oper = " - ";
                result = num1 - num2;
                break;
            case R.id.btnMult:
                oper = " * ";
                result = num1 * num2;
                break;
            case R.id.btnDiv:
                oper = " : ";
                if (num2 == 0)
                    break;
                result = num1/num2;
                break;
                default:
                    break;
        }

        //Выводим результат
        if(view.getId() == R.id.btnDiv || num2 == 0){
            Toast.makeText(this, "На ноль делить нельзя", Toast.LENGTH_LONG).show();
            tvResult.setText("Ошибка! На ноль делить нельзя!");
        }
        else tvResult.setText(num1 + oper + num2 + " = " + result);

    }

    public boolean onCreateOptionsMenu(Menu menu){
        menu.add(0, MENU_RESET_ID, 0, "Reset");
        menu.add(0, MENU_QUIT_ID, 0, "Quit");
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch (item.getItemId()){
            case MENU_RESET_ID:
                etNum1.setText("");
                etNum2.setText("");
                tvResult.setText("");
                break;
            case MENU_QUIT_ID:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
