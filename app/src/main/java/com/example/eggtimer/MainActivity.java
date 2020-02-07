package com.example.eggtimer;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    SeekBar seekbar;
    CountDownTimer countDownTimer;
    Boolean timerActive = false;
    Button btn;

    public void countDown(View view) {

        if (timerActive) {

            textView.setText("0:30");
            seekbar.setProgress(30);
            countDownTimer.cancel();
            seekbar.setEnabled(true);
            btn.setText("Go!");
            timerActive = false;

        } else {
            seekbar.setEnabled(false);
            timerActive = true;
            btn.setText("Stop!");
            countDownTimer = new CountDownTimer(seekbar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long millisUntilFinished) {
                    updateTimer((int) millisUntilFinished / 1000);
                    MediaPlayer mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.beeps2);
                    mediaPlayer.start();
                }

                @Override
                public void onFinish() {
                    Toast.makeText(MainActivity.this, "Count Down Finish!!", Toast.LENGTH_SHORT).show();
                    MediaPlayer mp = MediaPlayer.create(MainActivity.this, R.raw.pharmeasy);
                    mp.start();
                    seekbar.setEnabled(true);
                    textView.setText("0:30");
                    seekbar.setProgress(30);
                    btn.setText("GO!");
                }
            }.start();
        }
    }

    public void updateTimer(int time){
        int minutes = time/60;
        int seconds = time - (minutes*60);
        String secondPos = Integer.toString(seconds);

        if(seconds <= 9){
            secondPos = "0"+secondPos;
        }

        textView.setText(Integer.toString(minutes) + ":" + secondPos);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekbar = (SeekBar) findViewById(R.id.seekBar);
        textView = (TextView) findViewById(R.id.textView2);
        btn = (Button)findViewById(R.id.button);

        seekbar.setMax(600);
        seekbar.setProgress(30);


        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

    }
}
