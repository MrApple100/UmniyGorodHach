package com.example.umniygorodhach.presentation.screens.news

import androidx.compose.material.ExperimentalMaterialApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.traininghakatonsever.common.emitInIO
import com.example.umniygorodhach.common.Resource
import com.example.umniygorodhach.data.remote.api.news.models.NewsItemResponse
import com.example.umniygorodhach.data.repository.NewsRepository
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.PagerState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
@ExperimentalMaterialApi
@ExperimentalPagerApi
@HiltViewModel
class NewsViewModel @Inject constructor(
    private val newsRepository: NewsRepository,
    //private val authStateStorage: AuthStateStorage
) : ViewModel() {

    private val _newsItemResponsesFlow = MutableStateFlow<Resource<MutableList<NewsItemResponse>>>(Resource.Loading)
    val newsItemsResponsesFlow = _newsItemResponsesFlow.asStateFlow().also {fetchNews() }


    var cachedNewsItems = mutableListOf<NewsItemResponse>()

    private val _newsItemsFlow = MutableStateFlow(cachedNewsItems)
    val newsItemsFlow = _newsItemsFlow.asStateFlow()

    fun onSearch(query: String) {
        _newsItemsFlow.value = cachedNewsItems.filter { newsItem ->

            "${newsItem.title} ${newsItem.description} ${newsItem.author}".contains(query.trim(), ignoreCase = true)
        } as MutableList<NewsItemResponse>
    }

    fun onResourceSuccess(newsItems: List<NewsItemResponse>) {

        cachedNewsItems = newsItems as MutableList<NewsItemResponse>

        _newsItemsFlow.value = cachedNewsItems
    }

    fun onRefresh() {
            fetchNews()
    }

    private fun fetchNews() = _newsItemResponsesFlow.emitInIO(viewModelScope) {
        newsRepository.fetchNews()
    }

    val pagerState = PagerState()

}