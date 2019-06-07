package com.basyuk.development.l110_dialogfragment;

import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    DialogFragment dialogFragment1;
    DialogFragment dialogFragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dialogFragment1 = new Dialog1();
        dialogFragment2 = new Dialog2();
    }

    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.btnDlg1:
                dialogFragment1.show(getSupportFragmentManager(), "dlg1");
                break;

            case R.id.btnDlg2:
                dialogFragment2.show(getSupportFragmentManager(), "dlg2");
                break;

                default:
                    break;
        }
    }
}
