package at.deckweiss.logging.annotation;

import java.lang.annotation.*;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PerformanceLog {

    /**
     * The group identifier of performance logs. May be interesting for grouping certain performance logs.
     */
    String group() default "";
}