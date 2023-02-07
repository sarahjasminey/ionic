package com.user.myapplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class categories_Adapter extends ArrayAdapter<categories> {


    //the hero list that will be displayed
    private static List<categories> downlodeList;
    private static ArrayList<categories> arraylist;
    String image3_url;

    //the context object
    private Context mCtx;
    public categories_Adapter(@NonNull List<categories> downlodeList, Context mCtx) {
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
        LinearLayout  layout_container = listViewItem.findViewById(R.id.layout_container);
        // TextView imageView = listViewItem.findViewById(R.id.Downlodeicon);

        //Getting the hero for the specified position
        final categories hero = downlodeList.get(position);

        //setting hero values to textviews
            textViewName.setText(hero.getFilename());
        //downl.image(hero.getImage());

        Glide.with(mCtx)
                .load("https://teddiapp.com/app/assets/admin/image/category/" + hero.getImage())
                .fitCenter()
                .dontAnimate() // run ji
                .into(downl);

        layout_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(mCtx,Allcate_SecondActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("CategoryID",hero.getFileID());
                mCtx.startActivity(intent);

            }
        });
        return listViewItem;

    };

    // Filter Class
    public static void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        downlodeList.clear();
        if (charText.length() == 0) {
            downlodeList.addAll(arraylist);
        } else {
            for (categories wp : arraylist) {
                if (wp.getFilename().toLowerCase(Locale.getDefault()).contains(charText)) {
                    downlodeList.add(wp);
                }
            }
        }
    }
}
