![Maven Central Version](https://img.shields.io/maven-central/v/at.deckweiss/logging?style=flat&label=Maven%20Central)

# Backend Logging Package

Deckweiss' home-made logging package for Kotlin (based on SLF4J + Logback).

### Installation

Copy following line to your `build.gradle.kts` dependency list:

```kotlin
dependencies {
  implementation("at.deckweiss:logging:<version>")
}
```

### Consume Logging

Basic rules for logging:

- Always prefer hardcoded messages over dynamic ones (no parameters in messages)
- Use `LogParams` for any parameters you want to append (`toString()` is called for provided parameter values - also for complex objects)
- Avoid multilingual log messages

```kotlin
import at.deckweiss.logging.Logger
import at.deckweiss.logging.LoggerDelegate
import at.deckweiss.logging.LogParams

class MyFancyLoggingClass {
   private val logger: Logger by LoggerDelegate()

   fun myFancyLoggingMethod() {

      // Good logging (use named arguments when passing multiple parameters)
      logger.info("my message")
      logger.info(message = "my parametrized message", params = LogParams("id", 1))
      logger.info(message = "my parametrized message 2", params = LogParams("id", 1).add("name", "UsernameTest").add("user", complexUser))

      logger.error(message = "my error message", throwable = myFancyException)
      logger.error(message = "my parametrized error message", throwable = myFancyException, params = LogParams("id", 1))
      logger.error(message = "my parametrized error message 2", throwable = myFancyException, params = LogParams("id", 1).add("name", "UsernameTest"))

      // Avoid dynamic messages
      logger.info("my super bad message %s %s".formatted("is", "bad"))
      logger.info("my super bad message {} {}", "is", "bad")

      // Output example
      logger.info(message = "my parametrized message", params = LogParams("id", 1))  // my parametrized message [id="1"]
      logger.info(message = "my parametrized message", params = LogParams("id", 1).add("user", user))  // my parametrized message [id="1", user="<whatever user.toString() returns>"]
   }
}
```
