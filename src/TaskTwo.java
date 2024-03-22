//package javaone.sem4.market;

import buyerController.Buyer;
import buyerController.BuyerController;
import creator.Creator;
import exceptions.*;
import product.Product;
import product.ProductController;

import java.util.*;

public class TaskTwo {
    public static final int QUANTITY_PURCHASE = 3;
    public static final int MAX_QUANTITY = 3;

    public static final int POSSIBILITI_OF_QUANTITY_EXCEPTION = 1;


    public static void main(String[] args) {
        Market market;
        Creator creator = new Creator();
        int randomise, ordersId, randomiseTypeProduct;
        Product product;
        Buyer buyer;
        Map<Product,Integer> purchase;
        ArrayList<Map<Product,Integer>> purchases = new ArrayList<>();
        creator.createBuyer();
        creator.createProducts();
        market = new Market(creator.getBuyers(),creator.getProducts());

        try {
            for (int i = 0; i < QUANTITY_PURCHASE; i++) {
                buyer = market.getBuyers().get(i);//take buyer from market
                ordersId = market.createOrder(buyer);// create new order for buyer
                randomise = new Random().nextInt(MAX_QUANTITY)-
                        POSSIBILITI_OF_QUANTITY_EXCEPTION+i; //random QUANTITY products
                do {
                    randomiseTypeProduct =new Random().nextInt(market.getProducts().size()+1);//to fill the order
                } while (randomiseTypeProduct<1);
                for (int j = 0; j <randomiseTypeProduct; j++) {
                    product = market.getProducts().get(j);
                    market.addProductToOrder(ordersId,product,randomise);
                }// fill the order
                purchase = new HashMap<>(market.buy(ordersId));
               purchases.add(purchase);
               System.out.println(i+1 + " purchases are completed");
            }
        } catch (UserNotFoudException e){
            System.out.println(e.getMessage());
        } catch (ProductNotFoundException e){
            System.out.println(e.getMessage());
        } catch (QuantityIsNegativeException e){
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }

        System.out.println("In total, " + purchases.size() +" purchases were made");
        for (Map<Product,Integer> purchaseTemporary: purchases) {
            System.out.println(purchaseTemporary);
        }

        System.out.println("===========================================");
    }


}
