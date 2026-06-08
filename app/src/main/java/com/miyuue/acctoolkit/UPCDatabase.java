package com.miyuue.acctoolkit;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UPCDatabase extends SQLiteOpenHelper {
    private static final String DB_NAME = "upc.db";
    private static final int VERSION = 1;

    private static final class UPCTable
    {
        private static final String TABLE = "upcs";
        private static final String COL_ID = "_id";
        private static final String COL_NAME = "name";
        private static final String COL_UPC = "upc";
    }

    public UPCDatabase(Context context) { super(context, DB_NAME, null, VERSION); }

    @Override
    public void onCreate(SQLiteDatabase db)
    {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {

    }
}
