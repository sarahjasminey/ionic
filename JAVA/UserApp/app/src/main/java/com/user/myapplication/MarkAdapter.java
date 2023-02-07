package com.user.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

public class MarkAdapter extends ArrayAdapter<markpojo> {

    //the hero list that will be displayed
    private List<markpojo> downlodeList;
    String image3_url;

    //the context object
    private Context mCtx;
    public MarkAdapter(@NonNull List<markpojo> downlodeList, Context mCtx) {
        super(mCtx, R.layout.categorielist, downlodeList);
        this.downlodeList = downlodeList;
        this.mCtx = mCtx;
    }

    public View getView(int position, View view, ViewGroup parent) {
        // LayoutInflater inflater=context1.getLayoutInflater();
        // View rowView=inflater.inflate(R.layout.downlodelist, null,true);


        // TextView pono = (TextView) rowView.findViewById(R.id.filename);


        // pono.setText(filename[position]);

        //  return rowView;
        //getting the layoutinflater
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        //creating a view with our xml layout
        View listViewItem = inflater.inflate(R.layout.categorielist, null, true);

        //getting text views
        TextView textViewName = listViewItem.findViewById(R.id.filename);
        ImageView downloadImag = listViewItem.findViewById(R.id.Dowicon3);
        ImageView downl = listViewItem.findViewById(R.id.Dowicon2);
        LinearLayout layout_container = listViewItem.findViewById(R.id.layout_container);
        // TextView imageView = listViewItem.findViewById(R.id.Downlodeicon);

        //Getting the hero for the specified position
        final markpojo hero = downlodeList.get(position);

        //setting hero values to textviews
        //textViewName.setText(hero.getFilename());
        //downl.image(hero.getImage());




        return listViewItem;

    }
}
