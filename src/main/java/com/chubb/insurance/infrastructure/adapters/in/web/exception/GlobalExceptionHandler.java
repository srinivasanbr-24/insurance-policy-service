package com.chubb.insurance.infrastructure.adapters.in.web.exception;

import com.chubb.insurance.application.exception.PolicyNotFoundException;
import com.chubb.insurance.domain.exception.InvalidPolicyStateException;
import com.chubb.insurance.domain.exception.PolicyAlreadyFlaggedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(PolicyNotFoundException.class)
    public ProblemDetail handlePolicyNotFound(
            PolicyNotFoundException ex
    ) {

        ProblemDetail detail =
                ProblemDetail.forStatus(
                        HttpStatus.NOT_FOUND
                );

        detail.setTitle(
                "Policy Not Found"
        );

        detail.setDetail(
                ex.getMessage()
        );

        return detail;
    }

    @ExceptionHandler({
            InvalidPolicyStateException.class,
            PolicyAlreadyFlaggedException.class
    })
    public ProblemDetail handleBusinessException(
            RuntimeException ex
    ) {

        ProblemDetail detail =
                ProblemDetail.forStatus(
                        HttpStatus.BAD_REQUEST
                );

        detail.setTitle(
                "Business Rule Violation"
        );

        detail.setDetail(
                ex.getMessage()
        );

        return detail;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ProblemDetail handleValidationException(
            MethodArgumentNotValidException ex
    ) {

        ProblemDetail detail =
                ProblemDetail.forStatus(
                        HttpStatus.BAD_REQUEST
                );

        detail.setTitle(
                "Validation Failed"
        );

        detail.setDetail(
                ex.getMessage()
        );

        return detail;
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleException(
            Exception ex
    ) {

        ProblemDetail detail =
                ProblemDetail.forStatus(
                        HttpStatus.INTERNAL_SERVER_ERROR
                );

        detail.setTitle(
                "Internal Server Error"
        );

        detail.setDetail(
                ex.getMessage()
        );

        return detail;
    }
}