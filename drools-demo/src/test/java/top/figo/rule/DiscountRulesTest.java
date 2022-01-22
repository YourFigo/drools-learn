package top.figo.rule;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import top.figo.entity.Order;

/**
 * @Author Figo
 * @Date 2022/1/19 23:51
 */

public class DiscountRulesTest {

    private KieServices kieServices;
    private KieContainer kieContainer;
    private KieSession kieSession;

    // Kie: Knowledge is everything, Jboss一系列项目的总称

    /**
     * drools规则引擎:
     * working memory, 工作内存
     * Rule Base, 规则库
     * Inference Engine, 推理引擎
     * 其中, Inference Engine 包括：
     * Pattern Matcher, 匹配器
     * Agenda, 议程
     * Execution Engine, 执行引擎
     */

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
    public void testDiscountRules(){
        // 构建Fact对象，规则引擎的working memory中用到的JavaBean
        Order order = new Order();
        order.setOriginalPrice(255.0);

        // 将数据交给规则引擎，规则引擎根据Fact进行匹配
        kieSession.insert(order);
        // 执行规则引擎，出发规则
        kieSession.fireAllRules();
        System.out.print(order);
    }

}
