package my.appcompany.braintest;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView textView1;
    TextView textView;
    TextView textView4;
    Button button1;
    Button button2;
    Button button3;
    Button button4;
    TextView textView5;
    Button button6;
    Button button7;
    int ques;
    MediaPlayer mediaPlayer;
    double percentage;
    String percentage_string;

    char arr[]={'+','-','*'};
    int i;
    int tag[]={1,2,3,4};
    int j;
    int ans;
    int score=0;
    int k;

    Random rand= new Random();

    int operand1,operand2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1 = findViewById(R.id.textView1);
        textView = findViewById(R.id.textView);
        textView4 = findViewById(R.id.textView4);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);
        textView5 = findViewById(R.id.textView5);
        button6 = findViewById(R.id.button6);
        button7 = findViewById(R.id.button7);
        textView1.setVisibility(View.INVISIBLE);
        textView.setVisibility(View.INVISIBLE);
        textView4.setVisibility(View.INVISIBLE);
        button1.setVisibility(View.INVISIBLE);
        button2.setVisibility(View.INVISIBLE);
        button3.setVisibility(View.INVISIBLE);
        button4.setVisibility(View.INVISIBLE);
        textView5.setVisibility(View.INVISIBLE);
        button6.setVisibility(View.INVISIBLE);
        ques=0;
        k=0;

    }

    public void start(View view) {

        textView5.setVisibility(View.INVISIBLE);
        ans=0;
        score=0;
        ques=1;
        textView4.setText(Integer.toString(score)+"/"+Integer.toString(ques));

        button7.setVisibility(View.INVISIBLE);
        textView1.setVisibility(View.VISIBLE);
        textView.setVisibility(View.VISIBLE);
        textView4.setVisibility(View.VISIBLE);
        button1.setVisibility(View.VISIBLE);
        button2.setVisibility(View.VISIBLE);
        button3.setVisibility(View.VISIBLE);
        button4.setVisibility(View.VISIBLE);

        operand1= rand.nextInt(15)+1;
        operand2= rand.nextInt(15)+1;
        i=rand.nextInt(3);
        j=rand.nextInt(4);

        textView.setText(Integer.toString(operand1)+" "+arr[i]+" "+Integer.toString(operand2));
        ++k;


        if(arr[i]=='+')
        ans=operand1+operand2;
        else if(arr[i]=='-')
        ans=operand1-operand2;
        else
        ans=operand1*operand2;

        if(Integer.parseInt(button1.getTag().toString())==tag[j])
            button1.setText(Integer.toString(ans));
        else if(Integer.parseInt(button2.getTag().toString())==tag[j])
            button2.setText(Integer.toString(ans));
        else if(Integer.parseInt(button3.getTag().toString())==tag[j])
             button3.setText(Integer.toString(ans));
        else
             button4.setText(Integer.toString(ans));

            if(tag[j]==1)
            {
                    button2.setText(Integer.toString(rand.nextInt(226)));
                    button3.setText(Integer.toString(rand.nextInt(226)));
                    button4.setText(Integer.toString(rand.nextInt(226)));
            }
            else if(tag[j]==2)
            {
                button1.setText(Integer.toString(rand.nextInt(226)));
                button3.setText(Integer.toString(rand.nextInt(226)));
                button4.setText(Integer.toString(rand.nextInt(226)));
            }
            else if(tag[j]==3)
            {
                button2.setText(Integer.toString(rand.nextInt(226)));
                button1.setText(Integer.toString(rand.nextInt(226)));
                button4.setText(Integer.toString(rand.nextInt(226)));
            }
            else
            {
                button2.setText(Integer.toString(rand.nextInt(226)));
                button3.setText(Integer.toString(rand.nextInt(226)));
                button1.setText(Integer.toString(rand.nextInt(226)));
            }



        CountDownTimer countDownTimer= new CountDownTimer(30000, 1000) {
            @Override
            public void onTick(long l) {

                textView1.setText(Integer.toString((int)l/1000).concat("s"));
                if((int)l/1000==10) {
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.timeup);
                    mediaPlayer.start();
                }
            }

            @Override
            public void onFinish() {

                button6.setVisibility(View.VISIBLE);
                textView5.setVisibility(View.VISIBLE);
                percentage= (score/(28*1.0))*100.0;
                percentage_string=String.format("%.2f",percentage);
                textView5.setText("Your Score: "+percentage_string+"%");
                textView.setVisibility(View.INVISIBLE);
                button1.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.INVISIBLE);
                button3.setVisibility(View.INVISIBLE);
                button4.setVisibility(View.INVISIBLE);
            }
        }.start();

    }

    public void pressed(View view)
    {


            if (Integer.parseInt(view.getTag().toString()) == tag[j])
            {
                textView5.setVisibility(View.VISIBLE);
                textView5.setText("Correct ans");
                ++score;
                /*++ques;*/
                /*textView4.setText(Integer.toString(score) + "/" + Integer.toString(ques));*/
            }

            else
                {
                textView5.setVisibility(View.VISIBLE);
                textView5.setText("Wrong ans");
                /*++ques;*/
                /*textView4.setText(Integer.toString(score) + "/" + Integer.toString(ques));*/
                }
            if(k!=28)
        ++ques;
        textView4.setText(Integer.toString(score) + "/" + Integer.toString(ques));

            if(k==28){
                textView4.setText(Integer.toString(score) + "/" + Integer.toString(ques));
                textView.setText("Game Over");
                percentage= (score/(28*1.0))*100.0;
                percentage_string=String.format("%.2f",percentage);
                textView5.setVisibility(View.VISIBLE);
                textView5.setText("Your Score: "+percentage_string+"%");
                button1.setVisibility(View.INVISIBLE);
                button2.setVisibility(View.INVISIBLE);
                button3.setVisibility(View.INVISIBLE);
                button4.setVisibility(View.INVISIBLE);
                return;
            }


            operand1 = rand.nextInt(15) + 1;
            operand2 = rand.nextInt(15) + 1;
            i = rand.nextInt(3);
            j = rand.nextInt(4);


            textView.setText(Integer.toString(operand1) + " " + arr[i] + " " + Integer.toString(operand2));

            ++k;


            if (arr[i] == '+')
                ans = operand1 + operand2;
            else if (arr[i] == '-')
                ans = operand1 - operand2;
            else
                ans = operand1 * operand2;

            if (Integer.parseInt(button1.getTag().toString()) == tag[j])
                button1.setText(Integer.toString(ans));
            else if (Integer.parseInt(button2.getTag().toString()) == tag[j])
                button2.setText(Integer.toString(ans));
            else if (Integer.parseInt(button3.getTag().toString()) == tag[j])
                button3.setText(Integer.toString(ans));
            else
                button4.setText(Integer.toString(ans));

            if (tag[j] == 1) {
                button2.setText(Integer.toString(rand.nextInt(226)));
                button3.setText(Integer.toString(rand.nextInt(226)));
                button4.setText(Integer.toString(rand.nextInt(226)));
            }
            else if (tag[j] == 2) {
                button1.setText(Integer.toString(rand.nextInt(226)));
                button3.setText(Integer.toString(rand.nextInt(226)));
                button4.setText(Integer.toString(rand.nextInt(226)));
            }
            else if (tag[j] == 3) {
                button2.setText(Integer.toString(rand.nextInt(226)));
                button1.setText(Integer.toString(rand.nextInt(226)));
                button4.setText(Integer.toString(rand.nextInt(226)));
            }

            else
                {
                button2.setText(Integer.toString(rand.nextInt(226)));
                button3.setText(Integer.toString(rand.nextInt(226)));
                button1.setText(Integer.toString(rand.nextInt(226)));
            }


        }

    public void playAgain(View view)
    {
        textView5.setVisibility(View.INVISIBLE);
        ques=0;
        button6.setVisibility(View.INVISIBLE);
        start(view);
    }



}