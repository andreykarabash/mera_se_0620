package lesson12;

public class Packaging {
    public static void main(String[] args) {
        String nullStr = null;
        System.out.println(SomeUtilityClass.getStringLength(nullStr));


        String another = "Hello, String!";
        System.out.println(SomeUtilityClass.getStringLength(another));
    }
}
