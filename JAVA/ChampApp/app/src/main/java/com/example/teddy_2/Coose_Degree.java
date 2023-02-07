package com.example.teddy_2;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class Coose_Degree extends DialogFragment {

    int position=0;

    public interface Singlechoicelis{

        void onpositivebuttonclick(String[] list,int position);
        void onnagativebuttonclicked();
    }

    Singlechoicelis singlechoicelis;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        try {
            singlechoicelis= (Singlechoicelis) context;
        }catch (Exception e){
           throw new ClassCastException(getActivity().toString()+"singleChoiselistener must implement");
        }

    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        String[] list = getActivity().getResources().getStringArray(R.array.Select_degree);
        builder.setTitle("Select Your Degree")
                .setSingleChoiceItems(list, position, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        position = which;

                    }
                })

                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        singlechoicelis.onpositivebuttonclick(list, position);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        singlechoicelis.onnagativebuttonclicked();

                    }
                });
        return builder.create();

    }}


