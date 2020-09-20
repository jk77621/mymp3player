package com.example.mymp3project;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class MyDBHelper extends SQLiteOpenHelper {
    private Context context;
    private SQLiteDatabase sqLiteDatabase;

    public MyDBHelper(Context context, String dbName){
        //나의 데이터베이스를 생성한것이다. 기존에 내용이 있으면
        // 다시 만들지 않는다.
        super(context, dbName, null, 1);
        this.context = context;
    }

    //테이블을 생성한다.
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table myTBL(picture Integer primary key, title char(20), name char(20));");
    }

    //테이블을 삭제한다.
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("drop table if exists myTBL");
        onCreate(sqLiteDatabase);
    }



    public boolean insertMyTBL(MusicData musicData){
        boolean returnValue = false;
        try {
            sqLiteDatabase = this.getWritableDatabase();
            int mp3Picture = musicData.getMp3Picture();
            String title = musicData.getMp3Title();
            String name = musicData.getMp3Singer();

            String queryString = "insert into myTBL values(" + mp3Picture + ", '" + title + "', '" + name + "');";
            sqLiteDatabase.execSQL(queryString);
            returnValue = true;
        }catch(SQLException e){
            Log.d("database", e.getMessage());
            returnValue = false;
        }finally {
            sqLiteDatabase.close();
        }
        return returnValue;
    }

    public ArrayList<MusicData> selectMyTBL(){
        ArrayList<MusicData> arrayList = new ArrayList<MusicData>();

        sqLiteDatabase = this.getReadableDatabase();
        Cursor cursor = sqLiteDatabase.rawQuery("select * from myTBL;", null);

        while(cursor.moveToNext()){
            MusicData musicData = new MusicData(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
            arrayList.add(musicData);
        }//end of while

        cursor.close();
        sqLiteDatabase.close();

        return arrayList;
    }

}
