package com.example.umniygorodhach

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.ExperimentalTransitionApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.core.app.NotificationCompat
import com.example.umniygorodhach.data.repository.NotafRepository
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.serialization.ExperimentalSerializationApi
import java.time.LocalDateTime
import javax.inject.Inject


@ExperimentalSerializationApi
@ExperimentalComposeUiApi
@ExperimentalStdlibApi
@ExperimentalMotionApi
@ExperimentalMaterialApi
@ExperimentalTransitionApi
@ExperimentalAnimationApi
@ExperimentalPagerApi
class NotificationService : Service() {
   // private lateinit var notafRepository:NotafRepository
    private var notificationManager: NotificationManager? = null
    val DEFAULT_NOTIFICATION_ID = 101


    override fun onCreate() {
       // notafRepository = NotafRepository()
        val context = this as Context
        notificationManager = this.getSystemService( NOTIFICATION_SERVICE) as NotificationManager;
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val timefirst = LocalDateTime.now()
        var timeSecond = timefirst
        while (timeSecond.second + 60 > timefirst.second) {
            timeSecond = LocalDateTime.now()
        }
        sendNotification("Ticker","Title","Text");
        return Service.START_REDELIVER_INTENT
    }

    public fun sendNotification(Ticker: String, Title: String, Text: String) {
        val notificationIntent = Intent(this, MainActivity::class.java);
        notificationIntent.setAction(Intent.ACTION_MAIN);
        notificationIntent.addCategory(Intent.CATEGORY_LAUNCHER);

        val contentIntent = PendingIntent.getActivity(
            getApplicationContext(),
            0,
            notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        );

        val builder = NotificationCompat.Builder(this);
        builder.setContentIntent(contentIntent)
            .setOngoing(true)   //invulnerable
            .setTicker(Ticker)
            .setContentTitle(Title)
            .setContentText(Text)
            .setWhen(System.currentTimeMillis());

        val notification: Notification;
        if (android.os.Build.VERSION.SDK_INT <= 15) {
            notification = builder.getNotification(); // API 15 and lower
        } else {
            notification = builder.build();
        }

        startForeground(DEFAULT_NOTIFICATION_ID, notification);

    }
    override fun onBind(intent: Intent?): IBinder? {
        return Binder()
    }

    override fun onRebind(intent: Intent?) {
        super.onRebind(intent)
    }

    override fun onUnbind(intent: Intent?): Boolean {
        return super.onUnbind(intent)
    }

    override fun onDestroy() {
        //Removing any notifications
        notificationManager!!.cancel(DEFAULT_NOTIFICATION_ID)

        //Disabling service
        stopSelf()
        super.onDestroy()
    }
}