package net.yutobo.healthcheck.tester.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api")
public class HealthCheckController {
    @GetMapping("/echo")
    public String echo(HttpServletRequest req) {
        return req.getQueryString();
    }
}
