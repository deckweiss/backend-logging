package at.deckweiss.logging

import org.slf4j.LoggerFactory

object LoggerFactory {
    fun getLogger(clazz: Class<*>?): Logger {
        return LoggerImpl(LoggerFactory.getLogger(clazz))
    }

    fun getLogger(name: String?): Logger {
        return LoggerImpl(LoggerFactory.getLogger(name))
    }
}