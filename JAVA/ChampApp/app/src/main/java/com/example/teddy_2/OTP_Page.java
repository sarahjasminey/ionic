package com.example.teddy_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import javax.crypto.spec.OAEPParameterSpec;

public class OTP_Page extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_t_p__page);


        Button button = findViewById(R.id.verify_otp);
        RelativeLayout relativeLayout = findViewById(R.id.OTP);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                relativeLayout.setVisibility(View.GONE);
                relativeLayout.setVisibility(View.INVISIBLE);
//                Intent intent = new Intent(OTP_Page.this,Navig)

            }
        });


    }
}