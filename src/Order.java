import buyerController.Buyer;
import product.Product;

import java.util.HashMap;
import java.util.Map;

public class Order implements Comparable<Order> {

    private int id;
    private Buyer buyer;

    private OrderStatus orderStatus;
    private Map<Product, Integer> products;

    protected Order(int id,Buyer buyer) {
        this.id = id;
        this.buyer = buyer;
        this.orderStatus = OrderStatus.AT_WORK;
        products = new HashMap<>();
    }

    public Order(Order order) {
        this(order.getId(), order.getBuyer());
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

    @Override
    public int compareTo(Order o) {
        return this.id - o.getId();
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", buyer=" + buyer +
                ", orderStatus=" + orderStatus +
                ", products=" + products +
                '}';
    }
}
