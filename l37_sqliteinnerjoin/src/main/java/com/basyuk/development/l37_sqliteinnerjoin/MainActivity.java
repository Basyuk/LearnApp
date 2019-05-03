package com.basyuk.development.l37_sqliteinnerjoin;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {


    final String TAG = "myLogs";

    //Данные для таблицы должностей
    int[] positionID = { 1, 2, 3, 4 };
    String[] positionName = { "Директор", "Программер", "Бухгалтер", "Охранник" };
    int[] positionSalary = { 15000, 13000, 10000, 8000 };

    //данные для таблицы людей
    String[] peopleName = { "Иван", "Марья", "Петр", "Антон", "Даша", "Борис", "Костя", "Игорь" };
    int[] peoplePositionID = { 2, 3, 2, 2, 3, 1, 2, 4 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //подключаемся к БД
        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();

        //описание курсора
        Cursor cursor;

        //выводим данные по должностям
        Log.d(TAG, "--- Table position ---");
        cursor = sqLiteDatabase.query("position", null, null, null, null, null, null);
        logCursor(cursor);
        cursor.close();
        Log.d(TAG, "--- ----");

        //выводим в лог данные по людям
        Log.d(TAG, "--- Table people ---");
        cursor = sqLiteDatabase.query("people", null, null, null, null, null, null);
        logCursor(cursor);
        cursor.close();
        Log.d(TAG, "--- ---");

        //выводим результат объединения
        //используем rawQuery
        Log.d(TAG, "--- INNER JOIN with rawQuery ---");
        String sqlQuery = "select PL.name as Name, PS.name as Position, salary as Salary " + "from people as PL " + "inner join position as PS " + "on PL.positionID = PS.id " + "where salary > ?";
        cursor = sqLiteDatabase.rawQuery(sqlQuery, new String[] {"12000"} );
        logCursor(cursor);
        cursor.close();
        Log.d(TAG, "--- ---");

        //выводим результат объединения
        //используем query
        Log.d(TAG, "--- INNER JOIN with query");
        String table = "people as PL inner join position as PS on PL.positionID = PS.id";
        String columns[] = { "PL.name as Name", "PS.name as Position", "salary as Salary" };
        String selection = "salary < ?";
        String[] selectionArgs = {"12000"};
        cursor = sqLiteDatabase.query(table, columns, selection, selectionArgs, null, null, null);
        logCursor(cursor);
        cursor.close();
        Log.d(TAG, "--- ---");

        //закрываем БД
        dbHelper.close();

    }

    //вывод в лог данных курсора
    void logCursor(Cursor cursor){
        if(cursor != null) {
            if(cursor.moveToFirst()){
                String string;
                do {
                    string = "";
                    for (String cn : cursor.getColumnNames()){
                        string = string.concat(cn + "=" + cursor.getString(cursor.getColumnIndex(cn)) + ";");
                    }
                    Log.d(TAG, string);
                } while (cursor.moveToNext());
            }
        } else
            Log.d(TAG, "Cursor is null");
    }

    //Класс для работы с БД
    class DBHelper extends SQLiteOpenHelper {
        public DBHelper(Context context){
            super(context, "myDB", null, 1);
        }

        public void onCreate(SQLiteDatabase db){
            Log.d(TAG, "--- onCreate database ---");

            ContentValues contentValues = new ContentValues();

            // создаем таблицу должностей
            db.execSQL("create table position (" + "id integer primary key," + "name text," + "salary integer" + ");");

            //заполняем таблицу должностей
            for(int i = 0; i < positionID.length; i++){
                contentValues.clear();
                contentValues.put("id", positionID[i]);
                contentValues.put("name", positionName[i]);
                contentValues.put("salary", positionSalary[i]);
                db.insert("position", null, contentValues);
            }

            //создаем таблицу людей
            db.execSQL("create table people (" + "id integer primary key autoincrement," + "name text," + "positionID integer" + ");");

            //заполняем таблицу людей
            for (int i = 0; i < peopleName.length; i++){
                contentValues.clear();
                contentValues.put("name", peopleName[i]);
                contentValues.put("positionID", peoplePositionID[i]);
                db.insert("people", null, contentValues);
            }
        }

        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        }
    }
}
