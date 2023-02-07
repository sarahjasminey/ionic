package com.user.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class Home_page extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

//        MyProfile_Fragment myProfile_fragment = new MyProfile_Fragment();
//        FragmentManager fm = getSupportFragmentManager();
//
//        fm.beginTransaction().add(R.id.drawer_layout1,myProfile_fragment).commit();




        drawerLayout = findViewById(R.id.drawer_layout1);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        toolbar = findViewById(R.id.toolbarr);
        setSupportActionBar(toolbar);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        // getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        if (savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Home()).commit();
            navigationView.setCheckedItem(R.id.myHome);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_item,menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.myHome:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Home()).commit();
//                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                break;
            case R.id.myprofile:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new MyProfile_Fragment()).commit();
                MyProfile_Fragment myProfile_fragment = new MyProfile_Fragment();
                FragmentManager fm = getSupportFragmentManager();
                fm.beginTransaction().add(R.id.fragment_container,myProfile_fragment).commit();

//                Toast.makeText(this, "Home", Toast.LENGTH_SHORT).show();
                break;
//            case R.id.mycart:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new Wallet_Fragment()).commit();
////                Toast.makeText(this,"MY CART",Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.myfav:
//                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new SettingFragment()).commit();
////                Toast.makeText(this,"MY Fav",Toast.LENGTH_SHORT).show();
//                break;

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setSupportActionBar(Toolbar toolbar) {
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.item1:
                Toast.makeText(this,"ITEM 1",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item2:
                Toast.makeText(this,"ITEM 2",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item3:
                Toast.makeText(this,"ITEM 3",Toast.LENGTH_SHORT).show();
                return true;
            case R.id.item4:
                Toast.makeText(this,"ITEM 4",Toast.LENGTH_SHORT).show();
                return true;
        }
        if (toggle.onOptionsItemSelected(item)){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}