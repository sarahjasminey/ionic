package com.example.teddy_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Terms_Adapter extends ArrayAdapter<Terms_Bojo> {

    //the hero list that will be displayed
    private static List<Terms_Bojo> List1;
    //the context object
    private Context mCtx;

    public Terms_Adapter(@NonNull List<Terms_Bojo> downlodeList, Context mCtx) {
        super(mCtx, R.layout.terms_privicy, downlodeList);
        this.List1 = downlodeList;
        this.mCtx = mCtx;
    }

    //this method will return the list item
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //getting the layoutinflater
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        //creating a view with our xml layout
        View listViewItem = inflater.inflate(R.layout.terms_privicy, null, true);

        //getting text views
        TextView textViewName = listViewItem.findViewById(R.id.TCdescr);
        TextView textViewImageUrl = listViewItem.findViewById(R.id.TCdescr1);

        //Getting the hero for the specified position
        Terms_Bojo hero = List1.get(position);

        //setting hero values to textviews

        textViewName.setText(hero.getTerm_descri());
        textViewImageUrl.setText(hero.getPrivecy_descri());

        //returning the listitem
        return listViewItem;
    }
}
