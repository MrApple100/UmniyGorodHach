package com.example.umniygorodhach.presentation.screens.myevents

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.umniygorodhach.R
import com.example.umniygorodhach.presentation.navigation.LocalNavController
import com.example.umniygorodhach.presentation.screens.home.duration
import com.example.umniygorodhach.presentation.ui.components.LoadingError
import com.example.umniygorodhach.presentation.ui.components.shared_elements.SharedElement
import com.example.umniygorodhach.presentation.ui.components.shared_elements.utils.SharedElementsTransitionSpec
import com.example.umniygorodhach.presentation.utils.AppScreen
import com.example.umniygorodhach.presentation.utils.singletonViewModel

@Composable
fun MyEvents(
    myeventsViewModel:MyEventsViewModel = singletonViewModel()
){
    var isRefreshing = remember { mutableStateOf(false) }
    val eventsResource = myeventsViewModel.eventsResponsesFlow.collectAsState().value
    val events = myeventsViewModel.eventsFlow.collectAsState().value

    val navController = LocalNavController.current



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
            myeventsViewModel.onResourceSuccess(eventslist)
            /*Card(modifier = Modifier
                .fillMaxWidth()
                .clickable {
                   // navController.navigate(AppScreen.Raspisanie.navLink)

                }) {*/
            Column() {

               /* Text(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    textAlign = TextAlign.Right,
                    text = "Ближайшие мероприятия"
                )*/

                LazyColumn() {
                    items(events) { event ->
                        SharedElement(
                            key = event.id,
                            screenKey = AppScreen.Home.route,
                            transitionSpec = SharedElementsTransitionSpec(
                                durationMillis = duration
                            )
                        ) {
                            val expandedDeviceCardbool = remember { mutableStateOf(false) }

                            Card(modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    expandedDeviceCardbool.value = !expandedDeviceCardbool.value

                                }) {
                                Column(){
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
                                    AnimatedVisibility(expandedDeviceCardbool.value) {
                                        Spacer(Modifier.height(10.dp))
                                    }
                                    AnimatedVisibility(expandedDeviceCardbool.value) {
                                        Column() {

                                            Text(
                                                text = stringResource(R.string.players),
                                                style = MaterialTheme.typography.h6
                                            )
                                            LazyColumn(
                                                modifier = Modifier.height(100.dp)

                                            ) {

                                                items(event.listPlayer) { player ->
                                                    Text(
                                                        modifier = Modifier
                                                            .fillMaxWidth()
                                                            .height(20.dp)
                                                            .padding(10.dp, 0.dp),
                                                        text = player.lastname + " " + player.firstname[0] + ". " + player.middlename[0] + "."
                                                    )
                                                }
                                            }
                                        }
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