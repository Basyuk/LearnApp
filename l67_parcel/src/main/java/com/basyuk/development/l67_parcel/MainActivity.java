package com.basyuk.development.l67_parcel;

import android.os.Parcel;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    final String TAG = "myLogs";
    Parcel parcel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        writeParcel();
        readParcel();
        Log.d(TAG, "onCreate: " + parcel.marshall());
    }

    void writeParcel(){
        parcel = Parcel.obtain();

        byte b = 1;
        int i = 2;
        long l = 3;
        float f = 4;
        double d = 5;
        String s = "abcdefhg";

        logWriteInfo("before writing");
        parcel.writeByte(b);
        logWriteInfo("byte");
        parcel.writeInt(i);
        logWriteInfo("int");
        parcel.writeLong(l);
        logWriteInfo("long");
        parcel.writeFloat(f);
        logWriteInfo("float");
        parcel.writeDouble(d);
        logWriteInfo("double");
        parcel.writeString(s);
        logWriteInfo("String");

    }

    void logWriteInfo(String txt){
        Log.d(TAG, txt + ": dataSize = " + parcel.dataSize());
    }

    void readParcel(){
        logReadInfo("before reading");
        parcel.setDataPosition(0);
        logReadInfo("byte = " + parcel.readByte());
        logReadInfo("int = " + parcel.readInt());
        logReadInfo("long = " + parcel.readLong());
        logReadInfo( "float = " + parcel.readFloat());
        logReadInfo("double = " + parcel.readDouble());
        logReadInfo("String = " + parcel.readString());
    }

    void logReadInfo(String txt){
        Log.d(TAG, txt + ": dataPosition = " + parcel.dataPosition());
    }
}
