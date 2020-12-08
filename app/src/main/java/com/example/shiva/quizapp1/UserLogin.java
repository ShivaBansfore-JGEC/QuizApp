package com.example.shiva.quizapp1;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class UserLogin extends AppCompatActivity{

    private static final long COUNTDOWN_IN_MILIS=30000;
Button b1,b2,b3,b4;
TextView t1,t2,timer_tv;
TextView point;
Cursor res;
Toolbar toolbar;
 int correct_ans=0,wrong_ans=0;
int i=0;
private int mScore=0;
private int tot_score=0;
private String manswer;
MyDatabaseHelper db1=new MyDatabaseHelper(this);
History geoDb=new History(this);
GeneralScience gs=new GeneralScience(this);
CurrentAffairs crntaff=new CurrentAffairs(this);
Misllaneous msl=new Misllaneous(this);
ArrayList<MyData> data1=new ArrayList<>(3);
Window window;

private ColorStateList textColorDefault;
private CountDownTimer countDownTimer;
private long timeLeftInMilis;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        if(Build.VERSION.SDK_INT>=21){
            window=this.getWindow();
            window.setStatusBarColor(this.getResources().getColor(R.color.colorStatusBar));
        }
       b1=(Button)findViewById(R.id.option1);
       b2=(Button)findViewById(R.id.option2);
       b3=(Button)findViewById(R.id.option3);
       b4=(Button)findViewById(R.id.option4);
       t1=(TextView)findViewById(R.id.txt1);
       t2=(TextView)findViewById(R.id.mscore);
       timer_tv=(TextView)findViewById(R.id.tv_timer);
        final MediaPlayer mediaPlayer_wrong=MediaPlayer.create(this,R.raw.wrong_ans);
        final MediaPlayer mediaPlayer_wright=MediaPlayer.create(this,R.raw.wright_ans_sound1);


       point=(TextView) findViewById(R.id.points);

       //recieving category position
        Intent intent=getIntent();
        int cat_pos=intent.getIntExtra("category",0);

        switch (cat_pos){
            case 0:
                res=db1.getData1();
                break;
            case 1:
                res=geoDb.getData1();
                break;
            case 2:
                res=gs.getData1();
                break;
            case 3:
                res=crntaff.getData1();
                break;
            case 4:
                res=msl.getData1();
                break;
        }

        //res=db1.getData1();
        if (res!=null && res.getCount()>0)
        {

            while (res.moveToNext())
            {
                MyData d=new MyData(res.getString(0),res.getString(1),res.getString(2),res.getString(3),res.getString(4),res.getString(5));
                data1.add(d);
            }

        }
      updateQuestion();
      updateScore(mScore);
      b1.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              countDownTimer.cancel();
              if (b1.getText().equals(manswer))
              {
                  mediaPlayer_wright.start();
                  Toast.makeText(getApplicationContext(),"correct",Toast.LENGTH_SHORT).show();
                  correct_ans++;
                  mScore++;
                  tot_score+=10;
                  updateScore(mScore);
                  b1.setBackgroundColor(Color.parseColor("#28db89"));
              }else {
                  mediaPlayer_wrong.start();
                  Toast.makeText(getApplicationContext(),"Incorrect",Toast.LENGTH_SHORT).show();
                  b1.setBackgroundColor(Color.RED);
                  wrong_ans++;
              }
              new Handler().postDelayed(new Runnable() {
                  @Override
                  public void run() {
                      updateQuestion();
                  }
              },500);
          }
      });
      b2.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              countDownTimer.cancel();

              if (b2.getText().equals(manswer))
              {
                  mediaPlayer_wright.start();
                  Toast.makeText(getApplicationContext(),"correct",Toast.LENGTH_SHORT).show();
                  mScore++;
                  correct_ans++;
                  tot_score+=10;
                  updateScore(mScore);
                  b2.setBackgroundColor(Color.parseColor("#28db89"));
              }else {
                  mediaPlayer_wrong.start();
                  Toast.makeText(getApplicationContext(),"Incorrect",Toast.LENGTH_SHORT).show();
                  b2.setBackgroundColor(Color.RED);
                  wrong_ans++;
              }
              new Handler().postDelayed(new Runnable() {
                  @Override
                  public void run() {
                      updateQuestion();
                  }
              },500);
          }

      });
      b3.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              countDownTimer.cancel();
              if (b3.getText().equals(manswer))
              {
                  mediaPlayer_wright.start();
                  Toast.makeText(getApplicationContext(),"correct",Toast.LENGTH_SHORT).show();
                  mScore++;
                  correct_ans++;
                  tot_score+=10;
                  updateScore(mScore);
                  b3.setBackgroundColor(Color.parseColor("#28db89"));
              }else {
                  mediaPlayer_wrong.start();
                  Toast.makeText(getApplicationContext(),"Incorrect",Toast.LENGTH_SHORT).show();
                  b3.setBackgroundColor(Color.RED);
                  wrong_ans++;
              }
              new Handler().postDelayed(new Runnable() {
                  @Override
                  public void run() {
                      updateQuestion();
                  }
              },500);
          }
      });
      b4.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              countDownTimer.cancel();
              if (b4.getText().equals(manswer))
              {
                  mediaPlayer_wright.start();
                  Toast.makeText(getApplicationContext(),"correct",Toast.LENGTH_SHORT).show();
                  mScore++;
                  correct_ans++;
                  tot_score+=10;
                  updateScore(mScore);
                  b4.setBackgroundColor(Color.parseColor("#28db89"));
              }else {
                  mediaPlayer_wrong.start();
                  Toast.makeText(getApplicationContext(),"Incorrect",Toast.LENGTH_SHORT).show();
                  b4.setBackgroundColor(Color.RED);
                  wrong_ans++;
              }
              new Handler().postDelayed(new Runnable() {
                  @Override
                  public void run() {
                      updateQuestion();
                  }
              },500);
          }
      });
    }
    public int getLenth()
    {
        return data1.size();
    }
    public void updateQuestion()
    {
        String questSize[];
        if (i!=getLenth())
        {
            t1.setText(data1.get(i).question);
             b1.setText(data1.get(i).choice1);
             b2.setText(data1.get(i).choice2);
             b3.setText(data1.get(i).choice3);
             b4.setText(data1.get(i).choice4);
            manswer=data1.get(i).answer;
             i++;
             b1.setBackgroundColor(Color.WHITE);
            b2.setBackgroundColor(Color.WHITE);
            b3.setBackgroundColor(Color.WHITE);
            b4.setBackgroundColor(Color.WHITE);
            timeLeftInMilis=COUNTDOWN_IN_MILIS;
            startCountDown();
        }else
            {
            Toast.makeText(getApplicationContext(),"no question in library",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(this,MyScore.class);
                intent.putExtra("score",tot_score);
                intent.putExtra("correct_ans",correct_ans);
                intent.putExtra("wrong_ans",wrong_ans);
                startActivity(intent);
        }
    }
    private void startCountDown(){
        countDownTimer=new CountDownTimer(timeLeftInMilis,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeftInMilis=millisUntilFinished;
                UpdateCountDownText();
            }

            @Override
            public void onFinish() {
                timeLeftInMilis=0;
                UpdateCountDownText();
                updateQuestion();

            }
        }.start();
    }
    public void updateScore(int points)
    {

        t2.setText(""+mScore+"/"+getLenth());
        point.setText(""+(points*10));
    }
    private void UpdateCountDownText(){
        int minute=(int) (timeLeftInMilis/1000)/60;
        int seconds=(int)(timeLeftInMilis/1000)%60;
        String timFormatted=String.format(Locale.getDefault(),"%02d:%02d",minute,seconds);
        timer_tv.setText(timFormatted);
        if(timeLeftInMilis<1000){
            timer_tv.setTextColor(Color.RED);
        }else{
            timer_tv.setTextColor(Color.WHITE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(countDownTimer!=null){
            countDownTimer.cancel();
        }
    }
    public void onBackPressed() {

        finish();
        Intent intent = new Intent(getApplicationContext(),QuizTopic.class);
        startActivity(intent);
    }
}
class MyData
{
    String question;
    String choice1,choice2,choice3,choice4;
    String answer;
    MyData(String quest,String ch1,String ch2,String ch3,String ch4,String ans)
    {
        this.question=quest;
        this.choice1=ch1;
        this.choice2=ch2;
        this.choice3=ch3;
        this.choice4=ch4;
        this.answer=ans;
    }

}