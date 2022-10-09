package com.whoiszxl.springframework.test.aop;

public class HelloService implements IHelloService{

    @Override
    public String sayAll() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "大家好";
    }

    @Override
    public String say(String username) {
        return "hello " + username;
    }
}
