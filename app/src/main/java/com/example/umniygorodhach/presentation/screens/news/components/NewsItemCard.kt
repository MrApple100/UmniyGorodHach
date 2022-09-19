package com.example.umniygorodhach.presentation.screens.news.components

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.umniygorodhach.BuildConfig
import com.example.umniygorodhach.R
import com.example.umniygorodhach.data.remote.api.news.models.NewsItemResponse
import com.example.umniygorodhach.presentation.screens.news.NewsViewModel
import com.example.umniygorodhach.presentation.ui.components.IconizedRow
import com.example.umniygorodhach.presentation.ui.components.bottom_sheet.BottomSheetViewModel
import com.example.umniygorodhach.presentation.ui.extensions.fromIso8601
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun NewsItemCard(
	devicesViewModel: NewsViewModel,
	bottomSheetViewModel: BottomSheetViewModel,
	newsItem: NewsItemResponse,
	modifier: Modifier,
) {
	val expandedDeviceCardbool = remember { mutableStateOf(false) }

	var dialogUsersIsShown by remember { mutableStateOf(false) }

	val coroutineScope = rememberCoroutineScope()
	Card(
		modifier = modifier
			.clickable {
				expandedDeviceCardbool.value = !expandedDeviceCardbool.value
				Log.d("DeviceCard", newsItem.toString())
			},
		elevation = 2.dp,
		shape = RoundedCornerShape(5.dp)
	) {
		newsItem.run {
			Column(
				modifier = Modifier
					.padding(
						top = 10.dp,
						bottom = 8.dp,
						start = 15.dp,
						end = 15.dp
					)
					.fillMaxWidth()

			) {

					Column(
						modifier = Modifier
							.fillMaxWidth()

					) {
						Row() {
							IconizedRow(
								imageVector = Icons.Default.Person,
								opacity = .7f,
								spacing = 10.dp
							) {
								Text(
									text = author,
									style = MaterialTheme.typography.subtitle1
								)
							}
						}
						Row() {
							IconizedRow(
								imageVector = Icons.Default.Schedule,
								opacity = .7f,
								spacing = 10.dp
							) {
								Text(
									text = "15 июля в 2:21",
									style = MaterialTheme.typography.subtitle1
								)
							}
						}
						Row(
							horizontalArrangement = Arrangement.Center
						) {
							Text(

								text = title.toString(),
								fontWeight = FontWeight(500),
								fontSize = 17.sp,
								lineHeight = 22.sp,
								overflow = TextOverflow.Ellipsis,
								maxLines = 1,

								)
						}

					}
				AsyncImage(
					modifier = Modifier
						//.clip(RoundedCornerShape(10.dp))
						.fillMaxWidth()
						.height(200.dp),
					model = ImageRequest.Builder(LocalContext.current)
						.data(/*BuildConfig.API_URI+"/news/"+*/newsItem.picture)
						.crossfade(true)
						.build(),
					placeholder = painterResource(R.drawable.ic_wheel),
					contentScale = ContentScale.FillBounds,
					contentDescription = stringResource(R.string.app_name),

					)


				AnimatedVisibility(expandedDeviceCardbool.value) {
					Spacer(Modifier.height(10.dp))
				}
				AnimatedVisibility(expandedDeviceCardbool.value) {
					Row(verticalAlignment = Alignment.CenterVertically) {

						Text(
							text = text,
							fontWeight = FontWeight(500),
							fontSize = 16.sp,
							lineHeight = 22.sp
						)

					}
				}
/*
				if (dialogUsersIsShown)
					DeviceChangeOwnerDialog(
						onDismissRequest = { dialogUsersIsShown = false },
						device,
						devicesViewModel,
						afterChange = {
							dialogUsersIsShown = false
						},
						haveOwner = devicesViewModel.usersFlow.collectAsState().value.find { it ->
							it.id.equals(
								device.ownerId
							)
						}
					)

				AnimatedVisibility(expandedDeviceCardbool.value) {
					if (devicesViewModel.accesibleFlow.collectAsState().value) {

						Row(verticalAlignment = Alignment.CenterVertically,
							modifier = Modifier
								.clickable {

									dialogUsersIsShown = true
								}
						) {
							Icon(
								painter = painterResource(R.drawable.ic_person),
								contentDescription = stringResource(R.string.ownerId),
								modifier = Modifier
									.width(16.dp)
									.height(16.dp)

							)
							Spacer(Modifier.width(8.dp))

							Text(
								text = if (ownerlastName != null) "$ownerfirstName $ownerlastName" else stringResource(
									R.string.laboratory
								),
								fontWeight = FontWeight(500),
								fontSize = 16.sp,
								lineHeight = 22.sp,
								color = AppColors.accent.collectAsState().value,
								overflow = TextOverflow.Ellipsis

							)

						}
					} else {
						Row(
							verticalAlignment = Alignment.CenterVertically,

							) {
							Icon(
								painter = painterResource(R.drawable.ic_person),
								contentDescription = stringResource(R.string.ownerId),
								modifier = Modifier
									.width(16.dp)
									.height(16.dp)

							)
							Spacer(Modifier.width(8.dp))

							Text(
								text = if (ownerlastName != null) "$ownerfirstName $ownerlastName" else stringResource(
									R.string.laboratory
								),
								fontWeight = FontWeight(500),
								fontSize = 16.sp,
								lineHeight = 22.sp,
								overflow = TextOverflow.Ellipsis

							)

						}
					}
					Spacer(Modifier.height(8.dp))
				}*/


			}

		}

	}

}

