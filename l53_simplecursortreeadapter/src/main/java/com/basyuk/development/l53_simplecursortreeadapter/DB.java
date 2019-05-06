package com.basyuk.development.l53_simplecursortreeadapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DB {
    private static final String DB_NAME = "mydb";
    private static final int DB_VERSION = 1;

    // имя таблицы компаний, поля и запрос создания
    private static final String COMPANY_TABLE = "company";
    public static final String COMPANY_COLUMN_ID = "_id";
    public static final String COMPANY_COLUMN_NAME = "name";
    private static final String COMPANY_TABLE_CREATE =  "CREATE TABLE " + COMPANY_TABLE + "(" + COMPANY_COLUMN_ID + " INTEGER PRIMARY KEY, " + COMPANY_COLUMN_NAME + " text" + ");";

    // имя таблицы телефонов, поля и запрос создаия
    private static final String PHONE_TABLE = "phone";
    public static final String PHONE_COLUMN_ID = "_id";
    public static final String PHONE_COLUMN_NAME = "name";
    public static final String PHONE_COLUMN_COMPANY = "company";
    private static final String PHONE_TABLE_CREATE = "CREATE TABLE " + PHONE_TABLE + "(" + PHONE_COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + PHONE_COLUMN_NAME + " text, " + PHONE_COLUMN_COMPANY + " INTEGER" + ");";

    private final Context mCtx;
    private DBHelper mDBHelper;
    private SQLiteDatabase mDB;

    public DB(Context context){
        mCtx = context;
    }

    //открываем подключение
    public void open(){
        mDBHelper = new DBHelper(mCtx, DB_NAME, null, DB_VERSION);
        mDB = mDBHelper.getWritableDatabase();
    }

    //закрываем подключение
    public void close(){
        if (mDBHelper != null)
            mDBHelper.close();
    }

    //данные по компаниям
    public Cursor getCompanyData() {
        return mDB.query(COMPANY_TABLE, null, null, null, null,null, null);
    }

    //данные по телефонам конкретной группы
    public Cursor getPhoneData(long companyID){
        return mDB.query(PHONE_TABLE, null, PHONE_COLUMN_COMPANY + " = " + companyID, null, null, null, null);
    }

    private class DBHelper extends SQLiteOpenHelper{
        public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory cursorFactory, int version){
            super(context, name, cursorFactory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            ContentValues contentValues = new ContentValues();

            //названия компаний (групп)
            String[] companies = new String[]{ "HTC", "Samsung", "LG" };

            //создаем и заполняем таблицу компаний
            sqLiteDatabase.execSQL(COMPANY_TABLE_CREATE);
            for (int i = 0; i < companies.length; i++){
                contentValues.put(COMPANY_COLUMN_ID, i + 1);
                contentValues.put(COMPANY_COLUMN_NAME, companies[i]);
                sqLiteDatabase.insert(COMPANY_TABLE, null, contentValues);
            }

            // названия телефонов (элементов)
            String[] phonesHTC = new String[] { "Sensation", "Desire", "Wildfire", "Hero" };
            String[] phonesSamsung = new String[] { "Galaxy S II", "Galaxy Nexus", "Wave" };
            String[]  phonesLG = new String[] { "Optimus", "Optimus Link", "Optimus Black", "Optimus One"};

            // создаем и заполняем таблицу телефонов
            sqLiteDatabase.execSQL(PHONE_TABLE_CREATE);
            contentValues.clear();
            for (int i = 0; i < phonesHTC.length; i++){
                contentValues.put(PHONE_COLUMN_COMPANY, 1);
                contentValues.put(PHONE_COLUMN_NAME, phonesHTC[i]);
                sqLiteDatabase.insert(PHONE_TABLE, null, contentValues);
            }
            for (int i = 0; i < phonesSamsung.length; i++){
                contentValues.put(PHONE_COLUMN_COMPANY, 1);
                contentValues.put(PHONE_COLUMN_NAME, phonesSamsung[i]);
                sqLiteDatabase.insert(PHONE_TABLE, null, contentValues);
            }
            for (int i = 0; i < phonesLG.length; i++){
                contentValues.put(PHONE_COLUMN_COMPANY, 1);
                contentValues.put(PHONE_COLUMN_NAME, phonesLG[i]);
                sqLiteDatabase.insert(PHONE_TABLE, null, contentValues);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }

    }
}
