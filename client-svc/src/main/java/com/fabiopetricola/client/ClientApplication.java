package com.fabiopetricola.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.fabiopetricola.client"})
public class ClientApplication 
{
    public static void main( String[] args )
    {
        SpringApplication.run(ClientApplication.class, args);
    }
}
