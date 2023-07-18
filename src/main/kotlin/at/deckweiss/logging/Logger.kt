package at.deckweiss.logging

interface Logger {
    fun debug(message: String, throwable: Throwable? = null, params: LogParams? = null)
    fun info(message: String, throwable: Throwable? = null, params: LogParams? = null)
    fun warn(message: String, throwable: Throwable? = null, params: LogParams? = null)
    fun error(message: String, throwable: Throwable? = null, params: LogParams? = null)
}