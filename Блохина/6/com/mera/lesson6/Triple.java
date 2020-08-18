package com.mera.lesson6;

import java.util.Objects;

public class Triple <T, S, U> {
    private T firstElement;
    private S secondElement;
    private U thirdElement;

    public Triple(T first, S secondElement, U third) {
        this.firstElement = first;
        this.secondElement = secondElement;
        this.thirdElement = third;
    }

    public T getFirstElement() {
        return firstElement;
    }

    public S getSecondElement() {
        return secondElement;
    }

    public U getThirdElement() {
        return thirdElement;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Triple)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Triple<?, ?, ?> tripleObj = (Triple<?, ?, ?>) obj;
        return (Objects.equals(firstElement, tripleObj.firstElement) && Objects.equals(secondElement, tripleObj.secondElement) && Objects.equals(thirdElement, tripleObj.thirdElement));
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstElement, secondElement, thirdElement);
    }
}