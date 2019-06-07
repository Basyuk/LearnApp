package com.basyuk.development.l110_dialogfragment;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class Dialog1 extends DialogFragment implements View.OnClickListener {

    final String TAG = "myLogs";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        getDialog().setTitle("Title!");

        View view = inflater.inflate(R.layout.dialog1, null);

        view.findViewById(R.id.btnYes).setOnClickListener(this);
        view.findViewById(R.id.btnNo).setOnClickListener(this);
        view.findViewById(R.id.btnMaybe).setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        Log.d(TAG, "Dialog1: " + ((Button) view).getText());
        dismiss();
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        Log.d(TAG, "Dialog1: OnDismiss");
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        Log.d(TAG, "Dialog1: onCancel");
    }
}
