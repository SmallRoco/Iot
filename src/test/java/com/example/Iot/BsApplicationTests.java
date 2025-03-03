package com.example.Iot;

import com.example.Iot.service.MailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BsApplicationTests {


    @Autowired
    MailServiceImpl mailService;




}
