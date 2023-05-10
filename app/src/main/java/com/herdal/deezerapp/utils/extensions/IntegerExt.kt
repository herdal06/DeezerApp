package com.herdal.deezerapp.utils.extensions

fun Int.toTimeString(): String {
    val minutes = this / 60
    val seconds = this % 60
    return String.format("%d:%02d", minutes, seconds)
}