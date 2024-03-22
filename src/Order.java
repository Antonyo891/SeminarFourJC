import buyer.Buyer;
import product.Product;

import java.util.HashMap;
import java.util.Map;

public class Order implements Comparable<Order> {

    private int id;
    private Buyer buyer;
    private Holidays holiday;
    private OrderStatus orderStatus;
    private Map<Product, Integer> products;

    protected Order(int id,Buyer buyer,Holidays holiday) {
        this.id = id;
        this.buyer = buyer;
        this.orderStatus = OrderStatus.AT_WORK;
        products = new HashMap<>();
        this.holiday = holiday;
    }
    protected Order(int id,Buyer buyer) {
        this(id, buyer, Holidays.AN_ORDINARY_DAY);
    }

    public Order(Order order) {
        this.id = order.getId();
        this.buyer = order.getBuyer();
        this.holiday = order.getHoliday();
        this.orderStatus = order.getOrderStatus();
        this.products = order.getProducts();
    }

    public int getId() {
        return id;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Buyer getBuyer() {
        return buyer;
    }

    public void add (Product product, int quantity) {
        products.put(product, quantity);
    }

    public Map<Product, Integer> getProducts() {
        return products;
    }

    public Holidays getHoliday() {
        return holiday;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    @Override
    public int compareTo(Order o) {
        return this.id - o.getId();
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", buyer=" + buyer +
                ", holiday=" + holiday +
                ", orderStatus=" + orderStatus +
                ", products=" + products +
                '}';
    }
}
