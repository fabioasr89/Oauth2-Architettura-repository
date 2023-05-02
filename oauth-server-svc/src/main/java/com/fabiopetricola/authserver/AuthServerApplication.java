package com.fabiopetricola.authserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.fabiopetricola.authserver"})
public class AuthServerApplication 
{
    public static void main( String[] args )
    {
       SpringApplication.run(AuthServerApplication.class, args);
    }
}
