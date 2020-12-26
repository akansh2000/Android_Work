package my.appcompany.workouttimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    SeekBar SeekBarTime;
    TextView textView;
    Button reset;
    CountDownTimer countDownTimer;
    Button pause;
    Button cont;
    int second_left;
    boolean seekbar_enabled=false;


    public void UpdateTime(int sec_left)
    {
        int minute;
        int second;

        second_left=sec_left;

        minute=sec_left/60;
        second=sec_left-(minute*60);
        if(minute<10)
        {
            if(second<10)
                textView.setText("0"+Integer.toString(minute)+":"+"0"+Integer.toString(second));
            else
                textView.setText("0"+Integer.toString(minute)+":"+Integer.toString(second));
        }

        else if(second<10)
        {
            textView.setText(Integer.toString(minute)+":"+"0"+Integer.toString(second));
        }
        else
            textView.setText(Integer.toString(minute)+":"+Integer.toString(second));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        reset=findViewById(R.id.button2);
        reset.setVisibility(View.INVISIBLE);
        pause=findViewById(R.id.button3);
        pause.setVisibility(View.INVISIBLE);
        cont=findViewById(R.id.button4);
        cont.setVisibility(View.INVISIBLE);

        SeekBarTime= findViewById(R.id.seekBar);
        textView= findViewById(R.id.textView1);

        SeekBarTime.setMax(600);
        SeekBarTime.setProgress(30);


        SeekBarTime.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b)
            {
                UpdateTime(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }


    public void startTime(View view)
    {
      SeekBarTime.setEnabled(seekbar_enabled);
      seekbar_enabled=true;
      reset.setVisibility(View.VISIBLE);
      pause.setVisibility(View.VISIBLE);
      cont.setVisibility(View.VISIBLE);
        countDownTimer= new CountDownTimer(SeekBarTime.getProgress()*1000, 1000){


         @Override
         public void onTick(long l) {

         UpdateTime((int)l/1000);

         }

         @Override
         public void onFinish() {

             MediaPlayer mediaplayer= MediaPlayer.create(getApplicationContext(), R.raw.timeup);
             mediaplayer.start();
             SeekBarTime.setEnabled(true);
             seekbar_enabled=false;
         }
     }.start();
    }

    public void Reset(View view)
    {
        seekbar_enabled=false;
        SeekBarTime.setProgress(0);
        SeekBarTime.setEnabled(true);
        countDownTimer.cancel();
        textView.setText("00:00");
        pause.setVisibility(View.INVISIBLE);
        cont.setVisibility(View.INVISIBLE);
        reset.setVisibility(View.INVISIBLE);


    }

    public void Pause(View view)
    {
        cont=findViewById(R.id.button4);
        cont.setVisibility(View.VISIBLE);
        countDownTimer.cancel();
    }

    public void Cont(View view)
    {

        countDownTimer= new CountDownTimer(second_left*1000, 1000){


            @Override
            public void onTick(long l) {

                UpdateTime((int)l/1000);

            }

            @Override
            public void onFinish() {

                MediaPlayer mediaplayer= MediaPlayer.create(getApplicationContext(), R.raw.timeup);
                mediaplayer.start();
                SeekBarTime.setEnabled(true);
            }
        }.start();

    }


    }

