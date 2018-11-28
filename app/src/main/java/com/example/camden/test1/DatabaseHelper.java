package com.example.camden.test1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "stitp.db";
    public static final String TABLE_NAME = "stitp_table";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "PROGRAM_NAME";
    public static final String COL_3 = "HOST_NAME";
    public static final String COL_4 = "PROGRAM_INFO";
    public static final String COL_5 = "HOST_TEL";
    public static final String COL_6 = "TUTOR_NAME";
    public static final String COL_7 = "PARTNERS";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        SQLiteDatabase db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table stitp_table (ID INTEGER PRIMARY KEY AUTOINCREMENT, PROGRAM_NAME TEXT, HOST_NAME TEXT, PROGRAM_INFO TEXT,HOST_TEL INTEGER, TUTOR_NAME TEXY, PARTNERS TEXT)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {


        String sql = "DROP TABLE IF EXISTS " + TABLE_NAME;
        db.execSQL(sql);
        onCreate(db);

    }


    public boolean insertData(String pgName,String hostName,String pgInfo,String hostTel,String tutorName,String partners){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2,pgName);
        contentValues.put(COL_3,hostName);
        contentValues.put(COL_4,pgInfo);
        contentValues.put(COL_5,hostTel);
        contentValues.put(COL_6,tutorName);
        contentValues.put(COL_7,partners);
        long result =  db.insert(TABLE_NAME, null, contentValues);
        if (result == -1)
            return false;
        else
            return true;


    }


    public Cursor getAllData(){

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res =  db.rawQuery("select  * from "+ TABLE_NAME,null);
        return res;
    }

    public boolean updateData(String ID,String pgName,String hostName,String pgInfo,String hostTel,String tutorName,String partners){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1,ID);
        contentValues.put(COL_2,pgName);
        contentValues.put(COL_3,hostName);
        contentValues.put(COL_4,pgInfo);
        contentValues.put(COL_5,hostTel);
        contentValues.put(COL_6,tutorName);
        contentValues.put(COL_7,partners);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[]{ ID });
        return true;

    }


    public Integer deleteData


}
