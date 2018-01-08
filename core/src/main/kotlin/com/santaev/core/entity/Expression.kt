package com.santaev.core.entity

import com.santaev.core.entity.member.Bracket
import com.santaev.core.entity.member.IMember
import com.santaev.core.entity.member.Number
import com.santaev.core.entity.member.Operator
import com.santaev.core.util.LoggerProxy
import com.santaev.core.util.toNumber
import java.util.*

internal class Expression {

    private val members: MutableList<IMember> = ArrayList()
    private val infixNotationConverter = InfixNotationConverter()

    fun addMember(member: IMember) {
        members.add(member)
    }

    fun getMembers(): List<IMember> {
        return members
    }

    fun replaceLast(member: IMember) {
        if (!members.isEmpty()) {
            members.removeAt(members.size - 1)
            members.add(member)
        }
    }

    fun removeLast() {
        if (!members.isEmpty()) members.removeAt(members.size - 1)
    }

    fun calculate(): Number {
        LoggerProxy.log(TAG, "calculate")
        return if (members.isEmpty()) {
            0.toNumber()
        } else {
            val converted = infixNotationConverter.convert(members)
            LoggerProxy.log(TAG, "infix notation = " + converted)
            return evaluate(ArrayDeque(converted.reversed()))
        }
    }

    private fun evaluate(members: Deque<IMember>): Number {
        if (members.isEmpty()) {
            throw IllegalStateException("Empty expression")
        }
        if (members.size == 1) {
            return members.peek() as? Number
                    ?: throw IllegalStateException("One element array should contains number")
        }
        val first = members.pop()

        return when (first) {
            is Number -> {
                first
            }
            is Operator -> {
                val secondNumber = evaluate(members)
                val firstNumber = evaluate(members)
                first.perform(firstNumber, secondNumber)
            }
            is Bracket -> {
                throw RuntimeException("Not yet implemented")
            }
            else -> throw RuntimeException("Not yet implemented")
        }.also {
            LoggerProxy.log(TAG, "evaluate" + members.toString() + " = " + it)
        }
    }

    companion object {

        private val TAG = "Core: Expression"
    }
}