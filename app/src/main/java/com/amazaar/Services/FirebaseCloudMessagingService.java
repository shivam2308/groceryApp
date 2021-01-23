package com.amazaar.Services;

import android.app.Notification;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.amazaar.ClientServices.PushNotificationClientService;
import com.amazaar.ControlFlow.RegisterPushNorification;
import com.amazaar.DatabaseEnitityHelper.LoginEntityDaoHelper;
import com.amazaar.Module.AmazaarApplication;
import com.amazaar.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import javax.inject.Inject;

import ir.zadak.zadaknotify.notification.ZadakNotification;

public class FirebaseCloudMessagingService extends FirebaseMessagingService {

    @Inject
    public RegisterPushNorification m_pushNotification;

    @Inject
    public FirebaseCloudMessagingService(RegisterPushNorification pushNotification){
        m_pushNotification= pushNotification;
    }

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        long[] pattern = {10, 100, 1000, 200, 2000};
        Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        ZadakNotification.with(getApplicationContext())
                .load()
                .notificationChannelId("amazaar_notify")
                .title(remoteMessage.getNotification().getTitle())
                .message(remoteMessage.getNotification().getBody())
                .vibrate(pattern)
                .sound(uri)
                .smallIcon(R.mipmap.ic_launcher)
                .largeIcon(R.mipmap.ic_launcher)
                .flags(Notification.DEFAULT_ALL)
                .simple()
                .build();
    }

    @Override
    public void onNewToken(@NonNull String s) {
        Log.e("TOKEN",s);
        AmazaarApplication.setDeviceToken(s);
        m_pushNotification.registerPushNptificationToken();
    }


}
