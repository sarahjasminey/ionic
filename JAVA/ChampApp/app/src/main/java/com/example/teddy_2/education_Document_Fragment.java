package com.example.teddy_2;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
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

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link education_Document_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class education_Document_Fragment extends Fragment implements View.OnClickListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnStepThreeListener mListener;

    // private static final int CAMERA_REQUEST = 1888;
    private int PICK_IMAGE_REQUEST = 1;
    //Bitmap to get image from gallery
    private Bitmap bitmap;
    //Uri to store the image uri
    private Uri filePath;
    private static final int STORAGE_PERMISSION_CODE = 123;

    SharedPreferences sharedPreferences;
    String id;
    ImageView imageView1, imageView2, imageView3, imageView4;
    String ima1, ima2, ima3, ima4;
    private static final String apiurl="https://teddiapp.com/app/api/champs/educationdoc";

    public education_Document_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment education_Document_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static education_Document_Fragment newInstance(String param1, String param2) {
        education_Document_Fragment fragment = new education_Document_Fragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_education__document_, container, false);
    }

    //private Button backBT;
    private Button nextBT;
    ImageView schoolmarksheet ,degreemarksheet, expcertificate , bleow10th;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //backBT=view.findViewById(R.id.backBT);

        sharedPreferences = getActivity().getSharedPreferences("Teddy_2", Context.MODE_PRIVATE);
        id=sharedPreferences.getString("UserID","");
        String fisrttime1 = sharedPreferences.getString("FirstTimeeducation","");

        if (fisrttime1.equals("Yes")){

            Intent intent = new Intent(getActivity(),Terms_Condition.class);
            startActivity(intent);
        }else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("FirstTimeeducation","Yes");
            editor.apply();
        }

//
//        nextBT.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//            }
//        });
        nextBT=view.findViewById(R.id.sumbitt3);
        schoolmarksheet = (ImageView) view.findViewById(R.id.schoolmarksheet);
        degreemarksheet = (ImageView) view.findViewById(R.id.Degree_image);
        expcertificate= (ImageView) view.findViewById(R.id.expcer_image);
        bleow10th = (ImageView) view.findViewById(R.id.below10sheet);

        requestStoragePermission();

        //Setting clicklistener
        schoolmarksheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i, 1);
            }
        });

        //Setting clicklistener
        degreemarksheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(ii, 2);
            }
        });

        //Setting clicklistener
        expcertificate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iii = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(iii, 3);
            }
        });

        //Setting clicklistener
        bleow10th.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iiii = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(iiii, 4);
            }
        });
    }


    private void personalimgeJSON2() {

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, apiurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                schoolmarksheet.setImageResource(R.drawable.ic_launcher_background);
                degreemarksheet.setImageResource(R.drawable.ic_launcher_background);
                expcertificate.setImageResource(R.drawable.ic_launcher_background);
                bleow10th.setImageResource(R.drawable.ic_launcher_background);
            //    Toast.makeText(getContext(),"FileUploaded Successfully",Toast.LENGTH_SHORT).show();
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
                MyData.put("thenthb", ima1);
                MyData.put("twelth" , ima2);
                MyData.put("degree" , ima3);
                MyData.put("experience" , ima4);

                return MyData;
            }
        };

        // RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(MyStringRequest);
    }

    private void encodebitmap1(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);

        byte[] byteofimages=byteArrayOutputStream.toByteArray();
        ima1= Base64.encodeToString(byteofimages, Base64.DEFAULT);
    }

    private void encodebitmap2(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);

        byte[] byteofimages=byteArrayOutputStream.toByteArray();
        ima2= Base64.encodeToString(byteofimages, Base64.DEFAULT);
    }
    private void encodebitmap3(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);

        byte[] byteofimages=byteArrayOutputStream.toByteArray();
        ima3= Base64.encodeToString(byteofimages, Base64.DEFAULT);
    }
    private void encodebitmap4(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);

        byte[] byteofimages=byteArrayOutputStream.toByteArray();
        ima4= Base64.encodeToString(byteofimages, Base64.DEFAULT);
    }



    //handling the image chooser activity result
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            schoolmarksheet.setImageBitmap(photo);
            encodebitmap1(photo);
        }
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == getActivity().RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
                schoolmarksheet.setImageBitmap(bitmap);
                encodebitmap1(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        if (requestCode == 2) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            degreemarksheet.setImageBitmap(photo);
            encodebitmap2(photo);
        }
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == getActivity().RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
                degreemarksheet.setImageBitmap(bitmap);
                encodebitmap2(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (requestCode == 3) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            expcertificate.setImageBitmap(photo);
            encodebitmap3(photo);
        }
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == getActivity().RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
                expcertificate.setImageBitmap(bitmap);
                encodebitmap3(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (requestCode == 4) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            bleow10th.setImageBitmap(photo);
            encodebitmap4(photo);
        }
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == getActivity().RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
                bleow10th.setImageBitmap(bitmap);
                encodebitmap4(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    //Requesting permission
    private void requestStoragePermission() {
        if (ContextCompat.checkSelfPermission(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            return;

        if (ActivityCompat.shouldShowRequestPermissionRationale(getActivity(), Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //If the user has denied the permission previously your code will come to this block
            //Here you can explain why you need this permission
            //Explain here why you need this permission
        }
        //And finally ask for the permission
        ActivityCompat.requestPermissions(getActivity(), new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
    }


    //This method will be called when the user will tap on allow or deny
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        //Checking the request code of our request
        if (requestCode == STORAGE_PERMISSION_CODE) {

            //If permission is granted
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //Displaying a toast
                Toast.makeText(getActivity(), "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
            } else {
                //Displaying another toast if permission is not granted
                Toast.makeText(getActivity(), "Oops you just denied the permission", Toast.LENGTH_LONG).show();
            }
        }
    }


    @Override
    public void onResume() {
        super.onResume();
       // backBT.setOnClickListener(this);
        nextBT.setOnClickListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        //backBT.setOnClickListener(null);
        nextBT.setOnClickListener(null);
    }

    @Override
    public void onClick(View view) {

        if (schoolmarksheet.getDrawable() == null){
            //Image doesn´t exist.
        }else if (degreemarksheet.getDrawable() == null){

        }else if (expcertificate.getDrawable() == null){

        }else if (bleow10th.getDrawable() == null){

        }else {

            switch (view.getId()) {
                case R.id.sumbitt3:
                    personalimgeJSON2();
                    if (mListener != null)
                        mListener.onNextPressed(this);
                    break;
            }

        }

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnStepThreeListener) {
            mListener = (OnStepThreeListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
       // backBT=null;
        nextBT=null;
    }
    public interface OnStepThreeListener {
      //  void onBackPressed(Fragment fragment);
        void onNextPressed(Fragment fragment);
    }

}