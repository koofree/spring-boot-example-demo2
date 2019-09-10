package com.example.demo2

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
@Aspect
class SampleAOP {
    private val logger = LoggerFactory.getLogger(javaClass)

    @Around("execution(* com.example.demo2.controller.*.*(..))")
    fun advice(point: ProceedingJoinPoint): Any? {
        logger.info("${point.target} AOP Logging Start")

        val result = point.proceed()
        logger.info("AOP Logging End")
        return result
    }
}
