package com.example.teddy_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

public class Demo_Video_Page extends AppCompatActivity {

    SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo__video__page);

        sharedPreferences = getSharedPreferences("Teddy_2", Context.MODE_PRIVATE);
        String fisrttime1 = sharedPreferences.getString("FirstTimevideo","");


        if (fisrttime1.equals("Yes")){

            Intent intent = new Intent(Demo_Video_Page.this,Choose_Cat_page.class);
            startActivity(intent);
        }else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("FirstTimevideo","Yes");
            editor.apply();
        }

        VideoView videoView = (VideoView) findViewById(R.id.videoView1);
        Button button = findViewById(R.id.move);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Demo_Video_Page.this,Choose_Cat_page.class);
                startActivity(intent);
            }
        });

        MediaController mediaController= new MediaController(this);
        mediaController.setAnchorView(videoView);

        Uri uri = Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.videos);

        videoView.setMediaController(mediaController);
        videoView.setVideoURI(uri);
        videoView.requestFocus();
        videoView.start();


    }

}