package com.example.teddy_2;

import android.os.Bundle;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;

import com.google.android.material.tabs.TabLayout;

public class Wallet_page extends Fragment implements TabLayout.OnTabSelectedListener {

    //This is our tablayout
    private TabLayout tabLayout;


    ImageView imageView;

    Button cia1,cia2,model,assaignment,attedance;

    ListView listView;
    ActionBarDrawerToggle toggle;

    // List<Time_Table_setget> hero;

    //This is our viewPager
    private ViewPager viewPager;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_wallet_page, container, false);


        tabLayout = (TabLayout) view.findViewById(R.id.tabLayout);

        tabLayout.addTab(tabLayout.newTab().setText("Work History"));
        tabLayout.addTab(tabLayout.newTab().setText("Reedam History"));

        //Initializing viewPager
        viewPager = (ViewPager) view.findViewById(R.id.pager);



        //Creating our pager adapter
        Tablelayoutpager adapter1 = new Tablelayoutpager(getActivity().getSupportFragmentManager(), tabLayout.getTabCount());


        //Adding adapter to pager
        viewPager.setAdapter(adapter1);

        viewPager.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        tabLayout.getTabAt(position).select();
                    }
                });


        //Adding onTabSelectedListener to swipe views
        tabLayout.setOnTabSelectedListener(this);



        return view;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }
}