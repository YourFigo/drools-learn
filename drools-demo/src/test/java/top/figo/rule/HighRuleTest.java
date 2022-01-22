package top.figo.rule;

import org.drools.core.base.RuleNameStartsWithAgendaFilter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.QueryResults;
import top.figo.entity.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Figo
 * @Date 2022/1/22 23:51
 */
public class HighRuleTest {

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
    public void testGlobalVariable(){
        List<String> list = new ArrayList<>();
        kieSession.setGlobal("myGlobalList", list);
        kieSession.fireAllRules(new RuleNameStartsWithAgendaFilter("rule_global_variable"));

        System.out.println("global variable return from rules");

        // 操作返回的 global variable
        list.forEach(System.out::println);
        System.out.println("Add other member of global variable");
        list.add("Good bye");
        list.forEach(System.out::println);

    }

    @Test
    public void testQuery(){
        Person person1 = new Person();
        person1.setName("Tom");
        person1.setAge(19);

        Person person2 = new Person();
        person2.setName("Tony");
        person2.setAge(28);

        kieSession.insert(person1);
        kieSession.insert(person2);

        kieSession.fireAllRules(new RuleNameStartsWithAgendaFilter("rule_query"));
        QueryResults queryAge = kieSession.getQueryResults("query_age");
        queryAge.forEach(queryResultsRow -> {
            Person person = (Person) queryResultsRow.get("$person");
            System.out.println(person);
        });
    }

    @Test
    public void testQueryParam() {
        Person person1 = new Person();
        person1.setName("Tom");
        person1.setAge(19);

        Person person2 = new Person();
        person2.setName("Tony");
        person2.setAge(28);

        kieSession.insert(person1);
        kieSession.insert(person2);

        kieSession.fireAllRules(new RuleNameStartsWithAgendaFilter("rule_query"));
        QueryResults queryAgeName = kieSession.getQueryResults("query_age_name", "Toby");
        queryAgeName.forEach(queryResultsRow -> {
            Person person = (Person) queryResultsRow.get("$person");
            System.out.println(person);
        });
    }

    @Test
    public void testFunction() {
        Person person = new Person();
        person.setName("Wilson");
        person.setAge(35);

        kieSession.insert(person);
        kieSession.fireAllRules(new RuleNameStartsWithAgendaFilter("rule_function"));

    }
}
