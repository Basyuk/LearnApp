package com.basyuk.development.l69_parcelable;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

public class MyObject implements Parcelable {

    final static String TAG = "myLogs";

    public String s;
    public int i;

    //обычный конструктор
    public MyObject(String _s, int _i){
        Log.d(TAG, "MyObject(String _s, int _i)");
        s = _s;
        i = _i;
    }

    public MyObject(Parcel parcel) {
        Log.d(TAG, "MyObject(Parcel parcel)");
        s = parcel.readString();
        i = parcel.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        Log.d(TAG, "writeToParcel");
        parcel.writeString(s);
        parcel.writeInt(i);
    }

    public static final Parcelable.Creator<MyObject> CREATOR = new Parcelable.Creator<MyObject>(){
        //распаковываем объект из Parcel
        public MyObject createFromParcel(Parcel in){
            Log.d(TAG, "createFromParcel");
            return new MyObject(in);
        }

        public MyObject[] newArray(int size){
            return new MyObject[size];
        }
    };
}
