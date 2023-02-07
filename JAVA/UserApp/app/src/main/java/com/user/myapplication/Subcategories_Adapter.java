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

import java.util.List;

public class Subcategories_Adapter extends ArrayAdapter<categories> {
    //the hero list that will be displayed
    private List<categories> downlodeList;


    //the context object
    private Context mCtx;
    public Subcategories_Adapter(@NonNull List<categories> downlodeList, Context mCtx) {
        super(mCtx, R.layout.subcategoreslist, downlodeList);
        this.downlodeList = downlodeList;
        this.mCtx = mCtx;
    }

    public View getView(int position, View view, ViewGroup parent) {

        //getting the layoutinflater
        LayoutInflater inflater = LayoutInflater.from(mCtx);

        //creating a view with our xml layout
        View listViewItem = inflater.inflate(R.layout.subcategoreslist, null, true);

        //getting text views
        TextView textViewName = listViewItem.findViewById(R.id.filename1);
       ImageView downloadImag = listViewItem.findViewById(R.id.subicon);
        LinearLayout layout_container = listViewItem.findViewById(R.id.linearLayoutsub);
//        // TextView imageView = listViewItem.findViewById(R.id.Downlodeicon);

        //Getting the hero for the specified position
        final categories hero = downlodeList.get(position);

        //setting hero values to textviews
        textViewName.setText(hero.getFilename());
        // imageView.setText(hero.getImaurl());

        Glide.with(mCtx)
                .load("https://teddiapp.com/app/assets/admin/image/subcategory/" + hero.getImage())
                .fitCenter()
                .dontAnimate() // run ji
                .into(downloadImag);

        layout_container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent =new Intent(mCtx,Create_Job.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                intent.putExtra("CategoryID",hero.getFileID());
                mCtx.startActivity(intent);

            }
        });



        return listViewItem;

    };
}
