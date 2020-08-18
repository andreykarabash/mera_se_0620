package com.mera.lesson6;

import java.util.Objects;

public class Pair<T, S> {
    private T firstElement;
    private S secondElement;

    public Pair(T firstElement, S secondElement) {
        this.firstElement = firstElement;
        this.secondElement = secondElement;
    }

    public T getFirstElement() {
        return firstElement;
    }

    public S getSecondElement() {
        return secondElement;
    }


    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Pair)) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        Pair<?, ?> pairObj = (Pair<?, ?>) obj;
        return (Objects.equals(firstElement, pairObj.firstElement) && Objects.equals(secondElement, pairObj.secondElement));
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstElement, secondElement);
    }
}