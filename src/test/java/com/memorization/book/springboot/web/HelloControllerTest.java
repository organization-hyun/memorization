package com.memorization.book.springboot.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = HelloController.class)
class HelloControllerTest {

    @Autowired
    private MockMvc mvc;

    @DisplayName("hello가 리턴된다")
    @Test
    void hello() throws Exception {
        //given
        String hello = "hello";

        //then
        mvc.perform(get("/hello"))
                .andExpect(status().isOk())
                .andExpect(content().string(hello));

    }

    @DisplayName("helloDto가 리턴된다")
    @Test
    void test() throws Exception {
        //given
        String name = "hello";
        int amount = 1000;

        //then
        mvc.perform(
                        get("/hello")
                                .param("name", name)
                                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(result -> jsonPath("$.name", is(name)))
                .andExpect(result -> jsonPath("$.amount", is(amount)));
    }

}