package com.user.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.model.Place;

import java.io.IOException;
import java.util.List;

public class Thirdpage extends AppCompatActivity {

    //TextView textView;

    private String apikey = "AIzaSyACnr4sERwrbWWJOto6DNw6ZaRCf7WSiUM";
    TextView txtInfo;
    EditText search_place;
    private static int AUTOCOMPLETE_REQUEST_CODE = 1;
    List<Place.Field> fields;
    String latitude="",longitude="";
    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thirdpage);

        search_place = (EditText)findViewById(R.id.editcurrentlocation1);

//
//        EditText locationSearch = (EditText) findViewById(R.id.editcurrentlocation1);
//        locationSearch.setInputType(InputType.TYPE_CLASS_TEXT| InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);


        TextView search1 = (TextView)findViewById(R.id.search1);

        // Set the fields to specify which types of place data to
        // return after the user has made a selection.
        // fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG);

        search_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Autocomplete.IntentBuilder(
//                        AutocompleteActivityMode.FULLSCREEN, fields)
//                        .setTypeFilter(TypeFilter.ADDRESS)
//                        .build(Thirdpage.this);
                Intent intent = new Intent(Thirdpage.this,Currentpage.class);
                startActivity(intent);
              //  startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
            }
        });

        ImageView algo_text = findViewById(R.id.backicon);

        algo_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Thirdpage.this,Currentpage.class);
                startActivity(intent);
            }
        });

        search1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                EditText locationSearch = (EditText) findViewById(R.id.editcurrentlocation1);
               // locationSearch.setInputType(InputType.TYPE_CLASS_TEXT| InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);



                String location = locationSearch.getText().toString();
                List<Address> addressList = null;

                if (location != null || !location.equals("")) {
                    Geocoder geocoder = new Geocoder(Thirdpage.this);
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Address address = addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    map.addMarker(new MarkerOptions().position(latLng).title(location));
                    map.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                    Toast.makeText(Thirdpage.this, address.getLatitude() + " " + address.getLongitude(), Toast.LENGTH_LONG).show();
                }


            }
        });

        TextView usecurrentlocation = findViewById(R.id.usecurrentlocation);

        usecurrentlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Thirdpage.this, Currentpage.class);
                startActivity(intent);
            }
        });



    }
}