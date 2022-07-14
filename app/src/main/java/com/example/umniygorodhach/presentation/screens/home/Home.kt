package com.example.umniygorodhach.presentation.screens.home

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import com.google.accompanist.pager.ExperimentalPagerApi
import com.example.umniygorodhach.presentation.utils.singletonViewModel

@ExperimentalMaterialApi
@ExperimentalPagerApi
@Composable
fun Home(
    homeViewModel: HomeViewModel = singletonViewModel()
) {
    Text(text = "Home")

}