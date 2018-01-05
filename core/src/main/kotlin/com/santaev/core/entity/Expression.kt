package com.santaev.core.entity

import com.santaev.core.entity.member.Bracket
import com.santaev.core.entity.member.IMember
import com.santaev.core.entity.member.Number
import com.santaev.core.entity.member.Operator
import java.util.*

class Expression {

    private val members: MutableList<IMember> = ArrayList()

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
        return evaluate(members)
    }

    private fun evaluate(members: List<IMember>): Number {
        if (members.isEmpty()) {
            return Number(0.0)
        }
        if (members.size == 1) {
            return members[0] as? Number
                    ?: throw IllegalStateException("One element array should contains number")
        }
        val first = members[0]
        return when (first::class) {
            Number::class -> {
                val number = first as Number
                val operator = members[1] as? Operator
                        ?: throw IllegalStateException("Member after number should be operator")
                operator.perform(number, evaluate(members.subList(2, members.size)))
            }
            Operator::class -> {
                val operator = first as Operator
                if (operator == Operator.PLUS || operator == Operator.MINUS) {
                    val number = evaluate(members.subList(1, members.size))
                    operator.perform(Number(0.0), number)
                } else {
                    throw IllegalStateException("Incorrect prefix operator: " + operator)
                }
            }
            Bracket::class -> {
                throw RuntimeException("Not yet implemented")
            }
            else -> throw RuntimeException("Not yet implemented")
        }
    }
}