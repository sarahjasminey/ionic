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
import android.widget.EditText;
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
 * Use the {@link vehicle_Document_Fragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class vehicle_Document_Fragment extends Fragment implements View.OnClickListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnStepTwoListener mListener;

    private int PICK_IMAGE_REQUEST = 1;
    //Bitmap to get image from gallery
    private Bitmap bitmap;
    //Uri to store the image uri
    private Uri filePath;
    private static final int STORAGE_PERMISSION_CODE = 123;

    SharedPreferences sharedPreferences;
    String id;
    ImageView imageView1, imageView2, imageView3, imageView4;
    String imag1, imag2, imag3, ima4, ima5, ima6;

    private static final String apiurl="https://teddiapp.com/app/api/champs/vehicledoc";


    public vehicle_Document_Fragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment vehicle_Document_Fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static vehicle_Document_Fragment newInstance(String param1, String param2) {
        vehicle_Document_Fragment fragment = new vehicle_Document_Fragment();
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
        return inflater.inflate(R.layout.fragment_vehicle__document_, container, false);
    }

   // private Button backBT;
    private Button nextBT;
    ImageView drivingfrontimage ,drivingbackimage, vehiclercimage;
    EditText vname, vnumber, vcolor;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        sharedPreferences = getActivity().getSharedPreferences("Teddy_2", Context.MODE_PRIVATE);
        id=sharedPreferences.getString("UserID","");
        String fisrttime1 = sharedPreferences.getString("FirstTimevehicle","");


        if (fisrttime1.equals("Yes")){

            Intent intent = new Intent(getActivity(),Terms_Condition.class);
            startActivity(intent);
        }else {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("FirstTimevehicle","Yes");
            editor.apply();
        }


       // backBT=view.findViewById(R.id.backBT);
        nextBT=view.findViewById(R.id.sumbitt2);
        drivingfrontimage = (ImageView) view.findViewById(R.id.drivingfrontimage);
        drivingbackimage = (ImageView) view.findViewById(R.id.drivingbackimage);
        vehiclercimage= (ImageView) view.findViewById(R.id.vehicleimage);
        vname = view.findViewById(R.id.vehiclename);
        vnumber = view.findViewById(R.id.vehiclenumber);
        vcolor = view.findViewById(R.id.vcolor);


        nextBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                personalimgeJSON1();

                ima4= vname.getText().toString().trim();
                ima5= vnumber.getText().toString().trim();
                ima6= vcolor.getText().toString().trim();

            }
        });

        requestStoragePermission();

        //Setting clicklistener
        drivingfrontimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(i, 1);
            }
        });

        //Setting clicklistener
        drivingbackimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ii = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(ii, 2);
            }
        });

        //Setting clicklistener
        vehiclercimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iii = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(iii, 3);
            }
        });

    }


    //handling the image chooser activity result
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            drivingfrontimage.setImageBitmap(photo);
            encodebitmapp1(photo);
        }
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == getActivity().RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
                drivingfrontimage.setImageBitmap(bitmap);
                encodebitmapp1(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        if (requestCode == 2) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            drivingbackimage.setImageBitmap(photo);
            encodebitmapp2(photo);
        }
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == getActivity().RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
                drivingbackimage.setImageBitmap(bitmap);
               encodebitmapp2(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (requestCode == 3) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            vehiclercimage.setImageBitmap(photo);
            encodebitmapp3(photo);
        }
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == getActivity().RESULT_OK && data != null && data.getData() != null) {
            filePath = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), filePath);
                vehiclercimage.setImageBitmap(bitmap);
                encodebitmapp3(bitmap);

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

    private void personalimgeJSON1() {

        RequestQueue requestQueue = Volley.newRequestQueue(getActivity());
        StringRequest MyStringRequest = new StringRequest(Request.Method.POST, apiurl, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                drivingfrontimage.setImageResource(R.drawable.ic_launcher_background);
                drivingbackimage.setImageResource(R.drawable.ic_launcher_background);
                vehiclercimage.setImageResource(R.drawable.ic_launcher_background);
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
                MyData.put("license_front", imag1);
                MyData.put("license_back" , imag2);
                MyData.put("rc_front" , imag3);
                MyData.put("v_name" , ima4);
                MyData.put("v_number" , ima5);
                MyData.put("v_color" , ima6);

                return MyData;
            }
        };

        // RequestQueue requestQueue = Volley.newRequestQueue(this);

        requestQueue.add(MyStringRequest);
    }

    private void encodebitmapp1(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);

        byte[] byteofimages1=byteArrayOutputStream.toByteArray();
        imag1= Base64.encodeToString(byteofimages1, Base64.DEFAULT);
    }

    private void encodebitmapp2(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream1=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream1);

        byte[] byteofimages2=byteArrayOutputStream1.toByteArray();
        imag2= Base64.encodeToString(byteofimages2, Base64.DEFAULT);

    }
    private void encodebitmapp3(Bitmap bitmap)
    {
        ByteArrayOutputStream byteArrayOutputStream2=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream2);

        byte[] byteofimages3=byteArrayOutputStream2.toByteArray();
        imag3= Base64.encodeToString(byteofimages3, Base64.DEFAULT);

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
       // backBT.setOnClickListener(null);
        nextBT.setOnClickListener(null);
    }




    @Override
    public void onClick(View v) {

        if (drivingfrontimage.getDrawable() == null){
            //Image doesn´t exist.
        }else if (drivingbackimage.getDrawable() == null){

        }

//        }else if (ima4.length()==0){
//            vname.requestFocus();
//            vname.setError("Enter veichle name");
//        }
//        else if (ima5.length()==0){
//            vnumber.requestFocus();
//            vnumber.setError("Enter veichle name");
//        }
//        else if (ima6.length()==0){
//            vcolor.requestFocus();
//            vcolor.setError("Enter veichle name");

        else {
            switch (v.getId()) {
//            case R.id.backBT:
//                if (mListener != null)
//                    mListener.onBackPressed(this);
//                break;

                case R.id.sumbitt2:
                    if (mListener != null)
                        mListener.onNextPressed(this);
                    break;
            }
        }



    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnStepTwoListener) {
            mListener = (OnStepTwoListener) context;
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
    public interface OnStepTwoListener {
        //void onBackPressed(Fragment fragment);

        void onNextPressed(Fragment fragment);

    }
}