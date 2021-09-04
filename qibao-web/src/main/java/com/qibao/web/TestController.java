package com.qibao.web;

import com.qibao.common.utils.Assert;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {

    @GetMapping
    public String test() {
        return "OK!";
    }

    @PostMapping
    public String post(@RequestBody String s){
        return s;
    }

}