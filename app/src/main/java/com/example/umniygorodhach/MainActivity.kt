package com.example.umniygorodhach


import android.app.ActivityManager
import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.ExperimentalTransitionApi
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.CompositionLocalProvider
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.core.app.NotificationCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.compose.rememberNavController
import com.example.umniygorodhach.presentation.navigation.LocalNavController
import com.example.umniygorodhach.presentation.ui.UmniyGorodApp
import com.example.umniygorodhach.presentation.ui.theme.UmniyGorodTheme
import com.example.umniygorodhach.presentation.utils.LocalActivity
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.ExperimentalSerializationApi


@ExperimentalSerializationApi
@ExperimentalPagerApi
@ExperimentalAnimationApi
@ExperimentalTransitionApi
@ExperimentalMaterialApi
@ExperimentalMotionApi
@ExperimentalStdlibApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    var intentService: Intent? = null
    lateinit var notificationManager: NotificationManager;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
           // val authState by authViewModel.authStateFlow.collectAsState(null)
            UmniyGorodTheme(){
                Surface(color = MaterialTheme.colors.background) {
                    //when (authState?.isAuthorized) {
                     //   true -> {
                            CompositionLocalProvider(
                                LocalNavController provides rememberNavController(),
                                LocalActivity provides this
                            ) {
                                UmniyGorodApp()
                            }
                        //}
                        //false -> AuthScreen { authViewModel.onLoginEvent(authPageLauncher) }
                       // null -> {}
                   // }
                }
            }
        }
    }


    fun sendNotification(Ticker: String, Title: String, Text: String) {
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
            .setSmallIcon(R.drawable.ic_wheel)
            .setContentText(Text)
            .setWhen(System.currentTimeMillis());

        val notification: Notification;
        if (android.os.Build.VERSION.SDK_INT <= 15) {
            notification = builder.getNotification(); // API 15 and lower
        } else {
            notification = builder.build();
        }

        notificationManager.notify(500, notification);

    }
}

