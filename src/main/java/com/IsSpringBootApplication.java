package com;

import com.webservice.WeatherInterfaceImpl;
import io.micrometer.core.instrument.MeterRegistry;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.autoconfigure.metrics.MeterRegistryCustomizer;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import javax.xml.ws.Endpoint;

@SpringBootApplication
@MapperScan("com.excel")
@EnableAspectJAutoProxy
public class IsSpringBootApplication {
    public static void main(String[] args) {
        SpringApplication.run(IsSpringBootApplication.class);
        Endpoint.publish("http://127.0.0.1:12345/weather", new WeatherInterfaceImpl());
    }

    @Bean
    MeterRegistryCustomizer<MeterRegistry> configurer(
            @Value("${spring.application.name}") String applicationName) {
        return (registry) -> registry.config().commonTags("application", applicationName);
    }

}
