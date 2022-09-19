package com.example.umniygorodhach.data.repository

import com.example.traininghakatonsever.common.ResponseHandler
import com.example.umniygorodhach.data.remote.api.events.models.EventDetailDto
import com.example.umniygorodhach.data.remote.api.home.HomeApi
import com.example.umniygorodhach.data.remote.api.home.models.RaspItem
import javax.inject.Inject


class HomeRepository @Inject constructor(
    private val homeApi: HomeApi,
    private val handler: ResponseHandler
) {
    //Mock Data
    val timetable = RaspItem(
        "0","0","Пение с дирижёром","1644474444344","Позвали постоять в масовке"
    )
    val timetable2 = RaspItem(
        "1","0","Помогаю красить стены","1644484444444","Заставили, даже не платят"
    )
    val timetable3 = RaspItem(
        "2","0","Сдать кеды в стирку","1644494444444","Какой то плохой чел наступил на ногу"
    )
    val timetable4 = RaspItem(
        "3","0","Помыть кухню","1644544444444","Мама сказала чтобы мистер пропер не помогал"
    )
    val timetable5 = RaspItem(
        "4","0","Отправить вечером дз преподу","16444574444444","На почту @yandex.ru"
    )
    val listtime = arrayListOf<RaspItem>(timetable,timetable2,timetable3,timetable4,timetable5)

    suspend fun fetchTimeTable() = handler{
        listtime
        //homeApi.getTimeTabel(1) as MutableList<RaspItem>
    }
}