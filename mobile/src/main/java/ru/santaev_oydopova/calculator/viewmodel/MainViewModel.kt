package ru.santaev_oydopova.calculator.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.util.Log
import com.santaev.core.usecase.CalculateUseCase
import com.santaev.core.usecase.ICalculateUseCase
import com.santaev.core.usecase.dto.OperationDto
import io.reactivex.disposables.CompositeDisposable


internal class MainViewModel : ViewModel(), ICalculateUseCase.Output {

    private var useCase: ICalculateUseCase.Input = CalculateUseCase(this) // TODO use Dagger to infect dependencies
    private var result: MutableLiveData<String> = MutableLiveData()
    private var expression: MutableLiveData<String> = MutableLiveData()

    private val disposable = CompositeDisposable()

    init {
        result.value = "0"
        expression.value = ""

        disposable.add(useCase.getResult()
                .subscribe({ res ->
                    result.value = res.value
                }, { err ->
                    Log.e(TAG, "Error while get result", err)
                }))

        disposable.add(useCase.getExpression()
                .subscribe({ res ->
                    expression.value = res
                }, { err ->
                    Log.e(TAG, "Error while get expression", err)
                }))
    }

    override fun onError(msg: String) {
        Log.e(TAG, "OnError: " + msg)
    }

    fun getResult(): LiveData<String> {
        return result
    }

    fun getExpression(): LiveData<String> {
        return expression
    }

    fun onTypedNumber(number: Int) {
        if (number !in 0..9) {
            throw IllegalArgumentException("Number should be in 0..9")
        }
        useCase.addNumber(number)
    }

    fun onTypedOperation(operation: Operation) {
        when (operation) {
            Operation.PLUS -> useCase.addOperation(OperationDto.PLUS)
            Operation.MINUS -> useCase.addOperation(OperationDto.MINUS)
            Operation.MULTIPLY -> useCase.addOperation(OperationDto.MULTIPLE)
            Operation.DIVISION -> useCase.addOperation(OperationDto.DIVISION)
            Operation.CLEAR -> useCase.addOperation(OperationDto.CLEAR)
            Operation.CLEAR_LAST -> useCase.addOperation(OperationDto.CLEAR_LAST)
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }

    internal enum class Operation {
        PLUS,
        MINUS,
        MULTIPLY,
        DIVISION,
        CLEAR,
        CLEAR_LAST,
    }

    companion object {

        private val TAG = "MainViewModel"
    }
}