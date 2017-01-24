package com.example.raj.twodice;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    ImageView Iv,Iv2;
    int totalYourScore=0,totalCompScore=0,currentScore=0,turnScore=0;
    Button roll,reset,hold;
    int UPPER_LIMIT=0;
    Animation anim1,anim2;
    int imgArray[]={R.drawable.dice1,R.drawable.dice2,R.drawable.dice3,R.drawable.dice4,R.drawable.dice5,R.drawable.dice6};
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main2);
        tv=(TextView)findViewById(R.id.tv);
        Iv=(ImageView)findViewById(R.id.IV);
        Iv2=(ImageView)findViewById(R.id.IV2);
        roll=(Button)findViewById(R.id.roll);
        anim1 = new TranslateAnimation(00.0f,200.0f,0.0f,0.0f);
        anim1.setDuration(400);
        anim2 = new TranslateAnimation(00.0f,-200.0f,0.0f,0.0f);
        anim2.setDuration(400);
        reset=(Button)findViewById(R.id.reset);
        hold=(Button)findViewById(R.id.hold);
        roll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rollMethod();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetMethod();
            }
        });

        hold.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holdMethod();
            }
        });
    }
    public void rollMethod(){
        Random r=new Random();
        final int rand=r.nextInt(6)+1;
        Random ra=new Random();
        final int rand1=ra.nextInt(6)+1;
        Iv.setImageResource(imgArray[rand-1]);
        Iv.setAnimation(anim1);
        Iv2.setImageResource(imgArray[rand1-1]);
        Iv2.setAnimation(anim1);
        Handler H =new Handler();
        H.postDelayed(new Runnable() {
            @Override
            public void run() {
                enableButton();
                if(rand==1 && rand1==1){
                    currentScore=0;
                    totalYourScore=0;
                    compTurn();
                }else if(rand==1 ){
                    currentScore=0;
                    compTurn();
                }else if(rand1==1 ){
                    currentScore=0;
                    compTurn();
                }else if(rand==rand1 ){
                    hold.setEnabled(false);
                    currentScore=currentScore+rand+rand1;
                }else{
                    currentScore=currentScore+rand+rand1;
                    tv.setText("Your Score :"+totalYourScore+" computer score : "+totalCompScore+" User Turn Score : "+turnScore+" Current Score : "+currentScore);

                    if((currentScore+totalYourScore)>=100){
                        Toast.makeText(MainActivity.this, "User Win", Toast.LENGTH_SHORT).show();
                        resetMethod();
                    }
                }
                tv.setText("Your Score :"+totalYourScore+" computer score : "+totalCompScore+" User Turn Score : "+turnScore+" Current Score : "+currentScore);

            }
        },1000);
    }
    public void resetMethod(){
        totalYourScore=0;
        totalCompScore=0;
        currentScore=0;
        finishCompTurn();
        turnScore=0;
        enableButton();
        tv.setText("Your score: 0 computer score: 0");
    }
    public void holdMethod(){
        turnScore++;
        totalYourScore+=currentScore;
        currentScore=0;
        tv.setText("Your Score : "+totalYourScore+" computer score : "+totalCompScore+" Current Score : "+currentScore);
        compTurn();
    }
    private void enableButton(){
        roll.setEnabled(true);
        hold.setEnabled(true);
    }
    private void disableButton(){
        roll.setEnabled(false);
        hold.setEnabled(false);
    }
    public void compTurn2(){
        if(currentScore<=20){
            Random r=new Random();
            final int rand=r.nextInt(6)+1;
            Iv.setImageResource(imgArray[rand-1]);
            disableButton();
            Handler H =new Handler();
            H.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(rand==1){
                        currentScore=0;
                        finishCompTurn();
                    }else{
                        currentScore+=rand;
                        tv.setText("Your Score :"+totalYourScore+" computer score : "+totalCompScore+" User Turn Score : "+turnScore+" Current Score : "+currentScore);

                        if((currentScore+totalCompScore)>=100){
                            Toast.makeText(MainActivity.this, "computer win!!", Toast.LENGTH_SHORT).show();
                            turnScore=0;
                            resetMethod();
                        }
                        compTurn();
                    }


                }
            },1000);
        }else {
            finishCompTurn();
        }
    }
    public void finishCompTurn(){
        totalCompScore+=currentScore;
        tv.setText("Your Score :"+totalYourScore+" computer score : "+totalCompScore+" User Turn Score : "+turnScore+" Current Score : "+currentScore);
        currentScore=0;
        turnScore++;
        enableButton();
    }



    public void compTurn(){
        if(currentScore<=20){
            Random r=new Random();
            final int rand=r.nextInt(6)+1;
            Random ra=new Random();
            final int rand1=ra.nextInt(6)+1;
            Iv.setImageResource(imgArray[rand-1]);
            Iv2.setImageResource(imgArray[rand1-1]);
            Iv.setAnimation(anim2);
            Iv2.setAnimation(anim2);
            disableButton();
            Handler H =new Handler();
            H.postDelayed(new Runnable() {
                @Override
                public void run() {
                    if(rand==1 && rand1==1){
                        currentScore=0;
                        totalCompScore=0;
                        turnScore++;
                        finishCompTurn();
                    }else if(rand==1 ){
                        currentScore=0;
                        finishCompTurn();
                    }else if(rand1==1 ){
                        currentScore=0;
                        finishCompTurn();
                    }else if(rand==rand1 ){
                        currentScore=currentScore+rand+rand1;
                        tv.setText("Your Score :"+totalYourScore+" computer score : "+totalCompScore+" User Turn Score : "+turnScore+" Current Score : "+currentScore);
                        compTurn();
                    }else{
                        currentScore=currentScore+rand+rand1;
                        tv.setText("Your Score :"+totalYourScore+" computer score : "+totalCompScore+" User Turn Score : "+turnScore+" Current Score : "+currentScore);

                        if((currentScore+totalCompScore)>=100){
                            Toast.makeText(MainActivity.this, "You lose!!", Toast.LENGTH_SHORT).show();
                            turnScore=0;
                            resetMethod();
                        }
                        compTurn();
                    }


                }
            },1000);
        }else {
            finishCompTurn();
        }
    }
}
