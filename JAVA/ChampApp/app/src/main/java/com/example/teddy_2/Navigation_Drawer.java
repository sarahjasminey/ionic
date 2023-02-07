package com.example.teddy_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.TargetApi;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.agik.AGIKSwipeButton.Controller.OnSwipeCompleteListener;
import com.agik.AGIKSwipeButton.View.Swipe_Button_View;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;

public class Navigation_Drawer extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;
    Switch simpleSwitch1;
    String statusSwitch1, statusSwitch2;

    @TargetApi(Build.VERSION_CODES.O)
    MaterialProgressBar materialProgressBar;
    double progress;
    int endTime = 100;

     TextView switchBtn_txtView , profil, bankdetail, walle;
     ImageView notification;

    String categoyID="";
    String selectedCategory="",selectedCategoryID;
    SharedPreferences preferences;
    String user_id="";

    String selectedID;
    String URL = "https://teddiapp.com/app/api/champs/duty/?";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation__drawer);


        preferences = getSharedPreferences("Teddy_2", Context.MODE_PRIVATE);
        user_id=preferences.getString("UserID","");

        categoyID=getIntent().getStringExtra("DutyStatus");

        drawerLayout = findViewById(R.id.drawer_layout1);
        NavigationView navigationView = findViewById(R.id.nav_view);
       // simpleSwitch1 = (Switch) findViewById(R.id.simpleSwitch1);
        navigationView.setNavigationItemSelectedListener(this);
        toolbar = findViewById(R.id.toolbarr);
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        simpleSwitch1 = (Switch) findViewById(R.id.techknow1);
        FrameLayout drawerLayout = findViewById(R.id.fragment_container);
         switchBtn_txtView = (TextView) findViewById(R.id.switchBtn_txtView);
         notification = (ImageView) findViewById(R.id.notification);
         profil = (TextView) findViewById(R.id.profile);
         bankdetail = (TextView) findViewById(R.id.bankdetails);
         walle = (TextView) findViewById(R.id.wallet);


//        RelativeLayout relativeLayout1 = findViewById(R.id.timer);
//
//        relativeLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                scrollView.setVisibility(View.VISIBLE);
//                relativeLayout.setVisibility(View.INVISIBLE);
//                relativeLayout.setVisibility(View.GONE);
//            }
//        });

//        Swipe_Button_View swipe_button_view = findViewById(R.id.sb_swipe);
//        //  Fragment fragment = v.findViewById(R.id.homemap);
//
//
//        swipe_button_view.setOnSwipeCompleteListener_forward_reverse(new OnSwipeCompleteListener() {
//            @Override
//            public void onSwipe_Forward(Swipe_Button_View swipe_button_view) {
//
//            }
//
//            @Override
//            public void onSwipe_Reverse(Swipe_Button_View swipe_button_view) {
//
//            }
//        });

//        drawerLayout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                relativeLayout.setVisibility(View.VISIBLE);
//            }
//        });
        loadHeroList1();

        simpleSwitch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){

//                    statusSwitch1 = simpleSwitch1.getText().toString().trim();


                   drawerLayout.setVisibility(View.VISIBLE);
                    switchBtn_txtView.setText("ON DUTY");
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Home_map_Fragment()).commit();
//
//                    //  relativeLayout1.setVisibility(View.VISIBLE);
//                    relativeLayout1.postDelayed(new Runnable() {
//                        public void run() {
//                            relativeLayout1.setVisibility(View.VISIBLE);
//                        }
//                    }, 7000);
//
//                    final TextView counttime = findViewById(R.id.counttime);
//
//                    materialProgressBar = findViewById(R.id.material_pro);
//                    progress = 3.4;
//                    materialProgressBar.setSecondaryProgress(endTime);
//                    materialProgressBar.setProgress(0);
//
//
//                    new CountDownTimer(30000, 1000) {
//
//                        public void onTick(long millisUntilFinished) {
//                            setProgress((int) progress, endTime);
//                            progress = progress + 3.4;
//                            counttime.setText("" + millisUntilFinished / 1000);
//                            //here you can have your logic to set text to edittext
//                        }
//
//                        public void onFinish() {
//                            counttime.setText("done!");
//                            setProgress((int) progress, endTime);
//
//                            relativeLayout1.setVisibility(View.INVISIBLE);
//                            relativeLayout.setVisibility(View.GONE);
//
//                        }
//
//                    }.start();
//
//                    //statusSwitch1 = simpleSwitch1.getTextOn().toString();
                }
                else{
                    drawerLayout.setVisibility(View.VISIBLE);
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Offduty_page()).commit();
                    switchBtn_txtView.setText("OFF DUTY");
//                    relativeLayout1.setVisibility(View.INVISIBLE);
//                    relativeLayout1.setVisibility(View.GONE);
                    //statusSwitch1 = simpleSwitch1.getTextOff().toString();
                }
               // Toast.makeText(getApplicationContext(), "Switch1 :" + statusSwitch1 + "\n", Toast.LENGTH_LONG).show();

                statusSwitch1 = switchBtn_txtView.getText().toString().trim();
                selectedID = switchBtn_txtView.getText().toString().trim();

                updateCategory();
            }

        });


        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Offduty_page()).commit();
            navigationView.setCheckedItem(R.id.myprofile);
        }
    }


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        MenuInflater inflater = getMenuInflater();
//        inflater.inflate(R.menu.menu_item, menu);
//        return true;
//    }
//public void setProgress(int startTime, int endTime) {
//    materialProgressBar.setMax(endTime);
//    materialProgressBar.setSecondaryProgress(endTime);
//    materialProgressBar.setProgress(startTime);
//}

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {

            case R.id.myhomee:
                simpleSwitch1.setVisibility(View.VISIBLE);
                switchBtn_txtView.setVisibility(View.VISIBLE);
                notification.setVisibility(View.VISIBLE);
                profil.setVisibility(View.GONE);
                walle.setVisibility(View.GONE);
                bankdetail.setVisibility(View.GONE);

                if(switchBtn_txtView.getText().toString().equals("OFF DUTY"))
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Offduty_page()).commit();
                else if(switchBtn_txtView.getText().toString().equals("ON DUTY"))
                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Home_map_Fragment()).commit();
                break;
            case R.id.myprofile:
                simpleSwitch1.setVisibility(View.GONE);
                switchBtn_txtView.setVisibility(View.GONE);
                notification.setVisibility(View.GONE);
                profil.setVisibility(View.VISIBLE);
                bankdetail.setVisibility(View.GONE);
                walle.setVisibility(View.GONE);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Profile_Fragment()).commit();
//                drawerLayout.setVisibility(View.VISIBLE);
                break;
            case R.id.mybankdetails:
                simpleSwitch1.setVisibility(View.GONE);
                switchBtn_txtView.setVisibility(View.GONE);
                notification.setVisibility(View.GONE);
                bankdetail.setVisibility(View.VISIBLE);
                profil.setVisibility(View.GONE);
                walle.setVisibility(View.GONE);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Account_Number()).commit();
                break;
//            case R.id.mywallet:
//                simpleSwitch1.setVisibility(View.GONE);
//                switchBtn_txtView.setVisibility(View.GONE);
//                notification.setVisibility(View.GONE);
//                walle.setVisibility(View.VISIBLE);
//                profil.setVisibility(View.GONE);
//                bankdetail.setVisibility(View.GONE);
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Wallet_page()).commit();
//                break;
//            case R.id.mymap:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Home_map_Fragment()).commit();
//                break;

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    private void updateCategory() {

        RequestQueue requestQueue = Volley.newRequestQueue(Navigation_Drawer.this);
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, "https://teddiapp.com/app/api/champs/duty", new Response.Listener<String>() {
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

//                        Intent intent = new Intent(Navigation_Drawer.this,Navigation_Drawer.class);
//                        startActivity(intent);

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
                MyData.put("duty_status", statusSwitch1);
                MyData.put("champ_id", user_id);
                return MyData;
            }
        };

        // RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(MyStringRequest);
    }

//
    private void loadHeroList1() {


        //creating a string request to send request to the url
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL+"champ_id="+user_id,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {
                            //getting the whole json object from the response
                            JSONObject obj = new JSONObject(response);


                            JSONObject jsonObject = obj.getJSONObject("data");
                            if (jsonObject.getString("status").equals("success")){

                                String current_duty=jsonObject.getString("duty");
                                if(current_duty.equals("on")||current_duty.equals("ON DUTY")){
                                    drawerLayout.setVisibility(View.VISIBLE);
                                    switchBtn_txtView.setText("ON DUTY");
                                    simpleSwitch1.setChecked(true);
                                //   simpleSwitch1.setEnabled(true);
                                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Home_map_Fragment()).commit();

                                }else {
                                    drawerLayout.setVisibility(View.VISIBLE);
                                    simpleSwitch1.setChecked(false);
                                 //   simpleSwitch1.setEnabled(true);
                                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new Offduty_page()).commit();
                                    switchBtn_txtView.setText("OFF DUTY");
                                }
                                statusSwitch1 = switchBtn_txtView.getText().toString().trim();
                                selectedID = switchBtn_txtView.getText().toString().trim();
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