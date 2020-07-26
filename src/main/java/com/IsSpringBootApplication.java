package com;

import com.webservice.WeatherInterfaceImpl;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.ws.Endpoint;

@SpringBootApplication
@MapperScan("com.excel")
public class IsSpringBootApplication {
    public static void main(String[] args) {

        SpringApplication.run(IsSpringBootApplication.class);
        Endpoint.publish("http://127.0.0.1:12345/weather", new WeatherInterfaceImpl());
    }
}
