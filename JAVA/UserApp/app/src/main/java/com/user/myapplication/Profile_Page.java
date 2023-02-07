    package com.user.myapplication;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;
import java.util.Map;

import de.hdodenhof.circleimageview.CircleImageView;

    public class Profile_Page extends AppCompatActivity {


//        TextView name1, email1, about1, gender1, dob1, phone1, Address1;
//        Button logout;
//        ImageView editicon, profileimage;
//
//        //Image request code
//        private int PICK_IMAGE_REQUEST = 1;
//        //storage permission code
//        private static final int STORAGE_PERMISSION_CODE = 123;
//        //Bitmap to get image from gallery
//        private Bitmap bitmap;
//        //Uri to store the image uri
//        private Uri filePath;
//
        String id;
//        String profileimage1;
//        private static final int CAMERA_REQUEST = 1888;
//
//        private Bitmap yourbitmap;
        SharedPreferences sharedPreferences;
        CircleImageView img;
        ImageView btncamera;
        Button btnupload;
        Bitmap bitmap;
        String encodedimage;
        private static final String apiurl="https://teddiapp.com/app/api/champs/profilepic";

//        private static final String SHARED_PREF_NAME = "mypref";
//        private static final String KEY_NAME = "name";
//        private static final String KEY_EMAIL = "email";
//        private static final String KEY_ABOUT = "about";
//        private static final String KEY_GENDER = "gender";
//        private static final String KEY_DOB = "dob";
//        private static final String KEY_NUMBER = "number";
//        private static final String KEY_ADDRESS = "address";

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_profile__page);

            sharedPreferences = getSharedPreferences("MyApplication", Context.MODE_PRIVATE);
            id = sharedPreferences.getString("UserID", "");

          //  getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);


            img = (CircleImageView) findViewById(R.id.profile_image);

            btncamera = (ImageView) findViewById(R.id.sbmit_camera);
            btnupload = (Button) findViewById(R.id.sbmit_upload);


            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Dexter.withContext(getApplicationContext())
                            .withPermission(Manifest.permission.CAMERA)
                            .withListener(new PermissionListener() {
                                @Override
                                public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                                    Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                                    startActivityForResult(intent, 111);
                                }

                                @Override
                                public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                                }

                                @Override
                                public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                                    permissionToken.continuePermissionRequest();
                                }
                            }).check();
                }
            });

            btnupload.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    uploadtoserver();
                }
            });
        }


        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            if(requestCode==111 && resultCode==RESULT_OK)
            {
                bitmap=(Bitmap)data.getExtras().get("data");
                img.setImageBitmap(bitmap);
                encodebitmap(bitmap);
            }

            super.onActivityResult(requestCode, resultCode, data);
        }

        private void encodebitmap(Bitmap bitmap)
        {
            ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,byteArrayOutputStream);

            byte[] byteofimages=byteArrayOutputStream.toByteArray();
            encodedimage=android.util.Base64.encodeToString(byteofimages, Base64.DEFAULT);
        }

        private void uploadtoserver()
        {
            // final String name=t1.getText().toString().trim();
            //  final String desig=t2.getText().toString().trim();

            StringRequest request=new StringRequest(Request.Method.POST, apiurl, new Response.Listener<String>() {
                @Override
                public void onResponse(String response)
                {
                    // t1.setText("");
                    // t2.setText("");
                    img.setImageResource(R.drawable.ic_launcher_background);
                    Toast.makeText(getApplicationContext(),"FileUploaded Successfully",Toast.LENGTH_SHORT).show();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(getApplicationContext(),error.toString(),Toast.LENGTH_SHORT).show();
                }
            })
            {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError
                {
                    Map<String,String> map=new HashMap<String, String>();
                    // map.put("t1",name);
                   map.put("id", id);
                    map.put("image_path", encodedimage);
                    return map;
                }
            };

            RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
            queue.add(request);


        }  // end of function uploadto DB



//            editicon = (ImageView) findViewById(R.id.editicon);
//            name1 = (TextView) findViewById(R.id.tvName);
//            email1 = (TextView) findViewById(R.id.PEmail);
//            about1 = (TextView) findViewById(R.id.PAbout);
//            gender1 = (TextView) findViewById(R.id.PGender);
//            phone1 = (TextView) findViewById(R.id.tvDescription);
//            dob1 = (TextView) findViewById(R.id.PDOB);
//            Address1 = (TextView) findViewById(R.id.AddressT);
//            profileimage = (ImageView) findViewById(R.id.ivProfile);
//            profileimgeJSON();
//            Button button = (Button)findViewById(R.id.button);

//
//            sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
//
//            String name = sharedPreferences.getString(KEY_NAME, null);
//            String email = sharedPreferences.getString(KEY_EMAIL, null);
//            String about = sharedPreferences.getString(KEY_ABOUT, null);
//            String gender = sharedPreferences.getString(KEY_GENDER, null);
//            String dob = sharedPreferences.getString(KEY_DOB, null);
//            String phone = sharedPreferences.getString(KEY_NUMBER, null);
//            String addr = sharedPreferences.getString(KEY_ADDRESS, null);
//
//            if (name != null || email != null || about != null || gender != null || dob != null || phone != null || addr != null) {
//
//                name1.setText(name);
//                email1.setText(email);
//                about1.setText(about);
//                gender1.setText(gender);
//                dob1.setText(dob);
//                phone1.setText(phone);
//                Address1.setText(addr);
//            }


//button.setOnClickListener(new View.OnClickListener() {
//    @Override
//    public void onClick(View v) {
//        profileimage1 = getPath(filePath);
//
//    }
//});


//                   Glide.with(getApplicationContext())
//                           .load("" + profileimage1)
//                           .fitCenter()
//                           .dontAnimate()
//                           .into(profileimage);


//               profileimage.setImageBitmap(BitmapFactory.decodeFile(profileimage1));
//               yourbitmap = BitmapFactory.decodeFile(profileimage1);
////
//            ByteArrayOutputStream stream = new ByteArrayOutputStream();
//             Compress image to lower quality scale 1 - 100
//           yourbitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
//            byte[] image = stream.toByteArray();

         /* logout.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  SharedPreferences.Editor editor =sharedPreferences.edit();
              }
          });
*/
//
//            //Requesting storage permission
//            requestStoragePermission();
//
//            //Initializing views
//
//            profileimage = (ImageView) findViewById(R.id.ivProfile);
////        editText = (EditText) findViewById(R.id.editTextName);
//
//            //Setting clicklistener
//            profileimage.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    selectImage();
//                    if (v == profileimage) {
//
//                    }
//                }
//            });
//
//            editicon.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.clear();
//                    editor.commit();
//                    Intent intent = new Intent(Profile_Page.this, PersonalDetails.class);
//                    startActivity(intent);
//
//                }
//            });
//        }
//
//        //method to get the file path from uri
//        public String getPath(Uri uri) {
//            Cursor cursor = getContentResolver().query(uri, null, null, null, null);
//            cursor.moveToFirst();
//            String document_id = cursor.getString(0);
//            document_id = document_id.substring(document_id.lastIndexOf(":") + 1);
//            cursor.close();
//
//            cursor = getContentResolver().query(
//                    android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
//                    null, MediaStore.Images.Media._ID + " = ? ", new String[]{document_id}, null);
//            cursor.moveToFirst();
//            String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
//            cursor.close();
//
//            return path;
//        }
//
//        private void selectImage() {
//            final CharSequence[] options = { "Take Photo", "Choose from Gallery","Cancel" };
//            AlertDialog.Builder builder = new AlertDialog.Builder(Profile_Page.this);
//            builder.setTitle("Add Photo!");
//            builder.setItems(options, new DialogInterface.OnClickListener() {
//                @Override
//                public void onClick(DialogInterface dialog, int item) {
//                    if (options[item].equals("Take Photo"))
//                    {
//
//                        Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
//                        startActivityForResult(cameraIntent, CAMERA_REQUEST);
//
//                    }
//                    else if (options[item].equals("Choose from Gallery"))
//                    {
//
//                        showFileChooser();
////                        Intent intent = new   Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
////                        startActivityForResult(intent, 2);
//                    }
//                    else if (options[item].equals("Cancel")) {
//                        dialog.dismiss();
//                    }
//                }
//            });
//            builder.show();
//        }
//
//        private void profileimgeJSON() {
//
//            RequestQueue requestQueue = Volley.newRequestQueue(Profile_Page.this);
//            StringRequest MyStringRequest = new StringRequest(Request.Method.POST, "https://teddiapp.com/app/api/user/profilepic", new Response.Listener<String>() {
//                @Override
//                public void onResponse(String response) {
//                    //This code is executed if the server responds, whether or not the response contains data.
//                    //The String 'response' contains the server's response.
//                    Log.d("Response", response);
//
//                    try {
//
//                        // if (string.equals("User Created Successfully."))*/
//
//                        JSONObject object = new JSONObject(response);
//                        JSONObject stringg = object.getJSONObject("data");
//                        String string = stringg.getString("msg");
//
//                            /*JSONObject jsonObject = stringg.getJSONObject("details");
//                            String id = object.getString("id");
//                            String name = object.getString("name");
//                            String about = object.getString("about");
//
//*/
//
//                        {
//
//                        }
//
//                        Log.e("TAGNEW", String.valueOf(stringg));
//
//                    } catch (JSONException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }, new Response.ErrorListener() {
//                //Create an error listener to handle errors appropriately.
//                @Override
//                public void onErrorResponse(VolleyError error) {
//                    //This code is executed if there is an error.
//                }
//            }) {
//                protected Map<String, String> getParams() {
//                    Map<String, String> MyData = new HashMap<String, String>();
//                    MyData.put("id", id);
//                    MyData.put("image_path", profileimage1);
//
//                    return MyData;
//                }
//            };
//
//            // RequestQueue requestQueue = Volley.newRequestQueue(this);
//
//            requestQueue.add(MyStringRequest);
//        }
//
//        //method to show file chooser
//        private void showFileChooser() {
//            Intent intent = new Intent();
//            intent.setType("image/*");
//            intent.setAction(Intent.ACTION_GET_CONTENT);
//            startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);
//        }
//
//        //handling the image chooser activity result
//        @Override
//        protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//            super.onActivityResult(requestCode, resultCode, data);
//
//            if (requestCode == CAMERA_REQUEST) {
//                Bitmap photo = (Bitmap) data.getExtras().get("data");
//                profileimage.setImageBitmap(photo);
//            }
//            if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
//                filePath = data.getData();
//                try {
//                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
//                    profileimage.setImageBitmap(bitmap);
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//
//        //Requesting permission
//        private void requestStoragePermission() {
//            if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
//                return;
//
//            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
//                //If the user has denied the permission previously your code will come to this block
//                //Here you can explain why you need this permission
//                //Explain here why you need this permission
//            }
//            //And finally ask for the permission
//            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, STORAGE_PERMISSION_CODE);
//        }
//
//
//        //This method will be called when the user will tap on allow or deny
//        @Override
//        public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//
//            //Checking the request code of our request
//            if (requestCode == STORAGE_PERMISSION_CODE) {
//
//                //If permission is granted
//                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
//                    //Displaying a toast
//                    Toast.makeText(this, "Permission granted now you can read the storage", Toast.LENGTH_LONG).show();
//                } else {
//                    //Displaying another toast if permission is not granted
//                    Toast.makeText(this, "Oops you just denied the permission", Toast.LENGTH_LONG).show();
//                }
//            }
//        }
//
//
//        @Override
//        public boolean onCreateOptionsMenu(Menu menu) {
//            // Inflate the menu; this adds items to the action bar if it is present.
//            getMenuInflater().inflate(R.menu.activity_main, menu);
//            return true;
//
//        }

    }