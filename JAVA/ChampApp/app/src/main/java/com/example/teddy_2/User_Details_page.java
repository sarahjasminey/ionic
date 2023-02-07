package com.example.teddy_2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.annotation.TargetApi;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.media.browse.MediaBrowser;
import android.os.Build;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
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
import com.whiteelephant.monthpicker.MonthPickerDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;


public class User_Details_page extends AppCompatActivity implements AdapterView.OnItemSelectedListener, Coose_Degree.Singlechoicelis {

    String URL="https://teddiapp.com/app/api/champs/add";
    Button sumbitt;
    SharedPreferences sharedPreferences;
    String id;

     EditText name, dateofbirth , companyname, email , address , aadharnumber , CollegeName, Companyname;
     RadioGroup radioGroup;
    RadioButton radioButton;
    TextView datepicker ,pastoutstartyesr, pastoutendyear, CollegeDegree;

    String name1, dateofbirth1 , companyname1, email1 , address1 , aadharnumber1, radioButton1,datepicker1,year11,month11,
             cch,cch1,cch2,cch3,fresher1,exper2, collegename1, collegedegree1, pastoutstartyesr1, pastoutendyear1, Companyname1,
            PRYear1, PRmonth1, Toyear1, Tomonth1;

    CheckBox ch, ch1, ch2, ch3 , fresher , exper , checkBox3;

    RadioGroup radioeducation1, radioeducation2;
    RadioButton radioButton10belo, radioButton10above, radioButton12th, radioButtongraduate;

    String[] year1 = { "Select Year","0 Year", "1 Year", "2 Year", "3 Year", "4 Year",
            "5 Year", "6 Year", "7 Year", "8 Year", "9 Year", "10 Year", "11 Year", "12 Year"};
    String[] month1 = { "Select Month","0 month", "1 month", "2 month", "3 month", "4 month",
            "5 month", "6 month", "7 month", "8 month", "9 month", "10 month", "11 month", "12 month"};

    Spinner year, month, workyear, workmonth, PRYear, PRmonth, Toyear, Tomonth;

    TextView textView;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)

    TextView startyear, endyear;
    String check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user__details_page);

        sharedPreferences = getSharedPreferences("Teddy_2", Context.MODE_PRIVATE);
        id=sharedPreferences.getString("UserID","");
        String fisrttime1 = sharedPreferences.getString("FirstTimeInstalll","");


        if (fisrttime1.equals("Yes")){

            Intent intent = new Intent(User_Details_page.this,Document_Upload_page.class);
            startActivity(intent);
        }else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("FirstTimeInstalll","Yes");
            editor.apply();
        }



        sumbitt = findViewById(R.id.sumbitt);
        name = findViewById(R.id.Ename);
        companyname = findViewById(R.id.previouscompany);
        datepicker = findViewById(R.id.date);
        email = findViewById(R.id.email);
        address = findViewById(R.id.address);
        aadharnumber = findViewById(R.id.aadharnumber);
        radioGroup = (RadioGroup) findViewById(R.id.radiogroupp);
        startyear = findViewById(R.id.startyear);
        endyear = findViewById(R.id.endyaer);
        CollegeName = findViewById(R.id.college_name_edit);
        CollegeDegree = findViewById(R.id.college_degree_edit);
        pastoutstartyesr = findViewById(R.id.startyear);
        pastoutendyear = findViewById(R.id.endyaer);
        Companyname = findViewById(R.id.previouscompany);
        PRYear = findViewById(R.id.workyear);
        PRmonth = findViewById(R.id.workmonth);
        Toyear = findViewById(R.id.workyear1);
        Tomonth = findViewById(R.id.workmonth1);
//        radioeducation1 = findViewById(R.id.EducationRa1);
//        radioeducation2 = findViewById(R.id.EducationRa2);



        textView = findViewById(R.id.college_degree_edit);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment dialogFragment = new Coose_Degree();
                dialogFragment.setCancelable(false);
                dialogFragment.show(getSupportFragmentManager(),"single Choice Dialog");

            }
        });

        sumbitt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Sumbit();
                Intent intent = new Intent(User_Details_page.this,Document_Upload_page.class);
                startActivity(intent);

                int selectedid = radioGroup.getCheckedRadioButtonId();
                radioButton = (RadioButton)findViewById(selectedid);

                if (selectedid ==-1){

                    Toast.makeText(getApplicationContext(),"please enter radio",Toast.LENGTH_SHORT).show();
                }else {


                    StringBuffer result = new StringBuffer();
                    result.append("My Exper");
                    if (ch.isChecked()) {
                        result.append(ch.getText().toString());
                    }
                    if (ch1.isChecked()) {
                        result.append(ch1.getText().toString());
                    }
                    if (ch2.isChecked()) {
                        result.append(ch2.getText().toString());
                    }
                    if (ch3.isChecked()) {
                        result.append(ch3.getText().toString());
                    }
                    if (fresher.isChecked()) {
                        result.append(fresher.getText().toString());
                    }
                    if (exper.isChecked()) {
                        result.append(exper.getText().toString());

                    }
                    if (!ch.isChecked() && !ch1.isChecked() && !ch2.isChecked() && !ch3.isChecked())
                        result.append("\nnone");
                    Message.message(User_Details_page.this, result.toString());


                    name1 = name.getText().toString().trim();
                    companyname1 = companyname.getText().toString().trim();
                    email1 = email.getText().toString().trim();
                    address1 = address.getText().toString().trim();
                    aadharnumber1 = aadharnumber.getText().toString().trim();
                    radioButton1 = radioButton.getText().toString().trim();
                    datepicker1 = datepicker.getText().toString().trim();
                    year11 = year.getSelectedItem().toString().trim();
                    month11 = month.getSelectedItem().toString().trim();
                      check = result.getClass().toString().trim();
                    collegename1 = CollegeName.getText().toString().trim();
                    collegedegree1 = CollegeDegree.getText().toString().trim();
                    pastoutstartyesr1 = pastoutstartyesr.getText().toString().trim();
                    pastoutendyear1 = pastoutendyear.getText().toString().trim();
                    Companyname1 = companyname.getText().toString().trim();
                    PRYear1 = PRYear.getSelectedItem().toString().trim();

                    cch = ch.getText().toString().trim();
                    cch1 = ch1.getText().toString().trim();
                    cch2 = ch2.getText().toString().trim();
                    cch3 = ch3.getText().toString().trim();
                    fresher1 = fresher.getText().toString().trim();
                    exper2 = exper.getText().toString().trim();


//                    if (!name1.isEmpty()) {
//                        if (selectedid != 1) {
//                            radioButton1 = radioButton.getText().toString().trim();
//                            if (datepicker1.isEmpty()){
//                            if (email1.isEmpty()){
//                            if (address1.isEmpty()){
//
//                                Sumbit();
//                                Intent intent = new Intent(User_Details_page.this,Document_Upload_page.class);
//                                startActivity(intent);
//
//                            }else {
//                                address.requestFocus();
//                                address.setError("Please enter your address");
//                            }
//
//
//                            }else {
//                                email.requestFocus();
//                                email.setError("Please enter your email");
//                            }
//
//                            }else {
//                                datepicker.requestFocus();
//                                datepicker.setError("Please enter your date");
//                            }
//                        } else {
//                            Toast.makeText(getApplicationContext(), "Please select user type", Toast.LENGTH_SHORT).show();
//                        }
//
//                    } else {
//                        name.requestFocus();
//                        name.setError("Please enter your name");
//                    }
//                }}
                }}
//                if(name1.length()==0)
//                {
//                    name.requestFocus();
//                    name.setError("Please enter your name");
//                }
//                else if(radioButton1.length()==0)
//                {
//                    radioButton.requestFocus();
//                    radioButton.setError("Please enter About");
//                }
//                else if(datepicker1.length()==0)
//                {
//                    datepicker.requestFocus();
//                    datepicker.setError("Please enter Address");
//                }
//                else if(email1.length()==0)
//                {
//                    email.requestFocus();
//                    email.setError("Please enter your email");
//                }
//                else if(radioButton1.length()==0)
//                {
//                    radioButton.requestFocus();
//                    radioButton.setError("Please enter company or ind");
//                }
//                else if(datepicker1.length()==0)
//                {
//                    datepicker.requestFocus();
//                    datepicker.setError("Please enter DOB");
//                }
//
//                else if(companyname1.length()==0)
//                {
//                    companyname.requestFocus();
//                    companyname.setError("Please enter your companyname");
//                }
//                else
//                {
//                    Sumbit();
//                    Intent intent = new Intent(User_Details_page.this,Document_Upload_page.class);
//                    startActivity(intent);
//                    //Toast.makeText(PersonalDetails.this,"Validation Successful",Toast.LENGTH_LONG).show();
//                }
//
//            }
//
        });


        year = findViewById(R.id.workyear);
        workyear = findViewById(R.id.workyear1);
        // Create the instance of ArrayAdapter
        // having the list of courses
        ArrayAdapter ad = new ArrayAdapter(this, android.R.layout.simple_spinner_item, year1);

        // set simple layout resource file
        // for each item of spinner
        ad.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Set the ArrayAdapter (ad) data on the
        // Spinner which binds data to spinner
        year.setAdapter(ad);
        workyear.setAdapter(ad);

        year.setOnItemSelectedListener(User_Details_page.this);
        workyear.setOnItemSelectedListener(User_Details_page.this);

        month =findViewById(R.id.workmonth);
        workmonth = findViewById(R.id.workmonth1);
        // Create the instance of ArrayAdapter
        // having the list of courses
        ArrayAdapter ad1 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, month1);
        // set simple layout resource file
        // for each item of spinner
        ad1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        month.setAdapter(ad1);
        workmonth.setAdapter(ad1);
        month.setOnItemSelectedListener(User_Details_page.this);
        workmonth.setOnItemSelectedListener(User_Details_page.this);


        Calendar today = Calendar.getInstance();
//        final Calendar twoDaysAgo = (Calendar) today.clone();
//        twoDaysAgo.add(Calendar.DATE, -1);
        final Calendar twoDaysLater = (Calendar) today.clone();
        twoDaysLater.add(Calendar.DATE, 0);

        final Calendar c = Calendar.getInstance();
        final int year = c.get(Calendar.YEAR);
        // Get year
        //Get month
        final int month = c.get(Calendar.MONTH);
        //Get day
        final int day = c.get(Calendar.DAY_OF_MONTH);
        //Current Date
        // final String currentdate = DateFormat.getDateInstance(DateFormat.FULL).format(c.getTime());
       // String current = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(new Date());

        TextView datepicker = (TextView) findViewById(R.id.date);
        //datepicker.setText(current);

        datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(User_Details_page.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int monthofYear, int dayofMonth) {

                                datepicker.setText(dayofMonth + "/" + (monthofYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                datePickerDialog.getDatePicker().setMaxDate(twoDaysLater.getTimeInMillis());
                datePickerDialog.show();
            }
        });


        // Finding CheckBox by its unique ID
        ch=(CheckBox)findViewById(R.id.checkBox);
        ch1=(CheckBox)findViewById(R.id.checkBox1);
        ch2=(CheckBox)findViewById(R.id.checkBox2);
        ch3  =(CheckBox)findViewById(R.id.checkBox3);
        exper=(CheckBox)findViewById(R.id.experience);
        fresher = (CheckBox)findViewById(R.id.fresher);
        TextView precompany = (TextView)findViewById(R.id.precompany);
        TextView workexp = (TextView)findViewById(R.id.workexp);
        EditText previouscompany = (EditText)findViewById(R.id.previouscompany);
        Spinner workyear = (Spinner)findViewById(R.id.workyear);
        Spinner workmonth = (Spinner)findViewById(R.id.workmonth);
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.spinnerdate);
        TextView total = findViewById(R.id.TOTAL_EXP);
        LinearLayout linearLayout1 = findViewById(R.id.TOTAL_EXP_LAYOUT);
        LinearLayout linearLayout2 = findViewById(R.id.Expoverall);
        Spinner spinner1 = findViewById(R.id.workyear1);
        Spinner spinner2 = findViewById(R.id.workmonth1);
        ////////////// college information
        TextView collegeinformation = findViewById(R.id.College_information);

        TextView collegename = findViewById(R.id.college_name);
        EditText Ecollegename = findViewById(R.id.college_name_edit);

        TextView CollegeDegree = findViewById(R.id.college_degree);
        TextView TCollegeDegree = findViewById(R.id.college_degree_edit);

        TextView YearOfPastout = findViewById(R.id.yearofpastout);
        LinearLayout startend = findViewById(R.id.colleg_lin);
        TextView start = findViewById(R.id.startyear);
        TextView end = findViewById(R.id.endyaer);

        LinearLayout college = findViewById(R.id.college);



        ch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){

                    collegeinformation.setVisibility(View.VISIBLE);
                    collegename.setVisibility(View.VISIBLE);
                    Ecollegename.setVisibility(View.VISIBLE);
                    CollegeDegree.setVisibility(View.VISIBLE);
                    TCollegeDegree.setVisibility(View.VISIBLE);
                    YearOfPastout.setVisibility(View.VISIBLE);
                    startend.setVisibility(View.VISIBLE);
                    start.setVisibility(View.VISIBLE);
                    end.setVisibility(View.VISIBLE);
                    college.setVisibility(View.VISIBLE);

                }else {

                    collegeinformation.setVisibility(View.INVISIBLE);
                    collegename.setVisibility(View.INVISIBLE);
                    Ecollegename.setVisibility(View.INVISIBLE);
                    CollegeDegree.setVisibility(View.INVISIBLE);
                    TCollegeDegree.setVisibility(View.INVISIBLE);
                    YearOfPastout.setVisibility(View.INVISIBLE);
                    startend.setVisibility(View.INVISIBLE);
                    start.setVisibility(View.INVISIBLE);
                    end.setVisibility(View.INVISIBLE);
                    college.setVisibility(View.INVISIBLE);
                    collegeinformation.setVisibility(View.GONE);
                    collegename.setVisibility(View.GONE);
                    Ecollegename.setVisibility(View.GONE);
                    CollegeDegree.setVisibility(View.GONE);
                    TCollegeDegree.setVisibility(View.GONE);
                    YearOfPastout.setVisibility(View.GONE);
                    startend.setVisibility(View.GONE);
                    start.setVisibility(View.GONE);
                    end.setVisibility(View.GONE);
                    college.setVisibility(View.GONE);


                }
            }
        });



        exper.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                                                 @Override
                                                 public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                                                     if (isChecked) {
                                                         precompany.setVisibility(View.VISIBLE);
                                                         previouscompany.setVisibility(View.VISIBLE);
                                                         workexp.setVisibility(View.VISIBLE);
                                                         workyear.setVisibility(View.VISIBLE);
                                                         workmonth.setVisibility(View.VISIBLE);
                                                         linearLayout.setVisibility(View.VISIBLE);
                                                         total.setVisibility(View.VISIBLE);
                                                         linearLayout1.setVisibility(View.VISIBLE);
                                                         linearLayout2.setVisibility(View.VISIBLE);
                                                         spinner1.setVisibility(View.VISIBLE);
                                                         spinner2.setVisibility(View.VISIBLE);
//                                                         project.setVisibility(View.GONE);
//                                                         ProjectName.setVisibility(View.GONE);
                                                     } else {
                                                         precompany.setVisibility(View.INVISIBLE);
                                                         previouscompany.setVisibility(View.INVISIBLE);
                                                         precompany.setVisibility(View.GONE);
                                                         previouscompany.setVisibility(View.GONE);
                                                         workexp.setVisibility(View.INVISIBLE);
                                                         workyear.setVisibility(View.INVISIBLE);
                                                         workmonth.setVisibility(View.INVISIBLE);
                                                         workexp.setVisibility(View.GONE);
                                                         workyear.setVisibility(View.GONE);
                                                         workmonth.setVisibility(View.GONE);
                                                         linearLayout.setVisibility(View.INVISIBLE);
                                                         linearLayout.setVisibility(View.GONE);
                                                         total.setVisibility(View.INVISIBLE);
                                                         linearLayout1.setVisibility(View.INVISIBLE);
                                                         linearLayout2.setVisibility(View.INVISIBLE);
                                                         spinner1.setVisibility(View.INVISIBLE);
                                                         spinner2.setVisibility(View.INVISIBLE);
                                                         total.setVisibility(View.GONE);
                                                         linearLayout1.setVisibility(View.GONE);
                                                         linearLayout2.setVisibility(View.GONE);
                                                         spinner1.setVisibility(View.GONE);
                                                         spinner2.setVisibility(View.GONE);
//                                                         typeProject.setVisibility(View.GONE);
//                                                         project.setVisibility(View.VISIBLE);
//                                                         ProjectName.setVisibility(View.VISIBLE);
                          }
                     }
        });

    }



//    private void initUI() {
//
//        ch=(CheckBox)findViewById(R.id.checkBox);
//        ch1=(CheckBox)findViewById(R.id.checkBox1);
//        ch2=(CheckBox)findViewById(R.id.checkBox2);
//        ch3  =(CheckBox)findViewById(R.id.checkBox3);
//        sumbitt = (Button)findViewById(R.id.sumbitt);
//
//        sumbitt.setOnClickListener(this);
//
//    }

//    private JSONObject generateJSON(){
//        JSONObject jsonObjectChoices = new JSONObject();
//        try {
//            if(ch.isChecked()){
//                jsonObjectChoices.put("Dine-In","YES");
//            }else {
//                jsonObjectChoices.put("Dine-In","NO");
//            }
//            if(ch1.isChecked()){
//                jsonObjectChoices.put("Take-Out","YES");
//            }else {
//                jsonObjectChoices.put("Take-Out","NO");
//            }
//            if(ch2.isChecked()){
//                jsonObjectChoices.put("Delivery","YES");
//            }else {
//                jsonObjectChoices.put("Delivery","NO");
//            }
//            if(ch3.isChecked()){
//                jsonObjectChoices.put("Delivery","YES");
//            }else {
//                jsonObjectChoices.put("Delivery","NO");
//            }
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//        return jsonObjectChoices;
//    }

    private void Sumbit() {


        RequestQueue requestQueue = Volley.newRequestQueue(User_Details_page.this);
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.
                Log.d("Response", response);

                try {
                    JSONObject object = new JSONObject(response);
                    JSONObject stringg = object.getJSONObject("data");
                    String string = stringg.getString("msg");

                            /*JSONObject jsonObject = stringg.getJSONObject("details");
                            String id = object.getString("id");
                            String name = object.getString("name");
                            String about = object.getString("about");


*/

//                            editor.putString("UserID", object.getString("id"));
//                            editor.putBoolean("IsLogin", true);
//                            editor.commit();
//                            editor.apply();
//                            Log.e("TAGNEW", String.valueOf(stringg));

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
                MyData.put("aadhar", aadharnumber1);
                MyData.put("name", name1);
                MyData.put("email", email1);
                MyData.put("exp_year", year11);
                MyData.put("exp_month", month11);
                MyData.put("company_name", companyname1);
                MyData.put("address",address1);
                MyData.put("gender",radioButton1);
                MyData.put("dob",datepicker1);
                MyData.put("education",cch);
                MyData.put("experience",exper2);
                MyData.put("fresher",fresher1);
                MyData.put("college_name", collegename1);
                MyData.put("degree_name", collegedegree1);
                MyData.put("degree_join_year",pastoutstartyesr1);
                MyData.put("degree_compl_year",pastoutendyear1);

                return MyData;
            }
        };

        // RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(MyStringRequest);
    }

//    // This function is invoked when the button is pressed.
//    public void Check(View v)
//    {
//        String msg="";
//
//        // Concatenation of the checked options in if
//
//        // isChecked() is used to check whether
//        // the CheckBox is in true state or not.
//
//        if(ch.isChecked())
//            msg = msg + " Painting ";
//        if(ch1.isChecked())
//            msg = msg + " Reading ";
//        if(ch2.isChecked())
//            msg = msg + " Singing ";
//        if(ch3.isChecked())
//            msg = msg + " Cooking ";
//
//        // Toast is created to display the
//        // message using show() method.
//        Toast.makeText(this, msg + "are selected",
//                Toast.LENGTH_LONG).show();
//    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void onclickcheckbox(View view) {
        boolean checked = ((CheckBox) view).isChecked();
        switch (view.getId()){
            case R.id.checkBox:
            if (checked) {
                Log.d("Checkbox1", "checked");
            }
            else {
                Log.d("Checkbox1", "unchecked");
            }
            break;
            case R.id.checkBox1:
                if (checked) {
                    Log.d("Checkbox2", "checked");
                }
                else {
                    Log.d("Checkbox2", "unchecked");
                }
                break;
            case R.id.checkBox2:
                if (checked) {
                    Log.d("Checkbox3", "checked");
                }
                else {
                    Log.d("Checkbox3", "unchecked");
                }
                break;
            case R.id.checkBox3:
                if (checked) {
                    Log.d("Checkbox4", "checked");
                }
                else {
                    Log.d("Checkbox4", "unchecked");
                }
                break;
            case R.id.fresher:
                if (checked) {
                    Log.d("Checkbox5", "checked");
                }
                else {
                    Log.d("Checkbox5", "unchecked");
                }
                break;
            case R.id.exper:
                if (checked) {
                    Log.d("Checkbox6", "checked");
                }
                else {
                    Log.d("Checkbox6", "unchecked");
                }
                break;
        }
    }

    @Override
    public void onpositivebuttonclick(String[] list, int position) {

        textView.setText(list[position]);

    }

    @Override
    public void onnagativebuttonclicked() {

//        textView.setText("");

    }

    public void StartYear(View view) {
            final Calendar today = Calendar.getInstance();
        MonthPickerDialog.Builder builder = new MonthPickerDialog.Builder(User_Details_page.this,
                new MonthPickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(int selectedMonth, int selectedYear) {

                        startyear.setText(String.valueOf(selectedYear));
                    }
                }, today.get(Calendar.YEAR), today.get(Calendar.MONTH));

          builder.setActivatedMonth(Calendar.JULY)
                  .setMinYear(1990)
                  .setActivatedYear(today.get(Calendar.YEAR))
                  .setMaxYear(2030)
                  .setTitle("Select Year")
                  .showYearOnly()
                  .build().show();

        }


    public void EndYear(View view) {

        final Calendar today = Calendar.getInstance();
        MonthPickerDialog.Builder builder = new MonthPickerDialog.Builder(User_Details_page.this,
                new MonthPickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(int selectedMonth, int selectedYear) {

                        endyear.setText(String.valueOf(selectedYear));
                    }
                }, today.get(Calendar.YEAR), today.get(Calendar.MONTH));

        builder.setActivatedMonth(Calendar.JULY)
                .setMinYear(1900)
                .setActivatedYear(today.get(Calendar.YEAR))
                .setMaxYear(2030)
                .setTitle("Select Year")
                .showYearOnly()
                .build().show();
    }


    public static class Message{

        public static void message (Context context, String mesg){
            Toast.makeText(context,mesg,Toast.LENGTH_SHORT).show();
        }
    }
//
//    @Override
//    public void onClick(View v) {
//        generateJSON();
//

//        switch (v.getId()) {
//            case R.id.checkBox:
//                if (ch.isChecked())
//                    Toast.makeText(getApplicationContext(), "Android", Toast.LENGTH_LONG).show();
//                break;
//            case R.id.checkBox1:
//                if (ch1.isChecked())
//                    Toast.makeText(getApplicationContext(), "Java", Toast.LENGTH_LONG).show();
//                break;
//            case R.id.checkBox2:
//                if (ch2.isChecked())
//                    Toast.makeText(getApplicationContext(), "PHP", Toast.LENGTH_LONG).show();
//                break;
//            case R.id.checkBox3:
//                if (ch3.isChecked())
//                    Toast.makeText(getApplicationContext(), "Python", Toast.LENGTH_LONG).show();
//                break;
//
//        }


}