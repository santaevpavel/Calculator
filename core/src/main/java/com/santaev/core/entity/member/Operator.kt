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
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    },
    DIVISION {
        override fun perform(first: Number, second: Number): Number {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    };

    abstract fun perform(first: Number, second: Number): Number
}
