package com.user.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SearchView;
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

public class All_categories extends AppCompatActivity implements SearchView.OnQueryTextListener{

    //listview object
    ListView listView;
    Intent myIntent;
    String URL = "https://teddiapp.com/app/api/user/category";

    //the hero list where we will store all the hero objects after parsing json
    List<categories> downlodelist=new ArrayList<>();
    categories downlode;
    categories_Adapter adapter;
     String selectid;

    SearchView searchView;
    String image1_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_categories);

//        myIntent = new Intent(this,Allcate_SecondActivity.class);

        listView = (ListView) findViewById(R.id.dowlistview);
        downlodelist = new ArrayList<>();
        loadjsondownlod();



        searchView = findViewById(R.id.Searchview);
        searchView.setOnQueryTextListener(All_categories.this);

//        listView.setOnItemClickListener(Listclick);
//        downloadManager = (DownloadManager)getSystemService(DOWNLOAD_SERVICE);

    }


//    private AdapterView.OnItemClickListener Listclick = new AdapterView.OnItemClickListener() {
//        @Override
//        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//          categories itemvalue = (categories) listView.getItemAtPosition(position);
//            myIntent.putExtra("JOB_SELECTED");
//            startActivity(myIntent);
//            loadjsondownlod();
//        }
//    };

    private void loadjsondownlod() {


        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("strrrrr", ">>" + response);

                        try {

                            JSONObject obj = new JSONObject(response);

                            JSONObject jsonObject = obj.getJSONObject("data");
                            if (jsonObject.optString("status").equals("success")) {

                                JSONArray dataArray = jsonObject.getJSONArray("categories");
                                downlodelist=new ArrayList<>();

                                for (int i = 0; i < dataArray.length(); i++) {
                                    JSONObject dataobj1 = dataArray.getJSONObject(i);
                                    downlode=new categories();

                                    downlode.setFileID(dataobj1.getString("id"));
                                    downlode.setFilename(dataobj1.getString("category"));
                                    downlode.setImage(dataobj1.getString("images"));

                                    //adding the hero to herolist
                                    downlodelist.add(downlode);
                                }

                            }

//creating custom adapter object
                            categories_Adapter adapter = new categories_Adapter(downlodelist, getApplicationContext());


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

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.filter(text);
        return false;
    }
}