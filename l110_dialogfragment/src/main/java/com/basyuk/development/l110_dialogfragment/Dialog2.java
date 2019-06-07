package com.basyuk.development.l110_dialogfragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.View;

public class Dialog2 extends DialogFragment implements DialogInterface.OnClickListener {

    final String TAG = "myLogs";

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder adb = new AlertDialog.Builder(getActivity()).setTitle("Title!").setPositiveButton(R.string.yes, this).setNegativeButton(R.string.no, this).setNeutralButton(R.string.maybe, this).setMessage(R.string.message_text);
        return adb.create();

    }

    @Override
    public void onClick(DialogInterface dialogInterface, int which) {

        int i = 0;

        switch (which) {
            case Dialog.BUTTON_POSITIVE:
                i = R.string.yes;
                break;

            case Dialog.BUTTON_NEGATIVE:
                i = R.string.no;
                break;

            case Dialog.BUTTON_NEUTRAL:
                i = R.string.maybe;
                break;
        }

        if (i > 0)
            Log.d(TAG, "Dialog 2: " + getResources().getString(i));
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        Log.d(TAG, "Dialog2: onDismiss");
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        Log.d(TAG, "Dialog2: onCancel");
    }
}
