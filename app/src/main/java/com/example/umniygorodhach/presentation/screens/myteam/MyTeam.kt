package com.example.umniygorodhach.presentation.screens.myteam

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.umniygorodhach.R
import com.example.umniygorodhach.presentation.navigation.LocalNavController
import com.example.umniygorodhach.presentation.screens.player.PlayerViewModel
import com.example.umniygorodhach.presentation.ui.components.LoadingError
import com.example.umniygorodhach.presentation.ui.components.LoadingIndicator
import com.example.umniygorodhach.presentation.ui.theme.AppColors
import com.example.umniygorodhach.presentation.utils.AppBottomSheet
import com.example.umniygorodhach.presentation.utils.AppScreen
import com.example.umniygorodhach.presentation.utils.singletonViewModel

@Composable
fun MyTeam(
    playerViewModel: PlayerViewModel = singletonViewModel()
){
    val navController = LocalNavController.current
    val playerResource by playerViewModel.playerResourceFlow.collectAsState()

    val players = playerViewModel.playerFlow.collectAsState().value

    playerResource.handle(
        onLoading = {
            LoadingIndicator()
        },
        onError = { msg ->
            LoadingError(msg = msg)
        },
        onSuccess = { play ->
            playerViewModel.onResourseSuccess(play)
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.spacedBy(10.dp),
                contentPadding = PaddingValues(bottom = 15.dp, start = 20.dp, end = 20.dp),
                userScrollEnabled = true
            ) {
                item() {
                    Spacer(modifier = Modifier.height(15.dp))

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


                    Card(
                        modifier = Modifier
                            .fillMaxWidth(),
                        elevation = 2.dp,
                        shape = RoundedCornerShape(5.dp)
                    ) {
                            Row(
                                horizontalArrangement = Arrangement.End,
                                verticalAlignment = Alignment.Top,
                                modifier = Modifier
                                    .fillMaxSize()


                            ) {


                                    Icon(
                                        imageVector = Icons.Default.Delete,
                                        contentDescription = stringResource(R.string.delete),
                                        tint = colorResource(R.color.accent),
                                        modifier = Modifier
                                            .padding(10.dp)
                                            .width(16.dp)
                                            .height(16.dp)
                                            .padding(0.dp)
                                            .clickable {
                                                playerViewModel.onDeletePlayer(player){
                                                    playerViewModel.onRefresh()
                                                }
                                                //navController.navigate(AppScreen.DeviceDetails.route)

                                            }


                                    )
                                }
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
                }
            }
        })
}

