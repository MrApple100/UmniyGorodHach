package com.example.umniygorodhach.presentation.screens.news

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.umniygorodhach.presentation.screens.news.components.NewsItemCard
import com.example.umniygorodhach.presentation.ui.components.LoadingError
import com.example.umniygorodhach.presentation.ui.components.bottom_sheet.BottomSheetViewModel
import com.example.umniygorodhach.presentation.ui.components.top_app_bars.AppBarTabRow
import com.example.umniygorodhach.presentation.utils.NewsTab
import com.example.umniygorodhach.presentation.utils.singletonViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


@ExperimentalAnimationApi
@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun News(
    newsViewModel: NewsViewModel = singletonViewModel(),
    bottomSheetViewModel: BottomSheetViewModel = viewModel()

) {

    val newsResource by newsViewModel.newsItemsResponsesFlow.collectAsState()
    var isRefreshing by remember { mutableStateOf(false) }

    val tabs = listOf(
        NewsTab.News,
        NewsTab.Results,
    )

    Column {
        Surface(
            color = MaterialTheme.colors.primarySurface,
            contentColor = contentColorFor(MaterialTheme.colors.primarySurface),
            elevation = AppBarDefaults.TopAppBarElevation
        ) {
            AppBarTabRow(
                pagerState = newsViewModel.pagerState,
                tabs = tabs,
                isScrollable = true
            )
        }
        SwipeRefresh(
            modifier = Modifier
                .fillMaxSize(),
            state = rememberSwipeRefreshState(isRefreshing),
            onRefresh = newsViewModel::onRefresh
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                HorizontalPager(
                    modifier = Modifier.fillMaxSize(),
                    verticalAlignment = Alignment.Top,
                    count = tabs.size,
                    state = newsViewModel.pagerState
                ) { index ->
                    newsResource.handle(
                        onLoading = {
                            isRefreshing = true
                        },
                        onError = { msg ->
                            isRefreshing = false
                            LoadingError(msg = msg)
                        },
                        onSuccess = {
                            isRefreshing = false
                            newsViewModel.onResourceSuccess(it)
                            Box {
                                when(tabs[index]) {
                                    NewsTab.News -> NewsList(newsViewModel, bottomSheetViewModel)
                                    NewsTab.Results -> Results()

                                }

                            }



                        }

                    )
                }
            }


        }
    }

}

@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
private fun NewsList(
    newsViewModel: NewsViewModel,
    bottomSheetViewModel: BottomSheetViewModel
) {
    val newsItems by newsViewModel.newsItemsFlow.collectAsState()
   // val currentDeviceId = newsViewModel.deviceIdFlow.collectAsState()




    LazyColumn(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        contentPadding = PaddingValues(horizontal = 15.dp, vertical = 15.dp),
        modifier = Modifier.fillMaxSize()
    ) {


        items(newsItems) { newsItem ->


            NewsItemCard(
                devicesViewModel = newsViewModel,
                bottomSheetViewModel = bottomSheetViewModel,

                newsItem = newsItem,
                modifier = Modifier
                    .fillMaxWidth()

            )


        }


    }

}

@ExperimentalPagerApi
@ExperimentalMaterialApi
@ExperimentalAnimationApi
@Composable
fun Results(){
    Column(modifier = Modifier
        .fillMaxWidth()
    ){
        Card(modifier = Modifer) {
            
        }
    }
}