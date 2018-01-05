package ru.santaev_oydopova.calculator.view

import android.content.Context
import android.databinding.DataBindingUtil
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import ru.santaev_oydopova.calculator.R
import ru.santaev_oydopova.calculator.databinding.CalcButtonViewLayoutBinding

class CalcButton : FrameLayout {

    private lateinit var binding: CalcButtonViewLayoutBinding

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(
            context: Context?,
            attrs: AttributeSet?,
            defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        init()
    }

    constructor(
            context: Context?,
            attrs: AttributeSet?,
            defStyleAttr: Int,
            defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes) {
        init()
    }

    fun setText(text: String) {
        binding.text.text = text
    }

    private fun init() {
        binding = DataBindingUtil.inflate(
                LayoutInflater.from(context),
                R.layout.calc_button_view_layout,
                this,
                true
        )
    }
}