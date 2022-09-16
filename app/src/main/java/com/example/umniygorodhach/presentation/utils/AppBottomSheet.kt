package com.example.umniygorodhach.presentation.utils

import androidx.compose.material.ExperimentalMaterialApi
import com.example.umniygorodhach.data.remote.api.events.models.detail.Shift
import com.example.umniygorodhach.presentation.screens.events.EventViewModel
import com.example.umniygorodhach.presentation.ui.components.bottom_sheet.BottomSheetViewModel

@ExperimentalMaterialApi
sealed class AppBottomSheet {
	class EventShift(
		val shift: Shift,
		val salaries: List<Int>,
		val eventViewModel: EventViewModel
	): AppBottomSheet()
	class EventDescription(val markdown: String): AppBottomSheet()
	/*class DeviceInfo (
		val deviceDetails: DeviceDetails?,
		val devicesViewModel: DevicesViewModel,
		val bottomSheetViewModel: BottomSheetViewModel

	): AppBottomSheet()
	class DeviceNew(
		val devicesViewModel: DevicesViewModel,
		val bottomSheetViewModel: BottomSheetViewModel,
	): AppBottomSheet()

	object ProfileEquipment: AppBottomSheet()
	object ProfileSettings: AppBottomSheet()
	class ProfileEvents(
		val viewModel: UserViewModel
	): AppBottomSheet()
	object Equipment: AppBottomSheet()


	class UserSelection(
		val onSelect: (User) -> Unit
	): AppBottomSheet()*/
	object Unspecified: AppBottomSheet()
	override fun equals(other: Any?): Boolean {
		return false
	}
}
