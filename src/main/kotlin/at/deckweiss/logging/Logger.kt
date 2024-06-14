package at.deckweiss.logging

interface Logger {
  fun trace(message: String, throwable: Throwable? = null, params: Map<String, Any?>? = null)
  fun debug(message: String, throwable: Throwable? = null, params: Map<String, Any?>? = null)
  fun info(message: String, throwable: Throwable? = null, params: Map<String, Any?>? = null)
  fun warn(message: String, throwable: Throwable? = null, params: Map<String, Any?>? = null)
  fun error(message: String, throwable: Throwable? = null, params: Map<String, Any?>? = null)
}