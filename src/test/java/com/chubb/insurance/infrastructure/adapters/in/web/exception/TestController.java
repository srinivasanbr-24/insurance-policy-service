package com.chubb.insurance.infrastructure.adapters.in.web.exception;

import com.chubb.insurance.application.exception.PolicyNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
class TestController {

    @GetMapping("/test-error")
    String error() {
        throw new PolicyNotFoundException(
                "Policy not found"
        );
    }
}