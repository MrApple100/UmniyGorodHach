package com.example.umniygorodhach.presentation.screens.news

import androidx.compose.material.ExperimentalMaterialApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.traininghakatonsever.common.emitInIO
import com.example.umniygorodhach.common.Resource
import com.example.umniygorodhach.data.remote.api.news.models.MySborkaItem
import com.example.umniygorodhach.data.remote.api.news.models.NewsItemResponse
import com.example.umniygorodhach.data.remote.api.news.models.RatingRegionItem
import com.example.umniygorodhach.data.repository.NewsRepository
import com.example.umniygorodhach.data.repository.ResultsRepository
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@ExperimentalMaterialApi
@ExperimentalPagerApi
@HiltViewModel
class ResultsViewModel @Inject constructor(
    private val newsRepository: ResultsRepository,
) : ViewModel() {


    private val _regionItemResponsesFlow = MutableStateFlow<Resource<MutableList<RatingRegionItem>>>(
        Resource.Loading)
    val regionItemsResponsesFlow = _regionItemResponsesFlow.asStateFlow().also {fetchRegionItems() }


    var cachedregionItems = mutableListOf<RatingRegionItem>()

    private val _regionItemsFlow = MutableStateFlow(cachedregionItems)
    val regionItemsFlow = _regionItemsFlow.asStateFlow()



    private val _sborkaItemResponsesFlow = MutableStateFlow<Resource<MutableList<MySborkaItem>>>(
        Resource.Loading)
    val sborkaItemsResponsesFlow = _sborkaItemResponsesFlow.asStateFlow().also {fetchSborkaItems() }


    var cachedsborkaItems = mutableListOf<MySborkaItem>()

    private val _sborkaItemsFlow = MutableStateFlow(cachedsborkaItems)
    val sborkaItemsFlow = _sborkaItemsFlow.asStateFlow()


    private fun fetchRegionItems() = _regionItemResponsesFlow.emitInIO(viewModelScope) {
        newsRepository.fetchRatingRegion()
    }
    private fun fetchSborkaItems() = _sborkaItemResponsesFlow.emitInIO(viewModelScope) {
        newsRepository.fetchSborka()
    }

    fun onRegionResourceSuccess(Items: List<RatingRegionItem>) {

        cachedregionItems = Items as MutableList<RatingRegionItem>

        _regionItemsFlow.value = cachedregionItems
    }
    fun onSborkaResourceSuccess(Items: List<MySborkaItem>) {

        cachedsborkaItems = Items as MutableList<MySborkaItem>

        _sborkaItemsFlow.value = cachedsborkaItems
    }

    fun onRefresh() {
        fetchRegionItems()
        fetchSborkaItems()
    }
}