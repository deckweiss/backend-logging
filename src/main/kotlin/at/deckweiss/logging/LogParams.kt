package at.deckweiss.logging

class LogParams() {
    private val logParams: MutableList<LogParam> = mutableListOf()

    constructor(name: String, value: Any?) : this() {
        logParams.add(LogParam(name, value))
    }

    fun add(name: String, value: Any?): LogParams {
        logParams.add(LogParam(name, value))
        return this
    }

    fun addIfNotNull(name: String, value: Any?): LogParams {
        value?.let {
            logParams.add(LogParam(name, it))
        }
        return this
    }

    fun toParamString(): String {
        return when {
            logParams.isEmpty() -> ""
            else -> logParams.joinToString(prefix = "[", postfix = "]", separator = ", ") { it.toString() }
        }
    }

}