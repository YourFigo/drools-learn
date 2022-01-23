package top.figo.springbootdrools.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.figo.springbootdrools.entity.Order;
import top.figo.springbootdrools.service.RuleService;

/**
 * @Author Figo
 * @Date 2022/1/23 14:21
 */
@RestController
public class OrderController {

    @Autowired
    private RuleService ruleService;

    @PostMapping("/saveOrder")
    public Order saveOrder(@RequestBody Order order){
        return ruleService.executeRule(order);
    }
}
