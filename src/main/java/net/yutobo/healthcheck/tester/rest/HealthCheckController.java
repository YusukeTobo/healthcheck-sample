package net.yutobo.healthcheck.tester.rest;

import java.util.List;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import net.yutobo.healthcheck.tester.ComputerNameUtils;

@RestController
@RequestMapping("/api")
public class HealthCheckController {
    @GetMapping("/echo")
    public String echo(HttpServletRequest req) {
        return req.getQueryString();
    }

    @GetMapping("/delaylist")
    public List<String> getDelaylist() {
        return ComputerNameUtils.getComputerList(ComputerNameUtils.DELAYLIST_FILE);
    }

    @GetMapping("/unhealthylist")
    public List<String> getUnhealthyList() {
        return ComputerNameUtils.getComputerList(ComputerNameUtils.UNHEALTHYLIST_FILE);
    }

    @GetMapping("/health")
    public ResponseEntity<String> health() {
        if (ComputerNameUtils.hasComputerName(ComputerNameUtils.UNHEALTHYLIST_FILE)) {
            return ResponseEntity.status(HttpStatusCode.valueOf(503)).body("Service Unavailable");
        }
        return ResponseEntity.ok().body("Ok");
    }
}
