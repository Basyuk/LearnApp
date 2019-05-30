package com.basyuk.development.l102_touch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    TextView textView;
    float x, y;
    String sDown, sMove, sUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);
        textView = new TextView(this);
        textView.setOnTouchListener(this);
        setContentView(textView);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        x = motionEvent.getX();
        y = motionEvent.getY();

        switch (motionEvent.getAction()) {

            case MotionEvent.ACTION_DOWN: //нажатие
                sDown = "Down: " + x + ", " + y;
                sMove = "";
                sUp = "";
                break;

            case MotionEvent.ACTION_MOVE: //движение
                sMove = "Move: " + x + ", " + y;
                break;

            case MotionEvent.ACTION_UP: //отпускание
            case MotionEvent.ACTION_CANCEL:
                sMove = "";
                sUp = "Up: " + x + ", " + y;
                break;
        }
        textView.setText(sDown + "\n" + sMove + "\n" + sUp);
        return true;
    }
}
