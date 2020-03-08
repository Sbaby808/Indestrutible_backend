package com.indestructible_backend.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Sbaby
 * @Date 2020/03/08 13:46
 * @Version 1.0
 */
@RestController
public class TestController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String test(String name) {
        return "Hi, fuck you " + name + " !";
    }

}
