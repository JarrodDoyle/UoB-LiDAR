package com.lidar.lidar;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class LidarController {

    @RequestMapping("/")
    public String test(@RequestParam(name="a", required=false, defaultValue="aaa") String a, @RequestParam(name="b", required=false, defaultValue="bbb") String b) {
        return "<!DOCTYPE HTML><html><head><title>Getting Started: Serving Web Content</title></head><body><p style='color:red'>hello</p></body></html>";
    }

}