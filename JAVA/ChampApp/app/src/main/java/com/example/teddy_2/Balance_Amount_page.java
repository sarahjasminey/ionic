package com.example.teddy_2;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TableLayout;

import com.google.android.material.tabs.TabLayout;

import java.util.List;

public class Balance_Amount_page extends AppCompatActivity{

//    //This is our tablayout
//    private TabLayout tabLayout;
//
//
//    ImageView imageView;
//
//    Button cia1,cia2,model,assaignment,attedance;
//
//    ListView listView;
//    ActionBarDrawerToggle toggle;
//
//   // List<Time_Table_setget> hero;
//
//    //This is our viewPager
//    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_balance__amount_page);


      //  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar1);
       // setSupportActionBar(toolbar);
//
//        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
//
//        tabLayout.addTab(tabLayout.newTab().setText("Work History"));
//        tabLayout.addTab(tabLayout.newTab().setText("Reedam History"));
//
//        //Initializing viewPager
//        viewPager = (ViewPager) findViewById(R.id.pager);
//
//
//
//        //Creating our pager adapter
//        Tablelayoutpager adapter1 = new Tablelayoutpager(getSupportFragmentManager(), tabLayout.getTabCount());
//
//
//        //Adding adapter to pager
//        viewPager.setAdapter(adapter1);
//
//        viewPager.setOnPageChangeListener(
//                new ViewPager.SimpleOnPageChangeListener() {
//                    @Override
//                    public void onPageSelected(int position) {
//                        tabLayout.getTabAt(position).select();
//                    }
//                });
//
//
//        //Adding onTabSelectedListener to swipe views
//        tabLayout.setOnTabSelectedListener(this);
//
//    }
//
//    @Override
//    public void onTabSelected(TabLayout.Tab tab) {
//        viewPager.setCurrentItem(tab.getPosition());
//    }
//
//    @Override
//    public void onTabUnselected(TabLayout.Tab tab) {
//
//    }
//
//    @Override
//    public void onTabReselected(TabLayout.Tab tab) {
//
//    }
}}