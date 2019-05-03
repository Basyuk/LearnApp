package com.basyuk.development.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class l38_SQLiteTransaction extends AppCompatActivity {

    final String TAG = "myLogs";

    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_l38__sqlite_transaction);

        Log.d(TAG, "--- onCreate Activity ---");

        dbHelper = new DBHelper(this);
        myAction();

    }

    void myAction() {
        try {
            sqLiteDatabase = dbHelper.getWritableDatabase();
            delete(sqLiteDatabase, "mytable");

            sqLiteDatabase.beginTransaction();
            insert(sqLiteDatabase, "mytable", "val1");

            Log.d(TAG, "create DBHelper");
            DBHelper dbHelper1 = new DBHelper(this);
            Log.d(TAG, "get db");
            SQLiteDatabase sqLiteDatabase1 = dbHelper1.getWritableDatabase();
            read(sqLiteDatabase1, "mytable");
            dbHelper1.close();

            sqLiteDatabase.setTransactionSuccessful();
            sqLiteDatabase.endTransaction();

            read(sqLiteDatabase, "mytable");
            dbHelper.close();
        } catch (Exception ex){
            Log.d(TAG, ex.getClass() + "error: " + ex.getMessage());
        }

    }

    void insert(SQLiteDatabase sqLiteDatabase, String table, String value){
        Log.d(TAG, "Insert in table " + table + " value = " + value);

        ContentValues contentValues = new ContentValues();
        contentValues.put("val", value);
        sqLiteDatabase.insert(table, null, contentValues);
    }

    void read(SQLiteDatabase sqLiteDatabase, String table) {
        Log.d(TAG, "Read table " + table);

        Cursor cursor = sqLiteDatabase.query(table, null, null, null, null, null,null);
        if(cursor != null) {
            Log.d(TAG, "Records count = " + cursor.getCount());
            if(cursor.moveToFirst()) {
                do {
                    Log.d(TAG, cursor.getString(cursor.getColumnIndex("val")));
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
    }

    void delete(SQLiteDatabase sqLiteDatabase, String table){
        Log.d(TAG, "Delete all from table " + table);
        sqLiteDatabase.delete(table, null, null);
    }

    //класс для работы с БД
    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context){
            super(context, "MyDB", null, 1);
        }

        public void onCreate(SQLiteDatabase sqLiteDatabase){
            Log.d(TAG, "--- onCreate database ---");

            sqLiteDatabase.execSQL("create table mytable(" + "id integer primary key autoincrement," + "val text" + ");");
        }

        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion){

        }
    }
}