package ru.santaev_oydopova.calculator.activity

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import ru.santaev_oydopova.calculator.R
import ru.santaev_oydopova.calculator.databinding.ActivityMainBinding
import ru.santaev_oydopova.calculator.view.CalcButton
import ru.santaev_oydopova.calculator.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.apply {
            initNumberButtons(
                    arrayOf(
                            btnZero,
                            btnOne,
                            btnTwo,
                            btnThree,
                            btnFour,
                            btnFive,
                            btnSix,
                            btnSeven,
                            btnEight,
                            btnNine
                    ))
            initOperationButtons(btnPlus, "+", MainViewModel.Operation.PLUS)
            initOperationButtons(btnMinus, "-", MainViewModel.Operation.MINUS)
            initOperationButtons(btnMul, "×", MainViewModel.Operation.MULTIPLY)
            initOperationButtons(btnDiv, "÷", MainViewModel.Operation.DIVISION)
            initOperationButtons(btnClear, "C", MainViewModel.Operation.CLEAR)
            initOperationButtons(btnClearOne, "<<", MainViewModel.Operation.CLEAR_LAST)
            initOperationButtons(btnEqual, "=", MainViewModel.Operation.EQUAL)
        }

        observeViewModel()
    }

    private fun observeViewModel() {
        viewModel.getResult()
                .observe(this, Observer { res -> binding.result.text = "=${res ?: "0"}" })
        viewModel.getExpression()
                .observe(this, Observer { exp -> binding.expression.text = exp ?: "" })
    }

    private fun initNumberButtons(view: Array<CalcButton>) {
        view.forEachIndexed { index, calcButton ->
            calcButton.apply {
                setText(index.toString())
                setOnClickListener({ viewModel.onTypedNumber(index) })
            }
        }
    }

    private fun initOperationButtons(
            view: CalcButton,
            text: String,
            operation: MainViewModel.Operation
    ) {
        view.apply {
            setText(text)
            setOnClickListener { viewModel.onTypedOperation(operation) }
        }
    }
}
