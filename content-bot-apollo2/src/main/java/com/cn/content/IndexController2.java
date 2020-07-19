package com.cn.content;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试jmeter
 * @Time
 */

@RestController
@RequestMapping("/index")
public class IndexController2 {

    private AtomicInteger num=new AtomicInteger(1);


    @Value("${ab.test.key1}")
    private String mes1;

    @Value("${ab.test.key2}")
    private String mes2;

    @Value("${ab.test.key3}")
    private String mes3;
    @Value("${ab.test.key4}")
    private String mes4;
    @Value("${ab.test.key5}")
    private String mes5;
    @Scheduled(cron="0/4 * * * * ? ")
    public void test(){
        System.out.println("mes1="+mes1);
        System.out.println("mes2="+mes2);
        System.out.println("mes3="+mes3);
        System.out.println("mes4="+mes4);
        System.out.println("mes5="+mes5);
        System.out.println("==================================================================");
    }

    @GetMapping("/num")
    public  String  index01 (){
        LocalTime lt = LocalTime.now();
        String time=lt.getHour()+":"+lt.getMinute()+":"+lt.getSecond()+":"+lt.getNano();
        String s=String.format("当前线程:%s,累计访问:%s,时间:%s",Thread.currentThread().getName(),num.getAndIncrement(),time);
        System.out.println(s);
        return s;
    }

    public static void main(String[] args) {
        for (int i=0;i<5;i++){
        System.out.println("System.out.println(mes=mes"+i+");");
        }
    }
}
