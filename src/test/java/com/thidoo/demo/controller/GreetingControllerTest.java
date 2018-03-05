package com.thidoo.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.oracle.tools.packager.Log;
import com.thidoo.demo.model.Greeting;
import com.thidoo.demo.service.GreetingService;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(MockitoJUnitRunner.class)
@Configuration
public class GreetingControllerTest {

    private MockMvc mockMvc;

    @Mock
    private GreetingService greetingService;

//    @Resource
//    private WebApplicationContext webApplicationContext;

    @InjectMocks
    private GreetingController greetingController;

    ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        mockMvc = MockMvcBuilders.standaloneSetup(greetingController).build();
    }

    @Test
    public void shouldReturnCorrectGreetingAccordingToNameArg() throws Exception{
        String name = "Thi";
        String expectedContent = "Hello Thi!";

        Greeting expected = new Greeting(Greeting.getGreetingOut(), expectedContent);
        when(greetingService.getGreetingByName(name)).thenReturn(expected);

        mockMvc.perform(get("/greetings")
                .param("name", name))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", Matchers.is(Greeting.getGreetingOut())))
                .andExpect(jsonPath("$.content", Matchers.equalTo(expectedContent)));

        verify(greetingService, times(1)).getGreetingByName(name);
        verifyNoMoreInteractions(greetingService);
    }

    @Test
    public void shouldReturnDefaultGreetingWhenNoParam() throws Exception{
        String name = "";
        String expectedContent = "Hello World!";

        Greeting expected = new Greeting(Greeting.getGreetingOut(), expectedContent);
        when(greetingService.getGreetingByName(name)).thenReturn(expected);

        mockMvc.perform(get("/greetings")
                .param("name",name))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", Matchers.is(Greeting.getGreetingOut())))
                .andExpect(jsonPath("$.content", Matchers.equalTo(expectedContent)));

        verify(greetingService, times(1)).getGreetingByName(name);
        verifyNoMoreInteractions(greetingService);
    }

    @Test
    public void shouldSendCorrectGreetingAccordingToNameParam() throws Exception{

        String expectedContent = String.format("Hello Thi! You said: Hello Computer! Welcome!!");

        Greeting inGreeting = new Greeting();
        Greeting outGreeting = new Greeting(Greeting.getGreetingOut(), expectedContent);

        when(greetingService.generateGreeting(any())).thenReturn(outGreeting);

        mockMvc.perform(post("/greetings")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(objectMapper.writeValueAsString(inGreeting)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id", Matchers.is(Greeting.getGreetingOut())))
                .andExpect(jsonPath("$.content", Matchers.equalTo("Hello Thi! You said: Hello Computer! Welcome!!")));

        verify(greetingService, times(1)).generateGreeting(any());
        verifyNoMoreInteractions(greetingService);
    }


}
