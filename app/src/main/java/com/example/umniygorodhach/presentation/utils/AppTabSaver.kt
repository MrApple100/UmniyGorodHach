package com.example.umniygorodhach.presentation.utils

import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.saveable.Saver
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.core.os.bundleOf
import com.example.umniygorodhach.R
import kotlinx.parcelize.Parcelize

sealed class AppTab(
    val route: String,
    val startDestination: String,
    @StringRes val resourceId: Int,
    val icon: ImageVector,
    var accessible: Boolean = true
) {
    object Home: AppTab("home_tab", AppScreen.Home.route, R.string.home, Icons.Default.Home)
    object Health: AppTab("health_tab", AppScreen.Health.route, R.string.health, Icons.Default.HealthAndSafety)
    object News: AppTab("news_tab", AppScreen.News.route, R.string.news, Icons.Default.Newspaper,)
    object PlaceEvent: AppTab("placeevent_tab", AppScreen.PlaceEvent.route,  R.string.placeevent, Icons.Default.Place,)
    object Profile: AppTab("profile_tab", AppScreen.Profile.route, R.string.profile, Icons.Default.People,)
    object MyTeam: AppTab("myteam_tab", AppScreen.MyTeam.route, R.string.myteam, Icons.Default.Flag,)
    object Rules: AppTab("rules_tab", AppScreen.Rules.route,  R.string.rules, Icons.Default.Rule)
    object Help: AppTab("help_tab", AppScreen.Help.route, R.string.help, Icons.Default.Help)

    fun saveState() = bundleOf(SCREEN_KEY to route)

    fun asScreen() = when (this) {
        Home -> AppScreen.Home
        Health -> AppScreen.Health
        News -> AppScreen.News
        PlaceEvent -> AppScreen.PlaceEvent
        Profile -> AppScreen.Profile
        MyTeam -> AppScreen.MyTeam
        Rules -> AppScreen.Rules
        Help -> AppScreen.Help
    }

    companion object {
        const val SCREEN_KEY = "SCREEN_KEY"

        val all
            get() = listOf(
                Home,
                Health,
                News,
                PlaceEvent,
                Profile,
                MyTeam,
                Rules,
                Help
            )


        fun saver() = Saver<AppTab, Bundle>(
            save = { it.saveState() },
            restore = { restoreState(it) }
        )

        private fun restoreState(bundle: Bundle) = when (bundle.getString(SCREEN_KEY, null)) {
            Home.route -> Home
            Health.route -> Health
            News.route -> News
            PlaceEvent.route -> PlaceEvent
            Profile.route -> Profile
            MyTeam.route -> MyTeam
            Rules.route -> Rules
            Help.route -> Help
            else            -> {throw IllegalArgumentException("Invalid route. Maybe you forgot to add a new screen to AppTabSaver.kt?")}
        }

        /*fun applyClaims(claims: List<Any>) {
            Feedback.accessible = claims.contains(UserClaimCategories.FEEDBACK.ADMIN)
        }*/
    }
}

// This class represents any screen - tabs and their subscreens.
// It is needed to appropriately change top app bar behavior

@Parcelize
open class AppScreen(
    @StringRes val screenNameResource: Int,
    val route: String,
    val navLink: String = route.substringBefore("/{")
) : Parcelable {
    object Home : AppScreen(R.string.app_name,"home")
    object Health : AppScreen(R.string.app_name,"health")
    object News : AppScreen(R.string.app_name,"news")
    object PlaceEvent : AppScreen(R.string.app_name,"placeevent")
    object Profile : AppScreen(R.string.app_name,"profile")
    object MyTeam : AppScreen(R.string.app_name,"myteam")
    object Rules : AppScreen(R.string.app_name,"rules")
    object Help : AppScreen(R.string.app_name,"help")

    object Raspisanie : AppScreen(R.string.app_name,"rasp")//has back button



    companion object {
        fun getAll(context: Context) = listOf(
            Home,
            Health,
            News,
            PlaceEvent,
            Profile,
            MyTeam,
            Rules,
            Help,
            Raspisanie

        )
    }

}