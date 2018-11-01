package io.diaryofrifat.code.utils.helper.notification

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.media.AudioAttributes
import android.net.Uri
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import io.diaryofrifat.code.BaseMvpApplication
import io.diaryofrifat.code.utils.helper.DataUtils
import java.util.*

object NotificationUtils {
    enum class NotificationType {
        DEFAULT
    }

    private val notificationChannelRepository =
            EnumMap<NotificationType, NotificationChannelDetails>(NotificationType::class.java)

    init {
        notificationChannelRepository[NotificationType.DEFAULT] =
                NotificationChannelDetails("DefaultId", "Default", "Default Notification")
    }

    private fun createNotificationChannel(notificationManager: NotificationManager,
                                          channelDetails: NotificationChannelDetails,
                                          channelImportance: Int, soundFileUri: Uri?) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                    NotificationChannel(channelDetails.id, channelDetails.name, channelImportance)
                            .apply {
                                description = channelDetails.description
                            }

            if (soundFileUri != null) {
                // Configure the notification channel
                channel.setSound(soundFileUri, AudioAttributes.Builder()
                        .setUsage(AudioAttributes.USAGE_NOTIFICATION).build())
            }

            // Register the channel with the system
            notificationManager.createNotificationChannel(channel)
        }
    }

    fun buildNotification(notificationId: Int,
                          notificationType: NotificationType,
                          iconResourceId: Int,
                          title: String,
                          subtitle: String,
                          soundFileResourceId: Int? = null) {

        var soundFileUri: Uri? = null

        if (soundFileResourceId != null) {
            soundFileUri = DataUtils.getUriFromResource(soundFileResourceId)
        }

        val channelDetails = notificationChannelRepository[notificationType]!!

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationManager: NotificationManager =
                    BaseMvpApplication.getBaseApplicationContext()
                            .getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            var shouldCreateChannel = true

            for (notificationChannel in notificationManager.notificationChannels) {
                if (notificationChannel.id == channelDetails.id) {
                    shouldCreateChannel = false
                }
            }

            if (shouldCreateChannel) {
                createNotificationChannel(notificationManager, channelDetails,
                        NotificationManager.IMPORTANCE_DEFAULT, soundFileUri)
            }
        }

        val builder =
                NotificationCompat.Builder(BaseMvpApplication.getBaseApplicationContext(), channelDetails.id)
                        .setSmallIcon(iconResourceId)
                        .setContentTitle(title)
                        .setContentText(subtitle)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O && soundFileUri != null) {
            builder.setSound(soundFileUri)
        }

        val manager: NotificationManagerCompat = NotificationManagerCompat.from(
                BaseMvpApplication.getBaseApplicationContext())
        manager.notify(notificationId, builder.build())
    }
}