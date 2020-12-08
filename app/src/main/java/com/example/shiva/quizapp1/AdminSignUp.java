package com.example.shiva.quizapp1;

import android.content.Intent;
import android.database.Cursor;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class AdminSignUp extends AppCompatActivity {
EditText t1,t2;
Button b;
TextInputLayout l1,l2;
MyDatabaseHelper3 db3=new MyDatabaseHelper3(this);
Cursor res;
String myPass;
ArrayList<B> val1=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_sign_up);
        t1=(EditText)findViewById(R.id.admin_sign_name);
        t2=(EditText)findViewById(R.id.admin_sign_pass);
        b=(Button)findViewById(R.id.admin_sign);
        l1=(TextInputLayout)findViewById(R.id.inputLayoutName);
        l2=(TextInputLayout)findViewById(R.id.inputLayoutPass);
        res=db3.getData();
        if (res!=null && res.getCount()>0)
        {

            while (res.moveToNext())
            {
               B current=new B(res.getString(0),res.getString(1));
               val1.add(current);
            }

        }
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean inValid=false;
                if (t1.getText().toString().isEmpty())
                {
                    l1.setError("user name is mandatory");
                    inValid=false;
                }else {
                    l1.setErrorEnabled(true);
                    if (t2.getText().toString().length() < 8) {
                        l2.setError("minimum 8 characters is required");
                        inValid = false;
                    } else {
                        l2.setErrorEnabled(true);
                        for (int i=0;i<val1.size();i++)
                        {
                            myPass=val1.get(i).pass;
                        }
                        String sPass=t2.getText().toString();
                        if (sPass.equals(myPass))
                        {
                            Toast.makeText(getApplicationContext(),"congratulation! you are sign in",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                            startActivity(intent);
                        }else {
                            Toast.makeText(getApplicationContext(),"Incorrect username and password",Toast.LENGTH_SHORT).show();
                        }

                    }
                }

            }
        });


    }
}
class B
{
    String name;
    String pass;
    B(String name,String pass)
    {
        this.name=name;
        this.pass=pass;
    }
}