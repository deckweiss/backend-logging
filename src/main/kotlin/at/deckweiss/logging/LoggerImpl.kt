package at.deckweiss.logging

internal class LoggerImpl(private val slf4jLogger: org.slf4j.Logger) : Logger {
    override fun debug(message: String, throwable: Throwable?, params: LogParams?) {
        log(slf4jLogger::debug, message, throwable, params)
    }

    override fun info(message: String, throwable: Throwable?, params: LogParams?) {
        log(slf4jLogger::info, message, throwable, params)
    }

    override fun warn(message: String, throwable: Throwable?, params: LogParams?) {
        log(slf4jLogger::warn, message, throwable, params)
    }

    override fun error(message: String, throwable: Throwable?, params: LogParams?) {
        log(slf4jLogger::error, message, throwable, params)
    }


    private fun log(logFunc: (message: String, throwable: Throwable?) -> Unit, message: String, throwable: Throwable?, params: LogParams?) {
        val msg = buildMessage(message, params)
        logFunc(msg, throwable)
    }

    private fun buildMessage(message: String, params: LogParams?): String {
        return when (params) {
            null -> message
            else -> "$message ${params.toParamString()}"
        }
    }

}