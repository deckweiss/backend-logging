package at.deckweiss.logging;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.MDC;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LoggerImplTest {
    public static final String METHOD_NAME_KEY = "MethodName";

    @InjectMocks
    private LoggerImpl logger;

    @Mock
    private Logger slf4jLogger;

    @Test
    void givenNecessityToLog_whenInfoWithLogParamCalled_thenMethodNameIsCorrect() {
        try (MockedStatic<MDC> mdcMock = Mockito.mockStatic(MDC.class)) {
            this.logger.info("my test log message", new LogParams("testParam", "testValue"));
            mdcMock.verify(() -> MDC.put(METHOD_NAME_KEY, "givenNecessityToLog_whenInfoWithLogParamCalled_thenMethodNameIsCorrect"), times(1));
            mdcMock.verify(() -> MDC.remove(METHOD_NAME_KEY), times(1));
            mdcMock.verifyNoMoreInteractions();
            verify(this.slf4jLogger).info("my test log message [testParam=\"testValue\"]");
        }
    }

    @Test
    void givenNecessityToLog_whenInfoWithLogParamsCalled_thenMethodNameIsCorrect() {
        try (MockedStatic<MDC> mdcMock = Mockito.mockStatic(MDC.class)) {
            this.logger.info("my test log message", new LogParams("testParam", "testValue").add("second", "test"));
            mdcMock.verify(() -> MDC.put(METHOD_NAME_KEY, "givenNecessityToLog_whenInfoWithLogParamsCalled_thenMethodNameIsCorrect"), times(1));
            mdcMock.verify(() -> MDC.remove(METHOD_NAME_KEY), times(1));
            mdcMock.verifyNoMoreInteractions();
            verify(this.slf4jLogger).info("my test log message [testParam=\"testValue\", second=\"test\"]");
        }
    }

    @Test
    void givenNecessityToLog_whenInfoWithLogParamsToArrayCalled_thenMethodNameIsCorrect() {
        try (MockedStatic<MDC> mdcMock = Mockito.mockStatic(MDC.class)) {
            this.logger.info("my test log message", new LogParams("testParam", "testValue").add("second", "test"));
            mdcMock.verify(() -> MDC.put(METHOD_NAME_KEY, "givenNecessityToLog_whenInfoWithLogParamsToArrayCalled_thenMethodNameIsCorrect"), times(1));
            mdcMock.verify(() -> MDC.remove(METHOD_NAME_KEY), times(1));
            mdcMock.verifyNoMoreInteractions();
            verify(this.slf4jLogger).info("my test log message [testParam=\"testValue\", second=\"test\"]");
        }
    }

    @Test
    void givenNecessityToLog_whenErrorWithoutLogParamCalled_thenOnlyMessageIsLogged() {
        try (MockedStatic<MDC> mdcMock = Mockito.mockStatic(MDC.class)) {
            this.logger.error("my test error message");
            mdcMock.verify(() -> MDC.put(METHOD_NAME_KEY, "givenNecessityToLog_whenErrorWithoutLogParamCalled_thenOnlyMessageIsLogged"), times(1));
            mdcMock.verify(() -> MDC.remove(METHOD_NAME_KEY), times(1));
            mdcMock.verifyNoMoreInteractions();
            verify(this.slf4jLogger).error("my test error message");
        }
    }

    @Test
    void givenNecessityToLog_whenErrorWithExceptionCalled_thenExceptionIsLogged() {
        try (MockedStatic<MDC> mdcMock = Mockito.mockStatic(MDC.class)) {
            Exception e = new Exception("test error occured");

            this.logger.error("my test error message", e);
            mdcMock.verify(() -> MDC.put(METHOD_NAME_KEY, "givenNecessityToLog_whenErrorWithExceptionCalled_thenExceptionIsLogged"), times(1));
            mdcMock.verify(() -> MDC.remove(METHOD_NAME_KEY), times(1));
            mdcMock.verifyNoMoreInteractions();
            verify(this.slf4jLogger).error("my test error message", e);
        }
    }

    @Test
    void givenNecessityToLog_whenErrorWithExceptionAndLogParamsCalled_thenExceptionIsLogged() {
        try (MockedStatic<MDC> mdcMock = Mockito.mockStatic(MDC.class)) {
            Exception e = new Exception("test error occured");

            this.logger.error("my test error message", e, new LogParams("asdf", "test").add("asdf1", "test1").add("asdfasd", "test3"));
            mdcMock.verify(() -> MDC.put(METHOD_NAME_KEY, "givenNecessityToLog_whenErrorWithExceptionAndLogParamsCalled_thenExceptionIsLogged"), times(1));
            mdcMock.verify(() -> MDC.remove(METHOD_NAME_KEY), times(1));
            mdcMock.verifyNoMoreInteractions();
            verify(this.slf4jLogger).error("my test error message [asdf=\"test\", asdf1=\"test1\", asdfasd=\"test3\"]", e);
        }
    }
}