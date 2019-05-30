package com.basyuk.development.l103_multitouch;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    StringBuilder stringBuilder;
    TextView textView;
    int upPI = 0, downPI = 0;
    boolean inTouch = false;
    String result = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_main);

        textView = new TextView(this);
        textView.setTextSize(30);
        textView.setOnTouchListener(this);
        setContentView(textView);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        //событие
        int actionMask = motionEvent.getActionMasked();
        //индекс касания
        int pointerIndex = motionEvent.getActionIndex();
        //число касаний
        int poinerCount = motionEvent.getPointerCount();

        switch (actionMask) {
            case MotionEvent.ACTION_DOWN: //первое касание
                inTouch = true;

            case MotionEvent.ACTION_POINTER_DOWN: //последующие касания
                downPI = pointerIndex;
                break;

            case MotionEvent.ACTION_UP: //прерывание последнего касания
                inTouch = false;
                stringBuilder.setLength(0);

            case MotionEvent.ACTION_POINTER_UP: //прерывания касаний
                upPI = pointerIndex;
                break;

            case MotionEvent.ACTION_MOVE: //движение
                stringBuilder.setLength(0);

                for (int i = 0; i < 10; i++) {
                    stringBuilder.append("Index = " + i);
                    if (i < poinerCount) {
                        stringBuilder.append(", ID = " + motionEvent.getPointerId(i));
                        stringBuilder.append(", X = " + motionEvent.getX(i));
                        stringBuilder.append(", Y = " + motionEvent.getY(i));
                    } else {
                        stringBuilder.append(", ID = ");
                        stringBuilder.append(", X = ");
                        stringBuilder.append(", Y = ");
                    }
                    stringBuilder.append("\r\n");
                }
                break;
        }

        result = "down: " + downPI + "\n" + "up: " + upPI + "\n";

        if (inTouch) {
           result += "pointerCount = " + poinerCount + "\n" + stringBuilder.toString();
        }

        textView.setText(result);
        return true;
    }
}
