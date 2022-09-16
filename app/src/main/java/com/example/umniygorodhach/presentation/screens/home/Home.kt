package com.example.umniygorodhach.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.umniygorodhach.R
import com.example.umniygorodhach.presentation.navigation.LocalNavController
import com.example.umniygorodhach.presentation.screens.events.EventViewModel
import com.example.umniygorodhach.presentation.ui.components.ButtonLoadingIndicator
import com.example.umniygorodhach.presentation.ui.components.LoadingError
import com.example.umniygorodhach.presentation.ui.components.shared_elements.SharedElement
import com.example.umniygorodhach.presentation.ui.components.shared_elements.utils.SharedElementsTransitionSpec
import com.example.umniygorodhach.presentation.utils.AppScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.example.umniygorodhach.presentation.utils.singletonViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

val duration = 300

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun Home(
    homeViewModel: HomeViewModel = singletonViewModel(),
    eventsViewModel: EventViewModel = singletonViewModel()
) {
    var isRefreshing = remember { mutableStateOf(false) }
    val raspResource = homeViewModel.raspResponsesFlow.collectAsState().value
    val eventsResource = eventsViewModel.eventsResponsesFlow.collectAsState().value
    val events = eventsViewModel.eventsFlow.collectAsState().value


    var rasp = homeViewModel.raspFlow.collectAsState().value

    val navController = LocalNavController.current

    SwipeRefresh(
        modifier = Modifier.fillMaxWidth(),
        state = rememberSwipeRefreshState(isRefreshing.value),
        onRefresh = homeViewModel::onRefresh
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)) {

            Row(modifier = Modifier.fillMaxWidth()) {


                Text(
                    text = "15.07",
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1.0f)
                        .padding(10.dp)

                )
                Button(
                    onClick = {},
                    shape = RoundedCornerShape(5.dp)

                ){
                    Text(text = "Сканировать QR")
                }

            }
            Row(
                modifier = Modifier.
                fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img),
                    contentDescription = stringResource(R.string.app_name)
                )
                Text(
                    text = "20`C",
                    fontSize = 20.sp
                )

            }
            (raspResource).handle(
                onLoading = {
                    isRefreshing.value = true
                },
                onError = { msg ->
                    isRefreshing.value = false
                   // LoadingError(msg = msg)
                },
                onSuccess = {regionlist ->
                    isRefreshing.value = false
                    homeViewModel.onResourceSuccess(regionlist)
            Card(modifier = Modifier
                .fillMaxWidth()
                .clickable {
                    navController.navigate(AppScreen.Raspisanie.navLink)

                }) {
                Column() {

                    Text(
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        text = "Мое расписание"
                    )
                    Row(modifier = Modifier.padding(10.dp)) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1f, false),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(text = "Время", textAlign = TextAlign.Center)
                        }
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(4f, false),
                            horizontalArrangement = Arrangement.Center
                        ) {
                            Text(text = "Занятие", textAlign = TextAlign.Center)
                        }
                    }
                    LazyColumn() {
                        items(rasp) { item ->
                            Row(modifier = Modifier.padding(10.dp)) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(1f, false),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = "00" + ":" + "00",
                                        textAlign = TextAlign.Center
                                    )
                                }
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .weight(4f, false),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text(text = item.title, textAlign = TextAlign.Center)
                                }
                            }
                        }
                    }
                }
            }
            })
            (eventsResource).handle(
                onLoading = {
                    isRefreshing.value = true
                },
                onError = { msg ->
                    isRefreshing.value = false
                    LoadingError(msg = msg)
                },
                onSuccess = {eventslist ->
                    isRefreshing.value = false
                    eventsViewModel.onResourceSuccess(eventslist)
                    /*Card(modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                           // navController.navigate(AppScreen.Raspisanie.navLink)

                        }) {*/
                        Column() {

                            Text(
                                modifier = Modifier
                                    .padding(10.dp)
                                    .fillMaxWidth(),
                                textAlign = TextAlign.Right,
                                text = "Ближайшие мероприятия"
                            )

                            LazyColumn() {
                                items(events) { event ->
                                    SharedElement(
                                        key = event.id,
                                        screenKey = AppScreen.Home.route,
                                        transitionSpec = SharedElementsTransitionSpec(
                                            durationMillis = duration
                                        )
                                    ) {
                                        Card(modifier = Modifier
                                            .fillMaxWidth()
                                            .clickable {
                                                navController.navigate("${AppScreen.EventDetails.navLink}/${event.id}")

                                            }) {
                                            Row(modifier = Modifier.padding(10.dp)) {

                                                Row(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .weight(4f, false),
                                                    horizontalArrangement = Arrangement.Center
                                                ) {
                                                    Text(
                                                        text = event.title,
                                                        textAlign = TextAlign.Center
                                                    )
                                                }
                                                Row(
                                                    modifier = Modifier
                                                        .fillMaxWidth()
                                                        .weight(1f, false),
                                                    horizontalArrangement = Arrangement.Center
                                                ) {
                                                    Text(
                                                        text = "00" + ":" + "00",
                                                        textAlign = TextAlign.Center
                                                    )
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                   // }
                })

        }
    }
}