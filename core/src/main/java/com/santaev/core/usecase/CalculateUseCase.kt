package com.santaev.core.usecase

import com.santaev.core.entity.Expression
import com.santaev.core.entity.member.Number
import com.santaev.core.usecase.dto.OperationDto
import com.santaev.core.usecase.dto.ResultDto
import com.santaev.core.usecase.dto.ResultTypeDto
import com.santaev.core.util.LoggerProxy
import com.santaev.core.util.toNumber
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

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
        typingNumber = typingNumber?.let {
            it.mul(10.toNumber()).add(number.toNumber())
                    .also { expression.replaceLast(it) }
        } ?: number.toNumber().also { expression.addMember(it) }
    }

    override fun addOperation(operation: OperationDto) {
        when (operation) {
            OperationDto.PLUS -> {
            }
            OperationDto.MINUS -> {
            }
            OperationDto.MULTIPLE -> {
            }
            OperationDto.DIVISION -> {
            }
            OperationDto.EQUALS -> {
            }
            else -> {
                LoggerProxy.log(TAG, "Unknown operation: " + operation)
                output.onError("Unsupported operation")
            }
        }
    }

    companion object {

        private val TAG: String = "CalculateUseCase"
    }

}
