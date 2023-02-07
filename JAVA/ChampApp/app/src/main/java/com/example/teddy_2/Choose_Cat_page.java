package com.example.teddy_2;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.SearchView;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Choose_Cat_page extends AppCompatActivity implements SearchView.OnQueryTextListener {

    //listview object
    ListView listView;
    String URL = "https://teddiapp.com/app/api/champs/category";

   // String CATURL = "https://teddiapp.com/app/api/champs/category_to_work";

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)

    SearchView editsearch;
    //ArrayList<Cate> arraylist = new ArrayList<Cate>();

    //the hero list where we will store all the hero objects after parsing json
    ArrayList<Cate> downlodelist=new ArrayList<Cate>();
    Cate downlode;
    String selectid , list1 , id, category;
    TextView txtview;

    Cate_Adapter adapter;
    String categoyID="";
    String selectedCategory="",selectedCategoryID;
    SharedPreferences preferences;
    String user_id="";
    String value;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose__cat_page);

        preferences = getSharedPreferences("Teddy_2", Context.MODE_PRIVATE);
        user_id=preferences.getString("UserID","");

        listView = (ListView) findViewById(R.id.dowlistview);
        downlodelist = new ArrayList<>();
        loadjsondownlod();
        categoyID=getIntent().getStringExtra("CategoryID");



//
//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//                Cursor cursor = (Cursor) listView.getItemAtPosition(position);
//                id = cursor.getLong(cursor.getColumnIndexOrThrow("_id"));
//                //Toast.makeText(getActivity(), iD + "", Toast.LENGTH_LONG).show();
//                Intent result = new Intent(getApplicationContext(), Choose_Cat_page.class);
//                // intent.putExtra("ID", iD);
//                startActivity(result);
//
//            }
//        });

        listView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        listView.setItemChecked(2, true);



        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                list1 = listView.toString().trim();
                selectedCategoryID=downlodelist.get(position).getFileID();
                if(!selectedCategory.contains(selectedCategoryID)){
                    selectedCategory=selectedCategory+","+selectedCategoryID;
                }else {
                    selectedCategory=selectedCategory.replace(","+selectedCategoryID,"");
                }

                Toast.makeText(Choose_Cat_page.this, downlodelist.get(position).getFileID(), Toast.LENGTH_SHORT).show();

            }
        });

//        listView.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//
//            @Override
//            public void onItemSelected(AdapterView<?> arg0, View arg1,
//                                       int arg2, long arg3){
//        }
//
//        @Override
//        public void onNothingSelected(AdapterView<?> arg0) {
//
//
//        }
//    });

//        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//
//
//                setItemNormal();
//                View rowView = view;
//                setItemSelected(rowView);
//            }
//
//            public void setItemSelected(View view){
//                View rowView = view;
//                TextView tv = (TextView) rowView.findViewById(android.R.id.text1);
//                tv.setTextColor(Color.BLUE);
//
//
//            }
//
//            public void setItemNormal()
//            {
//                for (int i=0; i< listView.getChildCount(); i++)
//                {
//                    View v = listView.getChildAt(i);
//                    TextView txtview = ((TextView)v.findViewById(android.R.id.text1));
//                    txtview.setTextColor(Color.BLACK);
//                }
//            }
//        });
//


        Button button = findViewById(R.id.sumbitt);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!selectedCategory.isEmpty()){
                    selectedCategory=selectedCategory.substring(1);
                    updateCategory();
                }else {
                    Toast.makeText(getApplicationContext(),"Please select category",Toast.LENGTH_SHORT).show();
                }

            }
        });


//        // Locate the EditText in listview_main.xml
        editsearch = (SearchView) findViewById(R.id.Searchview);
       editsearch.setOnQueryTextListener(this);

    }
    private void updateCategory() {

        RequestQueue requestQueue = Volley.newRequestQueue(Choose_Cat_page.this);
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, "https://teddiapp.com/app/api/champs/category_to_work", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.
                Log.d("Response", response);

                try {

                    // if (string.equals("User Created Successfully."))*/

                    JSONObject object = new JSONObject(response);
                    JSONObject stringg = object.getJSONObject("data");
                    String string = stringg.getString("msg");
                    String status = stringg.getString("status");

                    if(status.equals("success"))
                    {
                        //Toast.makeText(MainActivity.this, string, Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(Choose_Cat_page.this,Navigation_Drawer.class);
                        startActivity(intent);

                    }

                    Log.e("TAGNEW", String.valueOf(stringg));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("categories", selectedCategory);
                MyData.put("champ_id", user_id);
                return MyData;
            }
        };

        // RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(MyStringRequest);
    }


//    private void loadcategory() {
//
//        RequestQueue requestQueue = Volley.newRequestQueue(Choose_Cat_page.this);
//        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, CATURL, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//                //This code is executed if the server responds, whether or not the response contains data.
//                //The String 'response' contains the server's response.
//                Log.d("Response", response);
//
//                try {
//                    JSONObject object = new JSONObject(response);
//                    JSONObject stringg = object.getJSONObject("data");
//                    String string = stringg.getString("msg");
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            //Create an error listener to handle errors appropriately.
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                //This code is executed if there is an error.
//            }
//        }) {
//            protected Map<String, String> getParams() {
//                Map<String, String> MyData = new HashMap<String, String>();
//                MyData.put("champ_id", id);
//                MyData.put("categories", category);
//
//                return MyData;
//            }
//        };
//
//        // RequestQueue requestQueue = Volley.newRequestQueue(this);
//
//        requestQueue.add(MyStringRequest);
//
//    }

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
                            downlode=new Cate();

                            downlode.setFileID(dataobj1.getString("id"));
                            downlode.setFilename(dataobj1.getString("category"));
                            downlode.setDescription(dataobj1.getString("description"));
                            downlode.setImage(dataobj1.getString("images"));
                            //downlode.setPricetype(dataobj1.getString("pricetype"));

                            //adding the hero to herolist
                            downlodelist.add(downlode);
                        }

                    }

//creating custom adapter object
                     adapter = new Cate_Adapter(downlodelist, getApplicationContext());

//                    adapter.notifyDataSetChanged();

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

//    @Override
//    public boolean onQueryTextSubmit(String query) {
//
//        return false;
//    }
//
//    @Override
//    public boolean onQueryTextChange(String newText) {
//        String text = newText;
//        //Cate_Adapter.filter(text);
//        return false;
//    }
}