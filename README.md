# Logging Package

Deckweiss' home-made logging package for Kotlin (based on SLF4J + Logback).

### Installation

Copy following line to your `build.gradle` dependency list:

```kotlin
implementation("at.deckweiss:logging:<version>")
```

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
- Use `LogParams` for any parameters you want to append (`toString()` is called for provided parameter values - also for complex objects)
- English text only

```kotlin
import at.deckweiss.logging.Logger
import at.deckweiss.logging.LoggerDelegate
import at.deckweiss.logging.LogParam
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

      // Bad logging - Don't use dynamic messages
      logger.info("my super bad message %s %s".formatted("is", "bad"))
      logger.info("my super bad message {} {}", "is", "bad")

      // Output example
      logger.info(message = "my parametrized message", params = LogParams("id", 1))  // my parametrized message [id="1"]
      logger.info(message = "my parametrized message", params = LogParams("id", 1).add("user", user))  // my parametrized message [id="1", user="<whatever user.toString() returns>"]
   }
}
```
