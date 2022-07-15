package com.example.umniygorodhach.presentation.screens.home.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.umniygorodhach.R
import com.google.accompanist.pager.ExperimentalPagerApi
import com.example.umniygorodhach.presentation.screens.home.HomeViewModel
import com.example.umniygorodhach.presentation.ui.components.SearchBar
import com.example.umniygorodhach.presentation.ui.components.top_app_bars.AppBarOption
import com.example.umniygorodhach.presentation.ui.components.top_app_bars.AppBarViewModel
import com.example.umniygorodhach.presentation.ui.components.top_app_bars.ExtendedTopAppBar
import com.example.umniygorodhach.presentation.ui.components.top_app_bars.TabbedTopAppBar
import com.example.umniygorodhach.presentation.utils.NewsTab

@ExperimentalMaterialApi
@ExperimentalMotionApi
@ExperimentalPagerApi
@Composable
fun HomeTopAppBar(
	homeViewModel: HomeViewModel = viewModel(),
	appBarViewModel: AppBarViewModel = viewModel()
) {
	val searchActivated = rememberSaveable { mutableStateOf(false) }
	val tabs = listOf(
		NewsTab.News,
		NewsTab.Results
	)

	if (searchActivated.value)
		BackHandler {
			searchActivated.value = false
		}

		ExtendedTopAppBar(
			options = listOf(
				AppBarOption.Clickable(
					icon = Icons.Default.Search,
					onClick = {
						searchActivated.value = true
					}
				)
			),
			hideBackButton = !searchActivated.value,
			hideOptions = searchActivated.value,
			onBackAction = {
				searchActivated.value = false
				//homeViewModel.onSearch("")
			}
		) {
			Column(

			) {


					Text(
						text = stringResource(R.string.news),
						fontSize = 20.sp,
						fontWeight = FontWeight(500),
						textAlign = TextAlign.Start,
						color = MaterialTheme.colors.onSurface
					)




			}
		}

}