package com.example.teddy_2;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class Tablelayoutpager extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    int tabCount;
    public Tablelayoutpager(@NonNull FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        switch (position) {
            case 0:
                Work_Histroy tab1 = new Work_Histroy();
                return tab1;
            case 1:
                Reedam_Amount tab2 = new Reedam_Amount();
                return tab2;
            default:
                return null;
        }
    }
    @Override
    public int getCount() {
        return tabCount;
    }
}
