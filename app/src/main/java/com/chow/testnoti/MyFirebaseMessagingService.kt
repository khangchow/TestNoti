package com.chow.testnoti

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService: FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        (getSystemService(NOTIFICATION_SERVICE) as NotificationManager).notify(
            System.currentTimeMillis().toInt(),
            NotificationCompat.Builder(this, "CHANNEL_ID")
                .setContentTitle("Title")
                .setContentText("Text")
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setForegroundServiceBehavior(NotificationCompat.FOREGROUND_SERVICE_IMMEDIATE)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setContentIntent(getPendingIntent())
                .build()
        )
    }

    fun getPendingIntentFlag() =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) PendingIntent.FLAG_IMMUTABLE
        else PendingIntent.FLAG_UPDATE_CURRENT

    private fun getPendingIntent() = PendingIntent.getActivity(
        this,
        0,
        Intent(this, MainActivity::class.java),
        getPendingIntentFlag()
    )

    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("CHOTAOTEST", "onNewToken: "+token)
    }
}