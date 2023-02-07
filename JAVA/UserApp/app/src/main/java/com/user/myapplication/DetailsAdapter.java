package com.user.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

import java.util.List;

public class DetailsAdapter extends ArrayAdapter<Detail> {

    private Context mCtx;
    private List<Detail> heroList;

    public DetailsAdapter(@NonNull List<Detail> heroList, Context mCtx) {
        super(mCtx, R.layout.detailslist, heroList);
        this.heroList = heroList;
        this.mCtx = mCtx;
    }

    public View getView(int position, View view, ViewGroup parent) {
        // LayoutInflater inflater = context1.getLayoutInflater();
        // View rowView = inflater.inflate(R.layout.detailslist, null, true);

        //  TextView titleText = (TextView) rowView.findViewById(R.id.productname);
        // TextView subtitleText = (TextView) rowView.findViewById(R.id.HsnNo);
        // TextView pono = (TextView) rowView.findViewById(R.id.WarrantyDate);

        //  titleText.setText(productname[position]);
        //  subtitleText.setText(hsnno[position]);
        //  pono.setText(warrantydate[position]);

        //getting the layoutinflater
        final LayoutInflater inflater = LayoutInflater.from(mCtx);

        //creating a view with our xml layout
        View listViewItem = inflater.inflate(R.layout.detailslist, null, true);

        //getting text views
//        TextView textViewName = listViewItem.findViewById(R.id.Gardener1);


        final Detail hero = heroList.get(position);

        //setting hero values to textviews
      //  textViewName.setText(hero.getProductname());


        return listViewItem;
    }
}
