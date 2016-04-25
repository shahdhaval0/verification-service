package org.test;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@EnableAutoConfiguration
@ComponentScan
@RefreshScope
@RestController
public class VerificationServiceController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    
    @Value("${message}")
    private String message;
    
    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }
    
    @RequestMapping("/message")
    public String message() {
        return this.message;
    }
}
