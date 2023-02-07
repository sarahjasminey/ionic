package com.user.myapplication;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;

public class CustomAdapt extends ArrayAdapter<GoodModel> {
    public CustomAdapt(@NonNull Context context, int resource) {
        super(context, resource);
    }
}
