package com.example.teddy_2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckedTextView;
import android.widget.Toast;

public class CustomAdapter extends BaseAdapter {

    String[] names;
    Context context;
    LayoutInflater inflter;
    String value;

    public CustomAdapter(Context context, String[] names) {
        this.context = context;
        this.names = names;
        inflter = (LayoutInflater.from(context));

    }
    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = inflter.inflate(R.layout.listtextview, null);
        final CheckedTextView simpleCheckedTextView = (CheckedTextView) convertView.findViewById(R.id.simpleCheckedTextView);
        simpleCheckedTextView.setText(names[position]);
// perform on Click Event Listener on CheckedTextView
        simpleCheckedTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (simpleCheckedTextView.isChecked()) {
// set cheek mark drawable and set checked property to false
                    value = "un-Checked";
                    simpleCheckedTextView.setCheckMarkDrawable(0);
                    simpleCheckedTextView.setChecked(false);
                } else {
// set cheek mark drawable and set checked property to true
                    value = "Checked";
                    simpleCheckedTextView.setCheckMarkDrawable(R.drawable.check_circle_outline);
                    simpleCheckedTextView.setChecked(true);
                }
                Toast.makeText(context, value, Toast.LENGTH_SHORT).show();
            }
        });
        return convertView;

    }
}
