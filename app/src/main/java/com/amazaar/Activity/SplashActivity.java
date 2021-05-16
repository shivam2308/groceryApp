package com.amazaar.Activity;

import android.app.Activity;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.amazaar.ControlFlow.DeviceAutoLogin;
import com.amazaar.ControlFlow.RegisterPushNorification;
import com.amazaar.Module.AmazaarApplication;
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

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import roboguice.RoboGuice;

import static com.amazaar.Utility.Constants.PERMISSIONS;

public class SplashActivity extends AwesomeSplash {

    int[] m_permission = new int[3];
    private FirebaseAuth mAuth;
    private FirebaseUser currentUser;
    @Inject
    private DeviceAutoLogin m_deviceLogin;
    @Inject
    private RegisterPushNorification m_pushnitification;

    public static boolean checkPermissions(Activity c, int[] pchk) {
        List<String> permissionlist = new ArrayList<>();

        for (int i : pchk) {
            if (ContextCompat.checkSelfPermission(c, PERMISSIONS[i - 1])
                    != PackageManager.PERMISSION_GRANTED) {
                permissionlist.add(PERMISSIONS[i - 1]);
            }
        }

        if (!permissionlist.isEmpty()) {
            ActivityCompat.requestPermissions(c, permissionlist.toArray(new String[permissionlist.size()]), 101);
            return false;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        currentUser = mAuth.getCurrentUser();
        injectMembers();
        m_permission[0] = 6;
        m_permission[1] = 15;
        m_permission[2] = 26;
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
        /// m_pushnitification.registerPushNptificationToken("");
        checkPermissions(this, m_permission);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String description = "amazaar";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(getString(R.string.channelId), getString(R.string.channelId), importance);
            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    @Override
    public void initSplash(ConfigSplash configSplash) {
        Utils.isOnline(SplashActivity.this, true);
        configSplash.setBackgroundColor(R.color.rippelColoragain); //any color you want form colors.xml
        configSplash.setAnimCircularRevealDuration(2000); //int ms
        configSplash.setRevealFlagX(Flags.REVEAL_RIGHT);  //or Flags.REVEAL_LEFT
        configSplash.setRevealFlagY(Flags.REVEAL_BOTTOM); //or Flags.REVEAL_TOP

        //Choose LOGO OR PATH; if you don't provide String value for path it's logo by default

        //Customize Logo
        configSplash.setLogoSplash(R.drawable.resized_again_logo); //or any other drawable
        configSplash.setAnimLogoSplashDuration(2000); //int ms
        configSplash.setAnimLogoSplashTechnique(Techniques.Bounce); //choose one form Techniques (ref: https://github.com/daimajia/AndroidViewAnimations)
        //Customize Path
        // configSplash.setPathSplash(Constants.DROID_LOGO); //set path String
        configSplash.setOriginalHeight(50); //in relation to your svg (path) resource
        configSplash.setOriginalWidth(50); //in relation to your svg (path) resource
        configSplash.setAnimPathStrokeDrawingDuration(3000);
        configSplash.setPathSplashStrokeSize(3); //I advise value be <5
        configSplash.setPathSplashStrokeColor(R.color.strokeColor); //any color you want form colors.xml
        configSplash.setAnimPathFillingDuration(3000);
        configSplash.setPathSplashFillColor(R.color.rippelColoragain); //path object filling color


        //Customize Title
        configSplash.setTitleSplash("");
        configSplash.setTitleTextColor(R.color.white);
        configSplash.setTitleTextSize(30f); //float value
        configSplash.setAnimTitleDuration(3000);
        configSplash.setAnimTitleTechnique(Techniques.FlipInX);
        //configSplash.setTitleFont("fonts/myfont.ttf"); //provide string to your font located in assets/fonts/
    }

    @Override
    public void animationsFinished() {
        m_deviceLogin.doLogin(getApplicationContext());

    }

    private void injectMembers() {
        Injector injector = RoboGuice.getInjector(getApplicationContext());
        injector.injectMembers(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AmazaarApplication.setCurrentActivity(this);
        // qrReaderFragment.getQRCodeReaderWidget().getView().getQrCodeReaderView().startCamera();
    }
}