import buyerController.Buyer;
import buyerController.BuyerController;
import exceptions.ProductNotFoundException;
import exceptions.QuantityIsNegativeException;
import exceptions.UserNotFoudException;
import product.Product;
import product.ProductController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Market {

    private List<Buyer> buyers;
    private List<Product> products;
    private List<Order> orders;

    public Market(List<Buyer> buyers, List<Product> products, List<Order> orders) {
        this.buyers = buyers;
        this.products = products;
        this.orders = orders;
    }

    public Market(List<Buyer> buyers, List<Product> products) {
        this(buyers,products,new ArrayList<>());
    }

    public Market() {
        this(new ArrayList<>(),new ArrayList<>(),new ArrayList<>());
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
            if (order.getId()>=newId) newId=order.getId();
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
