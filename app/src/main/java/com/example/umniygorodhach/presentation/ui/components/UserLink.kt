//package com.example.traininghakatonsever.presentation.ui.components
//
//import androidx.compose.runtime.Composable
//import ru.rtuitlab.itlab.data.remote.api.users.models.User
//import ru.rtuitlab.itlab.data.remote.api.users.models.UserResponse
//import umniygorodhach.presentation.navigation.getLocalNavController
//import com.example.umniygorodhach.InteractiveField
//import com.example.umniygorodhach.AppScreen
//
//@Composable
//fun UserLink(
//	user: UserResponse,
//	onNavigate: (() -> Unit)? = {}
//) {
//	val navController = LocalNavController.current
//	InteractiveField(value = user.fullName) {
//		navController.navigate("${AppScreen.EmployeeDetails.navLink}/${user.id}")
//		onNavigate?.invoke()
//	}
//}
//
//@Composable
//fun UserLink(
//	user: User,
//	onNavigate: (() -> Unit)? = {}
//) {
//	val userResponse = user.toUserResponse()
//	val navController = LocalNavController.current
//	InteractiveField(value = userResponse.fullName) {
//		navController.navigate("${AppScreen.EmployeeDetails.navLink}/${user.id}")
//		onNavigate?.invoke()
//	}
//}