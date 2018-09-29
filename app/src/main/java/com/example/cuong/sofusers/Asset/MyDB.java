package com.example.cuong.sofusers.Asset;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.List;

public class MyDB extends SQLiteOpenHelper {

    private static final String DB_NAME="SOFUSERS";
    private static final int DB_VERSION=1;
    private static final String DB_TABLE="Marked";
    private static final String COL_USER_ID="User_Id";
    private Context context;

    private static final String CREATQUERY="CREATE TABLE "+DB_TABLE+"("+COL_USER_ID+" TEXT)";


    public MyDB(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        this.context=context;
        Log.d("MyDB","NEW");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATQUERY);
        Log.d("MyDB","CREATED");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void addUserId(String userId){
        SQLiteDatabase db=this.getWritableDatabase();
        if(getUserId(userId)==false){
            ContentValues values=new ContentValues();
            values.put(COL_USER_ID,userId);
            db.insert(DB_TABLE,null,values);
            db.close();
        }
    }

    public Boolean getUserId(String userId){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+ DB_TABLE+" WHERE "+COL_USER_ID+"=?",new String[]{userId});
        if(cursor.getCount()>=1){
            cursor.moveToFirst();
            cursor.close();
            return true;
        }
        cursor.close();
        return false;
    }

    public void getUserIdList(List<String> list){
        String query="SELECT * FROM "+DB_TABLE;
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor cursor=db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do {
                list.add(cursor.getString(0));
            }
            while (cursor.moveToNext());
        }
    }

    public void deleteUserId(String userId){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(DB_TABLE,COL_USER_ID+"='" + userId +"'",null);
        db.close();
    }
}
