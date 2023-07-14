package com.app.hatim.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class subject_db extends SQLiteOpenHelper {
    public static final String db_name = "college.db";
    public static final String table_name = "student";
    public static final String col1 = "sub_name";
    public  subject_db(Context context)
    {
        super(context,db_name,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+ table_name + "("+col1+" TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists " + table_name);
    }

    public Boolean insertSubject(String subject)
    {
       SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

       //ContentValues -> To store the values in key value format as an array and inserts the value based on key in the table
       ContentValues contentValues = new ContentValues();
       contentValues.put(col1,subject);

       long result = sqLiteDatabase.insert(table_name,null,contentValues);

       //If result is -1 then insert is failed, or if the result if 1 (+1) then insertion is successful
       if(result == -1)
       {
           return false;
       }
       else
       {
           return true;
       }
    }

    //To Read Data from a table
    //To store the data in object format (Array Format)
    public Cursor readData()
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+ table_name,null);
        return cursor;
    }

    public Cursor readSpecificData(String sub)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from "+ table_name + " where "+ col1 + " = '"+ sub + "'", null);
        return cursor;
    }

    public Boolean deleteRecord(String sub)
    {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL("delete from "+ table_name + " where "+ col1 + " = '"+sub+"'");
        return true;
    }




}
