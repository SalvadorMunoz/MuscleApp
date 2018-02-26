package com.example.linux.muscleapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v4.app.NotificationCompat;

import com.example.linux.muscleapp.ui.session.MainActivity;

/**
 * Created by linux on 26/02/18.
 */

public class MuscleAppReceiver extends BroadcastReceiver {
    public static final int NOTIFICATION=30;
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent newIntent= new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,NOTIFICATION,newIntent,PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setContentTitle(context.getResources().getString(R.string.app_name));
        builder.setSmallIcon(R.drawable.ic_action_ok);
        builder.setLargeIcon(BitmapFactory.decodeResource(context.getResources(),R.drawable.ic_action_ok));
        builder.setContentText(context.getResources().getString(R.string.access));

        builder.setContentIntent(pendingIntent);
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        manager.notify(NOTIFICATION,builder.build());
    }
}
