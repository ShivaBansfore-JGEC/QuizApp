package com.example.shiva.quizapp1;

import android.content.Intent;
import android.database.Cursor;
import android.os.Build;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
EditText t1,t2,t3,t4,t5,t6;
Button b1,b2;
Toolbar toolbar;
Spinner spinner_cate;
TextView test;
TextInputLayout inputLayoutQ,inputLayoutOp1,inputLayoutOp2,inputLayoutOp3,inputLayoutOp4,inputLayoutAns;
Window window;
int spinner_pos;

//MY DATABASES
MyDatabaseHelper db2=new MyDatabaseHelper(this);
History GeoDb=new History(this);
GeneralScience gs=new GeneralScience(this);
CurrentAffairs cf=new CurrentAffairs(this);
Misllaneous msl=new Misllaneous(this);
//############################################
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(Build.VERSION.SDK_INT>=21){
            window=this.getWindow();
            window.setStatusBarColor(this.getResources().getColor(R.color.colorStatusBar));
        }
        t1=(EditText)findViewById(R.id.question);
        t2=(EditText)findViewById(R.id.opt1);
        t3=(EditText)findViewById(R.id.opt2);
        t4=(EditText)findViewById(R.id.opt3);
        t5=(EditText)findViewById(R.id.opt4);
        t6=(EditText)findViewById(R.id.answer);
        b1=(Button)findViewById(R.id.login);
        b2=(Button)findViewById(R.id.quiz);
        test=(TextView)findViewById(R.id.test);

        //Making our category list
        spinner_cate=(Spinner)findViewById(R.id.spinner_category);
        final List<String> list=new ArrayList<>();
        list.add("Select Category");
        list.add("History");
        list.add("Geography");
        list.add("General Science");
        list.add("Current Affairs");
        list.add("Misllaneous");

        ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_cate.setAdapter(arrayAdapter);
        spinner_cate.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner_cate.setSelection(position);
                spinner_pos=position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //####################################################


        inputLayoutQ=(TextInputLayout)findViewById(R.id.inputLayoutQuestion);
        inputLayoutOp1=(TextInputLayout)findViewById(R.id.inputLayoutOpt1);
        inputLayoutOp2=(TextInputLayout)findViewById(R.id.inputLayoutOpt2);
        inputLayoutOp3=(TextInputLayout)findViewById(R.id.inputLayoutOpt3);
        inputLayoutOp4=(TextInputLayout)findViewById(R.id.inputLayoutOpt4);
        inputLayoutAns=(TextInputLayout)findViewById(R.id.inputLayoutOpt5);

//        toolbar=(Toolbar)findViewById(R.id.toolbar5);
//        toolbar.setTitle("Admin Page");
//        toolbar.setSubtitle("update question here...!");
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean inValid=true;
                if (t1.getText().toString().length()<10)
                {
                    inputLayoutQ.setError("Invalid Question");
                    inValid=false;
                }else {
                    inputLayoutQ.setErrorEnabled(false);
                    if (t2.getText().toString().isEmpty())
                    {
                        inputLayoutOp1.setError("blank input is not accepted");
                        inValid=false;
                    }else {
                        inputLayoutOp1.setErrorEnabled(false);
                        if (t3.getText().toString().isEmpty())
                        {
                            inputLayoutOp2.setError("blank input is not accepted");
                            inValid=false;
                        }else {
                            inputLayoutOp2.setErrorEnabled(false);
                            if (t4.getText().toString().isEmpty())
                            {
                                inputLayoutOp3.setError("blank input is not accepted");
                                inValid=false;
                            }else {
                                inputLayoutOp3.setErrorEnabled(false);
                                if (t5.getText().toString().isEmpty())
                                {
                                    inputLayoutOp4.setError("blank input is not accepted");
                                    inValid=false;
                                }else {
                                    inputLayoutOp4.setErrorEnabled(false);
                                    if (t6.getText().toString().isEmpty())
                                    {
                                        inputLayoutAns.setError("blank input is not accepted");
                                        inValid=false;
                                    }else {
                                        inputLayoutAns.setErrorEnabled(false);
                                        String question=t1.getText().toString();
                                        String option1=t2.getText().toString();
                                        String option2=t3.getText().toString();
                                        String option3=t4.getText().toString();
                                        String option4=t5.getText().toString();
                                        String answer=t6.getText().toString();
                                        String category=list.get(spinner_pos);
                                        boolean result=false;
                                        if(category=="History"){
                                             result=db2.insertData(question,option1,option2,option3,option4,answer);
                                        }else if(category=="Geography"){
                                             result=GeoDb.insertData(question,option1,option2,option3,option4,answer);
                                        }else if (category=="General Science"){
                                            result=gs.insertData(question,option1,option2,option3,option4,answer);
                                        }else if(category=="Current Affairs"){
                                            result=cf.insertData(question,option1,option2,option3,option4,answer);
                                        }else if(category=="Misllaneous"){
                                            result=msl.insertData(question,option1,option2,option3,option4,answer);
                                        }
                                        if (result==true)
                                        {
                                            Toast.makeText(getApplicationContext(),"successful",Toast.LENGTH_SHORT).show();
                                        }else {
                                            Toast.makeText(getApplicationContext(),"failed",Toast.LENGTH_SHORT).show();
                                        }
                                        t1.setText("");
                                        t2.setText("");
                                        t3.setText("");
                                        t4.setText("");
                                        t5.setText("");
                                    }
                                }
                            }
                        }
                    }
                }
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),QuizTopic.class);
                startActivity(intent);
            }
        });

    }
    @Override
    public void onBackPressed() {

        finish();
        Intent intent = new Intent(getApplicationContext(),QuizTopic.class);
        startActivity(intent);
    }
}
