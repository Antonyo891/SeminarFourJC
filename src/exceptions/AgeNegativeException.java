package exceptions;

public class AgeNegativeException extends Exception{
    private int age;

    public AgeNegativeException(int age) {
        this.age = age;
    }

    public int getAge() {
        return age;
    }
}
