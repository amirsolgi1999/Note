package com.example.note.feature.domain.util

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date

@SuppressLint("SimpleDateFormat")
fun convertLongToTime(time: Long): String {
    val date = Date(time)
    val format = SimpleDateFormat("dd.MM.yyyy | HH:mm")  // yyyy.MM.dd HH:mm
    return format.format(date)
}

