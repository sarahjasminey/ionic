package com.user.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class PersonalDetails extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    // create array of Strings
    // and store name of courses
    String[] courses = { "Male", "Female", "Others"};

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private static final String  SHARED_PREF_NAME = "mypref";
    private static final String  KEY_NAME = "name";
    private static final String  KEY_EMAIL = "email";
    private static final String  KEY_ABOUT = "about";
    private static final String  KEY_GENDER = "gender";
    private static final String  KEY_DOB = "dob";
    private static final String  KEY_ADDRESS = "address";



    EditText Ename, About, Address, email1 , adharnumber  ;

    TextInputEditText companyname;
    RelativeLayout relativeLayout;

    String id;
    Button sumbit;
    Spinner spino;
    private RadioGroup radioGroup;
    RadioButton radioButton;
    TextView datepicker;
    ImageView aadharfrontimage, aadharbackimage;

    String Ename1, About1, Address1, email11 ,spino1, datepicker1 , adharnumber1, radioButton1, companyname1, aadharfrontimage1,aadharbackimage1;

    private int PICK_IMAGE_REQUEST = 1;
    //Bitmap to get image from gallery
    private Bitmap bitmap;
    //Uri to store the image uri
    private Uri filePath;
    private static final int STORAGE_PERMISSION_CODE = 123;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);

        sharedPreferences = getSharedPreferences("MyApplication", Context.MODE_PRIVATE);
        id = sharedPreferences.getString("UserID", "");
        String fisrttime = sharedPreferences.getString("FirstTimeInstall","");
        editor = sharedPreferences.edit();
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        String name = sharedPreferences.getString(KEY_NAME, null);


        if (fisrttime.equals("Yes")){

            Intent intent = new Intent(PersonalDetails.this,Navigationdrawer.class);
            startActivity(intent);
        }else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("FirstTimeInstall","Yes");
            editor.apply();
        }

        if (name != null) {

            Intent intent = new Intent(PersonalDetails.this,MyProfile_Fragment.class);
            startService(intent);
        }




        Calendar today = Calendar.getInstance();
//        final Calendar twoDaysAgo = (Calendar) today.clone();
//        twoDaysAgo.add(Calendar.DATE, -1);
        final Calendar twoDaysLater = (Calendar) today.clone();
        twoDaysLater.add(Calendar.DATE, 1);

        final Calendar c = Calendar.getInstance();
        // Get year
        final int year = c.get(Calendar.YEAR);
        //Get month
        final int month = c.get(Calendar.MONTH);
        //Get day
        final int day = c.get(Calendar.DAY_OF_MONTH);
        //Current Date
        // final String currentdate = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
       // String current = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

        datepicker = (TextView) findViewById(R.id.date);
//        datepicker.setText(current);

        datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(PersonalDetails.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int monthofYear, int dayofMonth) {

                                Calendar userAge = new GregorianCalendar(year,month,day);
                                Calendar minAdultAge = new GregorianCalendar();
                                minAdultAge.add(Calendar.YEAR, -18);
                                if (minAdultAge.before(userAge)) {
//                    SHOW_ERROR_MESSAGE;
                                }
                                datepicker.setText(dayofMonth + "/" + (monthofYear + 1) + "/" + year);
                            }
                        }, year, month, day);
//                datePickerDialog.getDatePicker().setMinDate(twoDaysAgo.getTimeInMillis());
                datePickerDialog.getDatePicker().setMaxDate(twoDaysLater.getTimeInMillis());
                datePickerDialog.show();
            }

        });

        spino = findViewById(R.id.coursesspinner);
        // Create the instance of ArrayAdapter
        // having the list of courses
        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, courses);

        // set simple layout resource file
        // for each item of spinner
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        spino.setAdapter(ad);

        Ename = (EditText) findViewById(R.id.Ename);
//        ImageView imageView = (ImageView)findViewById(R.id.mainbar);
        About = (EditText) findViewById(R.id.About);
        Address = (EditText) findViewById(R.id.Address);
        email1 = (EditText) findViewById(R.id.email1);
        sumbit = (Button) findViewById(R.id.sumbitt);
        radioGroup = (RadioGroup) findViewById(R.id.radioGroup);
        adharnumber = (EditText) findViewById(R.id.Eadhar);
        companyname = (TextInputEditText) findViewById(R.id.companyname);
        aadharfrontimage = (ImageView)findViewById(R.id.aadharfrontimage);
        aadharbackimage = (ImageView)findViewById(R.id.aadharbackimage);
        relativeLayout = findViewById(R.id.compa);
//        TextView textView = (TextView)findViewById(R.id.COMPANYNAME);


//
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId==R.id.radioButton1){
                  relativeLayout.setVisibility(View.INVISIBLE);
                 // textView.setVisibility(View.INVISIBLE);
                    relativeLayout.setVisibility(View.GONE);
                 // textView.setVisibility(View.GONE);


                }else if (checkedId==R.id.radioButton2){
                    relativeLayout.setVisibility(View.VISIBLE);
                    //textView.setVisibility(View.VISIBLE);

                }

            }
        });

        aadharfrontimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i, 1);
            }
        });

        //Setting clicklistener
        aadharbackimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(ii, 2);
            }
        });


        /*companyname.setEnabled(rg.isChecked());

        rg.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                companyname.setEnabled(isChecked);
            }
        });*/

        sumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                int selectedid = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton) findViewById(selectedid);


                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_NAME, Ename.getText().toString());
                editor.putString(KEY_EMAIL, email1.getText().toString());
                editor.putString(KEY_ABOUT, About.getText().toString());
                editor.putString(KEY_GENDER, spino.getSelectedItem().toString());
                editor.putString(KEY_DOB, datepicker.getText().toString());
                editor.putString(KEY_ADDRESS, Address.getText().toString());
                editor.apply();


                Ename1 = Ename.getText().toString().trim();
                About1 = About.getText().toString().trim();
                Address1 = Address.getText().toString().trim();
                adharnumber1 = adharnumber.getText().toString().trim();
                email11 = email1.getText().toString().trim();
                spino1 = spino.getSelectedItem().toString().trim();

                datepicker1 = datepicker.getText().toString().trim();

                companyname1 = companyname.getText().toString().trim();

//                Imageview1 = imageView.getDrawable().toString().trim();

//                if (aadharfrontimage.getDrawable() == null){
//                    //Image doesn´t exist.
//                }else if (aadharbackimage.getDrawable() == null){
//
//                }else {
//
//                    }
//                }

                if (!Ename1.isEmpty()) {
                    if (!spino1.isEmpty()) {
                        if (!datepicker1.isEmpty()) {
                            if (!About1.isEmpty()) {
                                if (selectedid != -1) {
                                    radioButton1 = radioButton.getText().toString().trim();
                                    if (radioButton1.equals("Company")) {
                                        if (!companyname1.isEmpty()) {
                                            if (!Address1.isEmpty()) {
                                                if (!email11.isEmpty()) {
                                                    if (!adharnumber1.isEmpty()) {

                                                        Sumbit();
                                                        Intent intent = new Intent(PersonalDetails.this, Home_page.class);
                                                        startActivity(intent);

                                                    }else {
                                                            adharnumber.requestFocus();
                                                            adharnumber.setError("Please enter your aadhar number");
                                                        }

                                                    } else {
                                                        email1.requestFocus();
                                                        email1.setError("Please enter your email");
                                                    }

                                                } else {
                                                    Address.requestFocus();
                                                    Address.setError("Please enter Address");
                                                }
                                            } else {
                                                companyname.requestFocus();
                                                companyname.setError("Please enter your companyname");
                                            }
                                        } else {
                                            if (!Address1.isEmpty()) {
                                                if (!email11.isEmpty()) {
                                                    if (!adharnumber1.isEmpty()) {

                                                        Sumbit();
                                                        Intent intent = new Intent(PersonalDetails.this, Home_page.class);
                                                        startActivity(intent);
                                                    } else {
                                                       adharnumber.requestFocus();
                                                        adharnumber.setError("Please enter your email");
                                                    }

                                                } else {
                                                    email1.requestFocus();
                                                    email1.setError("Please enter your email");
                                                }

                                            } else {
                                                Address.requestFocus();
                                                Address.setError("Please enter Address");
                                            }
                                        }

                                    } else {
                                        Toast.makeText(getApplicationContext(), "Please select user type", Toast.LENGTH_SHORT).show();
                                    }

                                } else {
                                    About.requestFocus();
                                    About.setError("Please enter About");
                                }

                            } else {
                                datepicker.requestFocus();
                                datepicker.setError("Please enter DOB");
                            }
                        } else {
                            spino.requestFocus();
                            spino.getSelectedView();
                        }
                    } else {
                        Ename.requestFocus();
                        Ename.setError("Please enter your name");
                    }



            }

            private void getAge(long selectedMilli) {
                Date dateOfBirth = new Date(selectedMilli);
                Calendar dob = Calendar.getInstance();
                dob.setTime(dateOfBirth);
                Calendar today = Calendar.getInstance();
                int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);
                if (today.get(Calendar.MONTH) < dob.get(Calendar.MONTH)) {
                    age--;
                } else if (today.get(Calendar.MONTH) == dob.get(Calendar.MONTH)
                        && today.get(Calendar.DAY_OF_MONTH) < dob
                        .get(Calendar.DAY_OF_MONTH)) {
                    age--;
                }

                if (age < 18) {
                    //do something
                } else {

                }

                String str_age = age + "";
                Log.d("", getClass().getSimpleName() + ": Age in year= " + age);
            }

            private void Sumbit() {

                RequestQueue requestQueue = Volley.newRequestQueue(PersonalDetails.this);
                StringRequest MyStringRequest = new StringRequest(Request.Method.POST, "http://teddiapp.com/app/api/user/add", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //This code is executed if the server responds, whether or not the response contains data.
                        //The String 'response' contains the server's response.
                        Log.d("Response", response);

                        try {
                            JSONObject object = new JSONObject(response);
                            JSONObject stringg = object.getJSONObject("data");
                            String string = stringg.getString("msg");


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
                        MyData.put("id", id);
                        MyData.put("name", Ename1);
                        MyData.put("about", About1);
                        MyData.put("address", Address1);
                        MyData.put("email", email11);
                        MyData.put("gender",spino1);
                        MyData.put("work_from",radioButton1);
                        MyData.put("dob",datepicker1);
                        MyData.put("aadhar",adharnumber1);
                        MyData.put("company_name",companyname1);
                        MyData.put("aadhar_front",aadharfrontimage1);
                        MyData.put("aadhar_back",aadharbackimage1);

                        return MyData;
                    }
                };

                // RequestQueue requestQueue = Volley.newRequestQueue(this);

                requestQueue.add(MyStringRequest);
            }
        });


        // Take the instance of Spinner and
        // apply OnItemSelectedListener on it which
        // tells which item of spinner is clicked

        spino.setOnItemSelectedListener(PersonalDetails.this);


    }

    private void changeEditTextAvailability(EditText companyname, boolean status) {

        companyname.setFocusable(status);
        companyname.setEnabled(status);
        companyname.setCursorVisible(status);
        companyname.setFocusableInTouchMode(status);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Toast.makeText(c, "ON ACTIVITY RESULT", Toast.LENGTH_SHORT).show();

        if (requestCode == 1){

            Bitmap photo = (Bitmap) data.getExtras().get("data");
            aadharfrontimage.setImageBitmap(photo);
            encodebitmap1(photo);

        }
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == PersonalDetails.this.RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {

                bitmap = MediaStore.Images.Media.getBitmap(PersonalDetails.this.getContentResolver(), filePath);
                aadharfrontimage.setImageBitmap(bitmap);
                encodebitmap1(bitmap);
                // IsBase64Encoded(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        if (requestCode == 2) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            aadharbackimage.setImageBitmap(photo);
            encodebitmap2(photo);
        }
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == PersonalDetails.this.RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(PersonalDetails.this.getContentResolver(), filePath);
                aadharbackimage.setImageBitmap(bitmap);
                encodebitmap2(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }}

    private void encodebitmap1(Bitmap bitmap)
    {

        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);


        byte[] byteofimages=byteArrayOutputStream.toByteArray();
        aadharfrontimage1= Base64.encodeToString(byteofimages, Base64.DEFAULT);
    }



    private void encodebitmap2(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream1=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream1);

        byte[] byteofimages=byteArrayOutputStream1.toByteArray();
        aadharbackimage1= Base64.encodeToString(byteofimages, Base64.DEFAULT);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // make toastof name of course
        // which is selected in spinner
       // Toast.makeText(getApplicationContext(), courses[position], Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}