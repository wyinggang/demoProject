package com.aop;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tag;
import io.micrometer.core.instrument.Timer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.ArrayList;

@Component
@Aspect
public class PrometheusMonitoring {

    @Autowired
    private MeterRegistry registry;

    @Value("${spring.application.name}")
    String application;

    @Pointcut("@annotation(com.aop.Monitoring)")
    public void pointut(){}


    @Around("pointut()")
    public Object around(ProceedingJoinPoint point) throws Throwable{
        Object result = null;
        ArrayList<Tag> tags = new ArrayList<>();
        Timer.Sample sample = Timer.start(registry);
        try {
             result = point.proceed();
        }catch (Exception e){
            e.printStackTrace();
            tags.add(Tag.of("exception",e.getClass().getSimpleName()));
            throw e;
        }finally {
            MethodSignature msig = (MethodSignature) point.getSignature();
            Method method = point.getTarget().getClass().getMethod(msig.getName(), msig.getParameterTypes());
            Monitoring monitoring = method.getAnnotation(Monitoring.class);
            tags.add(Tag.of("uri",monitoring.uri()));
            tags.add(Tag.of("method",monitoring.method()));
            tags.add(Tag.of("result",result.toString()));
            sample.stop(registry.timer("http_server_demoProject"+msig.getName(),tags));
        }
        return null;
    }



}
