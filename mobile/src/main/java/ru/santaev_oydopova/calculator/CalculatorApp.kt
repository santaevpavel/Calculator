package ru.santaev_oydopova.calculator

import android.app.Application
import android.util.Log
import com.santaev.core.util.ILogger
import com.santaev.core.util.LoggerProxy

class CalculatorApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initLogger()
    }

    private fun initLogger() {
        LoggerProxy.setLogger(object : ILogger {

            override fun log(tag: String, msg: String) {
                Log.d(tag, msg)
            }
        })
    }
}