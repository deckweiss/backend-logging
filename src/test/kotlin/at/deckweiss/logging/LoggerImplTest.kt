package at.deckweiss.logging

import org.junit.jupiter.api.Test
import java.lang.Exception

class LoggerImplTest {

    private val testLogger = LoggerFactory.getLogger(LoggerImplTest::class.java)

    @Test
    fun `Given necessity to log, when calling info with null as param 'throwable', then method executed successfully`() {
        testLogger.info(message = "my test info message", params = mapOf("param1" to "val1"))
    }

    @Test
    fun `Given necessity to log, when calling error without LogParams, then only message is logged`() {
        testLogger.error(message = "my test error message without params")
    }

    @Test
    fun `Given necessity to log, when calling error with exception, then exception is logged`() {
        testLogger.error(message = "my test error message with exception", throwable = Exception("test error occured"))
    }

    @Test
    fun `Given necessity to log, when calling error with exception and LogParams, then exception is logged`() {
        testLogger.error(message = "my test error message with exception and params", throwable = Exception("test error occured"), params = mapOf("param2" to "value2", "param3" to null))
    }

    @Test
    fun `Given necessity to log, when calling error with exception and empty LogParams, then exception is logged`() {
        testLogger.error(message = "my test error message with exception and empty params", throwable = Exception("test error occured"), params = mapOf())
    }
}