package com.example.shiva.quizapp1;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

public class MyScore extends AppCompatActivity {
TextView txt;
TextView tv_correct,tv_wrong;
Window window;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_score);

        if(Build.VERSION.SDK_INT>=21){
            window=this.getWindow();
            window.setStatusBarColor(this.getResources().getColor(R.color.colorStatusBar));
        }
        txt=(TextView)findViewById(R.id.score);
        tv_correct=(TextView) findViewById(R.id.tv_correct);
        tv_wrong=(TextView)findViewById(R.id.tv_wrong);
        Intent intent=getIntent();
        int sscore=intent.getIntExtra("score",0);
        int _correct=intent.getIntExtra("correct_ans",0);
        int _wrong=intent.getIntExtra("wrong_ans",0);
        txt.setText(""+sscore);
        tv_correct.setText(""+_correct);
        tv_wrong.setText(""+_wrong);
    }
    public void onBackPressed() {

        finish();
        Intent intent = new Intent(getApplicationContext(),QuizTopic.class);
        startActivity(intent);
    }
}
