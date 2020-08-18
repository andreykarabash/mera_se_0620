/******************************************************************
 * File: InfoTransaction.java
 * Purpose: Class of transactions which send to scene info messages
 * Notice: (c) 2020 Nikolay Kuzmichev. All rights reserved.
 ******************************************************************/

package main.transactions;

public class InfoTransaction extends Transaction {
    private String message;

    public InfoTransaction(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
