package com.example.umniygorodhach.presentation.screens.myevents

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.umniygorodhach.data.repository.EventsRepository
import com.example.umniygorodhach.data.repository.MyEventsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MyEventsViewModel @Inject constructor(
    private val myEventsRepository: MyEventsRepository,
) : ViewModel() {

}