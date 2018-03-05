package com.thidoo.demo.service;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.thidoo.demo.model.Greeting;
import netscape.javascript.JSObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class GreetingService {

    public Greeting generateGreeting(Greeting greeting){

        String response;
        String returnedName;

//        if (name.isEmpty()){
//            returnedName = "there";
//        }
//        else {
//            returnedName = name;
//        }


        if (greeting.getId() == Greeting.getGreetingIn()){
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("Hello " + "Thi" + "! ");
            stringBuilder.append("You said: " + greeting.getContent());
            stringBuilder.append(" Welcome!!");
            response = stringBuilder.toString();
        }
        else {
            response = "Invalid Greeting id";
        }

        return new Greeting(Greeting.getGreetingOut(), response);
    }

    public Greeting getGreetingByName(String name) {
        String returnedName;
        if (name.isEmpty()){
            returnedName = "World";
        }
        else {
            returnedName = name;
        }
        return new Greeting(Greeting.getGreetingOut(), String.format("Hello %s!", returnedName));
    }
}
