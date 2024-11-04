package org.scaler.usermicroservice.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class SampleController {
    @GetMapping("/tapas")
    public String gethello(){
        return "Hi Tapas";

    }}
