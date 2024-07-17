package eryaz.software.panda.core.notificationManager


import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Color
import android.media.RingtoneManager
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import eryaz.software.panda.R
import eryaz.software.panda.ui.MainActivity
import eryaz.software.panda.ui.dashboard.outbound.orderPicking.OrderPickingListFragment


class PushNotificationService  : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        super.onNewToken(token)
    }

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        val notification = message.notification
        showNotification(notification?.title,notification?.body)
    }

    private fun showNotification(title: String?, body: String?) {
        val intent = Intent(this, OrderPickingListFragment::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent,
            PendingIntent.FLAG_ONE_SHOT or PendingIntent.FLAG_IMMUTABLE)
        val notificationBuilder: NotificationCompat.Builder =
            NotificationCompat.Builder(this, NotificationHelper.CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_notification_zeus_logo) // Set custom icon
                .setLargeIcon(
                    BitmapFactory.decodeResource(
                        resources,
                        R.drawable.zeus_logo
                    )
                )
                .setContentTitle(title)
                .setContentText(body)
                .setAutoCancel(true)
                .setColor(getColor(R.color.ic_launcher_background)) // Set custom color
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)) // Set custom sound
                .setContentIntent(pendingIntent)
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notificationBuilder.build())
    }
}