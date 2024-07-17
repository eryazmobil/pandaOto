package eryaz.software.panda.core.notificationManager

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Build

class NotificationHelper {

    companion object {
        const val CHANNEL_ID = "Zeus"
        private const val CHANNEL_NAME = "ZeusWms"
        private const val CHANNEL_DESCRIPTION = "ZeusWmsApp"

        fun createNotificationChannel(context: Context) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val channel = NotificationChannel(
                    CHANNEL_ID,
                    CHANNEL_NAME,
                    NotificationManager.IMPORTANCE_DEFAULT
                )
                channel.setSound(
                    RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION),
                    AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                        .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                        .build()
                )
                channel.description = CHANNEL_DESCRIPTION
                val notificationManager = context.getSystemService(
                    NotificationManager::class.java
                )
                notificationManager.createNotificationChannel(channel)
            }
        }
    }


}