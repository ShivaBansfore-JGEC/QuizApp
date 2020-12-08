package com.example.shiva.quizapp1;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import java.util.ArrayList;

public class QuizTopic extends AppCompatActivity {
RecyclerView recyclerView;
private ArrayList<rv_model> list=new ArrayList<>();
rv_category adapter;
ImageView add_ques;

String topic[]={"History","Geography","General Science","Current Affairs","Misllaneous"};
int image[]={R.drawable.history,R.drawable.geo,R.drawable.science,R.drawable.newspaper,R.drawable.history_demo};
Window window;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_topic);

        if(Build.VERSION.SDK_INT>=21){
            window=this.getWindow();
            window.setStatusBarColor(this.getResources().getColor(R.color.colorStatusBar));
        }
        recyclerView=findViewById(R.id.rv_category);
        add_ques=(ImageView)findViewById(R.id.add_q_img);
        adapter=new rv_category(this,list);
        recyclerView.setAdapter(adapter);
        add_ques.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
       // GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        initialize();
        adapter.setOnItemClickListener(new rv_category.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
   switch (position)
   {
       case 0:
           Intent intent_his=new Intent(QuizTopic.this,UserLogin.class);
           intent_his.putExtra("category",0);
           startActivity(intent_his);

           break;
       case 1:
           Intent intent_geo=new Intent(QuizTopic.this,UserLogin.class);
           intent_geo.putExtra("category",1);
           startActivity(intent_geo);
           break;
       case 2:
           Intent intent_gs=new Intent(QuizTopic.this,UserLogin.class);
           intent_gs.putExtra("category",2);
           startActivity(intent_gs);
           break;
       case 3:
           Intent intent_ca=new Intent(QuizTopic.this,UserLogin.class);
           intent_ca.putExtra("category",3);
           startActivity(intent_ca);
           break;
       case 4:
           Intent intent_mis=new Intent(QuizTopic.this,UserLogin.class);
           intent_mis.putExtra("category",4);
           startActivity(intent_mis);
           break;


   }
            }
        });
    }
    public void initialize()
    {
        for (int i=0;i<topic.length;i++)
        {
            rv_model model=new rv_model(topic[i],image[i]);
            list.add(model);
        }
    }
    public void onBackPressed() {

        finish();
        Intent intent = new Intent(getApplicationContext(),MyHomePage.class);
        startActivity(intent);
    }

}
