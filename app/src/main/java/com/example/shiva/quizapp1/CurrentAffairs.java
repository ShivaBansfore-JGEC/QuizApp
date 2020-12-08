package com.example.shiva.quizapp1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
public class CurrentAffairs extends SQLiteOpenHelper {
    private static final String DATABASE_NAME="CurrentAffairDb.db";
    private static final String TABLE_NAME="user";
    public static final String col_question="question";
    public static final String col_option1="option1";
    public static final String col_option2="option2";
    public static final String col_option3="option3";
    public static final String col_option4="option4";
    public static final String col_answer="answer";

    public CurrentAffairs(Context context)
    {
        super(context,DATABASE_NAME,null,1);
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table "+TABLE_NAME+"(question text,option1 text,option2 text,option3 text,option4 text,answer text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public boolean insertData(String question,String option1,String option2,String option3,String option4,String answer)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(col_question,question);
        values.put(col_option1,option1);
        values.put(col_option2,option2);
        values.put(col_option3,option3);
        values.put(col_option4,option4);
        values.put(col_answer,answer);
        long data=db.insert(TABLE_NAME,null,values);
        if (data==-1)
        {
            return false;
        }else {
            return true;
        }
    }
    public Cursor getData1()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String query="select * from "+TABLE_NAME;
        Cursor cursor=db.rawQuery(query,null);
        return cursor;
    }
    public Cursor getData()
    {
        SQLiteDatabase db=this.getReadableDatabase();

        Cursor res=db.rawQuery("select * from "+TABLE_NAME,null);
        return res;
    }
}

