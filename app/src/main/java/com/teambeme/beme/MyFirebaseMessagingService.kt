package com.teambeme.beme

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent

import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build

import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.teambeme.beme.notification.view.NotificationActivity

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onNewToken(token: String) {
        Log.d(TAG, "new Token: $token")
//        val pref = this.getSharedPreferences("token", Context.MODE_PRIVATE)
//        val editor = pref.edit()
//        editor.putString("token", token).apply()
//        editor.commit()
//
//        Log.i("로그: ", "성공적으로 토큰을 저장함")

        sendRegistrationToServer(token)
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.from)
        super.onMessageReceived(remoteMessage)

        if (remoteMessage.data.isNotEmpty()) {
            Log.i("바디: ", remoteMessage.data["body"].toString())
            Log.i("타이틀: ", remoteMessage.data["title"].toString())
            sendNotification(remoteMessage)
        } else {
            Log.i("수신에러: ", "data가 비어있습니다. 메시지를 수신하지 못했습니다.")
            Log.i("data값: ", remoteMessage.data.toString())
        }
    }

    private fun sendNotification(remoteMessage: RemoteMessage) {
        val uniId = 0
        val intent = Intent(this, NotificationActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivities(
            this, uniId, arrayOf(intent), PendingIntent.FLAG_ONE_SHOT
        )

        val channelId = "노티피케이션 메시지"

        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notificationBuilder =
            NotificationCompat.Builder(this, channelId).setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle(remoteMessage.data["body"].toString())
                .setContentText(remoteMessage.data["title"].toString())
                .setAutoCancel(true)
                .setSound(soundUri)
                .setContentIntent(pendingIntent)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(channelId, "Notice", NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }
        notificationManager.notify(uniId, notificationBuilder.build())
    }

    private fun sendRegistrationToServer(token: String) {
        Log.d(TAG, "sendRegistrationTokenToServer($token)")
    }
    companion object {
        const val TAG = "BeMeApplication"
    }
}
