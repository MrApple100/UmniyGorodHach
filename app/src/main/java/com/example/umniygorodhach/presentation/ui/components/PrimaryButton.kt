package com.example.umniygorodhach.presentation.ui.components

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.umniygorodhach.presentation.ui.theme.AppColors
import java.util.*

@Composable
fun PrimaryButton(
	modifier: Modifier = Modifier,
	onClick: () -> Unit,
	text: String,
	enabled: Boolean = true,
	textWrapper: (@Composable RowScope.(text :@Composable () -> Unit) -> Unit)? = null
) {
	Button(
		modifier = modifier
			.clipToBounds(),
		onClick = onClick,
		colors = ButtonDefaults.buttonColors(
			backgroundColor = Color.Transparent,
			disabledBackgroundColor = Color.Transparent,
			contentColor = AppColors.accent.collectAsState().value,
			disabledContentColor = AppColors.greyText.collectAsState().value
		),
		elevation = ButtonDefaults.elevation(
			defaultElevation = 0.dp,
			pressedElevation = 0.dp
		),
		enabled = enabled
	) {
		if (textWrapper != null)
			textWrapper {
				Text(
					text = text.uppercase(Locale.getDefault()),
					fontSize = 14.sp,
					fontWeight = FontWeight(500),
					lineHeight = 22.sp
				)
			}
		else
			Text(
				text = text.uppercase(Locale.getDefault()),
				color = AppColors.accent.collectAsState().value,
				fontSize = 14.sp,
				fontWeight = FontWeight(500),
				lineHeight = 22.sp
			)
	}
}