package com.santaev.core.usecase

import com.santaev.core.usecase.dto.OperationDto
import com.santaev.core.usecase.dto.ResultDto
import io.reactivex.Observable

interface ICalculateUseCase {

    interface Input {

        fun getExpression(): Observable<String>

        fun getResult(): Observable<ResultDto>

        fun addNumber(number: Int)

        fun addOperation(operation: OperationDto)
    }

    interface Output {

        fun onError(msg: String)
    }

}