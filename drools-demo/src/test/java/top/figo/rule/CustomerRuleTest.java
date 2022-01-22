package top.figo.rule;

import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import top.figo.entity.Customer;
import top.figo.entity.Order;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Figo
 * @Date 2022/1/22 17:23
 */
public class CustomerRuleTest {

    private KieServices kieServices;
    private KieContainer kieContainer;
    private KieSession kieSession;

    @Before
    public void initialKie(){
        // 创建 KieServices 服务
        kieServices = KieServices.Factory.get();
        // 获取 KieContainer 容器
        kieContainer = kieServices.getKieClasspathContainer();
        // 获取 KieSession，用于和规则引擎交互
        kieSession = kieContainer.newKieSession();
    }

    @After
    public void closeKie(){
        // 关闭 KieSession
        kieSession.dispose();
    }

    @Test
    public void testContains(){
        Order order = new Order();
        Customer customer = new Customer();
        List<Order> orderList = new ArrayList<>();
        orderList.add(order);
        customer.setOrderList(orderList);

        kieSession.insert(order);
        kieSession.insert(customer);
        kieSession.fireAllRules();

        System.out.println(order);
        System.out.println(customer);
    }

    @Test
    public void testNotContains(){
        Order order = new Order();
        Customer customer = new Customer();
        List<Order> orderList = new ArrayList<>();
//        orderList.add(order);
        customer.setOrderList(orderList);

        kieSession.insert(order);
        kieSession.insert(customer);
        kieSession.fireAllRules();

        System.out.println(order);
        System.out.println(customer);
    }

    @Test
    public void testMemberOf(){
        Order order = new Order();
        Customer customer = new Customer();
        List<Order> orderList = new ArrayList<>();
        orderList.add(order);
        customer.setOrderList(orderList);

        kieSession.insert(order);
        kieSession.insert(customer);
        kieSession.fireAllRules();

        System.out.println(order);
        System.out.println(customer);
    }

    @Test
    public void testNotMemberOf(){
        Order order = new Order();
        Customer customer = new Customer();
        List<Order> orderList = new ArrayList<>();
//        orderList.add(order);
        customer.setOrderList(orderList);

        kieSession.insert(order);
        kieSession.insert(customer);
        kieSession.fireAllRules();

        System.out.println(order);
        System.out.println(customer);
    }

    @Test
    public void testMatches(){
        Customer customer = new Customer();
        customer.setName("张三");

        kieSession.insert(customer);
        kieSession.fireAllRules();

        System.out.println(customer);
    }

    @Test
    public void testNotMatches(){
        Customer customer = new Customer();
        Order order = new Order();

        List<Order> orderList = new ArrayList<>();
        orderList.add(order);
        customer.setName("李四");
        customer.setOrderList(orderList);

        kieSession.insert(order);
        kieSession.insert(customer);
        kieSession.fireAllRules(new RuleNameEqualsAgendaFilter("rule_not_matches"));

        System.out.println(order);
        System.out.println(customer);
    }

    @Test
    public void testSet(){
        Customer customer = new Customer();

        kieSession.insert(customer);
        kieSession.fireAllRules();

        System.out.println(customer);
    }

    @Test
    public void testModify(){
        Customer customer = new Customer();
        customer.setName("张三");

        kieSession.insert(customer);
        kieSession.fireAllRules();

        System.out.println(customer);
    }

    @Test
    public void testInsert(){

        kieSession.fireAllRules();
    }

    @Test
    public void testUpdate(){
        Customer customer = new Customer();
        customer.setName("Tom");

        kieSession.insert(customer);
        kieSession.fireAllRules();

        System.out.println(customer);
    }

    @Test
    public void testRetract(){
        Customer customer = new Customer();
        customer.setName("Tony");

        kieSession.insert(customer);
        kieSession.fireAllRules();

        System.out.println(customer);
    }

}
