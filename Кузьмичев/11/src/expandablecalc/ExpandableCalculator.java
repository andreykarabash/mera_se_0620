package expandablecalc;

public class ExpandableCalculator {
    public static void main(String[] args) {
        Calculator calc = new Calculator();
        calc.addOperation("add", (a, b) -> a + b);
        System.out.printf("add result: %f\n", calc.calculate("add", 2., 3.));

        calc.addOperation("sub", (a, b) -> a - b);
        System.out.printf("sub result: %f\n", calc.calculate("sub", 2., 3.));

        calc.addOperation("mul", (a, b) -> a * b);
        System.out.printf("mul result: %f\n", calc.calculate("mul", 2., 3.));

        calc.addOperation("div", (a, b) -> a / b);
        System.out.printf("div result: %f\n", calc.calculate("div", 2., 3.));

        calc.addOperation("pow", Math::pow);
        System.out.printf("pow result: %f\n", calc.calculate("pow", 2., 3.));

        calc.addOperation("root", (a, b) -> Math.pow(a, 1/b));
        System.out.printf("root result: %f\n", calc.calculate("root", 2., 3.));
    }
}
