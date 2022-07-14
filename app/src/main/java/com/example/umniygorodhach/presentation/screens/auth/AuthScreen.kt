package com.example.umniygorodhach.presentation.screens.auth


import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.serialization.ExperimentalSerializationApi


@ExperimentalMaterialApi
@ExperimentalAnimationApi
@ExperimentalSerializationApi
@ExperimentalStdlibApi
@Composable
fun AuthScreen(
	onLoginEvent: () -> Unit,
               ) {
	Column(
		modifier = Modifier
			.fillMaxSize()
			.fillMaxSize(),
		horizontalAlignment = Alignment.CenterHorizontally,
		verticalArrangement = Arrangement.Center
	) {
		/*Icon(
			painter = painterResource(R.drawable.ic_itlab),
			contentDescription = null,
			modifier = Modifier
				.height(110.dp)
				.width(120.dp),
			tint = MaterialTheme.colors.onBackground
		)

		Spacer(modifier = Modifier.padding(11.dp))

		Text(
			text = stringResource(R.string.rtuitlab),
			fontSize = 16.sp,
			fontWeight = FontWeight(400),
			lineHeight = 22.sp
		)

		Spacer(modifier = Modifier.padding(45.dp))

		Button(
			onClick = onLoginEvent,
			colors = ButtonDefaults.buttonColors(
				backgroundColor = Color.Transparent
			),
			elevation = ButtonDefaults.elevation(
				defaultElevation = 0.dp,
				pressedElevation = 0.dp
			)
		) {
			Text(
				text = stringResource(R.string.login).uppercase(Locale.getDefault()),
				color = AppColors.accent.collectAsState().value,
				fontSize = 14.sp,
				fontWeight = FontWeight(500),
				lineHeight = 22.sp
			)
		}*/
	}

}