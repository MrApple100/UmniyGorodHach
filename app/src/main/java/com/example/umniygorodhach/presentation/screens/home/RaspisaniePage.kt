package com.example.umniygorodhach.presentation.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.umniygorodhach.presentation.utils.AppScreen
import com.example.umniygorodhach.presentation.utils.singletonViewModel
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun Raspisanie(
    homeViewModel: HomeViewModel = singletonViewModel()
) {
    val rasp by homeViewModel.raspFlow.collectAsState()
    Column(verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally) {


        Card(modifier = Modifier
            .fillMaxWidth()) {
            Column() {

                Text(
                    modifier = Modifier.fillMaxWidth().padding(10.dp),
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
                                modifier = Modifier.fillMaxWidth().weight(4f, false),
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Column() {
                                Text(text = item.title, textAlign = TextAlign.Center)
                                Text(text = item.title, textAlign = TextAlign.Center)
                                }
                            }

                        }
                    }
                }
            }
        }
    }
}