package com.example.teddy_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Wallet_Adapter extends BaseAdapter {

    private Context context;
    private ArrayList<wallet> arrayList;
    private TextView serialNum, name, contactNum;
    public Wallet_Adapter(Context context, ArrayList<wallet> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }
    @Override
    public int getCount() {
            return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
            return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.row, parent, false);
        serialNum = convertView.findViewById(R.id.sub);
        name = convertView.findViewById(R.id.loca);
        contactNum = convertView.findViewById(R.id.time);
        serialNum.setText(" " + arrayList.get(position).getNum());
        name.setText(arrayList.get(position).getName());
        contactNum.setText(arrayList.get(position).getMobileNumber());
        return convertView;
    }
}
