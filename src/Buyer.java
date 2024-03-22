import java.util.Objects;

public class Buyer {
    private String name;
    private int age;
    private String phone;

    public Buyer(String name, int age, String phone) {
        this.name = name;
        this.age = age;
        this.phone = phone;
    }

    public Buyer(Buyer buyer) {
        this(buyer.getName(), buyer.getAge(), buyer.getPhone());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Buyer buyer = (Buyer) o;
        return age == buyer.age && Objects.equals(name, buyer.name) && Objects.equals(phone, buyer.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, age, phone);
    }
}
