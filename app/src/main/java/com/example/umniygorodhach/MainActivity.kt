package com.example.umniygorodhach


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
import androidx.navigation.compose.rememberNavController
import com.example.umniygorodhach.presentation.ui.ITLabApp
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.serialization.ExperimentalSerializationApi
import com.example.umniygorodhach.presentation.navigation.LocalNavController
import com.example.umniygorodhach.presentation.ui.theme.ITLabTheme
import com.example.umniygorodhach.presentation.utils.LocalActivity
import dagger.hilt.android.AndroidEntryPoint

@ExperimentalSerializationApi
@ExperimentalPagerApi
@ExperimentalAnimationApi
@ExperimentalTransitionApi
@ExperimentalMaterialApi
@ExperimentalMotionApi
@ExperimentalStdlibApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
           // val authState by authViewModel.authStateFlow.collectAsState(null)
            ITLabTheme {
                Surface(color = MaterialTheme.colors.background) {
                    //when (authState?.isAuthorized) {
                     //   true -> {
                            CompositionLocalProvider(
                                LocalNavController provides rememberNavController(),
                                LocalActivity provides this
                            ) {
                                ITLabApp()
                            }
                        //}
                        //false -> AuthScreen { authViewModel.onLoginEvent(authPageLauncher) }
                       // null -> {}
                   // }
                }
            }
        }
    }
}

