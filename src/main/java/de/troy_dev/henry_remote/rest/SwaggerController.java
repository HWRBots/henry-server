package de.troy_dev.henry_remote.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class SwaggerController {

    @RequestMapping(value = "")
    public void redirect(HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Location", "/swagger-ui.html");
        httpServletResponse.setStatus(302);
    }
}
