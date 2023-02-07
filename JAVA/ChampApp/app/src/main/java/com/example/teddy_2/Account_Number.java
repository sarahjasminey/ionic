package com.example.teddy_2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class Account_Number extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_account__number, container, false);


        Button button = view.findViewById(R.id.CONFIRM);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Home_map_Fragment home = new Home_map_Fragment();
//                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.fragment_container, home);
//                fragmentTransaction.commit();

            }
        });

        return view;
    }
}