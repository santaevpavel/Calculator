package com.santaev.core.util

import com.santaev.core.entity.member.Number

internal fun Int.toNumber(): Number {
    return Number(this.toDouble())
}

internal fun Double.toNumber(): Number {
    return Number(this)
}



