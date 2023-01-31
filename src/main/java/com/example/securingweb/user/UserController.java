package com.example.securingweb.user;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.cert.X509Certificate;

@Controller
public class UserController {
    @Autowired
    private HttpServletRequest context;

    @RequestMapping(value = "/user")
    public String user() {

        X509Certificate[] certs = (X509Certificate[]) context.getAttribute("jakarta.servlet.request.X509Certificate");
        System.out.println(certs[0]);

        return "user";
    }
}