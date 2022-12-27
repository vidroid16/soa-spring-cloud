package ru.shalya.soashopservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.shalya.soashopservice.services.ShopService;

@RestController
public class HelloController {
    private final ShopService shopService;

    @Autowired
    public HelloController(ShopService shopService) {
        this.shopService = shopService;
    }


    @GetMapping("/test")
    public String test(){
        return "test1";
    }
}
