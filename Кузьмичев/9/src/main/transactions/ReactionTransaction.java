/*****************************************************************************************
 * File: ReactionTransaction.java
 * Purpose: Class of transactions that send to scene decisions about received transactions
 * Notice: (c) 2020 Nikolay Kuzmichev. All rights reserved.
 *****************************************************************************************/

package main.transactions;

public class ReactionTransaction extends InfoTransaction {
    private Transaction transaction;
    private Boolean isExecutedTransaction;

    public ReactionTransaction(String message, Transaction transaction, Boolean isExecuted) {
        super(message);
        this.transaction = transaction;
        this.isExecutedTransaction = isExecuted;
    }

    public Boolean getExecuted() {
        return isExecutedTransaction;
    }

    public Transaction getErrorTransaction() {
        return transaction;
    }
}
