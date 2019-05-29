package com.basyuk.development.l101_contentprovider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;


public class MyContactProvider extends ContentProvider {

    final String TAG = "myLogs";

    //Константы для БД
    static final String DB_NAME = "mydb";
    static final int DB_VERSION = 1;

    //таблица
    static final String CONTACT_TABLE = "contacts";

    //поля
    static final String CONTACT_ID = "_id";
    static final String CONTACT_NAME = "name";
    static final String CONTACT_EMAIL = "email";

    //скрипт создания таблицы
    static final String DB_CREATE = "CREATE TABLE " + CONTACT_TABLE + "(" + CONTACT_ID + "integer PRIMARY KEY AUTOINCREMENT, " + CONTACT_NAME + " text, " + CONTACT_EMAIL + " text" + ");";

    // Uri
    // authority

    static final String AUTHORITY = "com.basyuk.development.providers.AddressBook";

    //path
    static final String CONTACT_PATH = "contacts";

    //Общий Uri
    public static final Uri CONTACT_CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/" + CONTACT_PATH);

    //Типы данных
    //набор строк
    static final String CONTACT_CONTENT_TYPE = "vnd.android.cursor.dir/vnd." + AUTHORITY + "." + CONTACT_PATH;

    //одна строка
    static final String CONTACT_CONTENT_ITEM_TYPE = "vnd.android.cursor.item/vnd." + AUTHORITY + "." + CONTACT_PATH;

    //UriMatcher
    // общий Uri
    static final int URI_CONTACTS = 1;

    //Uri с указанным ID
    static final int URI_CONTACTS_ID = 2;

    // описание и создание UriMatcher
    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, CONTACT_PATH, URI_CONTACTS);
        uriMatcher.addURI(AUTHORITY, CONTACT_PATH + "/#", URI_CONTACTS_ID);
    }

    DBHelper dbHelper;
    SQLiteDatabase sqLiteDatabase;
    
    
    @Override
    public boolean onCreate() {
        Log.d(TAG, "onCreate");
        dbHelper = new DBHelper(getContext());
        return true;
    }
    
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        Log.d(TAG, "query, " + uri.toString());

        //проверяем Uri
        switch (uriMatcher.match(uri)) {
            case URI_CONTACTS: //общий Uri
                Log.d(TAG, "URI_CONTACTS");
                // если сортировка не указана, ставим свою - по имени
                if (TextUtils.isEmpty(sortOrder)) {
                    sortOrder = CONTACT_NAME + "ASC";
                }
                break;

            case URI_CONTACTS_ID: //Uri с ID
                String id = uri.getLastPathSegment();
                Log.d(TAG, "URI_CONTACTS_ID, " + id);
                //добавляем ID к условию выборки
                if (TextUtils.isEmpty(selection)) {
                    selection = CONTACT_ID + " = " + id;
                } else {
                    selection = selection + " AND" + CONTACT_ID + " = " + id;
                }
                break;

                default:
                    throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        sqLiteDatabase = dbHelper.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.query(CONTACT_TABLE, projection, selection, selectionArgs, null, null, sortOrder);

        //просим Content Resolver уведомлять этот курсор об изменениях даннх в CONTACT_CONTENT_URI
        cursor.setNotificationUri(getContext().getContentResolver(), CONTACT_CONTENT_URI);
        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        Log.d(TAG, "getType, " + uri.toString());
        switch (uriMatcher.match(uri)) {
            case URI_CONTACTS:
                return CONTACT_CONTENT_TYPE;

            case URI_CONTACTS_ID:
                return CONTACT_CONTENT_ITEM_TYPE;
        }
        return null;
    }


    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        Log.d(TAG, "insert, " + uri.toString());
        if (uriMatcher.match(uri) != URI_CONTACTS)
            throw new IllegalArgumentException("Wrong URI: " + uri);

        sqLiteDatabase = dbHelper.getWritableDatabase();
        long rowID = sqLiteDatabase.insert(CONTACT_TABLE, null, contentValues);
        Uri resultUri = ContentUris.withAppendedId(CONTACT_CONTENT_URI, rowID);
        //уведомляем ContentResolver, что данные по адресу resultUri изменились
        getContext().getContentResolver().notifyChange(resultUri, null);
        return resultUri;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        Log.d(TAG, "delete, " + uri.toString());
        switch (uriMatcher.match(uri)) {
            case URI_CONTACTS:
                Log.d(TAG, "URI_CONTACTS");
                break;

            case  URI_CONTACTS_ID:
                String id = uri.getLastPathSegment();
                Log.d(TAG, "URI_CONTACTS_ID, " + id);
                if (TextUtils.isEmpty(selection)) {
                    selection = CONTACT_ID + " = " + id;
                } else {
                    selection = selection + " AND " + CONTACT_ID + " = " + id;
                }
                break;

                default:
                    throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        sqLiteDatabase = dbHelper.getWritableDatabase();
        int count = sqLiteDatabase.delete(CONTACT_TABLE, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        Log.d(TAG, "update, " + uri.toString());
        switch (uriMatcher.match(uri)) {
            case URI_CONTACTS:
                String id = uri.getLastPathSegment();
                Log.d(TAG, "URI_CONTACTS_ID, " + id);
                if (TextUtils.isEmpty(selection)) {
                    selection = CONTACT_ID + " = " + id;
                } else {
                    selection = selection + " AND " + CONTACT_ID + " = " + id;
                }
                break;

                default:
                    throw new IllegalArgumentException("Wrong URI: " + uri);
        }
        sqLiteDatabase = dbHelper.getWritableDatabase();
        int count = sqLiteDatabase.update(CONTACT_TABLE, contentValues, selection, selectionArgs);
        getContext().getContentResolver().notifyChange(uri, null);
        return count;
    }
    
    private class DBHelper extends SQLiteOpenHelper {
        
        public DBHelper(Context context) {
            super(context, DB_NAME, null, DB_VERSION);
        }
        
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(DB_CREATE);
            ContentValues contentValues = new ContentValues();
            for (int i = 1; i <= 3; i++) {
                contentValues.put(CONTACT_NAME, "name" + i);
                contentValues.put(CONTACT_EMAIL, "email" + i);
                sqLiteDatabase.insert(CONTACT_TABLE, null, contentValues);
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
            
        }
    }
}
