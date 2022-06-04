package com.example.assignment4;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Userdata.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table notekhata(id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, details TEXT, FlagB INTEGER DEFAULT 0, FlagC INTEGER DEFAULT 0);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int ii) {
        DB.execSQL("drop Table if exists notekhata");
    }
    public Boolean insertuserdata(String title, String details){
        SQLiteDatabase DB = this.getReadableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title",title);
        contentValues.put("details",details);
        long result = DB.insert("notekhata", null, contentValues);
        if(result == -1){
            return false;
        }
        else {
            return true;
        }
    }
    public Cursor getdata(){
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery("Select * from notekhata where FlagB=0;",null);
        return cursor;
    }
    public Cursor getdata2(){
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery("Select * from notekhata where FlagB=1 and FlagC=0;",null);
        return cursor;
    }
    public Cursor getdata3(){
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery("Select * from notekhata where FlagB=1 and FlagC=1;",null);
        return cursor;
    }
    public void delete(String tableName, long _id){
        SQLiteDatabase DB = this.getReadableDatabase();
        DB.delete("notekhata","id = "+_id,null);
    }
    public void update1(String tableName, long _id){
        SQLiteDatabase DB = this.getReadableDatabase();
        String strSQL="UPDATE notekhata SET FlagB = 1 WHERE id = "+ _id;
        DB.execSQL(strSQL);
    }
    public void update2(String tableName, long _id){
        SQLiteDatabase DB = this.getReadableDatabase();
        String strSQL="UPDATE notekhata SET FlagC = 1 WHERE id = "+ _id;
        DB.execSQL(strSQL);
    }
}
