package com.miyuue.acctoolkit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class UPCDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME = "upc.db";
    private static final int VERSION = 1;

    private static final class UPCTable {
        private static final String TABLE = "upcs";
        private static final String COL_UPC = "upc";
        private static final String COL_NAME = "name";
    }

    public UPCDatabase(Context context) { super(context, DB_NAME, null, VERSION); }

    // Is called when the DB file doesn't exist yet.
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " +
                UPCTable.TABLE + " (" +
                UPCTable.COL_UPC + " integer primary key, " +
                UPCTable.COL_NAME + " text) "
                );
    }

    // Is called when the DB file is an older version than what was requested.
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + UPCTable.TABLE);
        onCreate(db);
    }

    // Adds an entry to the UPC table
    public boolean addUPC(UPC upc) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UPCTable.COL_UPC, upc.getUPC());
        values.put(UPCTable.COL_NAME, upc.getName());

        long newEntryID = db.insert(UPCTable.TABLE, null, values);

        db.close();
        return (newEntryID != -1);
    }

    // Removes an entry in the UPC table (if it exists)
    public boolean removeUPC(UPC upc) {
        SQLiteDatabase db = getWritableDatabase();

        int rowsDeleted = db.delete(UPCTable.TABLE, UPCTable.COL_UPC + " = ?",
                new String[] { Long.toString(upc.getUPC()) });

        db.close();
        return (rowsDeleted > 0);
    }

    public ArrayList<UPC> getAllUPCs() {
        SQLiteDatabase db = getReadableDatabase();

        String query = "select * from " + UPCTable.TABLE;
        Cursor cursor = db.rawQuery(query, null);

        ArrayList<UPC> upcList = new ArrayList<>();
        if (cursor.moveToFirst()) {
            do {
                UPC upc = new UPC(cursor.getString(2), cursor.getLong(1));
                upcList.add(upc);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return upcList;
    }
}
