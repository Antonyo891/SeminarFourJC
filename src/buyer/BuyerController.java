package buyer;

import exceptions.NameIsEmptyException;
import exceptions.AgeNegativeException;

import java.util.ArrayList;

public class BuyerController {
private Buyer buyer;
private ArrayList<Buyer> buyers;

    public BuyerController(ArrayList<Buyer> buyers) {
        this.buyers = buyers;
    }

    public BuyerController() {
        this.buyers = new ArrayList<>();
    }

    public boolean addBuyer(Buyer buyer)
            throws AgeNegativeException, NameIsEmptyException {
        if (buyers.contains(buyer)) {
            System.out.println("Buyer already in the Market");
            return false;
        }
        return addBuyer(buyer.getId(),buyer.getName(),
                buyer.getAge(),buyer.getPhone(),buyer.getGender());
    }

    public boolean addBuyer(int idBuyer, String name, int age, String phone,Gender gender)
            throws AgeNegativeException, NameIsEmptyException {
        if (age<0) throw new AgeNegativeException(age);
        if (name.isEmpty()) throw  new NameIsEmptyException(
                "The name should not be empty.\nThere are still " +
                        buyers.size() + " buyers in the Market."
        );
        for (Buyer buyer: buyers) {
            if (idBuyer == buyer.getId()) {
                System.out.println("Buyer with Id " + idBuyer +
                        " already EXIST");
                return false;
            }
        }
        buyers.add(new Buyer(idBuyer, name, age, phone, gender));
        System.out.println("There are" + buyers.size() + " buyers in the Market.");
        return true;
    }

    public Buyer createBuyer(String name, int age, String phone, Gender gender)
            throws AgeNegativeException, NameIsEmptyException {
        int buyerId = newBuyerId();
        addBuyer(buyerId,name,age,phone, gender);
        return getBuyer(buyerId);
    }

    private int newBuyerId(){
        if (this.buyers.isEmpty()) return 1;
        int newBuyerId = 1;
        for (Buyer buyer: buyers) {
            if (newBuyerId<=buyer.getId()) newBuyerId=buyer.getId();
        }
        return newBuyerId+1;
    }

    public ArrayList<Buyer> getBuyers() {
        return buyers;
    }

    public void setGender(int buyerId, Gender gender){
        Buyer buyer = getBuyer(buyerId);
        if (buyer.getGender()==Gender.UNSPECIFIED)
            buyer.setGender(gender);
        else System.out.println("Gender for " +
                buyer.getName() + " already set by nature("+
                buyer.getGender() + ").");
    }

    public Buyer getBuyer(int buyerId){
        for (Buyer buyer: buyers) {
            if (buyer.getId()== buyerId)
                return buyer;
        }
        System.out.println("Buyer is not in the Market");
        return null;
    }

}
