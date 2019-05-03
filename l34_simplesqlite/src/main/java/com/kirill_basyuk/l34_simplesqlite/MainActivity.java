package com.kirill_basyuk.l34_simplesqlite;

import android.content.ContentValues;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    final String LOG_TAG = "myLogs";

    Button btnAdd, btnRead, btnClear, btnUpd, btnDel;
    EditText etName, etEmail, etID;

    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = findViewById(R.id.btnAdd);
        btnRead = findViewById(R.id.btnRead);
        btnClear = findViewById(R.id.btnClear);
        btnUpd = findViewById(R.id.btnUpd);
        btnDel = findViewById(R.id.btnDel);

        etEmail = findViewById(R.id.etEmail);
        etName = findViewById(R.id.etName);
        etID = findViewById(R.id.etID);

        btnClear.setOnClickListener(this);
        btnRead.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnDel.setOnClickListener(this);
        btnUpd.setOnClickListener(this);

        dbHelper = new DBHelper(this);
    }

    @Override
    public void onClick(View view) {

        //создаем объект для данных
        ContentValues contentValues = new ContentValues();

        //Получаем данный из форм для полей
        String name = etName.getText().toString();
        String email = etEmail.getText().toString();
        String id = etID.getText().toString();

        //подключаемся к БД
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        switch (view.getId()) {
            case R.id.btnAdd:
                Log.d(LOG_TAG, "---Insert in mytable:---");
                // подготовим данный для вставки в виде пар (наименование столба, значение)
                contentValues.put("name", name);
                contentValues.put("email", email);
                //вставляем запись и получаем её id
                long rowID = sqLiteDatabase.insert("mytable", null, contentValues);
                etName.setText("");
                etEmail.setText("");
                Log.d(LOG_TAG, "row inserted, ID = " + rowID);
                break;

            case R.id.btnRead:
                Log.d(LOG_TAG, "---Rows in mytable:---");
                //делаем запрос всех данных из таблицы mytable, получаем Cursor
                Cursor cursor = sqLiteDatabase.query("mytable", null, null, null, null, null, null);

                //Ставим позицию курсора на первую строку выборки
                // если в выборке нет строк, то вернется false
                if (cursor.moveToFirst()) {
                    //определяем номера столбцов по имени в выборке
                    int idColIndex = cursor.getColumnIndex("id");
                    int nameColIndex = cursor.getColumnIndex("name");
                    int emailColIndex = cursor.getColumnIndex("email");

                    do {
                        //получаем значения по номерам столбцов и пишем все в лог
                        Log.d(LOG_TAG,
                                "ID = " + cursor.getInt(idColIndex) +
                                        ", name = " + cursor.getString(nameColIndex) +
                                        ", email = " + cursor.getString(emailColIndex));
                    } while (cursor.moveToNext());
                } else
                    Log.d(LOG_TAG, "0 rows");
                cursor.close();
                break;

            case R.id.btnClear:
                Log.d(LOG_TAG, "---Clear mytable:---");
                //удаляем все записи
                int clearCount = sqLiteDatabase.delete("mytable", null, null);
                Log.d(LOG_TAG, "deleted rows count: " + clearCount);
                break;

            case R.id.btnUpd:
                if (id.equalsIgnoreCase("")){
                    break;
                }
                Log.d(LOG_TAG, "--- Update mytable: ---");
                //Подготовим значения для обновления
                contentValues.put("name", name);
                contentValues.put("email", email);
                //обновляем по ID
                int updCount = sqLiteDatabase.update("mytable", contentValues, "id = ?" , new String[]{id});
                Log.d(LOG_TAG, "updated rows count = " + updCount);
                break;

            case R.id.btnDel:
                if(id.equalsIgnoreCase("")){
                    break;
                }
                Log.d(LOG_TAG, "--- Delete from table ---");
                //удаляем по ID
                int delCount = sqLiteDatabase.delete("mytable", "id = " + id, null);
                Log.d(LOG_TAG, "deleted rows count = " + delCount);
                break;
        }
        //закрываем подключение к БД
        dbHelper.close();
    }

    class DBHelper extends SQLiteOpenHelper{
        public DBHelper(Context context){
            //конструктор суперкласса
            super(context, "myDB", null, 1);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            Log.d(LOG_TAG, "---onCreate database---");
            //создаем таблицу с полями
            sqLiteDatabase.execSQL("create table mytable ("
                    + "id integer primary key autoincrement,"
                    + "name text,"
                    + "email text" + ");");
        }

        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion){

        }

    }

}
