package ru.santaev_oydopova.calculator.util

import android.databinding.BindingAdapter
import ru.santaev_oydopova.calculator.view.CalcButton

@BindingAdapter("app:button_text")
fun setCalcButtonText(view: CalcButton, text: String) {
    view.setText(text)
}

@BindingAdapter("button_background")
fun setCalcButtonBackground(view: CalcButton, color: Int) {
    view.setBackgroundColor(color)
}

@BindingAdapter("button_text_color")
fun setCalcButtonTextColor(view: CalcButton, color: Int) {
    view.setTextColor(color)
}
