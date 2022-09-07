package com.example.umniygorodhach.presentation.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.umniygorodhach.R
import com.example.umniygorodhach.presentation.navigation.LocalNavController
import com.example.umniygorodhach.presentation.ui.components.LoadingError
import com.example.umniygorodhach.presentation.utils.AppScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import com.example.umniygorodhach.presentation.utils.singletonViewModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun Home(
    homeViewModel: HomeViewModel = singletonViewModel()
) {
    var isRefreshing = remember { mutableStateOf(false) }
    val raspResource = homeViewModel.raspResponsesFlow.collectAsState().value
    var rasp = homeViewModel.raspFlow.collectAsState().value

    val navController = LocalNavController.current

    SwipeRefresh(
        modifier = Modifier.fillMaxSize(),
        state = rememberSwipeRefreshState(isRefreshing.value),
        onRefresh = homeViewModel::onRefresh
    ) {
        Column(modifier = Modifier.fillMaxSize().padding(10.dp)) {

            Row(modifier = Modifier.fillMaxWidth()) {


                Text(text = "15.07")
            }
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.img),
                    contentDescription = stringResource(R.string.app_name)
                )
                Text(text = "20`")

            }
            (raspResource).handle(
                onLoading = {
                    isRefreshing.value = true
                },
                onError = { msg ->
                    isRefreshing.value = false
                    LoadingError(msg = msg)
                },
                onSuccess = {regionlist ->
                    isRefreshing.value = false
                    homeViewModel.onResourceSuccess(regionlist)
            Card(modifier = Modifier.fillMaxWidth()
                .clickable {
                    navController.navigate(AppScreen.Raspisanie.navLink)

                }) {
                Column() {

                    Text(
                        modifier = Modifier.padding(10.dp).fillMaxWidth(),
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
                                    modifier = Modifier.fillMaxWidth().weight(1f, false),
                                    horizontalArrangement = Arrangement.Center
                                ) {
                                    Text(
                                        text = "00" + ":" + "00",
                                        textAlign = TextAlign.Center
                                    )
                                }
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth().weight(4f, false),
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
        }
    }
}