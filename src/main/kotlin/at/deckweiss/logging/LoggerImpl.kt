package at.deckweiss.logging

import org.slf4j.spi.LoggingEventBuilder

internal class LoggerImpl(private val slf4jLogger: org.slf4j.Logger) : Logger {
  override fun trace(message: String, throwable: Throwable?, params: Map<String, Any?>?) {
    slf4jLogger.atTrace().logStructuredMessage(message, throwable, params)
  }

  override fun debug(message: String, throwable: Throwable?, params: Map<String, Any?>?) {
    slf4jLogger.atDebug().logStructuredMessage(message, throwable, params)
  }

  override fun info(message: String, throwable: Throwable?, params: Map<String, Any?>?) {
    slf4jLogger.atInfo().logStructuredMessage(message, throwable, params)
  }

  override fun warn(message: String, throwable: Throwable?, params: Map<String, Any?>?) {
    slf4jLogger.atWarn().logStructuredMessage(message, throwable, params)
  }

  override fun error(message: String, throwable: Throwable?, params: Map<String, Any?>?) {
    slf4jLogger.atError().logStructuredMessage(message, throwable, params)
  }

  private fun LoggingEventBuilder.logStructuredMessage(message: String, throwable: Throwable?, params: Map<String, Any?>?) {
    val paramsString = if (params.isNullOrEmpty()) "" else params.map { "${it.key}=\"${it.value}\"" }.joinToString(prefix = " [", postfix = "]")
    setMessage(message + paramsString)
    setCause(throwable)
    log()
  }
}