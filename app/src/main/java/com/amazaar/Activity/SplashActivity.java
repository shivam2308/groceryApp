package com.amazaar.Activity;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import androidx.annotation.NonNull;

import com.amazaar.ControlFlow.DeviceAutoLogin;
import com.amazaar.R;
import com.amazaar.Utility.Utils;
import com.daimajia.androidanimations.library.Techniques;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.inject.Injector;
import com.viksaa.sssplash.lib.activity.AwesomeSplash;
import com.viksaa.sssplash.lib.cnst.Flags;
import com.viksaa.sssplash.lib.model.ConfigSplash;

import javax.inject.Inject;

import roboguice.RoboGuice;

public class SplashActivity extends AwesomeSplash {

    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    @Inject
    private DeviceAutoLogin m_deviceLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        injectMembers();
        if (currentUser == null) {
            mAuth.signInAnonymously().
                    addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {

                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("TAG", e.getMessage());
                        }
                    });
        } else {
        }
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence name = "My app notification channel";
            String description = "Description for this channel";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("Hello", name, importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    public void initSplash(ConfigSplash configSplash) {
        Utils.isOnline(SplashActivity.this, true);
        configSplash.setBackgroundColor(R.color.colorPrimary); //any color you want form colors.xml
        configSplash.setAnimCircularRevealDuration(2000); //int ms
        configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);  //or Flags.REVEAL_LEFT
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM); //or Flags.REVEAL_TOP

        //Choose LOGO OR PATH; if you don't provide String value for path it's logo by default

        //Customize Logo
        configSplash.setLogoSplash(R.mipmap.ic_launcher); //or any other drawable
        configSplash.setAnimLogoSplashDuration(2000); //int ms
        configSplash.setAnimLogoSplashTechnique(Techniques.Bounce); //choose one form Techniques (ref: https://github.com/daimajia/AndroidViewAnimations)
        //Customize Path
        // configSplash.setPathSplash(Constants.DROID_LOGO); //set path String
        configSplash.setOriginalHeight(400); //in relation to your svg (path) resource
        configSplash.setOriginalWidth(400); //in relation to your svg (path) resource
        configSplash.setAnimPathStrokeDrawingDuration(3000);
        configSplash.setPathSplashStrokeSize(3); //I advise value be <5
        configSplash.setPathSplashStrokeColor(R.color.strokeColor); //any color you want form colors.xml
        configSplash.setAnimPathFillingDuration(3000);
        configSplash.setPathSplashFillColor(R.color.rippelColor); //path object filling color


        //Customize Title
        configSplash.setTitleSplash("Amazaar");
        configSplash.setTitleTextColor(R.color.white);
        configSplash.setTitleTextSize(30f); //float value
        configSplash.setAnimTitleDuration(3000);
        configSplash.setAnimTitleTechnique(Techniques.FlipInX);
        //configSplash.setTitleFont("fonts/myfont.ttf"); //provide string to your font located in assets/fonts/
    }

    @Override
    public void animationsFinished() {
        m_deviceLogin.doLogin(getApplicationContext());
        //Intent i = new Intent(getApplicationContext(), HomeActivity.class);
        //startActivity(i);
    }

    private void injectMembers() {
        Injector injector = RoboGuice.getInjector(getApplicationContext());
        injector.injectMembers(this);
    }
}