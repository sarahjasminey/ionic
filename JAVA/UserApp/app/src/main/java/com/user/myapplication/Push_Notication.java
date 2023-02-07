package com.user.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

public class Push_Notication extends AppCompatActivity {
    String token="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push__notication);


/*
        FirebaseMessaging.getInstance().subscribeToTopic("weather")
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        String msg = "Done";
                        if (!task.isSuccessful()) {
                            msg = "Faild";
                        }
                    }
                });
*/

        FirebaseOptions options = new FirebaseOptions.Builder()
                .setApplicationId("1:1088386694425:android:2bcca8bae0f77e05975988") // Required for Analytics.
                .setProjectId("teddiapp") // Required for Firebase Installations.
                .setApiKey("AIzaSyA9CX_mBOh_DVp_v2aThJ3HFYX5X6otC-Y") // Required for Auth.
                .build();

        FirebaseApp.initializeApp(this);
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w("TAG", "Fetching FCM registration token failed", task.getException());
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast
                   //     String msg = getString(R.string.msg_token_fmt, token);
                        Log.d("TAG", token);
                        Toast.makeText(Push_Notication.this, token, Toast.LENGTH_SHORT).show();
                    }
                });

    }
}