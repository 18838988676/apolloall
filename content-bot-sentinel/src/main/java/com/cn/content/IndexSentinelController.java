package com.cn.content;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 测试content-bot-sentinel
 * @Time
 */

@RestController
@RequestMapping("/index2")
public class IndexSentinelController {

    private AtomicInteger num=new AtomicInteger(1);


    @GetMapping("/first")
    public  String  index01 (){
        LocalTime lt = LocalTime.now();
        String time=lt.getHour()+":"+lt.getMinute()+":"+lt.getSecond()+":"+lt.getNano();
        String s=String.format("当前线程:%s,累计访问:%s,时间:%s",Thread.currentThread().getName(),num.getAndIncrement(),time);
        System.out.println(s);
        return s;
    }
    private final static String key="Hello";

    @GetMapping("/secone")
    public  String index02 (){
        String msg="";
        initFlowRules();
            try (Entry entry= SphU.entry(key)){
                msg="success";
            }catch (Exception e){
                msg="Blocked";
        }
        System.out.println(msg);
            return msg;
    }
private void initFlowRules(){
    List<FlowRule> rules=new ArrayList<>();
    FlowRule rule=new FlowRule();
    rule.setResource(key);
    rule.setGrade(RuleConstant.FLOW_GRADE_QPS);
    rule.setCount(90);
    rules.add(rule);
    FlowRuleManager.loadRules(rules);
}

}
