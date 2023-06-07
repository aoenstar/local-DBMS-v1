package com.example.localdbms;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

class DatabaseHelper extends SQLiteOpenHelper{

    private Context context;
    private static final String DATABASE_NAME = "SchoolBase1.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_table";
    private static final String COLUMN_ID = "_id";
    private static final String OBJECT_NAME = "obj_name";
    private static final String OBJECT_LOCATION = "location";
    private static final String COLUMN_SN = "sn";

    DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                OBJECT_NAME + " TEXT, " +
                OBJECT_LOCATION + " TEXT, " +
                COLUMN_SN + " INTEGER);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }


    void addItem(String name, String location, int sn)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(OBJECT_NAME, name);
        cv.put(OBJECT_LOCATION, location);
        cv.put(COLUMN_SN, sn);
        long result = db.insert(TABLE_NAME, null, cv);
        if(result == -1)
        {
            Toast.makeText(context, "FAILED", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "SUCCESS", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor readAllData()
    {
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null)
        {
            cursor = db.rawQuery(query, null);
        }

        return cursor;
    }

    void updateData(String row_id, String name, String location, String sn) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(OBJECT_NAME, name);
        cv.put(OBJECT_LOCATION, location);
        cv.put(COLUMN_SN, sn);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Failed to Update.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Successful update!", Toast.LENGTH_SHORT).show();
        }
    }

    void deleteRow(String row_id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if (result == -1)
        {
            Toast.makeText(context, "Delete failed!", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show();
        }
    }
}
