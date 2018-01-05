package com.santaev.core.util

object LoggerProxy : ILogger {

    private var logger: ILogger = StdLogger()

    override fun log(tag: String, msg: String) {
        logger.log(tag, msg)
    }

    fun getLogger(): ILogger {
        return logger
    }

    fun setLogger(logger: ILogger) {
        this.logger = logger
    }

    class StdLogger : ILogger {

        override fun log(tag: String, msg: String) {
            println("$tag: $msg")
        }
    }
}