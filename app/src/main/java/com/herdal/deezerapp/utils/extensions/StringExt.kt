package com.herdal.deezerapp.utils.extensions

fun String.toSeconds(): Int? {
    val parts = this.split(":")
    if (parts.size != 2) {
        return null
    }
    val minutes = parts[0].toIntOrNull() ?: return null
    val seconds = parts[1].toIntOrNull() ?: return null
    return minutes * 60 + seconds
}