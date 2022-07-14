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
    object Home: AppTab("home_tab", AppScreen.Home.route, R.string.app_name, Icons.Default.Home)
    object Health: AppTab("health_tab", AppScreen.Health.route, R.string.app_name, Icons.Default.HealthAndSafety)
    object News: AppTab("news_tab", AppScreen.News.route, R.string.app_name, Icons.Default.Newspaper,)
    object PlaceEvent: AppTab("placeevent_tab", AppScreen.PlaceEvent.route,  R.string.app_name, Icons.Default.Place,)
    object Profile: AppTab("profile_tab", AppScreen.Profile.route, R.string.app_name, Icons.Default.People,)
    object Rules: AppTab("rules_tab", AppScreen.Rules.route,  R.string.app_name, Icons.Default.Rule)
    object Help: AppTab("help_tab", AppScreen.Help.route, R.string.app_name, Icons.Default.Help)

    fun saveState() = bundleOf(SCREEN_KEY to route)

    fun asScreen() = when (this) {
        Home -> AppScreen.Home
        Health -> AppScreen.Health
        News -> AppScreen.News
        PlaceEvent -> AppScreen.PlaceEvent
        Profile -> AppScreen.Profile
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
    object Rules : AppScreen(R.string.app_name,"rules")
    object Help : AppScreen(R.string.app_name,"help")
   /* // Employee-related
    object Employees : AppScreen(R.string.app_name, "employees")
    object EmployeeDetails : AppScreen(R.string.app_name, "employee/{userId}") // Has back button

    // Feedback-related
    object Feedback : AppScreen(R.string.app_name, "feedback")

    // Events-related
    object Events : AppScreen(R.string.app_name, "events")

    @Parcelize
    class EventDetails(val title: String) :
        AppScreen(R.string.app_name, "event/{eventId}") { // Has back button
        companion object {
            const val route = "event/{eventId}"
            val navLink: String = route.substringBefore("/{")
        }
    }

    object EventNew : AppScreen(R.string.app_name, "event/new") // Has back button
    object EventsNotifications :
        AppScreen(R.string.app_name, "events/notifications") // Has back button

    // Projects-related
    object Projects : AppScreen(R.string.app_name, "projects")

    // Devices-related
    object Devices : AppScreen(R.string.app_name, "devices")

    // Profile-related
    object Profile : AppScreen(R.string.app_name, "profile")


    // Reports-related
    object Reports : AppScreen(R.string.app_name, "reports")
    class ReportDetails(val title: String) : AppScreen(R.string.app_name, "report/{reportId}") {
        companion object {
            const val route = "report/{reportId}"
            val navLink: String = route.substringBefore("/{")
        }
    }

    object NewReport : AppScreen(R.string.app_name, "reports/new")
*/

    companion object {
        fun getAll(context: Context) = listOf(
            Home,
            Health,
            News,
            PlaceEvent,
            Profile,
            Rules,
            Help
            /*Employees,
            EmployeeDetails,
            Feedback,
            Events,
            EventDetails(context.resources.getString(R.string.app_name)),
            EventsNotifications,
            Projects,
            Devices,
            Profile,
            Reports,
            ReportDetails(context.resources.getString( R.string.app_name)),
            NewReport*/
        )
    }

}