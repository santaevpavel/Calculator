package com.santaev.core.util

import com.santaev.core.entity.member.Number

fun Int.toNumber(): Number {
    return Number(this.toDouble())
}

fun Double.toNumber(): Number {
    return Number(this)
}



