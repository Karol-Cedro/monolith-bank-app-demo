package com.kcedro.monolithbankappl.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {


    //logowanie
    @GetMapping("/login")
    public String auth() {
        return "logowanie";
    }

    //rejestracja


    //zmiana limitow na karcie




}
