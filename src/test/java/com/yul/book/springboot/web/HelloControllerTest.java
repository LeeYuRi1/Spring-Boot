package com.yul.book.springboot.web;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class) //스프링 실행자를 사용. 스프링 부트 테스트와 JUnit 사이에 연결자 역할
@WebMvcTest //web(spring mvc)에 집중할 수 있는 어노테이션. @Controller, @ControllerAdvice등을 사용할 수 있고, @Service, @Component, @Repository등은 사용할 수 없음
public class HelloControllerTest {

    @Autowired //스프링이 관리하는 빈을 주입 받음
    private MockMvc mvc; //웹 api를 테스트할때 사용. 스프링 mvc 테스트의 시작점

    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) //MockMvc를 통해 /hello 주소로 HTTP GET 요청을 함
                .andExpect(status().isOk()) //mvc.perform의 결과를 검증함. HTTP Header의 status를 검증
                .andExpect(content().string(hello)); //응답 본문의 내용을 검증
    }

    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(get("/hello/dto")
                .param("name", name) //api 테스트할 때 사용될 요청 파라미터를 설정. 단, 값은 String만 허용
                .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) //jsonPath: JSON 응답값을 필드별로 검증할 수 있는 메소드. $를 기준으로 필드명을 명시
                .andExpect(jsonPath("$.amount", is(amount)));
    }
}