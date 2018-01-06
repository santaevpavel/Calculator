package ru.santaev_oydopova.calculator.util

import android.databinding.BindingAdapter
import ru.santaev_oydopova.calculator.view.CalcButton

@BindingAdapter("app:button_text")
fun setCalcButtonText(view: CalcButton, text: String) {
    view.setText(text)
}
