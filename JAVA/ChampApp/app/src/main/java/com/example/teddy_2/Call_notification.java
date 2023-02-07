package com.example.teddy_2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;


public class Call_notification extends AppCompatActivity {
    private static final int RELATIVE_TO_SELF = 0;
    public int counter;
    @TargetApi(Build.VERSION_CODES.O)
    MaterialProgressBar materialProgressBar;
    double progress;
   // int myProgress = 0;
    int endTime = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call_notification);

        final TextView counttime = findViewById(R.id.counttime);

        materialProgressBar = findViewById(R.id.material_pro);

       // myProgress = 0;

        progress = 3.4;

//        RotateAnimation makeVertical = new RotateAnimation(0, -90, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
//        makeVertical.setFillAfter(true);
        //materialProgressBar.startAnimation(makeVertical);
        materialProgressBar.setSecondaryProgress(endTime);
        materialProgressBar.setProgress(0);


        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                setProgress((int) progress, endTime);
                progress = progress + 3.4;
                counttime.setText("" + millisUntilFinished / 1000);

                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                counttime.setText("done!");
                setProgress((int) progress, endTime);
            }

        }.start();
    }

    public void setProgress(int startTime, int endTime) {
        materialProgressBar.setMax(endTime);
        materialProgressBar.setSecondaryProgress(endTime);
        materialProgressBar.setProgress(startTime);

//        }
//        new CountDownTimer(1000,25000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                counttime.setText(String.valueOf(counter));
//                counter++;
//            }
//            @Override
//            public void onFinish() {
//                counttime.setText("Finished");
//            }
//        }.start();
//    }
    }
}