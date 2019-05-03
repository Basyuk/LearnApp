package com.basyuk.development.l39_sqliteonupgradedb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    final String TAG = "myLogs";

    final String DB_NAME = "staff"; // имя БД
    final int DB_VERSION = 2; // версия БД

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBHelper dbHelper = new DBHelper(this);
        SQLiteDatabase sqLiteDatabase = dbHelper.getWritableDatabase();
        Log.d(TAG, "--- Staff db v." + sqLiteDatabase.getVersion() + " --- ");
        writeStaff(sqLiteDatabase);
        dbHelper.close();
    }

    // запрос данных и вывод в лог
    private void writeStaff(SQLiteDatabase sqLiteDatabase){
        Cursor cursor = sqLiteDatabase.rawQuery("SELECT * FROM people", null);
        logCursor(cursor, "Table people");
        cursor.close();

        cursor = sqLiteDatabase.rawQuery("SELECT * FROM position", null);
        logCursor(cursor, "Table position");
        cursor.close();

        String sqlQuery = "SELECT PL.name AS Name, PS.name AS Position, salary AS Salary "
                + "FROM people AS PL "
                + "INNER JOIN position AS PS "
                + "ON PL.posid = PS.id ";
        cursor = sqLiteDatabase.rawQuery(sqlQuery, null);
        logCursor(cursor, "inner join");
        cursor.close();
    }

    // вывод в лог данных из курсора
    void logCursor(Cursor cursor, String title){
        if(cursor != null){
            if(cursor.moveToFirst()){
                Log.d(TAG, title + ". " + cursor.getCount() + " rows");
                StringBuilder stringBuilder = new StringBuilder();
                do {
                    stringBuilder.setLength(0);
                    for (String cn : cursor.getColumnNames()){
                        stringBuilder.append(cn + " = " + cursor.getString(cursor.getColumnIndex(cn)) + "; ");
                    }
                    Log.d(TAG, stringBuilder.toString());
                } while (cursor.moveToNext());
            }
        } else
            Log.d(TAG, title + ". Cursor is null");
    }


    class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }

        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            Log.d(TAG, "--- onCreate database---");

            String[] people_name = {"Иван", "Марья", "Петр", "Антон", "Даша", "Борис", "Костя", "Игорь"};
            int[] people_posid = {2, 3, 2, 2, 3, 1, 2, 4};

            //данные для таблицы должностей
            int[] position_id = {1, 2, 3, 4};
            String[] position_name = { "Директор", "Программер", "Бухгалтер", "Охранник"};
            int[] position_salary = {15000, 13000, 10000, 8000};

            ContentValues contentValues = new ContentValues();

            //создаем таблицу должностей
            sqLiteDatabase.execSQL("CREATE TABLE position (" + "id integer PRIMARY KEY," + "name text, salary integer" + ");");

            //заполняем её
            for (int i = 0; i < position_id.length; i++){
                contentValues.clear();
                contentValues.put("id", position_id[i]);
                contentValues.put("name", position_name[i]);
                contentValues.put("salary", position_salary[i]);
                sqLiteDatabase.insert("position", null, contentValues);
            }

            // создаем таблицу людей
            sqLiteDatabase.execSQL("CREATE TABLE people (" + "id integer primary key autoincrement," + "name text, posid);");

            //заполняем ее
            for (int i = 0; i < people_name.length; i++) {
                contentValues.clear();
                contentValues.put("name", people_name[i]);
                contentValues.put("posid", people_posid[i]);
                sqLiteDatabase.insert("people", null, contentValues);
            }
        }

        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion){
            Log.d(TAG, "--- onUpgrade database from " + oldVersion + " to " + newVersion + " version ---");

            if (oldVersion == 1 && newVersion == 2){
                ContentValues contentValues = new ContentValues();

                //данные для таблицы должностей
                int[] position_id = { 1, 2 ,3 ,4 };
                String[] position_name = { "Директор", "Программер", "Бухгалтер", "Охранник"};
                int[] position_salary = { 15000, 13000, 10000, 8000};

                sqLiteDatabase.beginTransaction();
                try {
                    //создаем таблицу должностей
                    sqLiteDatabase.execSQL("CREATE TABLE position (" + "id integer PRIMARY KEY," + "name text, salary integer);");

                    //заполняем её
                    for (int i = 0; i < position_id.length; i++){
                        contentValues.clear();
                        contentValues.put("id", position_id[i]);
                        contentValues.put("name", position_name[i]);
                        contentValues.put("salary", position_salary[i]);
                        sqLiteDatabase.insert("position", null, contentValues);
                    }
                    sqLiteDatabase.execSQL("ALTER TABLE people ADD COLUMN posid integer;");
                    for (int i = 0; i < position_id.length; i++){
                        contentValues.clear();
                        contentValues.put("posid", position_id[i]);
                        sqLiteDatabase.update("people", contentValues, "position = ?", new String[]{position_name[i]});
                    }

                    sqLiteDatabase.execSQL("CREATE TEMPORARY TABLE people_tmp(" + "id integer, name text, position text, posid integer);");
                    sqLiteDatabase.execSQL("INSERT INTO people_tmp SELECT id, name, position, posid FROM people;");
                    sqLiteDatabase.execSQL("DROP TABLE people");

                    sqLiteDatabase.execSQL("CREATE TABLE people ("
                            + "id integer PRIMARY KEY AUTOINCREMENT," + "name text, posid integer);");

                    sqLiteDatabase.execSQL("INSERT INTO people SELECT id, name, posid FROM people_tmp;");
                    sqLiteDatabase.execSQL("DROP TABLE people_tmp;");

                    sqLiteDatabase.setTransactionSuccessful();
                } finally {
                    sqLiteDatabase.endTransaction();
                }
            }

        }
    }
}
