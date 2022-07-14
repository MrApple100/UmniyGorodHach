package com.example.umniygorodhach.presentation.ui.components.bottom_sheet

import androidx.activity.compose.BackHandler
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.ExperimentalTransitionApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalTransitionApi
@ExperimentalAnimationApi
@ExperimentalPagerApi
@ExperimentalMaterialApi
@Composable
fun BottomSheet(
	viewModel: BottomSheetViewModel = viewModel()
) {
	val currentSheet by viewModel.currentBottomSheet.collectAsState()
	val sheetIsVisible by viewModel.visibilityAsState.collectAsState()

	val coroutineScope = rememberCoroutineScope()
	if (sheetIsVisible)
		BackHandler {
			viewModel.hide(coroutineScope)
		}

	Column(
		modifier = Modifier
			.padding(
				top = 15.dp,
				start = 15.dp,
				end = 15.dp
			)
			.fillMaxWidth(),
		horizontalAlignment = Alignment.CenterHorizontally
	) {
		/*Icon(
			painter = painterResource(R.drawable.ic_upholder),
			contentDescription = null,
			tint = MaterialTheme.colors.onSurface
		)*/
		Spacer(Modifier.height(15.dp))

		when (currentSheet) {
			/*is AppBottomSheet.EventShift -> {
				val shift = currentSheet as AppBottomSheet.EventShift
				ShiftBottomSheet(
					shift = shift.shift,
					salaries = shift.salaries,
					eventViewModel = shift.eventViewModel
				)
			}
			is AppBottomSheet.EventDescription -> {
				MarkdownTextArea(
					modifier = Modifier.verticalScroll(rememberScrollState()),
					textMd = (currentSheet as AppBottomSheet.EventDescription).markdown
				)
			}
			is AppBottomSheet.ProfileSettings -> ProfileSettingsBottomSheet()
			is AppBottomSheet.ProfileEvents -> {
				val data = currentSheet as AppBottomSheet.ProfileEvents
				ProfileEventsBottomSheet(
					userViewModel = data.viewModel
				)
			}
			is AppBottomSheet.DeviceInfo -> {
				val details = currentSheet as AppBottomSheet.DeviceInfo
				val deviceDetails = details.deviceDetails
				val devicesViewModel = details.devicesViewModel
				val bottomSheetViewModel = details.bottomSheetViewModel
				DeviceInfoBottomSheet(devicesViewModel, bottomSheetViewModel,deviceDetails)
			}
			is AppBottomSheet.DeviceNew -> {
				val details = currentSheet as AppBottomSheet.DeviceNew
				val devicesViewModel = details.devicesViewModel
				val bottomSheetViewModel = details.bottomSheetViewModel
				DeviceNewBottomSheet(devicesViewModel,  bottomSheetViewModel)


			}
			is AppBottomSheet.UserSelection -> {
				UserSelectionBottomSheet(
					onSelect = (currentSheet as AppBottomSheet.UserSelection).onSelect,
					bottomSheetViewModel = viewModel
				)
			}*/
			else -> {}
		}
	}

}
