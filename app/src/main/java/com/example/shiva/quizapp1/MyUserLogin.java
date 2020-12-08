package com.example.shiva.quizapp1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MyUserLogin extends AppCompatActivity {
EditText t1,t2,t3;
Button button;
TextInputLayout l1,l2;
MyDatabaseHelper1 db=new MyDatabaseHelper1(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_user_login);
       // t1=(EditText)findViewById(R.id.user_id);
        t2=(EditText)findViewById(R.id.name);
        t3=(EditText)findViewById(R.id.pass);
        l1=(TextInputLayout)findViewById(R.id.inputLayoutName);
        l2=(TextInputLayout)findViewById(R.id.inputLayoutP);
        button=(Button)findViewById(R.id.user_create);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean inValid=false;
                if (t2.getText().toString().isEmpty())
                {
                    l1.setError("user name is mandatory");
                    inValid=false;
                }else {
                    l1.setErrorEnabled(true);
                    if (t3.getText().toString().length() < 8) {
                        l2.setError("minimum 8 characters is required");
                        inValid = false;
                    } else {
                        l2.setErrorEnabled(true);
                        String sname=t2.getText().toString();
                        String spass=t3.getText().toString();
                        boolean res=db.insertData(sname,spass);
                        if (res==true)
                        {
                            Toast.makeText(MyUserLogin.this,"insertion successful",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(MyUserLogin.this,"failed",Toast.LENGTH_SHORT).show();
                        }

                    }
                }

            }
        });

    }
}
class  MyDatabaseHelper1 extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME="Student.db";
    private static final String TABLE_NAME="student_table";
    public static final String col1="ID";
    public static final String col2="name";
    public static final String col3="password";
    MyDatabaseHelper1(Context context)
    {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create table "+TABLE_NAME+"(ID INTEGER PRIMARY KEY AUTOINCREMENT,name text,password text)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" Drop table if exists " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertData(String name, String password) {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(col2,name);
        contentValues.put(col3,password);
        long result=db.insert(TABLE_NAME, null, contentValues);
        if (result==-1)
        {
            return false;
        }else{
            return true;
        }

    }

    public Cursor getData()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor res=db.rawQuery("SELECT * FROM "+TABLE_NAME,null);
        return res;
    }



}
