package at.deckweiss.logging

import org.junit.jupiter.api.Test

class LogParamsTest {

    @Test
    fun `Given no LogParams, when adding new param, then param is added`() {
        val logParams: LogParams = LogParams()
        logParams.add("my new log object", "asdf")

        assert(logParams.toParamString() == "[my new log object=\"asdf\"]")
    }

    @Test
    fun `Given no LogParams, when adding new null param, then param is added`() {
        val logParams: LogParams = LogParams()
        logParams.add("my new log object", null)

        assert(logParams.toParamString() == "[my new log object=\"null\"]")
    }

    @Test
    fun `Given no LogParams, when adding new param with AddIfNotNull, then param is added`() {
        val logParams: LogParams = LogParams()
        logParams.addIfNotNull("my new log object", "asdf")

        assert(logParams.toParamString() == "[my new log object=\"asdf\"]")
    }

    @Test
    fun `Given no LogParams, when adding new null param with AddIfNotNull, then param is not added`() {
        val logParams: LogParams = LogParams()
        logParams.addIfNotNull("my new log object", null)

        assert(logParams.toParamString() == "")
    }

    @Test
    fun `Given multiple LogParams, when adding multiple params, then params are added`() {
        val logParams: LogParams = LogParams()

        logParams.add("param1", "test1").add("param2", "test2")

        assert(logParams.toParamString() == "[param1=\"test1\", param2=\"test2\"]")
    }
}