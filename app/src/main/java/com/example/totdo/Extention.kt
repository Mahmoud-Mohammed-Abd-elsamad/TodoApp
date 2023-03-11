package com.example.totdo

import java.util.*

fun Calendar.clearTime():Calendar{
    this.clear(Calendar.HOUR)
    this.clear(Calendar.MINUTE)
    this.clear(Calendar.MILLISECOND)
    this.clear(Calendar.SECOND)

    return this
}