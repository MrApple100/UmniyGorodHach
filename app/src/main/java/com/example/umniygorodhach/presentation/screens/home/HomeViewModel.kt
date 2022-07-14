package com.example.umniygorodhach.presentation.screens.home

import androidx.compose.material.ExperimentalMaterialApi
import androidx.lifecycle.ViewModel
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.lifecycle.HiltViewModel
import com.example.umniygorodhach.data.repository.EventsRepository
import com.example.umniygorodhach.data.repository.HomeRepository
import javax.inject.Inject

@ExperimentalMaterialApi
@ExperimentalPagerApi
@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: HomeRepository,
    //private val authStateStorage: AuthStateStorage
) : ViewModel() {

}