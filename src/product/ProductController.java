package product;

import exceptions.ProductPriceException;
import exceptions.TittleIsEmptyException;

import java.util.ArrayList;

public class ProductController {
    private ArrayList<Product> products;
    private Product product;


    public ProductController() {
        this.products = new ArrayList<>();
    }

    public ProductController(ArrayList<Product> products) {
        this.products = products;
    }

    public boolean addProduct(Product product){
        if (products.contains(product)) System.out.println("Product already EXIST");
        if (product.getId()<1) {
            product.setId(newProductId());
            products.add(product);
            return true;
        }
        return false;
    }

    public Product createProduct(String title, int price)
            throws ProductPriceException, TittleIsEmptyException {
        if (price<=0) throw new ProductPriceException(price);
        if (title.isEmpty()) throw  new TittleIsEmptyException(
                "The tittle should not be empty.\nThere are still " +
                        products.size() + " products in the controller."
        );
        for (Product product: products) {
            if (title.equalsIgnoreCase(product.getTitle())) {
                if (product.getPrice()==price) {
                    System.out.println("Product already EXIST");
                    return product;
                } else {
                    System.out.println("The price of the product" +
                           product.getTitle() + "has been changed from " +
                            product.getPrice() + " to " + price);
                    product.setPrice(price);
                    return null;
                }
            }
        }
        product = new Product(newProductId(),title,price);
        products.add(product);
        System.out.println("There are" + products.size() + " products in the controller.");
        return product;
    }

    private int newProductId(){
        if (this.products.isEmpty()) return 1;
        int newProductId = 1;
        for (Product product: products) {
            if (newProductId<=product.getId()) newProductId=product.getId();
        }
        return newProductId+1;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }
}
