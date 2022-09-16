
package com.example.umniygorodhach.presentation.screens.events


import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.ExperimentalMotionApi
import com.example.umniygorodhach.R
import com.example.umniygorodhach.data.close.dao.player.PlayerEntity
import com.example.umniygorodhach.data.remote.api.events.models.EventDetail
import com.example.umniygorodhach.presentation.navigation.LocalNavController
import com.example.umniygorodhach.presentation.screens.player.PlayerViewModel
import com.example.umniygorodhach.presentation.ui.components.IconizedRow
import com.example.umniygorodhach.presentation.ui.components.InteractiveField
import com.example.umniygorodhach.presentation.ui.components.LoadingError
import com.example.umniygorodhach.presentation.ui.components.LoadingIndicator
import com.example.umniygorodhach.presentation.ui.components.bottom_sheet.BottomSheetViewModel
import com.example.umniygorodhach.presentation.ui.components.top_app_bars.AppBarViewModel
import com.example.umniygorodhach.presentation.ui.components.top_app_bars.CollapsibleScrollArea
import com.example.umniygorodhach.presentation.ui.components.top_app_bars.SwipingStates
import com.example.umniygorodhach.presentation.ui.components.wheel_bottom_navigation.WheelNavigationViewModel
import com.example.umniygorodhach.presentation.ui.extensions.fromIso8601
import com.example.umniygorodhach.presentation.ui.theme.AppColors
import com.example.umniygorodhach.presentation.utils.AppBottomSheet
import com.example.umniygorodhach.presentation.utils.AppScreen
import com.example.umniygorodhach.presentation.utils.singletonViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@ExperimentalComposeUiApi
@ExperimentalMotionApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun Event(
	id:String,
    eventViewModel: EventViewModel = singletonViewModel(),
	playerViewModel: PlayerViewModel = singletonViewModel(),
    bottomSheetViewModel: BottomSheetViewModel = singletonViewModel(),
    appBarViewModel: AppBarViewModel = singletonViewModel(),
	wheelNavigationViewModel: WheelNavigationViewModel = singletonViewModel()

) {
	val wheelstateflow =wheelNavigationViewModel.currentState.collectAsState().value
	val wheelstate = remember { mutableStateOf(wheelstateflow)}
	val nestedScrollConnection = remember {
		object : NestedScrollConnection {
			override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
			if(wheelstate.value) {
				wheelNavigationViewModel.changeVisible()
				wheelstate.value=false
			}
				return Offset.Zero
			}
		}
	}

	val eventResource by eventViewModel.eventsResponsesFlow.collectAsState()

	Scaffold(
		scaffoldState = rememberScaffoldState(snackbarHostState = eventViewModel.snackbarHostState),
		modifier = Modifier.nestedScroll(nestedScrollConnection),

		) {
		Column(
			modifier = Modifier
				.fillMaxWidth()
		) {
			eventResource.handle(
				onLoading = {
					LoadingIndicator()
				},
				onError = { msg ->
					LoadingError(msg = msg)
				},
				onSuccess = { eventsList ->
					val thisEvent = eventsList.find { it.id == id }!!

					LaunchedEffect(null) {
						if (appBarViewModel.currentScreen.value is AppScreen.EventDetails)
							appBarViewModel.onNavigate(
								AppScreen.EventDetails( thisEvent.title)
							)
					}
					EventInfoWithList(
						event =  thisEvent.toEvent(),
						eventViewModel = eventViewModel,
						playerViewModel = playerViewModel,
						bottomSheetViewModel = bottomSheetViewModel
					)
				}
			)
		}
	}
}

@ExperimentalComposeUiApi
@ExperimentalMotionApi
@ExperimentalMaterialApi
@Composable
private fun EventInfoWithList(
	event: EventDetail,
	eventViewModel: EventViewModel,
	playerViewModel: PlayerViewModel,
	bottomSheetViewModel: BottomSheetViewModel
) {
	val swipingState = rememberSwipeableState(SwipingStates.EXPANDED)

	val playerResource by playerViewModel.playerResourceFlow.collectAsState()
	Log.d("Players",playerResource.toString())


//	MotionLayout(
//		start = startConstraintSet(),
//		end = endConstraintSet(),
//		progress = if (swipingState.progress.to == SwipingStates.COLLAPSED) swipingState.progress.fraction else 1f - swipingState.progress.fraction
//	) {
		var height by remember { mutableStateOf(200) }

		val density = LocalDensity.current
		EventInfo(
			event = event,
			modifier = Modifier
				.layoutId("info")
				.onGloballyPositioned {
					height = with(density) { it.size.height.toDp().value.toInt() }
				},
			bottomSheetViewModel = bottomSheetViewModel
		)
		playerResource.handle(
			onLoading = {
				LoadingIndicator()
			},
			onError = { msg ->
				LoadingError(msg = msg)
			},
			onSuccess = { players ->
				playerViewModel.onResourseSuccess(players)
				Log.d("Players",players.toString())

				CollapsibleScrollArea(
					swipingState = swipingState,
					heightDelta = height,
					modifier = Modifier
						.layoutId("list")
				) {
					ChosePlayers(
						event = event,
						playerViewModel = playerViewModel,
						eventViewModel = eventViewModel,
						bottomSheetViewModel = bottomSheetViewModel
					)
				}
			}
		)
	//}
}

@Composable
private fun startConstraintSet() = ConstraintSet {
	val info = createRefFor("info")
	val list = createRefFor("list")
	constrain(info) {
		start.linkTo(parent.start)
		top.linkTo(parent.top)
		end.linkTo(parent.end)
		pivotY = 1f
	}

	constrain(list) {
		top.linkTo(info.bottom)
	}
}

@SuppressLint("Range")
@Composable
private fun endConstraintSet() = ConstraintSet {
	val info = createRefFor("info")
	val list = createRefFor("list")
	constrain(info) {
		start.linkTo(parent.start)
		top.linkTo(parent.top)
		end.linkTo(parent.end)
		pivotY = -.4f
		scaleX = .7f
		scaleY = .7f
		alpha = 0f
	}

	constrain(list) {
		top.linkTo(parent.top)
		bottom.linkTo(parent.bottom)
	}
}

@ExperimentalMaterialApi
@Composable
private fun EventInfo(
	event: EventDetail,
	modifier: Modifier = Modifier,
	bottomSheetViewModel: BottomSheetViewModel
) {
	val context = LocalContext.current
	val coroutineScope = rememberCoroutineScope()
	Surface(
		modifier = modifier
			.fillMaxWidth(),
		color = MaterialTheme.colors.surface,
		elevation = 1.dp
	) {
		Column(
			modifier = Modifier
				.fillMaxWidth()
				.padding(20.dp),
			verticalArrangement = Arrangement.spacedBy(10.dp)
		) {
			IconizedRow(
				imageVector = Icons.Default.Schedule,
				opacity = .7f,
				spacing = 10.dp
			) {
				Text(
					text = "${
						event.beginTime.fromIso8601(
							context,
							","
						)
					} — ${event.endTime.fromIso8601(context, ",")}"
				)
			}

			IconizedRow(
				imageVector = Icons.Default.Payment,
				opacity = .7f,
				spacing = 10.dp
			) {
				Text(
					text = if (event.money != 0) stringResource(
						R.string.money,
						event.money
					) else stringResource(R.string.free)
				)
			}

			IconizedRow(
				imageVector = Icons.Default.LocationOn,
				opacity = .7f,
				spacing = 10.dp
			) {
				Text(
					text = event.address
				)
			}

			IconizedRow(
				imageVector = Icons.Default.People,
				opacity = .7f,
				spacing = 10.dp
			) {
				Text(
					text = "${event.currentParticipationCount}/${event.targetParticipationCount}"
				)
			}

			IconizedRow(
				imageVector = Icons.Default.Info,
				opacity = .7f,
				spacing = 10.dp
			) {
				Row(
					modifier = Modifier
						.fillMaxWidth(),
					horizontalArrangement = Arrangement.SpaceBetween
				) {
					Text(
						text = event.eventType
					)

					InteractiveField(
						value = stringResource(R.string.more),
						hasArrow = true
					) {
						bottomSheetViewModel.show(
							sheet = AppBottomSheet.EventDescription(event.description),
							scope = coroutineScope
						)
					}
				}
			}


		}
	}
}


@ExperimentalComposeUiApi
@ExperimentalMaterialApi
@Composable
private fun ChosePlayers(
	event: EventDetail,
	playerViewModel: PlayerViewModel,
	eventViewModel: EventViewModel,
	bottomSheetViewModel: BottomSheetViewModel) {
	val players = playerViewModel.playerFlow.collectAsState().value

	val navController = LocalNavController.current

	var chosenPlayers = remember{ mutableStateOf(arrayListOf<PlayerEntity>()) }
	val clist = Array<Color>(players.size) { it -> Color.Gray }
	val colorCard = remember{ mutableStateListOf<Color>(*clist)}

	val coroutineScope = rememberCoroutineScope()

	var isRefreshing by remember { mutableStateOf(false) }




	Column(
		modifier = Modifier
			.fillMaxSize()
	) {
		Button(
			modifier = Modifier
				.fillMaxWidth(),
			onClick = {
				eventViewModel.registratePlayers(
					event.id,
					chosenPlayers.value as List<PlayerEntity>
				) { isSuccess ->
					if (isSuccess) {
						navController.navigate("${AppScreen.MyEvents.navLink}")

					}
				}
			}
		) {
			Text(
				text = "Принять участие",
				style = MaterialTheme.typography.h5,
			)
		}
		SwipeRefresh(
			modifier = Modifier
				.fillMaxSize(),
			state = rememberSwipeRefreshState(isRefreshing),
			onRefresh = playerViewModel::onRefresh
		) {

			LazyColumn(
				modifier = Modifier
					.fillMaxSize(),
				verticalArrangement = Arrangement.spacedBy(10.dp),
				contentPadding = PaddingValues(bottom = 15.dp, start = 20.dp, end = 20.dp),
				userScrollEnabled = true
			) {
				item() {
					Spacer(modifier = Modifier.height(15.dp))
					Text(
						text = stringResource(R.string.choseplayer),
						style = MaterialTheme.typography.h4
					)
				}
				item() {
					Card(
						modifier = Modifier
							.fillMaxWidth()
							.height(50.dp)
							.clickable {
								navController.navigate("${AppScreen.CreatePlayer.navLink}")

							},
						elevation = 2.dp,
						shape = RoundedCornerShape(5.dp)
					) {
						Row(
							horizontalArrangement = Arrangement.Center,
							verticalAlignment = Alignment.CenterVertically
						) {
							Icon(
								imageVector = Icons.Default.Add,
								contentDescription = "add",
								modifier = Modifier
									.width(30.dp)
									.height(30.dp)

							)
						}
					}
				}
				items(
					items = players,

					) { player ->
					Log.d("Players", players.size.toString())

					Log.d("Players", player.toString())

					Card(
						modifier = Modifier
							.fillMaxWidth()

							.clickable {
								if (!chosenPlayers.value.contains(player)) {
									chosenPlayers.value.add(player)
									colorCard[players.indexOf(player)] = Color.Cyan
								} else {
									chosenPlayers.value.remove(player);
									colorCard[players.indexOf(player)] = Color.Gray
								}

								Log.d("Players", chosenPlayers.value.size.toString())
								Log.d("PlayersC", colorCard[players.indexOf(player)].toString())
							},
						elevation = 2.dp,
						shape = RoundedCornerShape(5.dp),
						backgroundColor = colorCard[players.indexOf(player)]
					) {
						Column(
							modifier = Modifier
								.padding(15.dp)
						) {
							Text(
								text = player.lastname,
								style = MaterialTheme.typography.h6,
							)
							Text(
								text = player.firstname,
								style = MaterialTheme.typography.h6,
							)
							Text(
								text = player.middlename,
								style = MaterialTheme.typography.h6,
							)
							Text(
								text = "Возраст: " + player.age.toString(),
								style = MaterialTheme.typography.subtitle1,
								color = AppColors.greyText.collectAsState().value
							)

							Spacer(Modifier.height(5.dp))


						}
					}
					/*PlayerCard(
					modifier = Modifier
						.fillMaxWidth()
						.clickable {
							if (!chosenPlayers.value.contains(player)) {
								chosenPlayers.value.add(player)
								colorCard.value[players.indexOf(player)] = Color.Gray
							} else {
								chosenPlayers.value.remove(player);
								colorCard.value[players.indexOf(player)] = Color.Cyan

							}
							Log.d("Players", chosenPlayers.value.size.toString())
						},
					colors = colorCard,
					listplayers = players,
					player = player
				)*/
				}
			}
		}
	}
	Log.d("Players",players.toString())

}

@Composable
fun PlayerCard(
	modifier: Modifier = Modifier,
	player: PlayerEntity,
	listplayers:MutableList<PlayerEntity>,
	colors: MutableState<Array<Color>>
){
	Log.d("Players",colors.value[listplayers.indexOf(player)].value.toString())

	Card(
		modifier = modifier,
		elevation = 2.dp,
		shape = RoundedCornerShape(5.dp),
		backgroundColor = colors.value[listplayers.indexOf(player)]
	) {
		Column(
			modifier = Modifier
				.padding(15.dp)
		) {
			Text(
				text = player.lastname,
				style = MaterialTheme.typography.h6,
			)
			Text(
				text = player.firstname,
				style = MaterialTheme.typography.h6,
			)
			Text(
				text = player.middlename,
				style = MaterialTheme.typography.h6,
			)
			Text(
				text = "Возраст: "+player.age.toString(),
				style = MaterialTheme.typography.subtitle1,
				color = AppColors.greyText.collectAsState().value
			)

			Spacer(Modifier.height(5.dp))


		}
	}
}


