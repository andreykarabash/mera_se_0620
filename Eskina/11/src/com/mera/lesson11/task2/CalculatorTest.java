package com.mera.lesson11.task2;

public class CalculatorTest {
    public final static String ADD = "сложение";
    public final static String SUBTRACT = "вычитание";
    public final static String MULTIPLY = "умножение";
    public final static String POW = "возведение в степень";
    public final static String DIVIDE = "деление";
    public final static String ROOT = "извлечение корня";

    public static void main(String[] args) {
        Calculator calc = new Calculator();
        Operation addition = (x, y) -> x + y;
        Operation subtraction = (x, y) -> x - y;
        Operation multiplication = (x, y) -> x * y;
        Operation pow = (x, y) -> Math.pow(x, y);
        Operation division = (x, y) -> x / y;
        Operation rootFunction = (x, y)-> Math.pow(x, 1.0 / y);

        calc.addOperation(ADD, addition);
        calc.addOperation(SUBTRACT, subtraction);
        calc.addOperation(MULTIPLY, multiplication);
        calc.addOperation(POW, pow);
        calc.addOperation(DIVIDE, division);
        calc.addOperation(ROOT, rootFunction);

        double x = 4.0;
        double y = 2.0;
        calc.calculate(ADD,x, y );
        calc.calculate(SUBTRACT, x, y);
        calc.calculate(MULTIPLY, x, y);
        calc.calculate(POW, x, y);
        calc.calculate(DIVIDE, x, y);
        calc.calculate(ROOT, x, y);

        calc.calculate(ROOT, 27.0,3.0);
        calc.calculate(ADD, 27.0,3.0);
    }
}
