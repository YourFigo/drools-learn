package top.figo.springbootdrools.entity;

/**
 * @Author Figo
 * @Date 2022/1/19 23:28
 */
public class Order {
    //订单原始价格，即优惠前价格
    private Double originalPrice;
    //订单真实价格，即优惠后价格
    private Double realPrice;

    public Double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(Double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public Double getRealPrice() {
        return realPrice;
    }

    public void setRealPrice(Double realPrice) {
        this.realPrice = realPrice;
    }

    @Override
    public String toString() {
        return "Order{" +
                "originalPrice=" + originalPrice +
                ", realPrice=" + realPrice +
                '}';
    }
}
