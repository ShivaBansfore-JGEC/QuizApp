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

public class AdminAccount extends AppCompatActivity {
EditText t1,t2;
Button b1;
TextInputLayout l1,l2;
MyDatabaseHelper3 db3=new MyDatabaseHelper3(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_account);
        t1=(EditText)findViewById(R.id.admin_name);
        t2=(EditText)findViewById(R.id.admin_pass);
        b1=(Button)findViewById(R.id.admin_ac);
        l1=(TextInputLayout)findViewById(R.id.inputLayoutName);
        l2=(TextInputLayout)findViewById(R.id.inputLayoutPass);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean inValid=false;
                if (t1.getText().toString().isEmpty())
                {
                    l1.setError("user name is mandatory");
                    inValid=false;
                }else{
                    l1.setErrorEnabled(true);
                    if (t2.getText().toString().length()<8)
                    {
                        l2.setError("minimum 8 characters is required");
                        inValid=false;
                    }else {
                        l2.setErrorEnabled(true);
                        String sname=t1.getText().toString();
                        String spass=t2.getText().toString();
                        boolean result=db3.insertData(sname,spass);
                        if (result==true)
                        {
                            Toast.makeText(getApplicationContext(),"successful",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(),"successful",Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            }
        });

    }
}
class  MyDatabaseHelper3 extends SQLiteOpenHelper
{
    private static final String DATABASE_NAME="Admin.db";
    private static final String TABLE_NAME="admin_table";
   public static final String col1="ID";
    public static final String col2="name";
    public static final String col3="password";
    MyDatabaseHelper3(Context context)
    {
        super(context,DATABASE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("Create table "+TABLE_NAME+"(name text,password text)");
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