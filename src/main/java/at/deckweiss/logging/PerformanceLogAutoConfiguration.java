package at.deckweiss.logging;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PerformanceLogAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public PerformanceLogAspect performanceLogAspect() {
        return new PerformanceLogAspect();
    }
}
