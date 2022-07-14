package com.example.umniygorodhach.presentation.ui


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.ExperimentalTransitionApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.umniygorodhach.presentation.navigation.AppNavigation
import com.example.umniygorodhach.presentation.navigation.LocalNavController
import com.example.umniygorodhach.presentation.ui.components.Custom_Scaffold
import com.example.umniygorodhach.presentation.ui.components.shared_elements.LocalSharedElementsRootScope
import com.example.umniygorodhach.presentation.ui.components.shared_elements.SharedElementsRoot
import com.example.umniygorodhach.presentation.ui.components.top_app_bars.AppBarViewModel
import com.example.umniygorodhach.presentation.ui.components.top_app_bars.AppTabsViewModel
import com.example.umniygorodhach.presentation.ui.components.top_app_bars.BasicTopAppBar
import com.example.umniygorodhach.presentation.ui.components.wheel_bottom_navigation.WheelNavigation
import com.example.umniygorodhach.presentation.ui.components.wheel_bottom_navigation.WheelNavigationViewModel
import com.example.umniygorodhach.presentation.utils.AppScreen
import com.example.umniygorodhach.presentation.utils.AppTab
import com.google.accompanist.pager.ExperimentalPagerApi
import kotlinx.serialization.ExperimentalSerializationApi
import com.example.umniygorodhach.presentation.screens.events.components.EventsTopAppBar
import com.example.umniygorodhach.presentation.screens.home.HomeViewModel
import com.example.umniygorodhach.presentation.screens.home.components.HomeTopAppBar
import com.example.umniygorodhach.presentation.screens.news.components.NewsTopAppBar
import com.example.umniygorodhach.presentation.ui.components.bottom_sheet.BottomSheet
import com.example.umniygorodhach.presentation.ui.components.bottom_sheet.BottomSheetViewModel

import com.example.umniygorodhach.presentation.utils.singletonViewModel

@ExperimentalSerializationApi
@ExperimentalStdlibApi
@ExperimentalMotionApi
@ExperimentalMaterialApi
@ExperimentalTransitionApi
@ExperimentalAnimationApi
@ExperimentalPagerApi
@Composable
fun ITLabApp(
    appBarViewModel: AppBarViewModel = viewModel(),
    appTabsViewModel: AppTabsViewModel = singletonViewModel(),
    bottomSheetViewModel: BottomSheetViewModel = viewModel(),
    homeViewModel: HomeViewModel = viewModel(),
    wheelNavigationViewModel: WheelNavigationViewModel = viewModel()
) {


    val currentScreen by appBarViewModel.currentScreen.collectAsState()

    val navController = LocalNavController.current

    var sharedElementScope = LocalSharedElementsRootScope.current

    val onBackAction: () -> Unit = {
        if (sharedElementScope?.isRunningTransition == false)
            if (!navController.popBackStack()) appBarViewModel.handleDeepLinkPop()
    }

    LaunchedEffect(bottomSheetViewModel.bottomSheetState.currentValue) {
        if (bottomSheetViewModel.bottomSheetState.currentValue == ModalBottomSheetValue.Hidden)
            bottomSheetViewModel.hide(this)
    }

    ModalBottomSheetLayout(
        sheetState = bottomSheetViewModel.bottomSheetState,
        sheetContent = { BottomSheet() },
        sheetShape = RoundedCornerShape(
            topStart = 16.dp,
            topEnd = 16.dp
        ),
        scrimColor = Color.Black.copy(.25f)
    ) {
        Custom_Scaffold(
            topBar = {
                when (currentScreen) {
                    AppScreen.Home -> HomeTopAppBar()
                    AppScreen.News -> NewsTopAppBar()

                    /*is AppScreen.EventDetails -> BasicTopAppBar(
                        text = stringResource(
                            currentScreen.screenNameResource,
                            (currentScreen as AppScreen.EventDetails).title
                        ),
                        onBackAction = onBackAction
                    )
                    AppScreen.EventNew,
                    AppScreen.EmployeeDetails -> BasicTopAppBar(
                        text = stringResource(currentScreen.screenNameResource),
                        onBackAction = onBackAction
                    )*/

                    /*is AppScreen.ReportDetails -> BasicTopAppBar(
                        text = stringResource(
                            currentScreen.screenNameResource,
                            (currentScreen as AppScreen.ReportDetails).title
                        ),
                        onBackAction = onBackAction
                    )*/
                    else -> BasicTopAppBar(
                        text = stringResource(currentScreen.screenNameResource),
                        onBackAction = onBackAction
                    )
                }
            },
            content = {
                Box(
                    modifier = Modifier.padding(
                        bottom = it.calculateBottomPadding(),
                        top = it.calculateTopPadding()
                    )
                ) {
                    SharedElementsRoot {
                        sharedElementScope = LocalSharedElementsRootScope.current
                        AppNavigation(navController)
                    }
                }


            },

            bottomBar = {

                //WheelNavigation is there

                val currentTab by appBarViewModel.currentTab.collectAsState()

                val pagesSize by appTabsViewModel.pagesSize.collectAsState()


                WheelNavigation(
                    pagesSize = pagesSize,
                    onClickWheel = {
                        //hide and show
                        wheelNavigationViewModel.changeVisible()
                    },

                    ) { WheelItem, appsPage ->

                    val navBackStackEntry by navController.currentBackStackEntryAsState()
                    val currentDestination = navBackStackEntry?.destination


                    val invitationsCount = 0


                    appsPage
                        .forEach { tab ->
                            WheelItem(
                                modifier = Modifier,
                                indexOfTab = appsPage.indexOf(tab),
                                sizeAppTabs = appsPage.size,
                                icon = {
                                    BadgedBox(
                                        badge = {
                                            if (tab is AppTab.Home && invitationsCount > 0)
                                                Badge(
                                                    backgroundColor = Color.Red,
                                                    contentColor = Color.White
                                                ) {
                                                    Text(invitationsCount.toString())
                                                }
                                        }
                                    ) {

                                        Icon(tab.icon, null)

                                    }
                                },
                                label = {

                                    Text(
                                        text = stringResource(tab.resourceId),
                                        fontSize = 9.sp,
                                        lineHeight = 16.sp,

                                        )

                                },
                                selected = currentDestination?.hierarchy?.any { it.route == tab.route } == true,
                                alwaysShowLabel = true,
                                onClick = {
                                    //hide and show
                                    wheelNavigationViewModel.changeVisible()

                                    // As per https://stackoverflow.com/questions/71789903/does-navoptionsbuilder-launchsingletop-work-with-nested-navigation-graphs-in-jet,

                                    // it seems to not be possible to have all three of multiple back stacks, resetting tabs and single top behavior at once by the means
                                    // of Jetpack Navigation APIs, but only two of the above.
                                    // This code provides resetting and singleTop behavior for the default tab.
                                    if (tab == currentTab) {
                                        navController.popBackStack(
                                            route = tab.startDestination,
                                            inclusive = false
                                        )
                                        return@WheelItem
                                    }
                                    // This code always leaves default tab's start destination on the bottom of the stack. Workaround needed?
                                    navController.navigate(tab.route) {
                                        popUpTo(navController.graph.findStartDestination().id) {
                                            saveState = true
                                        }
                                        launchSingleTop = true

                                        // We want to reset the graph if it is clicked while already selected
                                        restoreState = tab != currentTab
                                    }
                                    appBarViewModel.setCurrentTab(tab)

                                }
                            )

                        }

                }
            }
        )
    }
}