package com.example.umniygorodhach.presentation.screens.home.components

import androidx.activity.compose.BackHandler
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.example.umniygorodhach.presentation.screens.home.HomeViewModel
import com.example.umniygorodhach.presentation.ui.components.top_app_bars.AppBarViewModel

@ExperimentalMaterialApi
@ExperimentalMotionApi
@ExperimentalPagerApi
@Composable
fun HomeTopAppBar(
	homeViewModel: HomeViewModel = viewModel(),
	appBarViewModel: AppBarViewModel = viewModel()
) {

	var searchActivated by rememberSaveable { mutableStateOf(false) }

	if (searchActivated)
		BackHandler {
			searchActivated = false
		}
	Text(text = "HomeTop")
}
/*
	val showPastEventsChecked by eventsViewModel.showPastEvents.collectAsState()

	val context = LocalContext.current

	val listener = MaterialPickerOnPositiveButtonClickListener<Pair<Long, Long>> {
		eventsViewModel.setEventsDates(it.first, it.second)
	}

	val isDateSelectionMade by eventsViewModel.isDateSelectionMade.collectAsState()


	val beginEventsDate by eventsViewModel.beginEventsDate.collectAsState()
	val endEventsDate by eventsViewModel.endEventsDate.collectAsState()

	val navController by appBarViewModel.currentNavHost.collectAsState()

	CollapsibleTopAppBar(
		title = stringResource(R.string.events),
		options = listOf(
			AppBarOption.Dropdown(
				icon = Icons.Default.FilterList,
				dropdownMenuContent = { collapseAction ->
					LabelledCheckBox(
						checked = showPastEventsChecked,
						onCheckedChange = {
							eventsViewModel.toggleShowPastEvents(it)
							collapseAction()
						},
						label = stringResource(R.string.events_show_past)
					)
					DropdownMenuItem(
						onClick = {
							if (isDateSelectionMade)
								eventsViewModel.fetchPendingEvents()
							else
								MaterialDatePicker
									.Builder
									.dateRangePicker()
									.setSelection(
										Pair(beginEventsDate, endEventsDate)
									)
									.setTheme(R.style.ThemeOverlay_MaterialComponents_MaterialCalendar)
									.build()
									.apply {
										show((context as AppCompatActivity).supportFragmentManager, null)
										addOnPositiveButtonClickListener(listener)
									}
							collapseAction()
						}
					) {
						Text(
							text = stringResource(
								if (isDateSelectionMade) R.string.events_clear_period
								else R.string.events_choose_period
							),
							maxLines = 1,
							overflow = TextOverflow.Ellipsis
						)
					}
				}
			),
			AppBarOption.Clickable(
				icon = Icons.Default.Notifications,
				onClick = {
					appBarViewModel.onNavigate(AppScreen.EventsNotifications)
					navController?.navigate(AppScreen.EventsNotifications.route)
				},
				badgeCount = eventsViewModel.invitationsCountFlow.collectAsState().value
			),
			AppBarOption.Clickable(
				icon = Icons.Default.Search,
				onClick = {
					searchActivated = true
				}
			)
		),
		swipingState = eventsViewModel.swipingState,
		hideBackButton = !searchActivated,
		hideOptions = searchActivated,
		onBackAction = {
			searchActivated = false
			eventsViewModel.onSearch("")
		},
		searchActivated = searchActivated,
		searchBar = {
			SearchBar(
				onSearch = eventsViewModel::onSearch
			)
		}
	) {
		AppBarTabRow(
			modifier = it,
			pagerState = eventsViewModel.pagerState,
			tabs = listOf(
				EventTab.All,
				EventTab.My
			)
		)
	}*/

