import exceptions.ProductNotFoundException;
import exceptions.QuantityIsNegativeException;
import exceptions.UserNotFoudException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Market {

    private List<Buyer> buyers;
    private List<Product> products;
    private List<Order> orders;


    public Market() {
        buyers = new ArrayList<>();
        buyers.add(new Buyer("Tom", 45, "11111"));
        buyers.add(new Buyer("Bob", 26, "22222"));
        buyers.add(new Buyer("Jim", 53, "33333"));
        buyers.add(new Buyer("John", 40, "44444"));

        products = new ArrayList<>();
        products.add(new Product("Milk", 89));
        products.add(new Product("Bread", 26));
        products.add(new Product("Cheese", 125));

        orders = new ArrayList<>();
    }

    public int createOrder(Buyer buyer) throws UserNotFoudException {
        if (!buyers.contains(buyer)) throw new UserNotFoudException("user not found, " + buyer);
        int orderId = newOrderId();
        Order order = new Order(orderId,buyer);
        orders.add(order);
        return orderId;
    }

    private int newOrderId(){
        if (orders.isEmpty()) return 1;
        int newId = 1;
        for (Order order:orders) {
            if (order.getId()>newId) newId=order.getId();
        }
        return newId+1;
    }

    public void addProductToOrder(int orderId, Product product, int quantity)
            throws ProductNotFoundException, QuantityIsNegativeException {
        if (!products.contains(product)) throw new ProductNotFoundException("product not found");
        if (quantity <= 0) throw new QuantityIsNegativeException("quantity of product is negative");
        for (Order order: this.orders) {
            if (order.getId()==orderId) order.add(product, quantity);
        }
    }

    public Map<Product,Integer> buy(int orderId){
        if (orders.isEmpty()) {
            System.out.println("List of orders is empty");
            return null;
        }
        for (Order order: orders) {
            if (orderId==order.getId()) {
                order.setOrderStatus(OrderStatus.COMPLETED);
                System.out.println(order.getBuyer().getName() +
                        " ready to complete the order id= " +
                        + order.getId() + ". ");
                System.out.println("The buy was completed successfully");
                return new HashMap<>(order.getProducts());
            }
        }
        System.out.println("This order was not found");
        return null;
    }

    public List<Buyer> getBuyers() {
        return buyers;
    }

    public List<Product> getProducts() {
        return products;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public Order getOrder(int orderId){
        for (Order order:orders) {
            if(order.getId()==orderId) return order;
        }
        System.out.println("This order are not exist");
        return null;
    }
}
