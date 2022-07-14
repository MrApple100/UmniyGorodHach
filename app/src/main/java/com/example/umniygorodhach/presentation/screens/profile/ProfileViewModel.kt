package com.example.umniygorodhach.presentation.screens.profile

import androidx.lifecycle.ViewModel
import com.example.umniygorodhach.data.repository.HomeRepository
import javax.inject.Inject

class ProfileViewModel @Inject constructor(
    private val repository: HomeRepository,
    //private val authStateStorage: AuthStateStorage
) : ViewModel() {

}