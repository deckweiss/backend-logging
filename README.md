# Logging Package

Deckweiss' home-made logging package for Java (based on SLF4J + Logback).

### How To's

#### Update this package

1. `git checkout master` && `git pull` && `git checkout -b <my-new-branch>`
2. Always update the version in `build.gradle` according to semantic versioning.
    - `<Major>.<Minor>.<Patch>` eg. `1.0.5`
        - Major-Version changes when there's a breaking change introduced
        - Minor-Version changes when there's just a new feature or enhancement
        - Patch-Version changes when there's a bugfix
    - `<Major>.<Minor>.<Patch>-alpha<N>` eg. `1.0.5-alpha2`
        - Should be used for experimental stuff only.
3. Apply your changes to the code
4. Push your changes
5. After merging completed, the new version is automatically published to the registry

#### Consume Logging

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
        logger.info("my parametrized message", new LogParams("id", 1));
        logger.info("my parametrized message 2", new LogParams("id", 1).add("name", "UsernameTest").add("user", complexUser));

        logger.error("my error message", myFancyException);
        logger.error("my parametrized error message", myFancyException, new LogParams("id", 1));
        logger.error("my parametrized error message 2", myFancyException, new LogParams("id", 1).add("name", "UsernameTest"));

        // Bad logging - Don't use dynamic messages
        logger.info("my super bad message %s %s".formatted("is", "bad"));
        logger.info("my super bad message {} {}", "is", "bad");

        // Output example
        logger.info("my parametrized message", new LogParams("id", 1)); // my parametrized message [id="1"]
        logger.info("my parametrized message", new LogParams("id", 1).add("user", user)); // my parametrized message [id="1", user="<whatever user.toString() returns>"]
    }
}
```

#### Performance logging

There's an annotation that can be placed on a method, which is then watched how long its execution took.

```java
import at.deckweiss.logging.annotation.PerformanceLog;

public class MyFancyLoggingClass {

    @PerformanceLog
    public void myFancyMethod() {
        // do magic
    }

    // groups performance logs, so they can be more easily queried
    @PerformanceLog(group = "my-performance-group")
    public void myFancyMethod() {
        // do magic
    }
}
```

In order to make the annotation work in spring, a bean of type `PerformanceLogAspect` has to be exposed:

```java

@Configuration
public class MyConfigurationClass {

    @Bean
    public PerformanceLogAspect performanceLogAspect() {
        return new PerformanceLogAspect();
    }
}
```