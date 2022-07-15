package com.example.umniygorodhach


import android.app.ActivityManager
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
    private fun isMyServiceRunning(serviceClass: Class<*>): Boolean {
        val manager = getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
        for (service in manager.getRunningServices(Int.MAX_VALUE)) {
            if (serviceClass.name == service.service.className) {
                return true
            }
        }
        return false
    }
}

