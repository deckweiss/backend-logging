# Logging Package

Deckweiss' home-made logging package for Java (based on SLF4J + Logback).

### How To

Basic rules for logging:

- Always prefer hardcoded messages over dynamic ones (no parameters in messages)
- Use LogParam(s) for any parameters you want to append (toString() is called for provided parameter values - also for complex objects)
- English text only

```java
import at.deckweiss.logging.Logger;
import at.deckweiss.logging.LoggerFactory;
import at.deckweiss.logging.LogParam;
import at.deckweiss.logging.LogParams;

public class MyFancyLoggingClass {
    private static final Logger logger = LoggerFactory.getLogger(MyFancyClass.class);

    public void myFancyLoggingMethod() {

        // Good logging
        logger.info("my message");
        logger.info("my parametrized message", new LogParam("id", 1));
        logger.info("my parametrized message 2", new LogParams("id", 1).add("name", "UsernameTest").add("user", complexUser));

        logger.error("my error message", myFancyException);
        logger.error("my parametrized error message", myFancyException, new LogParam("id", 1));
        logger.error("my parametrized error message 2", myFancyException, new LogParams("id", 1).add("name", "UsernameTest"));

        // Bad logging - Don't use dynamic messages
        logger.info("my super bad message %s %s".formatted("is", "bad"));
        logger.info("my super bad message {} {}", "is", "bad");

        // Output example
        logger.info("my parametrized message", new LogParam("id", 1)); // my parametrized message [id="1"]
        logger.info("my parametrized message", new LogParams("id", 1).add("user", user)); // my parametrized message [id="1", user="<whatever user.toString() returns>"]
    }
}
```