package jtps;

import java.util.ArrayList;

/**
 * jTPS.java
 * 
 * This class is used for managing an abstract transaction processing
 * system for the purpose of managing an undo/redo system for an
 * application. Note that one must specify all work done via custom
 * transactions.
 * 
 * @author THE McKilla Gorilla (accept no imposters)
 * @version 2.0
 */
public class jTPS {
    // THE TRANSACTION STACK
    private ArrayList<jTPS_Transaction> transactions = new ArrayList();
    
    // KEEPS TRACK OF WHERE WE ARE IN THE STACK, THUS AFFECTING WHAT
    // TRANSACTION MAY BE DONE OR UNDONE AT ANY GIVEN TIME
    private int mostRecentTransaction = -1;
    
    /**
     * This function adds the transaction argument to the top of
     * the transaction processing system stack and then executes it. Note that it does
     * When this method has completed transaction will be at the top 
     * of the stack, it will have been completed, and the counter have
     * been moved accordingly.
     * 
     * @param transaction The custom transaction to be added to
     * the transaction processing system stack and executed.
     */
    public void addTransaction(jTPS_Transaction transaction) {
        // ARE THERE OLD UNDONE TRANSACTIONS ON THE STACK THAT FIRST
        // NEED TO BE CLEARED OUT, i.e. ARE WE BRANCHING?
        if ((mostRecentTransaction < 0)|| (mostRecentTransaction < (transactions.size()-1))) {
            for (int i = transactions.size()-1; i > mostRecentTransaction; i--) {
                transactions.remove(i);
            }
        }

        // AND NOW ADD THE TRANSACTION
        transactions.add(transaction);

        // AND EXECUTE IT
        doTransaction();        
    }

    /**
     * This function gets the most recent transaction and executes it,
     * moving the TPS counter. Note that this may be the transaction
     * at the top of the TPS stack or somewhere in the middle (i.e. a redo).
     */
    public void doTransaction() {
        if (hasTransactionToRedo()) {
            jTPS_Transaction transaction = transactions.get(mostRecentTransaction+1);
            transaction.doTransaction();
            mostRecentTransaction++;
        }
    }

    /**
     * This function gets the most recently executed transaction on the 
     * TPS stack and undoes it, moving the TPS counter accordingly.
     */
    public void undoTransaction() {
        if (hasTransactionToUndo()) {
            jTPS_Transaction transaction = transactions.get(mostRecentTransaction);
            transaction.undoTransaction();
            mostRecentTransaction--;
        }
    }

    /**
     * This method clears all transactions from the TPS stack
     * and resets the counter that keeps track of the location
     * of the top of the stack.
     */
    public void clearAllTransactions() {
        // REMOVE ALL THE TRANSACTIONS
        transactions.clear();
        
        // MAKE SURE TO RESET THE LOCATION OF THE
        // TOP OF THE TPS STACK TOO
        mostRecentTransaction = -1;        
    }
    
    /**
     * Accessor method that returns the number of transactions currently
     * on the transaction stack. This includes those that may have been
     * done, undone, and redone.
     * 
     * @return The number of transactions currently in the transaction stack.
     */
    public int getSize() {
        return this.transactions.size();
    }
    
    /**
     * This method returns the number of transactions currently in the
     * transaction stack that can be redone, meaning they have been added
     * and done, and then undone.
     * 
     * @return The number of transactions in the stack that can be redone.
     */
    public int getRedoSize() {
        return getSize() - mostRecentTransaction - 1;
    }

    /**
     * This method returns the number of transactions currently in the 
     * transaction stack that can be undone.
     * 
     * @return The number of transactions in the transaction stack that
     * can be undone.
     */
    public int getUndoSize() {
        return mostRecentTransaction + 1;
    }
    
    /**
     * This method tests to see if there is a transaction on the stack that
     * can be undone at the time this function is called.
     * 
     * @return true if an undo operation is possible, false otherwise.
     */
    public boolean hasTransactionToUndo() {
        return mostRecentTransaction >= 0;
    }
    
    /**
     * This method tests to see if there is a transaction on the stack that
     * can be redone at the time this function is called.
     * 
     * @return true if a redo operation is possible, false otherwise.
     */
    public boolean hasTransactionToRedo() {
        return mostRecentTransaction < (transactions.size()-1);
    }
        
    /**
     * This method builds and returns a textual summary of the current
     * Transaction Processing System, this includes the toString of
     * each transaction in the stack.
     * 
     * @return A textual summary of the TPS.
     */
    public String toString() {
        String text = "--Number of Transactions: " + transactions.size() + "\n";
        text += "--Current Index on Stack: " + mostRecentTransaction + "\n";
        text += "--Current Transaction Stack:\n";
        for (int i = 0; i <= mostRecentTransaction; i++) {
            jTPS_Transaction jT = transactions.get(i);
            text += "----" + jT.toString() + "\n";
        }
        return text;
    }
}