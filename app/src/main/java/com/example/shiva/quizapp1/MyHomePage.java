package com.example.shiva.quizapp1;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class MyHomePage extends AppCompatActivity {
Button button,b2;
MyDatabaseHelper1 db1=new MyDatabaseHelper1(this);
EditText e1,e2;
Cursor res;
String mypass;
int i=0;
Toolbar toolbar;
ImageView img;
private String pass;
TextInputLayout inputLayoutName,inputLayoutPass;

    ArrayList<A> val=new ArrayList<>();
    Window window;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_home_page);

        if(Build.VERSION.SDK_INT>=21){
            window=this.getWindow();
            window.setStatusBarColor(this.getResources().getColor(R.color.colorStatusBar));
        }

        button=(Button)findViewById(R.id.login);
        e1=(EditText)findViewById(R.id.user_pass);
        e2=(EditText)findViewById(R.id.user_name);
        img=(ImageView)findViewById(R.id.img);
        inputLayoutName=(TextInputLayout)findViewById(R.id.inputLayoutName);
        inputLayoutPass=(TextInputLayout)findViewById(R.id.inputLayoutPass);


        b2=(Button)findViewById(R.id.account);
        res=db1.getData();
        if (res!=null && res.getCount()>0)
        {

            while (res.moveToNext())
            {
                A current=new A(res.getString(0),res.getString(1),res.getString(2));
                val.add(current);

            }

        }
    b2.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent=new Intent(getApplicationContext(),MyUserLogin.class);
            startActivity(intent);
        }
    });
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean inValid=true;

               String pass=e1.getText().toString();
              for (i=0;i<val.size();i++)
              {
                  mypass=val.get(i).pass;
              }
                if (e2.getText().toString().isEmpty())
                {
                    inputLayoutName.setError("user name is mandatory");
                    inValid=false;

                }else {
                    inputLayoutName.setErrorEnabled(false);
                    if (e1.getText().toString().trim().length()<8)
                    {
                        inputLayoutPass.setError("minimum 8 characters are required");
                        inValid=false;
                    }else {
                        inputLayoutPass.setErrorEnabled(false);
                        if (pass.equals(mypass))
                        {   e1.setText("");
                            e2.setText("");
                            Toast.makeText(getApplicationContext(),"congratulation..! you are sign in",Toast.LENGTH_SHORT).show();
                            Intent intent1=new Intent(getApplicationContext(),QuizTopic.class);
                            startActivity(intent1);
                        }else {
                            Toast.makeText(getApplicationContext(),"Incorrect username and password",Toast.LENGTH_SHORT).show();
                        }
                    }

                }


            }
        });
    }
    public void match()
    {
        if (i<val.size())
        {
            pass=val.get(i).pass;
            i++;
        }
        if (e1.getText().toString().equals(pass))
        {
            Toast.makeText(getApplicationContext(),"correct",Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(),"Incorrect",Toast.LENGTH_SHORT).show();
        }
    }
}
class A
{
    String id;
    String name;
    String pass;
    A(String id,String name,String pass)
    {
        this.id=id;
        this.name=name;
        this.pass=pass;
    }
}
