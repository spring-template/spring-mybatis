package org.bytewen.controller;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController

public class OpenidController {
    @GetMapping("/login")
    public void login(@RequestParam("code") String code){
        System.out.println(code);
        HttpPost request=new HttpPost("https://github.com/login/oauth/access_token");

    }
}
