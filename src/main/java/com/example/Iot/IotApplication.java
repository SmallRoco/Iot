package com.example.Iot;

import com.example.Iot.service.DanPianJi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

@SpringBootApplication
public class IotApplication {




    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(IotApplication.class, args);


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    run.getBean(DanPianJi.class).start();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }).start();
    }
}
