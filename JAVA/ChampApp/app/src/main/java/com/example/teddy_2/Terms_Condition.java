package com.example.teddy_2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Terms_Condition extends AppCompatActivity {

    ListView listView;
    String URL = "https://teddiapp.com/app/api/user/terms";
    SharedPreferences sharedPreferences;

    List<Terms_Bojo>heroList;
//    ArrayList<Terms_Bojo> downlodelist=new ArrayList<Terms_Bojo>();
//    Terms_Bojo terms_bojo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_terms__condition);

        sharedPreferences = getSharedPreferences("Teddy_2", Context.MODE_PRIVATE);
        String fisrttime1 = sharedPreferences.getString("FirstTimeterms","");


        if (fisrttime1.equals("Yes")){

            Intent intent = new Intent(Terms_Condition.this,Demo_Video_Page.class);
            startActivity(intent);
        }else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("FirstTimeterms","Yes");
            editor.apply();
        }

        //initializing listview and hero list
        listView = (ListView) findViewById(R.id.termlist);
        heroList = new ArrayList<>();

        //this method will fetch and parse the data
        loadHeroList();

        Button button = findViewById(R.id.accept);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Terms_Condition.this,Demo_Video_Page.class);
                startActivity(intent);
            }
        });
    }

    private void loadHeroList() {


        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);


                            JSONObject jsonObject = obj.getJSONObject("data");
                            if (jsonObject.optString("status").equals("success")) {

                                JSONArray dataArray = jsonObject.getJSONArray("terms");


                                for (int i = 0; i < dataArray.length(); i++) {
                                    JSONObject dataobj1 = dataArray.getJSONObject(i);

                                    Terms_Bojo terms_bojo = new Terms_Bojo();

                                    terms_bojo.setFileid(dataobj1.getString("id"));
                                    terms_bojo.setTerm_descri(dataobj1.getString("terms_desc"));
                                    terms_bojo.setPrivecy_descri(dataobj1.getString("privacy_desc"));
                                    //downlode.setPricetype(dataobj1.getString("pricetype"));

                                    //adding the hero to herolist
                                   heroList.add(terms_bojo);
                                }

                                //creating custom adapter object
                                Terms_Adapter adapter = new Terms_Adapter(heroList, getApplicationContext());

                                //adding the adapter to listview
                                listView.setAdapter(adapter);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }
}