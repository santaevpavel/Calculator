package ru.santaev_oydopova.calculator

import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Editable
import android.text.TextWatcher
import android.widget.TextView
import com.santaev.core.usecase.CalculateUseCase
import com.santaev.core.usecase.ICalculateUseCase
import ru.santaev_oydopova.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), ICalculateUseCase.Output {

    private val usecase: ICalculateUseCase.Input = CalculateUseCase(this)
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        val textView = findViewById<TextView>(R.id.text)
        val editText = findViewById<TextView>(R.id.edit_text)

        usecase
                .getExpression()
                .subscribe({ expr -> textView.text = expr })

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(p0: Editable?) {
                usecase.addNumber(p0!!.toString())
            }

        })
    }
}
