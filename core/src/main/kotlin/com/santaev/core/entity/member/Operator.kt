package com.santaev.core.entity.member

enum class Operator : IMember {
    PLUS {
        override fun perform(first: Number, second: Number): Number {
            return Number(first.value + second.value)
        }
    },
    MINUS {
        override fun perform(first: Number, second: Number): Number {
            return Number(first.value - second.value)
        }
    },
    MULTIPLE {
        override fun perform(first: Number, second: Number): Number {
            return first.mul(second)
        }
    },
    DIVISION {
        override fun perform(first: Number, second: Number): Number {
            return first.div(second)
        }
    };

    abstract fun perform(first: Number, second: Number): Number
}
