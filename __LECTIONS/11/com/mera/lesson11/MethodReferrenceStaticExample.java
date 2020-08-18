package com.mera.lesson11;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class MethodReferrenceStaticExample {

    public interface NumberOperation {
        int invoke(int one, int another);
    }

    public static void main(String[] args) {
        // static method referrence

        NumberOperation plusAnonymous = new NumberOperation() {
            @Override
            public int invoke(int one, int another) {
                return summ(one, another);
            }
        };

        NumberOperation plusLambda = (one, two) -> summ(one, two);


        NumberOperation plus = MethodReferrenceStaticExample::summ;
        NumberOperation minus = MethodReferrenceStaticExample::diff;

        final Calc calc = new Calc();
        calc.addOperation("sum", plus);
        calc.addOperation("diff", minus);
        calc.addOperation("multiply", MethodReferrenceStaticExample::multiply );

        System.out.println(calc.calc("multiply", 2, 21));
        System.out.println(calc.calc("sum", 2, 21));
        System.out.println(calc.calc("diff", 2, 21));


    }



     static class Calc {
         Map<String, NumberOperation> operations = new HashMap<>();

         public void addOperation(String name, NumberOperation operation) {
             operations.put(name, operation);
         }


         public  int calc(String operationName, int a, int b) {
             return operations.get(operationName).invoke(a, b);
         }
     }

    static int summ(int op1, int op2) {
        return op1 + op2;
    }
    static int diff(int op1, int op2) {
        return op1 - op2;
    }

    static int multiply(int op1, int op2) {
        return op1 * op2;
    }

}
