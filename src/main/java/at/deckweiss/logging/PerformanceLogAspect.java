package at.deckweiss.logging;

import at.deckweiss.logging.annotation.PerformanceLog;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class PerformanceLogAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(PerformanceLogAspect.class);

    @Pointcut("@annotation(performanceLog)")
    public void callAt(PerformanceLog performanceLog) {
    }

    @Around(value = "callAt(performanceLog)", argNames = "pjp,performanceLog")
    public Object logPerformance(ProceedingJoinPoint pjp, PerformanceLog performanceLog) throws Throwable {
        long start = System.currentTimeMillis();

        try {
            Object result = pjp.proceed();
            log(System.currentTimeMillis() - start, formatSignature(pjp.getSignature()), getGroupOrNull(performanceLog));
            return result;
        } catch (Throwable throwable) {
            log(System.currentTimeMillis() - start, formatSignature(pjp.getSignature()), getGroupOrNull(performanceLog));
            throw throwable;
        }
    }

    private void log(long diffInMs, String signature, String group) {
        LOGGER.info("Measured performance", new LogParams("method", signature).add("took-milliseconds", diffInMs).addIfNotNull("group", group));
    }

    private String formatSignature(Signature signature) {
        return String.format("%s.%s(..)", signature.getDeclaringType().getSimpleName(), signature.getName());
    }

    private String getGroupOrNull(PerformanceLog performanceLog) {
        return performanceLog.group() == null || performanceLog.group().length() == 0
               ? null
               : performanceLog.group();
    }
}