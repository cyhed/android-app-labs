package com.example.lab4fragmentsandsqlite.DB;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DBHelper extends SQLiteOpenHelper {
    private static final String DB_TABLE = "notes";
    public DBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + DB_TABLE + "("
                + "id integer primary key autoincrement,"
                + "name text,"
                + "description text"
                + ");");
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    // Удаляем старую таблицу и создаём новую68
        db.execSQL("DROP TABLE IF IT EXISTS " + DB_TABLE);
        onCreate(db);
    }
}
