package com.user.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
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

public class Allcate_SecondActivity extends AppCompatActivity {


    //listview object
    ListView listView;
    String URL = "https://teddiapp.com/app/api/user/subcat/";

    //the hero list where we will store all the hero objects after parsing json
    List<categories> downlodelist=new ArrayList<>();
    categories downlode;
    String categoyID="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allcate__second);

        categoyID=getIntent().getStringExtra("CategoryID");

        listView = (ListView) findViewById(R.id.dowlistviewback);
        downlodelist = new ArrayList<>();
        SubcatAll();

    }

    private void SubcatAll() {


        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL+categoyID, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                Log.d("strrrrr", ">>" + response);

                try {

                    JSONObject obj = new JSONObject(response);

                    JSONObject jsonObject = obj.getJSONObject("data");
                    if (jsonObject.optString("status").equals("success")) {

                        JSONArray dataArray = jsonObject.getJSONArray("sub_categories");
                        downlodelist=new ArrayList<>();

                        for (int i = 0; i < dataArray.length(); i++) {
                            JSONObject dataobj1 = dataArray.getJSONObject(i);
                            downlode=new categories();

                            //downlode.setFileID(dataobj1.getString("id"));
                            downlode.setFilename(dataobj1.getString("sub_category"));

                            //adding the hero to herolist
                            downlodelist.add(downlode);
                        }

                    }

//creating custom adapter object
                    Subcategories_Adapter adapter = new Subcategories_Adapter(downlodelist, getApplicationContext());

                    //adding the adapter to listview
                    listView.setAdapter(adapter);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //displaying the error in toast if occurrs
                        //displaying the error in toast if occurrs
                        Log.e("Volley",error.toString());
                        Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);



    }
}