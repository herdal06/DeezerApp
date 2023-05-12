package com.herdal.deezerapp.utils.extensions

import java.text.SimpleDateFormat
import java.util.*

fun String.toSeconds(): Int? {
    val parts = this.split(":")
    if (parts.size != 2) {
        return null
    }
    val minutes = parts[0].toIntOrNull() ?: return null
    val seconds = parts[1].toIntOrNull() ?: return null
    return minutes * 60 + seconds
}

fun String.toFormattedDate(): String {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
    val outputFormat = SimpleDateFormat("d MMM yyyy", Locale.getDefault())
    inputFormat.timeZone = TimeZone.getTimeZone("UTC") // set the time zone
    val date = inputFormat.parse(this)
    outputFormat.timeZone = TimeZone.getDefault()
    return outputFormat.format(date)
}