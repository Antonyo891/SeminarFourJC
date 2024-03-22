package product;

public class Product {
    private static final int NOT_SET_ID = -1;
    private String title;
    private int price;

    private int id;

    Product(int id, String title, int price) {
        this.title = title;
        this.price = price;
        this.id = id;
    }

    Product(Product product) {
        this(product.getId(), product.getTitle(), product.getPrice());
    }

    public String getTitle() {
        return title;
    }

    void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    void setPrice(int price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    void setId(int newId){
        this.id = newId;
    }
    @Override
    public String toString() {
        return "product.Product{" +
                "title='" + title + '\'' +
                ", price=" + price +
                '}';
    }
}
