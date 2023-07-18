package at.deckweiss.logging.util

import ch.qos.logback.classic.Level
import ch.qos.logback.classic.spi.ILoggingEvent
import ch.qos.logback.core.pattern.color.ANSIConstants
import ch.qos.logback.core.pattern.color.ForegroundCompositeConverterBase

class HighlightColorConverter : ForegroundCompositeConverterBase<ILoggingEvent>() {
    override fun getForegroundColorCode(event: ILoggingEvent): String {
        return when (event.level) {
            Level.ERROR -> ANSIConstants.BOLD + ANSIConstants.RED_FG
            Level.WARN -> ANSIConstants.YELLOW_FG
            Level.INFO -> ANSIConstants.DEFAULT_FG
            else -> ANSIConstants.DEFAULT_FG
        }
    }
}