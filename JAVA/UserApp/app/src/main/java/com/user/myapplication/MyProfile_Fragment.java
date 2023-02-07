package com.user.myapplication;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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
import android.widget.TextView;
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


public class MyProfile_Fragment extends Fragment {


    private Context context;

    TextView name1, email1, about1, gender1, dob1, phone1, Address1;
    Button logout, gotohome;
    CircleImageView profileimage;

    ImageView editicon;


    //Image request code
    private int PICK_IMAGE_REQUEST = 1;
    //storage permission code
    private static final int STORAGE_PERMISSION_CODE = 123;
    //Bitmap to get image from gallery
    private Bitmap bitmap;
    String profileimage1;

    private static final String apiurl="https://teddiapp.com/app/api/user/profilepic";
    //Uri to store the image uri
    private Uri filePath;

    String id;

    private static final int CAMERA_REQUEST = 1888;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_ABOUT = "about";
    private static final String KEY_GENDER = "gender";
    private static final String KEY_DOB = "dob";
    private static final String KEY_NUMBER = "numberr";
    private static final String KEY_ADDRESS = "address";
    private static final String KEY_IMAGE = "profileimage";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_my_profile_, container, false);


        sharedPreferences = getActivity().getSharedPreferences("MyApplication", Context.MODE_PRIVATE);
        id = sharedPreferences.getString("UserID", "");
        editor = sharedPreferences.edit();


        editicon = (ImageView) view.findViewById(R.id.editicon);
        name1 = (TextView) view.findViewById(R.id.tvName);
        email1 = (TextView) view.findViewById(R.id.PEmai1);
        about1 = (TextView) view.findViewById(R.id.PAbout);
        gender1 = (TextView) view.findViewById(R.id.PGender);
        phone1 = (TextView) view.findViewById(R.id.tvDescription);
        dob1 = (TextView) view.findViewById(R.id.PDOB);
        Address1 = (TextView) view.findViewById(R.id.AddressT);
        profileimage = (CircleImageView) view.findViewById(R.id.ivProfile);
        //gotohome = (Button) view.findViewById(R.id.gotohome);

//        Button button = (Button) view.findViewById(R.id.button);



//        gotohome.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                profileimgeJSON();
////                Home home = new Home();
////                FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
////                fragmentTransaction.replace(R.id.fragment_container, home);
////                fragmentTransaction.commit();
//            }
//        });

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_IMAGE, profileimage.toString());
        editor.apply();

        sharedPreferences = getActivity().getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);

        String name = sharedPreferences.getString(KEY_NAME, null);
        String email = sharedPreferences.getString(KEY_EMAIL, null);
        String about = sharedPreferences.getString(KEY_ABOUT, null);
        String gender = sharedPreferences.getString(KEY_GENDER, null);
        String dob = sharedPreferences.getString(KEY_DOB, null);
        String phone = sharedPreferences.getString(KEY_NUMBER, null);
        String addr = sharedPreferences.getString(KEY_ADDRESS, null);
        String proimage = sharedPreferences.getString(KEY_IMAGE, null);

        if (name != null || email != null || about != null || gender != null || dob != null || phone != null || addr != null || proimage != null) {

            name1.setText(name);
            email1.setText(email);
            about1.setText(about);
            gender1.setText(gender);

            dob1.setText(dob);
            phone1.setText(phone);
            Address1.setText(addr);

//            Glide.with(this)
//                    .load("https://teddiapp.com/app/uploads/champs/"+proimage)
//                    .fitCenter()
//                    .dontAnimate() // run ji
//                    .into(profileimage);
        }

        //context = container.getContext();
        //ContentResolver contentResolver = context.getContentResolver();

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                profileimage1 = getPath(filePath);
//
//            }
//        });

        //profileimage.setImageBitmap(BitmapFactory.decodeFile(profileimage1));
        // yourbitmap = BitmapFactory.decodeFile(profileimage1);


        //Requesting storage permission
        requestStoragePermission();

        //Initializing views

//        editText = (EditText) findViewById(R.id.editTextName);

        //Setting clicklistener
        profileimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage();
                if (v == profileimage) {

                }
            }
        });

        editicon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.clear();
                editor.commit();
                Intent intent = new Intent(getActivity(), PersonalDetails.class);
                startActivity(intent);

            }
        });
        return view;
    }

    //method to get the file path from uri
    public String getPath(Uri uri) {
        Cursor cursor = getActivity().getContentResolver().query(uri, null, null, null, null);
        cursor.moveToFirst();
        String document_id = cursor.getString(0);
        document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
        cursor.close();

        cursor = getActivity().getContentResolver().query(
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
        cursor.moveToFirst();
        String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
        cursor.close();

        return path;
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

                    // if (string.equals("User Created Successfully."))*/

                    JSONObject object = new JSONObject(response);
                    JSONObject stringg = object.getJSONObject("data");
                    String string = stringg.getString("msg");

                            /*JSONObject jsonObject = stringg.getJSONObject("details");
                            String id = object.getString("id");
                            String name = object.getString("name");
                           String about = object.getString("about");

*/

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

    //method to show file chooser
    private void showFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
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
        profileimage1=Base64.encodeToString(byteofimages, Base64.DEFAULT);

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


//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getActivity().getMenuInflater().inflate(R.menu.activity_main, menu);
//        return true;
//
//    }
}