package com.user.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioGroup;
import android.widget.Spinner;
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
import java.util.Locale;
import java.util.Map;

public class Create_Job extends AppCompatActivity implements LocationListener {

    EditText duration, address;
    TextView currentlocation;
    TextView noofperson , noofdays , currentlocationtext;
    int minteger , minteger1 = 1;
    LocationManager locationManager;

    SharedPreferences sharedPreferences;
    String id, idd;
    String noofperson1, duration1, address1, spinner1, subspinner1, noofdays1, currentlocationtext1,list1;
    Button postjob;


    //String[] courses = { "Office worker", "Computer programmer", "Factory worker", "Miner","Real estate agent", "Veterinarian"};

    private String URLstring = "https://teddiapp.com/app/api/user/category";
    private static ProgressDialog mProgressDialog;
    private ArrayList<GoodModel> goodModelArrayList;
    private ArrayList<GoodModel> goodModelArrayList1;
    private ArrayList<GoodModel> goodModelArrayList2;
    private ArrayList<GoodModel2>goodModel2ArrayListsub;
    private ArrayList<String> names = new ArrayList<String>();
    private Spinner spinner, supspinner,  pricespinner;

    private String SUBURL = "https://teddiapp.com/app/api/user/subcat/";
    private static ProgressDialog mProgressDialog1;
    private ArrayList<String> names1 = new ArrayList<String>();

//    Price

    private String PRICEURL = "https://teddiapp.com/app/api/user/price/?";

    private static ProgressDialog mProgressDialog2;
    private ArrayList<String> names2 = new ArrayList<String>();

    String selectedID , priceID;
    List<String> times=new ArrayList<>();
    List<String> prices=new ArrayList<>();
    ListView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create__job);

        sharedPreferences = getSharedPreferences("MyApplication", Context.MODE_PRIVATE);
        id = sharedPreferences.getString("UserID", "");

///////////////////////////////////////////////
        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        Toast.makeText(this,"name : "+name,Toast.LENGTH_SHORT).show();
////////////////////////////////////////////////////////

        RadioGroup groupRadio=(RadioGroup)findViewById(R.id.radiogroupp);
        TextView perimeterEditText=(TextView) findViewById(R.id.currentlocationn);
        EditText circumferenceEditText=(EditText)findViewById(R.id.crearjobaddress);
        currentlocation = (TextView) findViewById(R.id.currentlocationn);
//        TextView perimeterEditText1=(TextView)findViewById(R.id.NO_OF_Days);
//        TextView circumferenceEditText1=(TextView)findViewById(R.id.ADD) ;
        ListView pricerate = (ListView)findViewById(R.id.price_rate);
        list=(ListView)findViewById(R.id.price_rate);

//
//        String[] maintitle ={
//                "1 hour","2 hour",
//                "3 hour","4 hour",
//                "Half Day","Full Day",
//        };
//
//        String[] subtitle ={
//                "200/-","300/-",
//                "600/-","850/-",
//                "200/-", "200/-",
//        };
//

        //Runtime permissions


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                list1 = list.toString().trim();
                Toast.makeText(Create_Job.this, times.get(position)+"", Toast.LENGTH_SHORT).show();
              //  setItemNormal();
//                View rowView = view;
//                setItemSelected(rowView);
            }

//            public void setItemSelected(View view){
//                View rowView = view;
//                TextView tv = (TextView) rowView.findViewById(android.R.id.text1);
//                tv.setTextColor(Color.BLUE);
//            }
//
//            public void setItemNormal() {
//                for (int i=0; i< list.getChildCount(); i++)
//                {
//                    View v = list.getChildAt(i);
//                    //TextView txtview = ((TextView) v.findViewById(R.id.menurow_title));
//                    TextView txtview = ((TextView)v.findViewById(android.R.id.text1));
//                    txtview.setTextColor(Color.BLACK);
//                }
//            }
        });



        groupRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if(checkedId==R.id.radionbuttonnn)
                {
                    circumferenceEditText.setVisibility(View.VISIBLE);
                    perimeterEditText.setVisibility(View.INVISIBLE);
                    perimeterEditText.setVisibility(View.GONE);
//                    circumferenceEditText1.setVisibility(View.VISIBLE);
//                    perimeterEditText1.setVisibility(View.INVISIBLE);
//                    perimeterEditText1.setVisibility(View.GONE);
                }
                else if(checkedId==R.id.radiobuttonn)
                {
                    perimeterEditText.setVisibility(View.VISIBLE);
                    circumferenceEditText.setVisibility(View.INVISIBLE);
                    circumferenceEditText.setVisibility(View.GONE);
                    getLocation();
//                    perimeterEditText1.setVisibility(View.VISIBLE);
//                    circumferenceEditText1.setVisibility(View.INVISIBLE);
//                    circumferenceEditText1.setVisibility(View.GONE);

                }
//                else if (checkedId==R.id.radiobuttonnnn){
//
//                    pricerate.setVisibility(View.VISIBLE);
//
//                    perimeterEditText.setVisibility(View.INVISIBLE);
//                    perimeterEditText1.setVisibility(View.INVISIBLE);
//                    circumferenceEditText.setVisibility(View.INVISIBLE);
//                    circumferenceEditText1.setVisibility(View.INVISIBLE);
//                    perimeterEditText.setVisibility(View.GONE);
//                    perimeterEditText1.setVisibility(View.GONE);
//                    circumferenceEditText.setVisibility(View.GONE);
//                    circumferenceEditText1.setVisibility(View.GONE);

            }
        });


        noofperson = (TextView) findViewById(R.id.NoofP);
        //duration = (EditText) findViewById(R.id.workduraion);
        address = (EditText) findViewById(R.id.crearjobaddress);
        postjob = (Button) findViewById(R.id.postjob);
        noofdays = (TextView) findViewById(R.id.Noofd1);
        spinner = findViewById(R.id.craetjobspinner);
        supspinner = findViewById(R.id.craetjobspinner1);
        pricespinner = (Spinner) findViewById(R.id.getvalue);
        currentlocationtext = (TextView)findViewById(R.id.currentlocationn);
        retrieveJSON();

///*
//        spinner.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                String selectedID=goodModelArrayList.get(position).getId();
//                subcatJSON(selectedID);
//            }
//        });
//*/

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
                ((TextView) parent.getChildAt(0)).setTextSize(20);
                selectedID = goodModelArrayList.get(position).getId();
                subcatJSON(selectedID);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });



     supspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
         @Override
         public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

             ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
             ((TextView) parent.getChildAt(0)).setTextSize(20);
             priceID = goodModel2ArrayListsub.get(position).getIdd();
             priceJSON(selectedID,priceID);
         }

         @Override
         public void onNothingSelected(AdapterView<?> parent) {

         }
     });



        postjob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                noofperson1 = noofperson.getText().toString().trim();
                //duration1 = duration.getText().toString().trim();
                address1 = address.getText().toString().trim();
                spinner1 = spinner.getSelectedItem().toString().trim();
                subspinner1 = supspinner.getSelectedItem().toString().trim();
                noofdays1 = noofdays.getText().toString().trim();
                currentlocationtext1 = currentlocationtext.getText().toString().trim();


                if(noofperson1.length()==0)
                {
                    noofperson.requestFocus();
                    noofperson.setError("Please enter person");
                }
                else if(noofdays1.length()==0)
                {
                    noofdays.requestFocus();
                    noofdays.setError("Please enter days");
                }
                else
                {
                   Jobposting();
                    Intent intent = new Intent(Create_Job.this,Push_Notication.class);
                    startActivity(intent);
                    //Toast.makeText(PersonalDetails.this,"Validation Successful",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    @SuppressLint("MissingPermission")
    private void getLocation() {

        try {
            locationManager = (LocationManager) getApplicationContext().getSystemService(LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,5000,5,Create_Job.this);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onLocationChanged(Location location) {
        Toast.makeText(this, ""+location.getLatitude()+","+location.getLongitude(), Toast.LENGTH_SHORT).show();
        try {
            Geocoder geocoder = new Geocoder(Create_Job.this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
            String address = addresses.get(0).getAddressLine(0);

            currentlocation.setText(address);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    private void priceJSON(String selectedID, String priceID) {

        SSimpleProgressDialog(this, "Loading...", "Fetching Json", false);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, PRICEURL+"cat_id="+selectedID+"&sub_cat_id="+priceID,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("strrrrr", ">>" + response);

                        try {

                            JSONObject obj = new JSONObject(response);

                            JSONObject jsonObject = obj.getJSONObject("data");

                            if (jsonObject.optString("price_type").equals("1")){

                                times = new ArrayList<>();
                                prices = new ArrayList<>();
//                                JSONObject priceObject=jsonObject.getJSONObject("price");
                                JSONArray dataArray = jsonObject.getJSONArray("price");

                                for (int i = 0; i < dataArray.length(); i++) {

                                    JSONObject dataobj1 = dataArray.getJSONObject(i);
                                    times.add(dataobj1.getString("time"));
                                    prices.add(dataobj1.getString("price"));


                                }
                                price_Adapter adapter=new price_Adapter(Create_Job.this, times, prices);
                                list.setAdapter(adapter);


                                if(mProgressDialog2.isShowing()){
                                    mProgressDialog2.dismiss();
                                }
                            }else if (jsonObject.optString("price_type").equals("2")){


                                times = new ArrayList<>();
                                prices = new ArrayList<>();
//                                JSONObject priceObject=jsonObject.getJSONObject("price");
                                JSONArray dataArray = jsonObject.getJSONArray("price");

                                for (int i = 0; i < dataArray.length(); i++) {

                                    JSONObject dataobj1 = dataArray.getJSONObject(i);
                                    times.add(dataobj1.getString("time"));
                                    prices.add(dataobj1.getString("price"));

                                }
                                price_Adapter adapter = new price_Adapter(Create_Job.this, times, prices);
                                list.setAdapter(adapter);

                                if (mProgressDialog2.isShowing()) {
                                    mProgressDialog2.dismiss();
                                }
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

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);
    }

    public static void RRSimpleProgressDialog() {
        try {
            if (mProgressDialog2 != null) {
                if (mProgressDialog2.isShowing()) {
                    mProgressDialog2.dismiss();
                    mProgressDialog2 = null;
                }
            }
        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();

        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void SSimpleProgressDialog(Context context, String title,
                                            String msg, boolean isCancelable) {
        try {
            if (mProgressDialog2 == null) {
                mProgressDialog2 = ProgressDialog.show(context, title, msg);
                mProgressDialog2.setCancelable(isCancelable);
            }

            if (!mProgressDialog2.isShowing()) {
                mProgressDialog2.show();
            }

        } catch (IllegalArgumentException ie) {
            ie.printStackTrace();
        } catch (RuntimeException re) {
            re.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    ///////////////////////////////////  person add
    public void increaseInteger(View view) {

        minteger = minteger + 1;
        display(minteger);

    }
    public void decreaseInteger(View view) {

       if (minteger>1) {
           minteger = minteger - 1;
           display(minteger);

       }
    }
    private void display(int number) {
        TextView displayInteger = (TextView) findViewById(
                R.id.NoofP);
        displayInteger.setText("" + number);
    }

    public void increaseInteger1(View view) {

        minteger1 = minteger1 + 1;
        display1(minteger1);

    }
    public void decreaseInteger1(View view) {

        if (minteger1>1) {
            minteger1 = minteger1 - 1;
            display1(minteger1);

        }
    }
    private void display1(int number) {
        TextView displayInteger = (TextView) findViewById(
                R.id.Noofd1);
        displayInteger.setText("" + number);
    }

////////////////////////////////////////////


    private void subcatJSON(String categoryID) {

//        SimpleProgressDialog(this, "Loading...","Fetching Json",false);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, SUBURL+categoryID,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("strrrrr", ">>" + response);

                        try {

                            JSONObject obj = new JSONObject(response);

                            JSONObject jsonObject = obj.getJSONObject("data");

                            if(jsonObject.optString("status").equals("success")){

                                goodModel2ArrayListsub = new ArrayList<>();
                                names1=new ArrayList<>();
                                JSONArray dataArray  = null;
                                try {
                                    dataArray = jsonObject.getJSONArray("sub_categories");

                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                                for (int i = 0; i < dataArray.length(); i++) {

                                    GoodModel2 playerModel2 = new GoodModel2();
                                    JSONObject dataobj1 = dataArray.getJSONObject(i);

                                   // playerModel1.setIdd(dataobj1.getString("cat_id"));

                                   // playerModel2.setId(dataobj1.getString("cat_id"));
                                    playerModel2.setIdd(dataobj1.getString("id"));
                                    playerModel2.setGetprice(dataobj1.getString("sub_category"));

                                    goodModel2ArrayListsub.add(playerModel2);
                                }


                                for (int i = 0; i < goodModel2ArrayListsub.size(); i++){


                                    names1.add(goodModel2ArrayListsub.get(i).getGetprice().toString());
                                   // names1.add(goodModelArrayList.get(i).getId().toString());
                                }


                                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(Create_Job.this, android.R.layout.simple_spinner_dropdown_item, names1);
                                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                                supspinner.setAdapter(spinnerArrayAdapter);
//                                RSimpleProgressDialog();

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

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);


//    }
//    public static void RSimpleProgressDialog() {
//        try {
//            if (mProgressDialog1 != null) {
//                if (mProgressDialog1.isShowing()) {
//                    mProgressDialog1.dismiss();
//                    mProgressDialog1 = null;
//                }
//            }
//        } catch (IllegalArgumentException ie) {
//            ie.printStackTrace();
//
//        } catch (RuntimeException re) {
//            re.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public static void SimpleProgressDialog(Context context, String title,
//                                                String msg, boolean isCancelable) {
//        try {
//            if (mProgressDialog1 == null) {
//                mProgressDialog1 = ProgressDialog.show(context, title, msg);
//                mProgressDialog1.setCancelable(isCancelable);
//            }
//
//            if (!mProgressDialog1.isShowing()) {
//                mProgressDialog1.show();
//            }
//
//        } catch (IllegalArgumentException ie) {
//            ie.printStackTrace();
//        } catch (RuntimeException re) {
//            re.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }


    private void retrieveJSON() {

       // showSimpleProgressDialog(this, "Loading...","Fetching Json",false);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLstring,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d("strrrrr", ">>" + response);

                        try {

                            JSONObject obj = new JSONObject(response);

                            JSONObject jsonObject = obj.getJSONObject("data");

                            if(jsonObject.optString("status").equals("success")){

                                goodModelArrayList = new ArrayList<>();
                                names=new ArrayList<>();
                                JSONArray dataArray  = jsonObject.getJSONArray("categories");

                                for (int i = 0; i < dataArray.length(); i++) {

                                    GoodModel playerModel = new GoodModel();
                                    JSONObject dataobj = dataArray.getJSONObject(i);

//                                   editor1.putString("UserIDD", jsonObject.getString("id"));
//                                   editor1.putBoolean("Istrue", true);
//                                   editor1.commit();
//                                   editor1.apply();


                                    playerModel.setId(dataobj.getString("id"));
                                    playerModel.setCountry(dataobj.getString("category"));

                                    goodModelArrayList.add(playerModel);

                                }
/*
                                if(goodModelArrayList.size()>0)
                                subcatJSON(goodModelArrayList.get(0).getId());
*/

                                for (int i = 0; i < goodModelArrayList.size(); i++){
                                    //names.add(goodModelArrayList.get(i).getId().toString());
                                    names.add(goodModelArrayList.get(i).getCountry().toString());
                                }

                                ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(Create_Job.this, android.R.layout.simple_spinner_dropdown_item, names);
                                spinnerArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
                                spinner.setAdapter(spinnerArrayAdapter);
                                //removeSimpleProgressDialog();
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

        // request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(stringRequest);


    }

//    public static void removeSimpleProgressDialog() {
//        try {
//            if (mProgressDialog != null) {
//                if (mProgressDialog.isShowing()) {
//                    mProgressDialog.dismiss();
//                    mProgressDialog = null;
//                }
//            }
//        } catch (IllegalArgumentException ie) {
//            ie.printStackTrace();
//
//        } catch (RuntimeException re) {
//            re.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//    }
//
//    public static void showSimpleProgressDialog(Context context, String title,
//                                                String msg, boolean isCancelable) {
//        try {
//            if (mProgressDialog == null) {
//                mProgressDialog = ProgressDialog.show(context, title, msg);
//                mProgressDialog.setCancelable(isCancelable);
//            }
//
//            if (!mProgressDialog.isShowing()) {
//                mProgressDialog.show();
//            }
//
//        } catch (IllegalArgumentException ie) {
//            ie.printStackTrace();
//        } catch (RuntimeException re) {
//            re.printStackTrace();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }


    private void Jobposting() {

        RequestQueue requestQueue = Volley.newRequestQueue(Create_Job.this);
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, "https://teddiapp.com/app/api/user/job", new Response.Listener<String>() {
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
                    //Intent intent = new Intent(PersonalDetails.this,Profile_Page.class);
                    // startActivity(intent);

                    {

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
                MyData.put("user_id", id);
                MyData.put("job_id", idd);
                MyData.put("category", selectedID);
                MyData.put("sub_category", subspinner1);
                MyData.put("location",currentlocationtext1);
                MyData.put("no_of_persons", noofperson1);
                MyData.put("no_of_days", noofdays1);
                MyData.put("duration", list1);
                //MyData.put("description", address1);

                return MyData;
            }
        };

        // RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(MyStringRequest);
    }






}