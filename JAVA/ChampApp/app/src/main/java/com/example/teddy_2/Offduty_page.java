package com.example.teddy_2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;


public class Offduty_page extends Fragment {


//    Switch simpleSwitch1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_offduty_page, container, false);

        RelativeLayout relativeLayout = view.findViewById(R.id.total_earning);
        HorizontalScrollView scrollView = view.findViewById(R.id.total_Scroll);
        TextView textView = view.findViewById(R.id.alert1);
        ImageView imageView = view.findViewById(R.id.alert);
//        simpleSwitch1 = (Switch) view.findViewById(R.id.techknow1);
        FrameLayout drawerLayout = view.findViewById(R.id.fragment_container);
        final TextView switchBtn_txtView = (TextView) view.findViewById(R.id.switchBtn_txtView);
//
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scrollView.setVisibility(View.VISIBLE);
                relativeLayout.setVisibility(View.INVISIBLE);
                relativeLayout.setVisibility(View.GONE);
            }
        });


//        simpleSwitch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                if (isChecked){
//
//                    drawerLayout.setVisibility(View.VISIBLE);
//                    switchBtn_txtView.setText("ON DUTY");
//                    imageView.setVisibility(View.INVISIBLE);
//                    textView.setVisibility(View.INVISIBLE);
//                    imageView.setVisibility(View.GONE);
//                    textView.setVisibility(View.GONE);
//
//                }
//                else{
//                    drawerLayout.setVisibility(View.INVISIBLE);
//                    drawerLayout.setVisibility(View.GONE);
//                    imageView.setVisibility(View.VISIBLE);
//                    textView.setVisibility(View.VISIBLE);
//                    switchBtn_txtView.setText("OFF DUTY");
////                    relativeLayout1.setVisibility(View.INVISIBLE);
////                    relativeLayout1.setVisibility(View.GONE);
//                    //statusSwitch1 = simpleSwitch1.getTextOff().toString();
//                }
//                // Toast.makeText(getApplicationContext(), "Switch1 :" + statusSwitch1 + "\n", Toast.LENGTH_LONG).show();
//
//            }
//        });





        return view;
    }
}