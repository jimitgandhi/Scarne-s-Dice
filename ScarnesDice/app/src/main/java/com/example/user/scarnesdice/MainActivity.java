package com.example.user.scarnesdice;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView yourTScore;
    TextView compTScore;
    TextView yourScore;
    TextView compScore;
    Button roll;
    Button hold;
    int yourTurnScore=0;
    int compTurnScore=0;
    int yourOverallScore=0;
    int compOverallScore=0;
    Random random;
    int[] diceImage={R.drawable.dice1,R.drawable.dice2,R.drawable.dice3,R.drawable.dice4,R.drawable.dice5,R.drawable.dice6};
    ImageView dice;

    public void rollIt(View view) {

        random=new Random();
        int val=random.nextInt(6);
        int[] diceImage={R.drawable.dice1,R.drawable.dice2,R.drawable.dice3,R.drawable.dice4,R.drawable.dice5,R.drawable.dice6};
        dice.animate().rotation(dice.getRotation()+3600).setDuration(250);
        dice.setImageResource(diceImage[val]);
        if(val==0) {
            yourTurnScore=0;
            holdIt(view);
        }
        else {
            yourTurnScore+=val+1;
        }
        yourTScore.setText(""+yourTurnScore);

    }

    public void holdIt(View view) {

        yourOverallScore+=yourTurnScore;
        if(yourOverallScore>=100) {
            Toast.makeText(this, "You win!", Toast.LENGTH_SHORT).show();
            resetIt(view);
        }
        else {
            yourTurnScore = 0;
            yourTScore.setText("" + yourTurnScore);
            yourScore.setText("" + yourOverallScore);
            compPlays();
        }
    }

    public void resetIt(View view) {
        yourTurnScore=0;
        compTurnScore=0;
        yourOverallScore=0;
        compOverallScore=0;
        yourTScore.setText(""+yourTurnScore);
        yourScore.setText(""+yourOverallScore);
        compTScore.setText(""+compTurnScore);
        compScore.setText(""+compOverallScore);
    }

    public void compPlays() {
        roll.setEnabled(false);
        hold.setEnabled(false);
        final Handler handler = new Handler();
        random=new Random();
        int noOfPlays=random.nextInt(10);
        int i=0;
        if(compTurnScore<20) {
            int compVal=random.nextInt(6);
            dice.animate().rotation(dice.getRotation()+3600).setDuration(250);
            dice.setImageResource(diceImage[compVal]);
            if(compVal==0) {
                compTurnScore=0;
                compTScore.setText(""+compTurnScore);
            }
            else {
                compTurnScore+=compVal+1;
                compTScore.setText(""+compTurnScore);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        compPlays();
                    }
                }, 1000);
                return;
            }
        }
        compOverallScore+=compTurnScore;
        if(compOverallScore>=100) {
            Toast.makeText(this, "You lose!", Toast.LENGTH_SHORT).show();
            resetIt(dice);
        }
        else {
            compTurnScore = 0;
            compTScore.setText("" + compTurnScore);
            compScore.setText("" + compOverallScore);
        }

        roll.setEnabled(true);
        hold.setEnabled(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dice=(ImageView)findViewById(R.id.imageView);
        yourTScore=(TextView)findViewById(R.id.yourTScore);
        compTScore=(TextView)findViewById(R.id.compTScore);
        yourScore=(TextView)findViewById(R.id.yourScore);
        compScore=(TextView)findViewById(R.id.compScore);
        roll=(Button)findViewById(R.id.roll);
        hold=(Button)findViewById(R.id.hold);
    }
}
