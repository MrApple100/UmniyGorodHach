package com.example.umniygorodhach.presentation.screens.news

import androidx.compose.material.ExperimentalMaterialApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.traininghakatonsever.common.emitInIO
import com.example.umniygorodhach.common.Resource
import com.example.umniygorodhach.data.remote.api.news.models.NewsItemResponse
import com.example.umniygorodhach.data.remote.api.news.models.OnlineMatch
import com.example.umniygorodhach.data.repository.NewsRepository
import com.example.umniygorodhach.data.remote.api.news.models.TransItemResponse
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

            "${newsItem.title} ${newsItem.text} ${newsItem.author}".contains(query.trim(), ignoreCase = true)
        } as MutableList<NewsItemResponse>
    }

    fun onResourceSuccess(newsItems: List<NewsItemResponse>) {

        cachedNewsItems = newsItems as MutableList<NewsItemResponse>

        _newsItemsFlow.value = cachedNewsItems
    }

    fun onRefresh() {
        fetchNews()
        fetchTrans()
    }

    private fun fetchNews() = _newsItemResponsesFlow.emitInIO(viewModelScope) {
        newsRepository.fetchNews()
    }

    val pagerState = PagerState()


    private val _transItemResponsesFlow = MutableStateFlow<Resource<MutableList<TransItemResponse>>>(Resource.Loading)
    val transItemsResponsesFlow = _transItemResponsesFlow.asStateFlow().also {fetchTrans() }


    var cachedTransItems = mutableListOf<TransItemResponse>()

    private val _transItemsFlow = MutableStateFlow(cachedTransItems)
    val transItemsFlow = _transItemsFlow.asStateFlow()

    private fun fetchTrans() = _transItemResponsesFlow.emitInIO(viewModelScope) {
        newsRepository.fetchTrans()
    }

    fun onTransResourceSuccess(transItems: MutableList<TransItemResponse>) {

        cachedTransItems = transItems as MutableList<TransItemResponse>

        _transItemsFlow.value = cachedTransItems
    }


    private val _OnlineMatchResponsesFlow = MutableStateFlow<Resource<OnlineMatch>>(Resource.Loading)
    val OnlineMatchResponsesFlow = _OnlineMatchResponsesFlow.asStateFlow().also {fetchOnlineMatch() }


    var cachedOnlineMatch:OnlineMatch? = null

    private val _OnlineMatchFlow = MutableStateFlow(cachedOnlineMatch)
    val OnlineMatchFlow = _OnlineMatchFlow.asStateFlow()

    private fun fetchOnlineMatch() = _OnlineMatchResponsesFlow.emitInIO(viewModelScope) {
        newsRepository.fetchOnlineMatch()
    }

    fun onOnlineMatchResourceSuccess(onlineMatch: OnlineMatch) {

        cachedOnlineMatch = onlineMatch

        _OnlineMatchFlow.value = cachedOnlineMatch
    }
}