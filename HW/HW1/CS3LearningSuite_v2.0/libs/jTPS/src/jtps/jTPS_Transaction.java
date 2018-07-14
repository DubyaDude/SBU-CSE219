package jtps;

/**
 * jTPS_Transaction.java
 * 
 * This interface provides the structure that all transactions to be
 * managed by jTPS must follow. Each transaction must have defined
 * behavior for do and undo. Note that when defining custom transaction
 * classes one needs to make sure the constructor is given the objects
 * it will need to manipulate at the time the do and undo methods
 * are called.
 * 
 * @author THE McKilla Gorilla (accept no imposters)
 * @version 2.0
 */
public interface jTPS_Transaction {
    /**
     * This method is called by jTPS when a transaction is executed.
     */
    public void doTransaction();
    
    /**
     * This method is called by jTPS when a transaction is undone.
     */
    public void undoTransaction();
}