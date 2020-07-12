package com.cn.content;

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


    @GetMapping("/num")
    public  String  index01 (){
        LocalTime lt = LocalTime.now();
        String time=lt.getHour()+":"+lt.getMinute()+":"+lt.getSecond()+":"+lt.getNano();
        String s=String.format("当前线程:%s,累计访问:%s,时间:%s",Thread.currentThread().getName(),num.getAndIncrement(),time);
        System.out.println(s);
        return s;
    }



}
