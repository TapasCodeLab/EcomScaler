package org.scaler.productmicroservice;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sample")
public class SampleController {
    @GetMapping("/sayhello/{name}")
    public String sayHello(@PathVariable("name") String name){
        return "Hello "+name;
    }

    @GetMapping("/saybye")
    public String saybye(){
        return "See you soon... ";
    }
}
