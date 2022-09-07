package com.example.umniygorodhach.presentation.screens.home

import androidx.compose.material.ExperimentalMaterialApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.traininghakatonsever.common.emitInIO
import com.example.umniygorodhach.common.Resource
import com.example.umniygorodhach.data.remote.api.home.models.RaspItem
import com.example.umniygorodhach.data.remote.api.news.models.NewsItemResponse
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.umniygorodhach.data.repository.EventsRepository
import com.example.umniygorodhach.data.repository.HomeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@ExperimentalMaterialApi
@ExperimentalPagerApi
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
    //private val authStateStorage: AuthStateStorage
) : ViewModel() {

    private val _raspResponsesFlow = MutableStateFlow<Resource<MutableList<RaspItem>>>(Resource.Loading)
    val raspResponsesFlow = _raspResponsesFlow.asStateFlow().also {fetchTimeTable() }

    var cachedrasp = mutableListOf<RaspItem>()

    private var _raspFlow = MutableStateFlow(cachedrasp)
    val raspFlow = _raspFlow.asStateFlow()

    fun addRasp(rasp:RaspItem){
        cachedrasp.add( rasp)
        _raspFlow.value = cachedrasp
    }
    private fun fetchTimeTable() = _raspResponsesFlow.emitInIO(viewModelScope) {
        homeRepository.fetchTimeTable()
    }


    fun onResourceSuccess(newsItems: List<RaspItem>) {

        cachedrasp = newsItems as MutableList<RaspItem>

        _raspFlow.value = cachedrasp
    }

    fun onRefresh() {
        fetchTimeTable()
    }
}