package top.figo.rule;

import org.drools.core.base.RuleNameEqualsAgendaFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.util.Date;

/**
 * @Author Figo
 * @Date 2022/1/22 19:23
 */
public class AttributeRuleTest {

    private KieServices kieServices;
    private KieContainer kieContainer;
    private KieSession kieSession;

    @Before
    public void initialKie(){
        System.setProperty("drools.dateformat", "yyyy-MM-dd");
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
    public void testDateEffective(){
        System.out.println(new Date());
        kieSession.fireAllRules();
    }

    @Test
    public void testAgendaGroup(){
        //只给agenda_02设置焦点
        kieSession.getAgenda().getAgendaGroup("agenda_02").setFocus();
        kieSession.getAgenda().getAgendaGroup("agenda_01").setFocus();
        kieSession.fireAllRules();
    }

    @Test
    public void testTimer() throws InterruptedException {

        System.out.println(new Date());
        new Thread(new Runnable() {
            @Override
            public void run() {
                kieSession.fireUntilHalt();
            }
        }).start();
        Thread.sleep(10000);
        kieSession.halt();
    }
}
