package com.example.umniygorodhach.presentation.screens.player.components


import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import com.example.umniygorodhach.presentation.ui.components.top_app_bars.BasicTopAppBar

@ExperimentalMaterialApi
@Composable
fun CreatePlayerTopAppBar(
    onBackAction: () -> Unit
) {
    val scope = rememberCoroutineScope()
    BasicTopAppBar(
        text = "Создание участника",
        options = listOf(
        ),
        onBackAction = onBackAction
    )
}