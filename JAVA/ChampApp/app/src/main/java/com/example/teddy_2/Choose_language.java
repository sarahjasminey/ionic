package com.example.teddy_2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.icu.util.Currency;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Choose_language extends AppCompatActivity {

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_language);

        sharedPreferences = getSharedPreferences("Teddy_2", Context.MODE_PRIVATE);
        String fisrttime1 = sharedPreferences.getString("FirstTimelanguage","");


        if (fisrttime1.equals("Yes")){

            Intent intent = new Intent(Choose_language.this,Current_Location.class);
            startActivity(intent);
        }else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("FirstTimelanguage","Yes");
            editor.apply();
        }

        Button button = (Button)findViewById(R.id.Language_sumbitt);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Choose_language.this, Current_Location.class);
                startActivity(intent);
            }
        });

        ListView listView = (ListView) findViewById(R.id.listview);
       final TextView textView = (TextView) findViewById(R.id.CL);
        String[] players = new String[] {"English", "தமிழ் (Tamil)", "తెలుగు (Telugu)", "हिन्दी (Hindi)"};
        List<String> Players_list = new ArrayList<String>(Arrays.asList(players));
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Players_list);
        listView.setAdapter(arrayAdapter);
       // CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), players);
        //listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                //textView.setText("The best football player is : " + selectedItem);

                //////////////////////////////////////////
                setItemNormal();
                View rowView = view;
                setItemSelected(rowView);

            }

            public void setItemSelected(View view){
                View rowView = view;
                TextView tv = (TextView) rowView.findViewById(android.R.id.text1);
                tv.setTextColor(Color.BLUE);


            }

            public void setItemNormal()
            {
                for (int i=0; i< listView.getChildCount(); i++)
                {
                    View v = listView.getChildAt(i);
                    //TextView txtview = ((TextView) v.findViewById(R.id.menurow_title));
                    TextView txtview = ((TextView)v.findViewById(android.R.id.text1));
                    txtview.setTextColor(Color.BLACK);
                }
            }
        });
    }
    }
