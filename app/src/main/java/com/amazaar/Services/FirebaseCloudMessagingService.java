package com.amazaar.Services;

import android.app.Notification;
import android.media.RingtoneManager;
import android.net.Uri;
import android.util.Log;

import androidx.annotation.NonNull;

import com.amazaar.R;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import ir.zadak.zadaknotify.notification.ZadakNotification;

public class FirebaseCloudMessagingService extends FirebaseMessagingService {
    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        long[] pattern = {10, 100, 1000, 200, 2000};
        Uri uri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        Log.e("TOKEN","RECIVED");
        ZadakNotification.with(getApplicationContext())
                .load()
                .notificationChannelId("Hello")
                .title(remoteMessage.getNotification().getTitle())
                .message(remoteMessage.getNotification().getBody())
                .vibrate(pattern)
                .sound(uri)
                .smallIcon(R.drawable.ic_launcher)
                .largeIcon(R.drawable.ic_launcher)
                .flags(Notification.DEFAULT_ALL)
                .simple()
                .build();
    }

    @Override
    public void onNewToken(@NonNull String s) {
        Log.e("TOKEN",s);
    }
}
