package com.example.umniygorodhach.data.converters

import com.example.umniygorodhach.data.close.dao.player.PlayerEntity
import com.google.gson.reflect.TypeToken


class PlayerListConverter :
    ListConverter<PlayerEntity?>(object : TypeToken<List<PlayerEntity?>?>() {}.type)