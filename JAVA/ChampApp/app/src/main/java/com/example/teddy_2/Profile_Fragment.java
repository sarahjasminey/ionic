package com.example.teddy_2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

public class Profile_Fragment extends Fragment {

    CircleImageView profileimage;
    SharedPreferences sharedPreferences;
    String id;
    private static final int CAMERA_REQUEST = 1888;
    private int PICK_IMAGE_REQUEST = 1;
    private Bitmap bitmap;
    String profileimage1;
    private static final String apiurl="https://teddiapp.com/app/api/champs/profilepic";
    private Uri filePath;
    Button gotohome;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_profile_, container, false);

        sharedPreferences = getActivity().getSharedPreferences("Teddy_2", Context.MODE_PRIVATE);
        id=sharedPreferences.getString("UserID","");

        profileimage = (CircleImageView) view.findViewById(R.id.ivProfile);
        gotohome = (Button) view.findViewById(R.id.gotohome);

        gotohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                profileimgeJSON();
//                Home home = new Home();
//                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
//                fragmentTransaction.replace(R.id.fragment_container, home);
//                fragmentTransaction.commit();
            }
        });

        profileimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
                if (v == profileimage) {

                }
            }
        });

        return view;
    }

    private void selectImage() {
        final CharSequence[] options = {"Take Photo", "Choose from Gallery", "Cancel"};
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Add Photo!");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Take Photo")) {

                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);

                } else if (options[item].equals("Choose from Gallery")) {

                    showFileChooser();
//                        Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//                        startActivityForResult(intent, 2);
                } else if (options[item].equals("Cancel")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    private void showFileChooser() {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

    }

    private void profileimgeJSON() {

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, apiurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                profileimage.setImageResource(R.drawable.ic_launcher_background);
                Toast.makeText(getContext(),"FileUploaded Successfully",Toast.LENGTH_SHORT).show();
                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.
                Log.d("Response", response);

                try {
                    JSONObject object = new JSONObject(response);
                    JSONObject stringg = object.getJSONObject("data");
                    {
                    }
                    Log.e("TAGNEW", String.valueOf(stringg));

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                //This code is executed if there is an error.
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                //  String image = getStringImage(bitmap);
                MyData.put("id" , id);
                MyData.put("image_path", profileimage1);

                return MyData;
            }
        };

        // RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(MyStringRequest);
    }

    //handling the image chooser activity result
    @SuppressLint("LongLogTag")
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            profileimage.setImageBitmap(photo);
            encodebitmap(photo);
        }
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == getActivity().RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
                profileimage.setImageBitmap(bitmap);
                encodebitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    private void encodebitmap(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);

        byte[] byteofimages=byteArrayOutputStream.toByteArray();
        profileimage1= Base64.encodeToString(byteofimages, Base64.DEFAULT);
    }



}