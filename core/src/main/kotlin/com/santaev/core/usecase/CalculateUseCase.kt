package com.santaev.core.usecase

import com.santaev.core.entity.Expression
import com.santaev.core.entity.member.Number
import com.santaev.core.entity.member.Operator
import com.santaev.core.usecase.dto.OperationDto
import com.santaev.core.usecase.dto.ResultDto
import com.santaev.core.usecase.dto.ResultTypeDto
import com.santaev.core.util.LoggerProxy
import com.santaev.core.util.toNumber
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import java.util.*

class CalculateUseCase(
        private val output: ICalculateUseCase.Output
) : ICalculateUseCase.Input {

    private val expressionSubject: BehaviorSubject<String> =
            BehaviorSubject.createDefault("")
    private val resultSubject: BehaviorSubject<ResultDto> =
            BehaviorSubject.createDefault(ResultDto(ResultTypeDto.EMPTY, ""))

    private var expression = Expression()
    private var typingNumber: Number? = null

    override fun getExpression(): Observable<String> {
        return expressionSubject
    }

    override fun getResult(): Observable<ResultDto> {
        return resultSubject
    }

    override fun addNumber(number: Int) {
        if (number !in 0..9) {
            throw IllegalArgumentException("Number should be in 0..9")
        }
        typingNumber = typingNumber?.let {
            it.mul(10.toNumber()).add(number.toNumber())
                    .also { expression.replaceLast(it) }
        } ?: number.toNumber().also { expression.addMember(it) }

        updateResult()
        updateExpression()
    }

    override fun addOperation(operation: OperationDto) {
        try {
            when (operation) {
                OperationDto.PLUS -> {
                    typingNumber = null
                    addOrReplaceLastOperator(Operator.PLUS)
                }
                OperationDto.MINUS -> {
                    typingNumber = null
                    addOrReplaceLastOperator(Operator.MINUS)
                }
                OperationDto.MULTIPLE -> {
                    typingNumber = null
                    addOrReplaceLastOperator(Operator.MULTIPLE)
                }
                OperationDto.DIVISION -> {
                    typingNumber = null
                    addOrReplaceLastOperator(Operator.DIVISION)
                }
                OperationDto.CLEAR -> {
                    expression = Expression()
                    typingNumber = null
                }
                OperationDto.CLEAR_LAST -> {
                    expression.removeLast()
                    typingNumber = null
                }
                else -> {
                    LoggerProxy.log(TAG, "Unknown operation: " + operation)
                    output.onError("Unsupported operation")
                }
            }
            updateResult()

        } catch (e: Exception) {
            resultSubject.onNext(ResultDto(ResultTypeDto.ERROR, "Error"))
        }
        updateExpression()

    }

    private fun addOrReplaceLastOperator(operator: Operator) {
        expression.getMembers().lastOrNull()?.let {
            if (it is Operator) expression.replaceLast(operator) else expression.addMember(operator)
        }
    }

    private fun updateExpression() {
        val res = StringBuilder()
        expression.getMembers().forEach({ m ->
            if (m is Number) {
                res.append(String.format(Locale.ENGLISH, "%.0f ", m.value))
            }
            if (m is Operator) {
                res.append(m.toString() + " ")
            }
        })
        expressionSubject.onNext(res.toString())
    }

    private fun updateResult() {
        try {
            val result = expression.calculate()
            resultSubject.onNext(ResultDto(ResultTypeDto.SUCCESS, result.value.toString()
            ))
        } catch (e: Exception) {
            resultSubject.onNext(ResultDto(ResultTypeDto.ERROR, "Error"))
        }
    }

    companion object {

        private val TAG: String = "CalculateUseCase"
    }

}
