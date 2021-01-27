package com.example.reminder;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;


public class Broadcast extends BroadcastReceiver {
    private String CHANNEL_ID="15";

    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationCompat.Builder builder
                =new NotificationCompat.Builder(context,CHANNEL_ID)
                .setSmallIcon(android.R.drawable.sym_def_app_icon)
                .setContentTitle("It's time to train")
                .setContentText(intent.getStringExtra("muscle"))
                .setPriority(4)
                .setVibrate(new long[] {0, 1000})
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setLights(Color.GREEN,1,1)
                .setAutoCancel(true);

        Intent actionIntent = new Intent(context, DetailActivity.class);
        actionIntent.putExtra("muscle",intent.getStringExtra("muscle"));
        actionIntent.putExtra("exercise",intent.getStringExtra("exercise"));
        actionIntent.putExtra("count",intent.getStringExtra("count"));
        PendingIntent actionPendingIntent = PendingIntent.getActivity(
                context,
                0,
                actionIntent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(actionPendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel =
                    new NotificationChannel(CHANNEL_ID, "Notification Channel",
                            NotificationManager.IMPORTANCE_DEFAULT);
            AudioAttributes att = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                    .build();

            channel.setSound(Settings.System.DEFAULT_NOTIFICATION_URI,att);
            notificationManager.createNotificationChannel(channel);
        }
        notificationManager.notify(intent.getIntExtra("not_id",0), builder.build());



    }
}
