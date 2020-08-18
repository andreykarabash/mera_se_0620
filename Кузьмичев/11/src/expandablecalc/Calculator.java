package expandablecalc;

import java.util.HashMap;
import java.util.Map;

public class Calculator {
    private Map<String, Operation> operations = new HashMap<>();

    Double calculate(String operationName, Double number1, Double number2) {
        if (operations.containsKey(operationName)) {
            return operations.get(operationName).doOperation(number1, number2);
        }
        throw new IllegalArgumentException(String.format("Operation \"%s\" is not found", operationName));
    }

    void addOperation(String operationName, Operation implementation) {
        operations.put(operationName, implementation);
    }
}