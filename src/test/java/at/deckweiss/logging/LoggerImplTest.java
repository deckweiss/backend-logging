package at.deckweiss.logging;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.slf4j.MDC;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoggerImplTest {
    public static final String METHOD_NAME_KEY = "MethodName";

    @InjectMocks
    private LoggerImpl logger;

    @Mock
    private Logger slf4jLogger;

    private MockedStatic<MDC> mdcMockedStatic;

    @AfterEach
    private void afterEach() {
        if (this.mdcMockedStatic != null && !this.mdcMockedStatic.isClosed()) {
            this.mdcMockedStatic.close();
        }
    }

    @Test
    void givenNecessityToLog_whenInfoWithLogParamCalled_thenMethodNameIsCorrect() {
        this.mdcMockedStatic = mockStatic(MDC.class);

        this.logger.info("my test log message", new LogParam("testParam", "testValue"));
        this.mdcMockedStatic.verify(times(1), () -> MDC.put(METHOD_NAME_KEY, "givenNecessityToLog_whenInfoWithLogParamCalled_thenMethodNameIsCorrect"));
        this.mdcMockedStatic.verify(times(1), () -> MDC.remove(METHOD_NAME_KEY));
        this.mdcMockedStatic.verifyNoMoreInteractions();
        verify(this.slf4jLogger).info("my test log message [testParam=\"testValue\"]");
    }

    @Test
    void givenNecessityToLog_whenInfoWithLogParamsCalled_thenMethodNameIsCorrect() {
        this.mdcMockedStatic = mockStatic(MDC.class);

        this.logger.info("my test log message", new LogParams("testParam", "testValue").add("second", "test"));
        this.mdcMockedStatic.verify(times(1), () -> MDC.put(METHOD_NAME_KEY, "givenNecessityToLog_whenInfoWithLogParamsCalled_thenMethodNameIsCorrect"));
        this.mdcMockedStatic.verify(times(1), () -> MDC.remove(METHOD_NAME_KEY));
        this.mdcMockedStatic.verifyNoMoreInteractions();
        verify(this.slf4jLogger).info("my test log message [testParam=\"testValue\", second=\"test\"]");
    }

    @Test
    void givenNecessityToLog_whenInfoWithLogParamsToArrayCalled_thenMethodNameIsCorrect() {
        this.mdcMockedStatic = mockStatic(MDC.class);

        this.logger.info("my test log message", new LogParams("testParam", "testValue").add("second", "test").toArray());
        this.mdcMockedStatic.verify(times(1), () -> MDC.put(METHOD_NAME_KEY, "givenNecessityToLog_whenInfoWithLogParamsToArrayCalled_thenMethodNameIsCorrect"));
        this.mdcMockedStatic.verify(times(1), () -> MDC.remove(METHOD_NAME_KEY));
        this.mdcMockedStatic.verifyNoMoreInteractions();
        verify(this.slf4jLogger).info("my test log message [testParam=\"testValue\", second=\"test\"]");
    }

    @Test
    void givenNecessityToLog_whenErrorWithoutLogParamCalled_thenOnlyMessageIsLogged() {
        this.mdcMockedStatic = mockStatic(MDC.class);

        this.logger.error("my test error message");
        this.mdcMockedStatic.verify(times(1), () -> MDC.put(METHOD_NAME_KEY, "givenNecessityToLog_whenErrorWithoutLogParamCalled_thenOnlyMessageIsLogged"));
        this.mdcMockedStatic.verify(times(1), () -> MDC.remove(METHOD_NAME_KEY));
        this.mdcMockedStatic.verifyNoMoreInteractions();
        verify(this.slf4jLogger).error("my test error message");
    }

    @Test
    void givenNecessityToLog_whenErrorWithExceptionCalled_thenExceptionIsLogged() {
        this.mdcMockedStatic = mockStatic(MDC.class);
        Exception e = new Exception("test error occured");

        this.logger.error("my test error message", e);
        this.mdcMockedStatic.verify(times(1), () -> MDC.put(METHOD_NAME_KEY, "givenNecessityToLog_whenErrorWithExceptionCalled_thenExceptionIsLogged"));
        this.mdcMockedStatic.verify(times(1), () -> MDC.remove(METHOD_NAME_KEY));
        this.mdcMockedStatic.verifyNoMoreInteractions();
        verify(this.slf4jLogger).error("my test error message", e);
    }

    @Test
    void givenNecessityToLog_whenErrorWithExceptionAndLogParamsCalled_thenExceptionIsLogged() {
        this.mdcMockedStatic = mockStatic(MDC.class);
        Exception e = new Exception("test error occured");

        this.logger.error("my test error message", e, new LogParams("asdf", "test").add("asdf1", "test1").add("asdfasd", "test3"));
        this.mdcMockedStatic.verify(times(1), () -> MDC.put(METHOD_NAME_KEY, "givenNecessityToLog_whenErrorWithExceptionAndLogParamsCalled_thenExceptionIsLogged"));
        this.mdcMockedStatic.verify(times(1), () -> MDC.remove(METHOD_NAME_KEY));
        this.mdcMockedStatic.verifyNoMoreInteractions();
        verify(this.slf4jLogger).error("my test error message [asdf=\"test\", asdf1=\"test1\", asdfasd=\"test3\"]", e);
    }
}