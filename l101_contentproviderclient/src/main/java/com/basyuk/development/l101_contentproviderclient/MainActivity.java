package com.basyuk.development.l101_contentproviderclient;

import android.content.ContentUris;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends AppCompatActivity {

    final String TAG = "myLogs";

    final Uri CONTACT_URI = Uri.parse("content://com.basyuk.development.AddressBook/contacts");

    final String CONTACT_NAME = "name";
    final String CONTACT_EMAIL = "email";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Cursor cursor = getContentResolver().query(CONTACT_URI, null, null, null, null);
        startManagingCursor(cursor);

        String from[] = { "name", "email" };
        int to[] = { android.R.id.text1, android.R.id.text2 };
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, cursor, from, to);

        ListView listViewContact = findViewById(R.id.lvContact);
        listViewContact.setAdapter(adapter);
    }

    public void onClickInsert(View view) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(CONTACT_NAME, "name 4");
        contentValues.put(CONTACT_EMAIL, "email 4");
        Uri newUri = getContentResolver().insert(CONTACT_URI, contentValues);
        Log.d(TAG, "insert, result Uri: " + newUri.toString());
    }

    public void onClickUpdate(View view){
        ContentValues contentValues = new ContentValues();
        contentValues.put(CONTACT_NAME, "name 5");
        contentValues.put(CONTACT_EMAIL, "email 5");
        Uri uri = ContentUris.withAppendedId(CONTACT_URI, 3);
        int count = getContentResolver().update(uri, contentValues, null, null);
        Log.d(TAG, "update, count = " + count);
    }

    public void onClickDelete(View view) {
        Uri uri = ContentUris.withAppendedId(CONTACT_URI, 3);
        int count = getContentResolver().delete(uri, null, null);
        Log.d(TAG, "delete, count = " + count);
    }

    public void onClickError(View view) {
        Uri uri = Uri.parse("content://com.basyuk.development.AddressBook/phones");
        try {
            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
        } catch (Exception e) {
            Log.d(TAG, "Error: " + e.getClass() + ", " + e.getMessage());
        }
    }
}
