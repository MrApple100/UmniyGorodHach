package com.example.umniygorodhach.data.repository

import com.example.traininghakatonsever.common.ResponseHandler
import com.example.umniygorodhach.data.remote.api.Notafications.NotApi
import com.example.umniygorodhach.data.remote.api.Notafications.models.Notaf
import com.example.umniygorodhach.data.remote.api.home.HomeApi
import com.example.umniygorodhach.data.remote.api.home.models.RaspItem
import javax.inject.Inject

class NotafRepository constructor(
    private val notafApi: NotApi,
) {
    suspend fun fetcNotes() =
        notafApi.getNotes() as MutableList<Notaf>

}