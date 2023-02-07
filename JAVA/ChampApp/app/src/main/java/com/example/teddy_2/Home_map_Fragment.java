package com.example.teddy_2;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.agik.AGIKSwipeButton.Controller.OnSwipeCompleteListener;
import com.agik.AGIKSwipeButton.View.Swipe_Button_View;
import com.ebanx.swipebtn.OnStateChangeListener;
import com.ebanx.swipebtn.SwipeButton;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Locale;

import me.zhanghai.android.materialprogressbar.MaterialProgressBar;


public class Home_map_Fragment extends Fragment implements OnMapReadyCallback, LocationListener, GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener {

    LocationManager locationManager;
    private static final int REQUEST_LOCATION = 1;

    private GoogleMap mMap;
    Location mLastLocation;
    Marker mCurrLocationMarker;
    GoogleApiClient mGoogleApiClient;
    LocationRequest mLocationRequest;

    @TargetApi(Build.VERSION_CODES.O)
    MaterialProgressBar materialProgressBar;
    double progress;
    int endTime = 100;
    Button skipbutton;



    public static Home_map_Fragment newInstance() {
        Home_map_Fragment fragment = new Home_map_Fragment();
        return fragment;
    }

    public Home_map_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_home_map_, container, false);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) this.getChildFragmentManager().findFragmentById(R.id.homemap);
        mapFragment.getMapAsync(this);


        SwipeButton swipeButton = v.findViewById(R.id.sb_swipe);
        SwipeButton swipeButton1 = v.findViewById(R.id.sb_swipe2);
        SwipeButton swipeButton2 = v.findViewById(R.id.sb_swipe3);
        ImageView imageView = v.findViewById(R.id.lo1);
        ImageView imageView1 = v.findViewById(R.id.lo2);
        RelativeLayout relativeLayout = v.findViewById(R.id.re2);
        RelativeLayout relativeLayout1 = v.findViewById(R.id.timer);
        Button accept = v.findViewById(R.id.Accept_button);
        skipbutton  = v.findViewById(R.id.skip);






        skipbutton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                relativeLayout1.setVisibility(View.GONE);
            }
        });


        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                relativeLayout.setVisibility(View.VISIBLE);
                relativeLayout1.setVisibility(View.INVISIBLE);
                relativeLayout1.setVisibility(View.GONE);
            }
        });

        swipeButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        swipeButton2.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(boolean active) {

      Intent intent = new Intent(getActivity(),Collect_Your_Payment.class);
      startActivity(intent);


            }
        });


        swipeButton1.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(boolean active) {

//                 startActivity(new Intent(getActivity(),OTP_Page.class));

                swipeButton1.setVisibility(View.INVISIBLE);
                swipeButton1.setVisibility(View.GONE);
                swipeButton2.setVisibility(View.VISIBLE);
                imageView.setVisibility(View.INVISIBLE);
                imageView.setVisibility(View.GONE);
                imageView1.setVisibility(View.VISIBLE);


            }
        });

        swipeButton.setOnStateChangeListener(new OnStateChangeListener() {
            @Override
            public void onStateChange(boolean active) {

                swipeButton.setVisibility(View.INVISIBLE);
                swipeButton.setVisibility(View.GONE);
                swipeButton1.setVisibility(View.VISIBLE);
                // startActivity(new Intent(Navigation_Drawer.this,Current_Location.class));

            }
        });

        View fragment = v.findViewById(R.id.homemap);


        //  relativeLayout1.setVisibility(View.VISIBLE);
        relativeLayout1.postDelayed(new Runnable() {
            public void run() {
                relativeLayout1.setVisibility(View.VISIBLE);
            }
        }, 7000);

        final TextView counttime = v.findViewById(R.id.counttime);

        materialProgressBar = v.findViewById(R.id.material_pro);
        progress = 3.4;
        materialProgressBar.setSecondaryProgress(endTime);
        materialProgressBar.setProgress(0);


        new CountDownTimer(30000, 1000) {

            public void onTick(long millisUntilFinished) {
                setProgress((int) progress, endTime);
                progress = progress + 3.4;
                counttime.setText("" + millisUntilFinished / 1000);
                //here you can have your logic to set text to edittext
            }

            public void onFinish() {
                counttime.setText("done!");
                setProgress((int) progress, endTime);
                relativeLayout1.setVisibility(View.INVISIBLE);
                relativeLayout1.setVisibility(View.GONE);
            }

        }.start();

        //statusSwitch1 = simpleSwitch1.getTextOn().toString();


        return  v;
    }

    public void setProgress(int startTime, int endTime) {
        materialProgressBar.setMax(endTime);
        materialProgressBar.setSecondaryProgress(endTime);
        materialProgressBar.setProgress(startTime);
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {

        mLocationRequest = new LocationRequest();
        mLocationRequest.setInterval(1000);
        mLocationRequest.setFastestInterval(1000);
        mLocationRequest.setPriority(LocationRequest.PRIORITY_BALANCED_POWER_ACCURACY);
        if (ContextCompat.checkSelfPermission(getActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            LocationServices.FusedLocationApi.requestLocationUpdates(mGoogleApiClient, mLocationRequest, this);
        }

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {


      //  Toast.makeText(getActivity(),""+location.getLatitude()+""+location.getLongitude(),Toast.LENGTH_SHORT).show();

        try {
            Geocoder geocoder = new Geocoder(getActivity(), Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(),location.getLongitude(),1);
           // String string = addresses.get(0).getAddressLine(0);

           // showLocation.setText(string);

        }catch (Exception e){
            e.printStackTrace();
        }


        mLastLocation = location;
        if (mCurrLocationMarker != null) {
            mCurrLocationMarker.remove();
        }
        //Place current location marker
        LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
//        MarkerOptions markerOptions = new MarkerOptions();
//        markerOptions.position(latLng);
//        markerOptions.title("Current Position");
//        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
//        mCurrLocationMarker = mMap.addMarker(markerOptions);

        //move map camera
        mMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(12));

        //stop location updates
        if (mGoogleApiClient != null){
            LocationServices.FusedLocationApi.removeLocationUpdates(mGoogleApiClient,this);
        }



    }

    protected synchronized void buildGoogleApiClient() {
        mGoogleApiClient = new GoogleApiClient.Builder(getActivity())
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this)
                .addApi(LocationServices.API).build();
        mGoogleApiClient.connect();
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {
                buildGoogleApiClient();
                mMap.setMyLocationEnabled(true);
            }
        }
        else {
            buildGoogleApiClient();
            mMap.setMyLocationEnabled(true);
        }

    }
}