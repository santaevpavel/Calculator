package com.santaev.core.entity

import com.santaev.core.entity.member.Bracket
import com.santaev.core.entity.member.IMember
import com.santaev.core.entity.member.Number
import com.santaev.core.entity.member.Operator
import java.util.*

internal class InfixNotationConverter {

    fun convert(members: List<IMember>): List<IMember> {
        val queue = ArrayList<IMember>(members.size)
        val inputQueue: Queue<IMember> = ArrayDeque<IMember>(members.size).apply {
            addAll(members)
        }
        val operatorStack = ArrayDeque<IMember>()

        while (inputQueue.isNotEmpty()) {
            val m = inputQueue.poll()
            when (m) {
                is Number -> {
                    queue.add(m)
                }
                is Operator -> {
                    while (true) {
                        val op: IMember? = if (operatorStack.isNotEmpty()) operatorStack.peek() else break
                        if (((op is Operator && op.priority < m.priority)
                                || (op is Operator && op.priority == m.priority) /* TODO add left associative condition*/
                                && ((op != Bracket.LEFT)))) {
                            queue.add(operatorStack.pop())
                        } else {
                            break
                        }
                    }
                    operatorStack.push(m)
                }
                is Bracket -> throw RuntimeException("Not yet implemented")
                else -> throw RuntimeException("Not yet implemented")
            }
        }
        while (operatorStack.isNotEmpty()) {
            queue.add(operatorStack.pop())
        }
        return queue
    }
}