package top.figo.springbootdrools.service;

import org.kie.api.KieBase;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.figo.springbootdrools.entity.Order;

/**
 * @Author Figo
 * @Date 2022/1/23 14:16
 */
@Service
public class RuleService {

    @Autowired
    private KieBase kieBase;

    public Order executeRule(Order order){
        KieSession kieSession = kieBase.newKieSession();
        kieSession.insert(order);
        kieSession.fireAllRules();
        kieSession.dispose();
        return order;
    }
}
