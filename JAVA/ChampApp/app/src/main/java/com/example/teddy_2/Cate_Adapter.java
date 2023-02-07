package com.example.teddy_2;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Cate_Adapter  extends ArrayAdapter<Cate> {



    //the hero list that will be displayed
    private static List<Cate> downlodeList;
    String[] names;
    private static ArrayList<Cate> arraylist;
    //the context object
    private Context mCtx;
    String value;


    public Cate_Adapter(@NonNull List<Cate> downlodeList, Context mCtx) {
        super(mCtx, R.layout.catelist, downlodeList);
        this.downlodeList = downlodeList;
        this.mCtx = mCtx;
        this.arraylist = new ArrayList<Cate>();
        this.arraylist.addAll(downlodeList);
    }

    public class ViewHolder {
        TextView textViewName;
        TextView textViewName1;
        ImageView imageView;
        TextView textViewName2;
    }

    @Override
    public int getCount() {
        return downlodeList.size();
    }

    @Override
    public Cate getItem(int position) {
        return downlodeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    public View getView(int position, View view, ViewGroup parent) {

        final ViewHolder holder;

        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(mCtx);

            //creating a view with our xml layout
            view = inflater.inflate(R.layout.catelist, null, true);
            view.setTag(holder);

            //getting text views
            holder.textViewName = view.findViewById(R.id.filename);
            holder.textViewName1 = view.findViewById(R.id.filename1);
//            holder.textViewName2 = view.findViewById(R.id.filename2);
            //ImageView downloadImag = view.findViewById(R.id.Dowicon3);
            holder.imageView = view.findViewById(R.id.imageicon);

        }else {
            holder = (ViewHolder) view.getTag();
        }


        CheckedTextView linearLayout = view.findViewById(R.id.filename);

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (linearLayout.isChecked()) {
// set cheek mark drawable and set checked property to false
                    value = "un-Checked";
                    linearLayout.setCheckMarkDrawable(0);
                    linearLayout.setChecked(false);
                } else {
// set cheek mark drawable and set checked property to true
                    value = "Checked";
                    linearLayout.setCheckMarkDrawable(R.drawable.check_circle);
                    linearLayout.setChecked(true);
                }
                Toast.makeText(mCtx, value, Toast.LENGTH_SHORT).show();
            }
        });



        //LinearLayout layout_container = listViewItem.findViewById(R.id.layout_container);
        // TextView imageView = listViewItem.findViewById(R.id.Downlodeicon);

        //Getting the hero for the specified position
        final Cate hero = downlodeList.get(position);


        //setting hero values to textviews
       holder.textViewName.setText(hero.getFilename());
        holder.textViewName1.setText(hero.getDescription());
//        holder.textViewName2.setText(hero.getPricetype());

       Glide.with(mCtx)
                .load("https://teddiapp.com/app/assets/admin/image/category/" + hero.getImage())
                .fitCenter()
                .dontAnimate() // run ji
                .into(holder.imageView);

        return view;



        // imageView.setText(hero.getImaurl());
//
//        layout_container.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                Intent intent =new Intent(mCtx,Allcate_SecondActivity.class);
//                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                intent.putExtra("CategoryID",hero.getFileID());
//                mCtx.startActivity(intent);
//
//            }
//        });


    };

    // Filter Class
        public static void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        downlodeList.clear();
        if (charText.length() == 0) {
            downlodeList.addAll(arraylist);
        } else {
            for (Cate wp : arraylist) {
                if (wp.getFilename().toLowerCase(Locale.getDefault()).contains(charText)) {
                    downlodeList.add(wp);
                }
            }
        }
    }
}
