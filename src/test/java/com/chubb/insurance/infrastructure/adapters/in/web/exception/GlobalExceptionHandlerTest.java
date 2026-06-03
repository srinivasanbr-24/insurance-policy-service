package com.chubb.insurance.infrastructure.adapters.in.web.exception;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(TestController.class)
@Import(GlobalExceptionHandler.class)
class GlobalExceptionHandlerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void shouldReturnProblemDetails()
            throws Exception {

        mockMvc.perform(
                        get("/test-error")
                )
                .andExpect(
                        status().isNotFound()
                )
                .andExpect(
                        jsonPath("$.title")
                                .value("Policy Not Found")
                );
    }
}
