package com.santaev.core.entity.member

internal enum class Operator(val priority: Int) : IMember {
    PLUS(1) {
        override fun perform(first: Number, second: Number): Number {
            return Number(first.value + second.value)
        }
    },
    MINUS(1) {
        override fun perform(first: Number, second: Number): Number {
            return Number(first.value - second.value)
        }
    },
    MULTIPLE(0) {
        override fun perform(first: Number, second: Number): Number {
            return first.mul(second)
        }
    },
    DIVISION(0) {
        override fun perform(first: Number, second: Number): Number {
            return first.div(second)
        }
    };

    abstract fun perform(first: Number, second: Number): Number
}
