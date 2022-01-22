package top.figo.entity;

import java.util.List;

/**
 * @Author Figo
 * @Date 2022/1/22 16:43
 */
public class Customer {

    private String name;
    private List<Order> orderList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "name='" + name + '\'' +
                ", orderList=" + orderList +
                '}';
    }
}
