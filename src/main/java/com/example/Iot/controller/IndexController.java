package com.example.Iot.controller;


import com.example.Iot.service.DanPianJi;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "*")
public class IndexController {


    @GetMapping("/index")
    public String getList(){

        return DanPianJi.data;

    }




}
