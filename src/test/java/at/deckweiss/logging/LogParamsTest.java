package at.deckweiss.logging;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

class LogParamsTest {

    @Test
    void givenNoLogParams_whenAddingNewParam_thenParamIsAdded() {
        LogParams logParams = new LogParams();
        logParams.add("my new log object", "asdf");

        assertThat(logParams.toArray()).extracting("paramName", "paramValue")
                                       .containsExactly(tuple("my new log object", "asdf"));
    }

    @Test
    void givenNoLogParams_whenAddingNewNullParam_thenParamIsAdded() {
        LogParams logParams = new LogParams();
        logParams.add("my new log object", null);

        assertThat(logParams.toArray()).extracting("paramName", "paramValue")
                                       .containsExactly(tuple("my new log object", null));
    }

    @Test
    void givenNoLogParams_whenAddingIfNotNullNewParam_thenParamIsAdded() {
        LogParams logParams = new LogParams();
        logParams.addIfNotNull("my new log object", "asdf");

        assertThat(logParams.toArray()).extracting("paramName", "paramValue")
                                       .containsExactly(tuple("my new log object", "asdf"));
    }

    @Test
    void givenNoLogParams_whenAddingIfNotNullNewNullParam_thenParamIsNotAdded() {
        LogParams logParams = new LogParams();
        logParams.addIfNotNull("my new log object", null);

        assertThat(logParams.toArray()).isEmpty();
    }
}