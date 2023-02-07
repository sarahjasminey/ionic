package com.user.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {

    Button button;
   // TextView textView;

    String s_phone_number;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    Boolean islogin = false;
    EditText editText;


    private static final String  KEY_NUMBER = "numberr";
    private static final String  SHARED_PREF_NAME = "mypref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("MyApplication", Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();


        islogin = sharedPreferences.getBoolean("IsLogin", false);
        if (islogin) {

            openNewActivity();
        }


        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,Context.MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_NUMBER,null);


        if (name !=null){
              Log.i("saral", name);

            Intent intent = new Intent(MainActivity.this,MyProfile_Fragment.class);
            startService(intent);
        }


        button = (Button) findViewById(R.id.loginbutton);

        editText = (EditText)findViewById(R.id.editTextUsername);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.apply();
                editor.putString(KEY_NUMBER, editText.getText().toString());

                s_phone_number=editText.getText().toString().trim();


                if (!s_phone_number.isEmpty()){
                    login();

                }
                else {
                     editText.setError("Enter Your Number");
                     editText.requestFocus();

                     return;
                }

//                if (!editText.getText().toString().matches("[0-9]{10}")){
//                    login();
//                }else {
//                    editText.setError("Enter 10 Digit Mobile Number");
//                    editText.requestFocus();
//                    return;
//                }
            }
        });

    }

    private boolean isValidMobile(String phone) {
        if(!Pattern.matches("[a-zA-Z]+", phone)) {
            return phone.length() > 6 && phone.length() <= 13;
        }
        return false;
    }

    private void openNewActivity() {
        Intent intent = new Intent(this, Login_Otp.class);
        startActivity(intent);
//        finish();

    }

    public void login(){

        RequestQueue requestQueue = Volley.newRequestQueue(MainActivity.this);
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, "http://teddiapp.com/app/api/user/check_newuser", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.
                Log.d("Response", "test");
                Log.d("Response", response);

                try {

                    // if (string.equals("User Created Successfully."))*/

                    JSONObject object = new JSONObject(response);
                     JSONObject stringg = object.getJSONObject("data");
                     String string = stringg.getString("msg");

                      JSONObject jsonObject = stringg.getJSONObject("details");
                      String id = jsonObject.getString("id");
                    String otp= jsonObject.getString("otp");

                    {
                        Toast.makeText(MainActivity.this, string, Toast.LENGTH_SHORT).show();

                            editor.putString("UserID", jsonObject.getString("id"));
                            editor.putBoolean("IsLogin", true);
                            editor.commit();
                            editor.apply();
                            openNewActivity();
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
                MyData.put("phone", s_phone_number);
                return MyData;
            }
        };

       // RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(MyStringRequest);
    }


}








