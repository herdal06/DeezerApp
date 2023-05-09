package com.herdal.deezerapp.utils.extensions

import android.view.View

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.INVISIBLE
}

fun View.gone() {
    visibility = View.GONE
}

fun View.isGone(): Boolean {
    return this.visibility == View.GONE
}