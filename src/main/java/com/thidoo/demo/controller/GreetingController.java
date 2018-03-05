package com.thidoo.demo.controller;

import com.thidoo.demo.model.Greeting;
import com.thidoo.demo.service.GreetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
public class GreetingController {

    @Resource
    private GreetingService greetingService;

    @Autowired
    public GreetingController(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    @RequestMapping(value="/greetings", method = RequestMethod.GET)
    public @ResponseBody Greeting sendGreeting(@RequestParam(value="name") String name){
        return greetingService.getGreetingByName(name);
    }

    @RequestMapping(value="/greetings", method = RequestMethod.POST)
    @ResponseBody
    public Greeting exchangeGreeting(@RequestBody Greeting greeting){
        return greetingService.generateGreeting(greeting);
    }

}
