
package com.user.myapplication;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import com.google.android.libraries.places.api.model.Place;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.libraries.places.api.model.TypeFilter;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.model.AutocompleteActivityMode;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.io.IOException;
import java.util.List;

public class Bottomsheet extends BottomSheetDialogFragment {


    private String apikey = "AIzaSyACnr4sERwrbWWJOto6DNw6ZaRCf7WSiUM";
    TextView txtInfo;
    EditText search_place;
    private static int AUTOCOMPLETE_REQUEST_CODE = 1;
    List<Place.Field> fields;
    String latitude="",longitude="";
    private GoogleMap map;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable
            ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottom_sheet_layout,
                container, false);

        search_place = (EditText)v.findViewById(R.id.editcurrentlocation1);

        TextView search = (TextView)v.findViewById(R.id.search);

        // Set the fields to specify which types of place data to
        // return after the user has made a selection.
        // fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.ADDRESS, Place.Field.LAT_LNG);

        search_place.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Autocomplete.IntentBuilder(
                        AutocompleteActivityMode.FULLSCREEN, fields)
                        .setTypeFilter(TypeFilter.ADDRESS)
                        .build(getActivity());
                startActivityForResult(intent, AUTOCOMPLETE_REQUEST_CODE);
            }
        });


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getActivity(),Currentpage.class);
                startActivity(intent);

                EditText locationSearch = (EditText) v.findViewById(R.id.editcurrentlocation1);
                String location = locationSearch.getText().toString();
                List<Address> addressList = null;

                if (location != null || !location.equals("")) {
                    Geocoder geocoder = new Geocoder(getActivity());
                    try {
                        addressList = geocoder.getFromLocationName(location, 1);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Address address = addressList.get(0);
                    LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                    map.addMarker(new MarkerOptions().position(latLng).title(location));
                    map.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                    Toast.makeText(getActivity(), address.getLatitude() + " " + address.getLongitude(), Toast.LENGTH_LONG).show();
                }


            }
        });

      /*  // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.currentloaction);
        mapFragment.getMapAsync(this);*/


        TextView usecurrentlocation = v.findViewById(R.id.usecurrentlocation);

        usecurrentlocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Currentpage.class);
                startActivity(intent);
            }
        });

        ImageView algo_text = v.findViewById(R.id.backicon);

        algo_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return v;

    }

/*
    @Override
    public void onMapReady(GoogleMap googleMap) {

        map=googleMap;
        LatLng sydney = new LatLng(-33.852, 151.211);
        map.addMarker(new MarkerOptions().position(sydney)
                .title("Marker in Sydney"));
        map.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == AUTOCOMPLETE_REQUEST_CODE){


          //  if (resultCode == RESULT_)
        }

    }*/



       /* public void searchLocation (View view) {
            EditText locationSearch = (EditText) view.findViewById(R.id.editcurrentlocation1);
            String location = locationSearch.getText().toString();
            List<Address> addressList = null;

            if (location != null || !location.equals("")) {
                Geocoder geocoder = new Geocoder(getActivity());
                try {
                    addressList = geocoder.getFromLocationName(location, 1);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                Address address = addressList.get(0);
                LatLng latLng = new LatLng(address.getLatitude(), address.getLongitude());
                map.addMarker(new MarkerOptions().position(latLng).title(location));
                map.animateCamera(CameraUpdateFactory.newLatLng(latLng));
                Toast.makeText(getActivity(), address.getLatitude() + " " + address.getLongitude(), Toast.LENGTH_LONG).show();
            }


        }*/

}





