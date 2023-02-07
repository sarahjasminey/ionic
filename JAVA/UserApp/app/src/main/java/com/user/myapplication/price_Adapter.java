package com.user.myapplication;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class price_Adapter extends ArrayAdapter<String> {

    private final Activity context;
    private final List<String> maintitle;
    private final List<String> subtitle;

    public price_Adapter(Activity context, List<String> maintitle, List<String> subtitle) {
        super(context, R.layout.price_list, maintitle);
        this.context = context;
        this.maintitle = maintitle;
        this.subtitle = subtitle;
    }

    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater=context.getLayoutInflater();
        View rowView=inflater.inflate(R.layout.price_list, null,true);

        TextView titleText = (TextView) rowView.findViewById(R.id.hours);
        TextView subtitleText = (TextView) rowView.findViewById(R.id.pricerate);

        titleText.setText(maintitle.get(position));
        subtitleText.setText(subtitle.get(position));

        return rowView;

    };
}
