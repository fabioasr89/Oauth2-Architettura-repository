package com.fabiopetricola.resources;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.fabiopetricola.resources"})
@EnableFeignClients(basePackages = {"com.fabiopetricola.resources.feign"})
public class ResourcesServerApplication 
{
    public static void main( String[] args )
    {
       SpringApplication.run(ResourcesServerApplication.class, args);
    }
}
