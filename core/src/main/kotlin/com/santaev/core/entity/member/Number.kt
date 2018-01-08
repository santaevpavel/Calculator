package com.santaev.core.entity.member

internal data class Number(val value: Double) : IMember {

    fun mul(other: Number): Number {
        return Number(value * other.value)
    }

    fun div(other: Number): Number {
        return Number(value / other.value)
    }

    fun add(other: Number): Number {
        return Number(value + other.value)
    }

}