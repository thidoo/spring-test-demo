package com.thidoo.demo.model;

public class Greeting {

    private static final int GREETING_OUT = 1;
    private static final int GREETING_IN = 2;

    private int id;
    private String content;

    public Greeting(){
    }

    public Greeting(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public static int getGreetingOut() {
        return GREETING_OUT;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public static int getGreetingIn() {
        return GREETING_IN;
    }

    @Override
    public String toString() {
        return "Greeting{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}
