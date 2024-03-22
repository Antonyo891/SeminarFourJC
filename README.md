## Урок 4. Обработка исключений <br>
##### 1. Доделать задания про интернет-магазин. Обработать исключения, показывать "говорящие" сообщения о том, что пошло не так.
[BuyerController](https://github.com/Antonyo891/SeminarFourJC/blob/master/src/buyerController/BuyerController.java), [ProductController](https://github.com/Antonyo891/SeminarFourJC/blob/master/src/product/ProductController.java) для работы с продуктами и покупателями
[Creator](https://github.com/Antonyo891/SeminarFourJC/blob/master/src/creator/Creator.java) для создания продуктов и покупателей через их контроллеры в нем же ловятся созданные исключения.
Добавил Enum [OrderStatus](https://github.com/Antonyo891/SeminarFourJC/blob/master/src/OrderStatus.java)
Точка входа [TaskTwo](https://github.com/Antonyo891/SeminarFourJC/blob/master/src/TaskTwo.java)
```
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
```
##### 2. В класс покупателя добавить [перечисление](https://github.com/Antonyo891/SeminarFourJC/blob/master/src/buyer/Gender.java) с гендерами, добавить в [объекты покупателей](https://github.com/Antonyo891/SeminarFourJC/blob/master/src/buyer/Buyer.java) свойство «пол» со значением созданного перечисления. Добавить геттеры, сеттеры.

##### 3. Добавить перечисление с праздниками (нет праздника, Новый Год, 8 марта, 23 февраля).
[Holidays](https://github.com/Antonyo891/SeminarFourJC/blob/master/src/Holidays.java)
##### 4. В метод оформления заказа добавить параметр - дата заказа (можно сразу использовать значение из перечисления), при создании заказа проверять значение перечисления и пол покупателя. Делать скидку мужчинам - на 23 февраля, женщинам на 8 марта в размере 15%, всем на Новый год в размере 20%. В заказе указывать общую сумму со скидкой.
[Market](https://github.com/Antonyo891/SeminarFourJC/blob/master/src/Market.java)
метод buy() оформаляет покупку заказа
```
public Map<Product,Integer> buy(int orderId){
        if (orders.isEmpty()) {
            System.out.println("List of orders is empty");
            return null;
        }
        for (Order order: orders) {
            double totalSum;
            if (orderId==order.getId()) {
                order.setOrderStatus(OrderStatus.COMPLETED);
                System.out.print(order.getBuyer().getName() +
                        " ready to complete the order id= " +
                        + order.getId() + ". ");
                System.out.println(order);
                totalSum = getTotalPrice(order);
                System.out.println("The buy was completed successfully." +
                        " Total SUM = " + totalSum +
                        ". ");
                return new HashMap<>(order.getProducts());
            }
        }
        System.out.println("This order was not found");
        return null;
```
Метод getTotalPrice(order) считает конечную сумму в зависимости от праздника и пола покупателя
```
public double getTotalPrice(Order order){
        double totalPrice = getPrice(order);
        if (order.getHoliday()==Holidays.AN_ORDINARY_DAY) return totalPrice;
        if (order.getHoliday()==Holidays.NEW_YEAR) {
            System.out.println("HAPPY NEW YEAR. YOUR "+ NEW_YEAR_DISCOUNT +
                    "% discount% = " + totalPrice*(NEW_YEAR_DISCOUNT*1.00/100));
            return totalPrice * (1 - NEW_YEAR_DISCOUNT*1.00/100);
        }
        if ((order.getHoliday()==Holidays.MANS_DAY)&&(order.getBuyer().getGender()== Gender.MAN)) {

            System.out.println("HAPPY MANS DAY. YOUR" + MANS_WOMANS_DISCOUNT + "% discount% = " +
                    totalPrice*(MANS_WOMANS_DISCOUNT*1.00/100));
            return totalPrice*(1 -MANS_WOMANS_DISCOUNT*1.00/100);
        }
        if ((order.getHoliday()==Holidays.WOMANS_DAY)&&(order.getBuyer().getGender()== Gender.WOMAN)) {

            System.out.println("HAPPY WOMANS DAY. YOUR " + MANS_WOMANS_DISCOUNT + "% discount% = " +
                    totalPrice*(MANS_WOMANS_DISCOUNT*1.00/100));
            return totalPrice*(1 -MANS_WOMANS_DISCOUNT*1.00/100);
        }
        return totalPrice;
    }
```
##### (*) любые доработки в магазин на Ваше усмотрение - описать в readme либо в тексте к ПЗ
