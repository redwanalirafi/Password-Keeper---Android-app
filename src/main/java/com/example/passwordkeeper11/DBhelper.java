package com.example.passwordkeeper11;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBhelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="alldata.db";
    public static final String TABLE_NAME="logdata";
    public static final String COL1="ID";
    public static final String COL2="MAIL";
    public static final String COL3="PASS";


    public DBhelper(Context context) {
        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //sqLiteDatabase.execSQL("CREATE TABLE "+TABLE_NAME+ " ("+COL1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COL2+" TEXT, "+COL3+" TEXT)");
        sqLiteDatabase.execSQL("Create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,MAIL TEXT,PASS TEXT);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String email,String password){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();

        contentValues.put(COL2,email);
        contentValues.put(COL3,password);
        long result = db.insert(TABLE_NAME,null,contentValues);
        if(result==-1){
            return false;
        }else {
            return  true;
        }
    }

    public Cursor getData(){
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery("Select * from "+TABLE_NAME,null);
        return res;
    }

    //Clear
    public void cleardata() {
        SQLiteDatabase db = this.getReadableDatabase();
        String clearDBQuery = "DROP TABLE " + TABLE_NAME;
        db.execSQL(clearDBQuery);
        SQLiteDatabase db1 = this.getReadableDatabase();
        db1.execSQL("Create table " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,MAIL TEXT,PASS TEXT);");
    }

    public boolean deleteTitle(String id)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.delete(TABLE_NAME,COL1 + "=? ", new String[]{id}) > 0;
    }

}
