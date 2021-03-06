package com.example.umniygorodhach.presentation.screens.news.components

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.umniygorodhach.R
import com.example.umniygorodhach.presentation.screens.news.NewsViewModel
import com.example.umniygorodhach.presentation.ui.components.SearchBar
import com.example.umniygorodhach.presentation.ui.components.top_app_bars.AppBarOption
import com.example.umniygorodhach.presentation.ui.components.top_app_bars.AppBarTabRow
import com.example.umniygorodhach.presentation.ui.components.top_app_bars.ExtendedTopAppBar
import com.example.umniygorodhach.presentation.ui.components.top_app_bars.TabbedTopAppBar
import com.example.umniygorodhach.presentation.utils.NewsTab
import com.example.umniygorodhach.presentation.utils.singletonViewModel
import com.google.accompanist.pager.ExperimentalPagerApi

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun NewsTopAppBar(
	newsViewModel: NewsViewModel = singletonViewModel()
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
	TabbedTopAppBar(
		pagerState = newsViewModel.pagerState,
		tabs = tabs,
	) {
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
				newsViewModel.onSearch("")
			}
		) {
			Column(

			) {


					if (searchActivated.value) {
						SearchBar(
							onSearch = newsViewModel::onSearch
						)
					} else {
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
	}
}