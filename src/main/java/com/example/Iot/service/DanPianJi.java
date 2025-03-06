package com.example.Iot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedInputStream;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;


@Service
public class DanPianJi {

    public static String test = "feat_test";
    public static String data = "name=100,name2=1,name3=1,name4=1";


    @Autowired
    MailServiceImpl mailService;
    public static BufferedInputStream bufferedInputStream;

    public static Socket accept = null;

    public static CurrThread curr = null;

    public void start() throws IOException {

        ServerSocket serverSocket = new ServerSocket(6000);

        while (true) {


            Socket socket = serverSocket.accept();

            if(accept!=null){
                accept.close();
            }

            accept = socket;

            System.out.println("连接成功");

            InputStream inputStream = accept.getInputStream();

            bufferedInputStream = new BufferedInputStream(inputStream);

            CurrThread thread = new CurrThread();

            thread.setMailService(DanPianJi.this.mailService);

            if (curr!=null){
                curr.setStop();
            }

            curr = thread;
            new Thread(thread).start();


        }


    }




}


@Service
class CurrThread implements Runnable {

    public volatile boolean stop = false;

    public boolean setStop() {
        return stop = true;
    }

    MailServiceImpl mailService;


    public MailServiceImpl getMailService() {
        return mailService;
    }

    public void setMailService(MailServiceImpl mailService) {
        this.mailService = mailService;
    }

    @Override
    public void run() {


        byte[] bytes;

        while (true) {

            try {

                bytes = new byte[100];

                DanPianJi.bufferedInputStream.read(bytes);

                //解析数据
                getData(new String(bytes),mailService);

                if(stop){
                    return;
                }


            } catch (IOException e) {
                return;
            }


        }


    }




    public void getData(String s,MailServiceImpl mailService){

        System.out.println("recv："+s);

        if(s.contains("data")){

            s = s.split(";")[0];
            DanPianJi.data = s;

            /*String[] split = s.split(",");

            DanPianJi.co2 = (byte) (100-(byte) Integer.parseInt(split[0].split("=")[1].trim()));
            DanPianJi.fire = (byte) Integer.parseInt(split[1].split("=")[1].substring(0,1));
            System.out.println(DanPianJi.co2);
            System.out.println(DanPianJi.fire);*/


        }else if(s.contains("mail")){

            s = s.split(";")[0];
            String[] split = s.split("__");
            mailService.sendMessage(split[0],split[1],split[2]);

        }

    }
}