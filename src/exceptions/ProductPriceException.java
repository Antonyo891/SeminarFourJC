package exceptions;

public class ProductPriceException extends Exception {
    private int price;

    public ProductPriceException(String message) {
        super(message);
    }

    public ProductPriceException(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }
}
