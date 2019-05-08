package com.basyuk.development.l67_progressdialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    ProgressDialog progressDialog;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view){
        switch (view.getId()) {
            case R.id.btnDefault:
                progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("Title");
                progressDialog.setMessage("Message");
                //добавляем кнопку
                progressDialog.setButton(Dialog.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                progressDialog.show();
                break;

            case R.id.btnHoriz:
                progressDialog = new ProgressDialog(this);
                progressDialog.setTitle("Title");
                progressDialog.setMessage("Massage");
                //меняем стиль на индикатор
                progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                //устанавливаем максимум
                progressDialog.setMax(2148);
                //включаем анимацию ожидания
                progressDialog.setIndeterminate(true);
                progressDialog.show();
                handler = new Handler() {
                    public void handleMessage(Message m) {
                        //выключаем анимацию ожидания
                        progressDialog.setIndeterminate(false);
                        if (progressDialog.getProgress() < progressDialog.getMax()){
                            //увеличиваем значение индикаторов
                            progressDialog.incrementProgressBy(50);
                            progressDialog.incrementSecondaryProgressBy(75);
                            handler.sendEmptyMessageDelayed(0, 100);
                        } else
                            progressDialog.dismiss();
                    }
                };
                handler.sendEmptyMessageDelayed(0, 2000);
                break;

                default:
                    break;
        }
    }
}
