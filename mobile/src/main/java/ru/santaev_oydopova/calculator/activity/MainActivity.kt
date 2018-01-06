package ru.santaev_oydopova.calculator.activity

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.santaev.core.usecase.CalculateUseCase
import com.santaev.core.usecase.ICalculateUseCase
import com.santaev.core.usecase.dto.OperationDto
import com.santaev.core.usecase.dto.ResultDto
import com.santaev.core.usecase.dto.ResultTypeDto
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import ru.santaev_oydopova.calculator.R
import ru.santaev_oydopova.calculator.databinding.ActivityMainBinding
import ru.santaev_oydopova.calculator.view.CalcButton

class MainActivity : AppCompatActivity(), ICalculateUseCase.Output {

    override fun onError(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private val usecase: ICalculateUseCase.Input = CalculateUseCase(this)
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        initNumberButton(binding.btnZero, 0)
        initNumberButton(binding.btnOne, 1)
        initNumberButton(binding.btnTwo, 2)
        initNumberButton(binding.btnThree, 3)
        initNumberButton(binding.btnFour, 4)
        initNumberButton(binding.btnFive, 5)
        initNumberButton(binding.btnSix, 6)
        initNumberButton(binding.btnSeven, 7)
        initNumberButton(binding.btnEight, 8)
        initNumberButton(binding.btnNine, 9)

        binding.btnPlus.apply {
            setText("+")
            setOnClickListener({ usecase.addOperation(OperationDto.PLUS) })
        }

        binding.btnMinus.apply {
            setText("-")
            setOnClickListener({ usecase.addOperation(OperationDto.MINUS) })
        }

        binding.btnMul.apply {
            setText("**")
            setOnClickListener({ usecase.addOperation(OperationDto.MULTIPLE) })
        }

        binding.btnDiv.apply {
            setText("/")
            setOnClickListener({ usecase.addOperation(OperationDto.DIVISION) })
        }

        binding.btnClear.apply {
            setText("C")
            setOnClickListener({ usecase.addOperation(OperationDto.CLEAR) })
        }

        binding.btnClearOne.apply {
            setText("C")
            setOnClickListener({ usecase.addOperation(OperationDto.CLEAR_LAST) })
        }

        Observable.combineLatest(
                usecase.getResult(),
                usecase.getExpression(),
                BiFunction<ResultDto, String, Pair<ResultDto, String>> { t1, t2 -> Pair(t1, t2) }
        ).subscribe({ result ->
            if (result.first.type == ResultTypeDto.ERROR) {
                binding.result.setTextColor(resources.getColor(R.color.calc_button_text_err_color))
            } else {
                binding.result.setTextColor(resources.getColor(R.color.calc_button_text_def_color))
            }
            binding.result.text = "${result.second}\n${result.first.value}"
        })
    }

    private fun initNumberButton(view: CalcButton, num: Int) {
        view.apply {
            setText(num.toString())
            setOnClickListener({ usecase.addNumber(num) })
        }
    }
}
