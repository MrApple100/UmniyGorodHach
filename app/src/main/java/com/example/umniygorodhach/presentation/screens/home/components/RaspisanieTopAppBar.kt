package com.example.umniygorodhach.presentation.screens.home.components

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.umniygorodhach.presentation.ui.components.bottom_sheet.BottomSheetViewModel
import com.example.umniygorodhach.presentation.ui.components.top_app_bars.BasicTopAppBar

@ExperimentalMaterialApi
@Composable
fun RaspisanieTopAppBar(
	onBackAction: () -> Unit
) {
	val scope = rememberCoroutineScope()
	BasicTopAppBar(
		text = "Расписание",
		options = listOf(
		),
		onBackAction = onBackAction
	)
}