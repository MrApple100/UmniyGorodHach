package com.example.umniygorodhach.presentation.screens.news

import android.content.Intent
import android.graphics.drawable.shapes.RectShape
import android.net.Uri
import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.example.umniygorodhach.BuildConfig
import com.example.umniygorodhach.R
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
    resultsViewModel: ResultsViewModel = singletonViewModel(),
    bottomSheetViewModel: BottomSheetViewModel = viewModel()

) {

    val newsResource by newsViewModel.newsItemsResponsesFlow.collectAsState()


    val mysborkaResource by resultsViewModel.sborkaItemsResponsesFlow.collectAsState()
    val regionResource by resultsViewModel.regionItemsResponsesFlow.collectAsState()

    val mysborkas by resultsViewModel.sborkaItemsFlow.collectAsState()
    val regions by resultsViewModel.regionItemsFlow.collectAsState()


    var isRefreshing by remember { mutableStateOf(false) }

    val tabs = listOf(
        NewsTab.News,
        NewsTab.Results
    )

    Spacer(modifier = Modifier.height(50.dp))
    Column() {
        HorizontalPager(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.Top,
            count = tabs.size,
            state = newsViewModel.pagerState,
            itemSpacing = 1.dp
        ) { index ->
            SwipeRefresh(
                modifier = Modifier.fillMaxSize(),
                state = rememberSwipeRefreshState(isRefreshing),
                onRefresh = newsViewModel::onRefresh
            ) {

                Box {
                    when (tabs[index]) {
                        NewsTab.News -> {
                            Column(
                            ) {

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

                                        NewsList(newsViewModel, bottomSheetViewModel)

                                    }

                                )
                            }
                        }


                        NewsTab.Results -> {
                            var isRefreshing by remember { mutableStateOf(false) }

                            SwipeRefresh(
                                modifier = Modifier.fillMaxSize(),
                                state = rememberSwipeRefreshState(isRefreshing),
                                onRefresh = resultsViewModel::onRefresh
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    (mysborkaResource).handle(
                                        onLoading = {
                                            isRefreshing = true
                                        },
                                        onError = { msg ->
                                            isRefreshing = false
                                            LoadingError(msg = msg)
                                        },
                                        onSuccess = { mysborkalist ->
                                            isRefreshing = false
                                            resultsViewModel.onSborkaResourceSuccess(
                                                mysborkalist
                                            )
                                            Card(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .heightIn(200.dp)
                                                    .padding(15.dp)
                                            ) {
                                                Row(
                                                    modifier = Modifier
                                                        .padding(5.dp)
                                                ) {

                                                    Column() {

                                                        Row(
                                                            modifier = Modifier
                                                                .fillMaxWidth()
                                                                .padding(5.dp),
                                                            horizontalArrangement = Arrangement.Center
                                                        ) {
                                                            Text(text = "Моя Сборная")
                                                        }
                                                        Row() {
                                                            Column(
                                                                modifier = Modifier.weight(
                                                                    3f,
                                                                    false
                                                                )
                                                            ) {

                                                                Row(
                                                                    modifier = Modifier.fillMaxWidth(),
                                                                    horizontalArrangement = Arrangement.Center
                                                                ) {
                                                                    Text(text = "Имя/Название команды")
                                                                }
                                                                LazyColumn() {

                                                                    items(mysborkas) { it
                                                                        Row(
                                                                            modifier = Modifier.fillMaxWidth(),
                                                                            horizontalArrangement = Arrangement.Center
                                                                        ) {
                                                                            Text(text = it.nameteam)
                                                                        }
                                                                    }
                                                                }

                                                            }
                                                            Column(
                                                                modifier = Modifier.weight(
                                                                    1f,
                                                                    false
                                                                )

                                                            ) {

                                                                Row(
                                                                    modifier = Modifier.fillMaxWidth(),
                                                                    horizontalArrangement = Arrangement.Center
                                                                ) {
                                                                    Text(text = "Этап")
                                                                }

                                                                LazyColumn() {

                                                                    items(mysborkas) { it
                                                                        Row(
                                                                            modifier = Modifier.fillMaxWidth(),
                                                                            horizontalArrangement = Arrangement.Center
                                                                        ) {
                                                                            Text(text = it.term)
                                                                        }
                                                                    }
                                                                }

                                                            }
                                                            Column(
                                                                modifier = Modifier.weight(
                                                                    1f,
                                                                    false
                                                                )
                                                            ) {

                                                                Row(
                                                                    modifier = Modifier.fillMaxWidth(),
                                                                    horizontalArrangement = Arrangement.Center
                                                                ) {
                                                                    Text(text = "Место")
                                                                }
                                                                LazyColumn() {

                                                                    items(mysborkas) { it
                                                                        Row(
                                                                            modifier = Modifier.fillMaxWidth(),
                                                                            horizontalArrangement = Arrangement.Center
                                                                        ) {
                                                                            Text(text = it.place)
                                                                        }
                                                                    }
                                                                }

                                                            }
                                                        }
                                                    }

                                                }
                                            }

                                        })

                                    (regionResource).handle(
                                        onLoading = {
                                            isRefreshing = true
                                        },
                                        onError = { msg ->
                                            isRefreshing = false
                                            LoadingError(msg = msg)
                                        },
                                        onSuccess = { regionlist ->
                                            isRefreshing = false
                                            resultsViewModel.onRegionResourceSuccess(
                                                regionlist
                                            )
                                            Card(
                                                modifier = Modifier
                                                    .fillMaxWidth()
                                                    .padding(15.dp)
                                                    .heightIn(200.dp)
                                            )
                                            {
                                                Row(
                                                    modifier = Modifier
                                                        .padding(5.dp)
                                                ) {

                                                    Column() {

                                                        Row(
                                                            modifier = Modifier
                                                                .fillMaxWidth()
                                                                .padding(5.dp),
                                                            horizontalArrangement = Arrangement.Center
                                                        ) {
                                                            Text(text = "Рейтинг Регионов")
                                                        }
                                                        Row() {
                                                            Column(
                                                                modifier = Modifier.weight(
                                                                    1f,
                                                                    false
                                                                )

                                                            ) {

                                                                LazyColumn() {

                                                                    items(regions) { it
                                                                        Row(
                                                                            modifier = Modifier.fillMaxWidth(),
                                                                            horizontalArrangement = Arrangement.Center
                                                                        ) {
                                                                            Text(text = it.place)
                                                                        }
                                                                    }
                                                                }

                                                            }
                                                            Column(
                                                                modifier = Modifier.weight(
                                                                    3f,
                                                                    false
                                                                )

                                                            ) {


                                                                LazyColumn() {

                                                                    items(regions) { it
                                                                        Row(
                                                                            modifier = Modifier.fillMaxWidth(),
                                                                            horizontalArrangement = Arrangement.Center
                                                                        ) {
                                                                            Text(text = it.region)
                                                                        }
                                                                    }
                                                                }

                                                            }
                                                            Column(
                                                                modifier = Modifier.weight(
                                                                    1f,
                                                                    false
                                                                )

                                                            ) {

                                                                LazyColumn() {

                                                                    items(regions) { it
                                                                        Row(
                                                                            modifier = Modifier.fillMaxWidth(),
                                                                            horizontalArrangement = Arrangement.Center
                                                                        ) {
                                                                            Text(text = it.points)
                                                                        }
                                                                    }
                                                                }

                                                            }
                                                        }
                                                    }

                                                }
                                            }

                                        })
                                }
                            }
                        }

                        }

                    }
                }
            }


        }
    }


    @ExperimentalPagerApi
    @ExperimentalMaterialApi
    @ExperimentalAnimationApi
    @Composable
    private fun TranslationRow(
        newsViewModel: NewsViewModel,
        bottomSheetViewModel: BottomSheetViewModel
    ) {
        val context = LocalContext.current
        val translist by newsViewModel.transItemsFlow.collectAsState()
        Log.d("ASDF", translist.toString())

        LazyRow(
        ) {

            items(translist) { trans ->
                AsyncImage(
                    modifier = Modifier
                        .padding(5.dp)
                        .clip(RoundedCornerShape(70.dp))
                        .fillMaxWidth()
                        .height(70.dp)
                        .width(70.dp)
                        .clickable {
                            val browserIntent =
                                Intent(Intent.ACTION_VIEW, Uri.parse(trans.broadURL));
                            startActivity(context, browserIntent, null)
                        },
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(/*BuildConfig.API_URI + "/broadcast/" + */trans.picture)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.ic_wheel),
                    contentScale = ContentScale.FillBounds,
                    contentDescription = stringResource(R.string.app_name),

                    )
            }
        }

    }


    @ExperimentalPagerApi
    @ExperimentalMaterialApi
    @ExperimentalAnimationApi
    @Composable
    private fun OnlineTable(
        newsViewModel: NewsViewModel,
        bottomSheetViewModel: BottomSheetViewModel
    ) {
        val context = LocalContext.current
        val onlineMatch by newsViewModel.OnlineMatchFlow.collectAsState()
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(200.dp)
                .padding(15.dp)
                .clickable {
                    val browserIntent =
                        Intent(Intent.ACTION_VIEW, Uri.parse(onlineMatch?.url));
                    startActivity(context, browserIntent, null)
                }
        ) {
            Column() {


                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f, false),
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(text = onlineMatch?.firstteam.toString())
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .weight(1f, false),
                        horizontalArrangement = Arrangement.End
                    ) {
                        Text(text = onlineMatch?.secondteam.toString())
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = "0:23")
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = onlineMatch?.firstNumber.toString() + ":" + onlineMatch?.secondNumber.toString(),
                        fontSize = 70.sp,
                        fontWeight = FontWeight(500),
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colors.onSurface,
                    )

                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = onlineMatch?.vid.toString())
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(text = onlineMatch?.term.toString())
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
        newsItems.reverse()
        // val currentDeviceId = newsViewModel.deviceIdFlow.collectAsState()

        val transResource by newsViewModel.transItemsResponsesFlow.collectAsState()
        val OnlineMatchResource by newsViewModel.OnlineMatchResponsesFlow.collectAsState()


        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp),
            contentPadding = PaddingValues(horizontal = 15.dp, vertical = 15.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            item {
                (OnlineMatchResource).handle(
                    onLoading = {
                        //  isRefreshing = true
                    },
                    onError = { msg ->
                        //  isRefreshing = false
                        LoadingError(msg = msg)
                    },
                    onSuccess = { OnlineMatch ->
                        newsViewModel.onOnlineMatchResourceSuccess(OnlineMatch)
                        OnlineTable(newsViewModel, bottomSheetViewModel)
                    })
                (transResource).handle(
                    onLoading = {
                        //  isRefreshing = true
                    },
                    onError = { msg ->
                        //  isRefreshing = false
                        LoadingError(msg = msg)
                    },
                    onSuccess = { translist ->
                        newsViewModel.onTransResourceSuccess(translist)
                        TranslationRow(newsViewModel, bottomSheetViewModel)
                    })
            }

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

