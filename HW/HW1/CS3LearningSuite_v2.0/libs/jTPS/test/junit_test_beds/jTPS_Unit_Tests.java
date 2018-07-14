package junit_test_beds;

import demo.AddToNum_Transaction;
import demo.Num;
import jtps.jTPS;
import junit.framework.Assert;
import org.junit.Test;

/**
 * jTPS_Unit_Tests.java
 * 
 * This file provides a test bed for the jTPS framework.
 * 
 * @author McKilla Gorilla
 * @version 2.0
 */
public class jTPS_Unit_Tests {
    /**
     * This JUnit test is for testing the adding of transactions.
     */
    @Test
    public void testAdd() {
        // WE'LL JUST USE A SIMPLE NUM FOR TESTING
        jTPS tps = new jTPS();
        Num num = new Num();
        Assert.assertEquals(num.getNum(), 0);
        
        // ADD 5 TRANSACTION
        tps.addTransaction(new AddToNum_Transaction(num, 5));
        Assert.assertEquals(5, num.getNum());
        Assert.assertEquals(1, tps.getSize());
        Assert.assertEquals(0, tps.getRedoSize());
        Assert.assertEquals(1, tps.getUndoSize());
        
        // ADD 10 TRANSACTION
        tps.addTransaction(new AddToNum_Transaction(num, 10));
        Assert.assertEquals(15, num.getNum());
        Assert.assertEquals(2, tps.getSize());
        Assert.assertEquals(0, tps.getRedoSize());
        Assert.assertEquals(2, tps.getUndoSize());
        
        // ADD 15 TRANSACTION
        tps.addTransaction(new AddToNum_Transaction(num, 20));
        Assert.assertEquals(35, num.getNum());
        Assert.assertEquals(3, tps.getSize());
        Assert.assertEquals(0, tps.getRedoSize());
        Assert.assertEquals(3, tps.getUndoSize());
    }

    /**
     * This JUnit test is for testing the undoing of transactions.
     */
    @Test
    public void testUndo() {
        // WE'LL JUST USE A SIMPLE NUM FOR TESTING
        jTPS tps = new jTPS();
        Num num = new Num();
        Assert.assertEquals(num.getNum(), 0);
        Assert.assertFalse(tps.hasTransactionToUndo());
        Assert.assertFalse(tps.hasTransactionToRedo());
        
        // ADD 3 TRANSACTIONS (5, 10, and 15)
        tps.addTransaction(new AddToNum_Transaction(num, 5));
        tps.addTransaction(new AddToNum_Transaction(num, 10));
        tps.addTransaction(new AddToNum_Transaction(num, 20));
        Assert.assertTrue(tps.hasTransactionToUndo());
        Assert.assertFalse(tps.hasTransactionToRedo());
        Assert.assertEquals(35, num.getNum());
        Assert.assertTrue(tps.hasTransactionToUndo());
        Assert.assertEquals(3, tps.getSize());
        Assert.assertEquals(0, tps.getRedoSize());
        Assert.assertEquals(3, tps.getUndoSize());
        
        // UNDO A TRANSACTION
        tps.undoTransaction();
        Assert.assertTrue(tps.hasTransactionToUndo());
        Assert.assertTrue(tps.hasTransactionToRedo());
        Assert.assertEquals(15, num.getNum());
        Assert.assertEquals(3, tps.getSize());
        Assert.assertEquals(1, tps.getRedoSize());
        Assert.assertEquals(2, tps.getUndoSize());
        
        // UNDO ANOTHER
        tps.undoTransaction();
        Assert.assertTrue(tps.hasTransactionToUndo());
        Assert.assertTrue(tps.hasTransactionToRedo());
        Assert.assertEquals(5, num.getNum());
        Assert.assertEquals(3, tps.getSize());
        Assert.assertEquals(2, tps.getRedoSize());
        Assert.assertEquals(1, tps.getUndoSize());
        
        // AND ANOTHER
        tps.undoTransaction();
        Assert.assertFalse(tps.hasTransactionToUndo());
        Assert.assertTrue(tps.hasTransactionToRedo());
        Assert.assertEquals(0, num.getNum());
        Assert.assertEquals(3, tps.getSize());
        Assert.assertEquals(3, tps.getRedoSize());
        Assert.assertEquals(0, tps.getUndoSize());
        
        // WE HAVE NO MORE TO UNDO SO THIS SHOULD DO NOTHING
        tps.undoTransaction();
        Assert.assertFalse(tps.hasTransactionToUndo());
        Assert.assertTrue(tps.hasTransactionToRedo());
        Assert.assertEquals(0, num.getNum());
        Assert.assertEquals(3, tps.getSize());
        Assert.assertEquals(3, tps.getRedoSize());
        Assert.assertEquals(0, tps.getUndoSize());
    }
    
    /**
     * This JUnit test is for testing the redoing of transactions.
     */
    @Test
    public void testRedo() {
        // WE'LL JUST USE A SIMPLE NUM FOR TESTING
        jTPS tps = new jTPS();
        Num num = new Num();
        Assert.assertEquals(num.getNum(), 0);
        
        // ADD 3 TRANSACTIONS (5, 10, and 15)
        tps.addTransaction(new AddToNum_Transaction(num, 5));
        tps.addTransaction(new AddToNum_Transaction(num, 10));
        tps.addTransaction(new AddToNum_Transaction(num, 20));
        Assert.assertTrue(tps.hasTransactionToUndo());
        Assert.assertFalse(tps.hasTransactionToRedo());
        Assert.assertEquals(35, num.getNum());
        Assert.assertEquals(3, tps.getSize());
        Assert.assertEquals(0, tps.getRedoSize());
        Assert.assertEquals(3, tps.getUndoSize());
        
        // UNDO A TRANSACTION AND THEN REDO IT
        tps.undoTransaction();
        tps.doTransaction();
        Assert.assertTrue(tps.hasTransactionToUndo());
        Assert.assertFalse(tps.hasTransactionToRedo());
        Assert.assertEquals(35, num.getNum());
        Assert.assertEquals(3, tps.getSize());
        Assert.assertEquals(0, tps.getRedoSize());
        Assert.assertEquals(3, tps.getUndoSize());
        
        // UNDO TWO TRANSACTIONS AND THEN REDO THEM
        tps.undoTransaction();
        tps.undoTransaction();
        tps.doTransaction();
        tps.doTransaction();
        Assert.assertTrue(tps.hasTransactionToUndo());
        Assert.assertFalse(tps.hasTransactionToRedo());
        Assert.assertEquals(35, num.getNum());
        Assert.assertEquals(3, tps.getSize());
        Assert.assertEquals(0, tps.getRedoSize());
        Assert.assertEquals(3, tps.getUndoSize());
        
        // UNDO ALL THREE TRANSACTIONS AND REDO THEM
        tps.undoTransaction();
        tps.undoTransaction();
        tps.undoTransaction();
        tps.doTransaction();
        tps.doTransaction();
        tps.doTransaction();
        Assert.assertTrue(tps.hasTransactionToUndo());
        Assert.assertFalse(tps.hasTransactionToRedo());
        Assert.assertEquals(35, num.getNum());
        Assert.assertEquals(3, tps.getSize());
        Assert.assertEquals(0, tps.getRedoSize());
        Assert.assertEquals(3, tps.getUndoSize());
        
        // UNDO THREE TRANSACTIONS AND REDO TWO
        tps.undoTransaction();
        tps.undoTransaction();
        tps.undoTransaction();
        tps.doTransaction();
        tps.doTransaction();
        Assert.assertTrue(tps.hasTransactionToUndo());
        Assert.assertTrue(tps.hasTransactionToRedo());
        Assert.assertEquals(15, num.getNum());
        Assert.assertEquals(3, tps.getSize());
        Assert.assertEquals(1, tps.getRedoSize());
        Assert.assertEquals(2, tps.getUndoSize());
        
        // UNDO ALL THREE TRANSACTIONS AND REDO FOUR, WHICH
        // SHOULD NOT PRODUCE AN ERROR BUT THE LAST
        // REDO SHOULD DO NOTHING
        tps.undoTransaction();
        tps.undoTransaction();
        tps.undoTransaction();
        tps.doTransaction();
        tps.doTransaction();
        tps.doTransaction();
        tps.doTransaction();
        Assert.assertTrue(tps.hasTransactionToUndo());
        Assert.assertFalse(tps.hasTransactionToRedo());
        Assert.assertEquals(35, num.getNum());
        Assert.assertEquals(3, tps.getSize());
        Assert.assertEquals(0, tps.getRedoSize());
        Assert.assertEquals(3, tps.getUndoSize());
    }    

    /**
     * This JUnit test is for testing clearing of transactions.
     */
    @Test
    public void testClear() {
        // WE'LL JUST USE A SIMPLE NUM FOR TESTING
        jTPS tps = new jTPS();
        Num num = new Num();
        Assert.assertEquals(num.getNum(), 0);
        
        // ADD 3 TRANSACTIONS (5, 10, and 15)
        tps.addTransaction(new AddToNum_Transaction(num, 5));
        tps.addTransaction(new AddToNum_Transaction(num, 10));
        tps.addTransaction(new AddToNum_Transaction(num, 20));
        Assert.assertEquals(35, num.getNum());
        Assert.assertEquals(3, tps.getSize());
        Assert.assertEquals(0, tps.getRedoSize());
        Assert.assertEquals(3, tps.getUndoSize());
                
        // CLEAR ALL THE TRANSACTIONS
        tps.clearAllTransactions();
        Assert.assertEquals(35, num.getNum());
        Assert.assertEquals(0, tps.getSize());
        Assert.assertEquals(0, tps.getRedoSize());
        Assert.assertEquals(0, tps.getUndoSize());
        
        // ADD 3 TRANSACTIONS (5, 10, and 15)
        tps.addTransaction(new AddToNum_Transaction(num, 5));
        tps.addTransaction(new AddToNum_Transaction(num, 10));
        tps.addTransaction(new AddToNum_Transaction(num, 20));
        Assert.assertEquals(70, num.getNum());
        Assert.assertEquals(3, tps.getSize());
        Assert.assertEquals(0, tps.getRedoSize());
        Assert.assertEquals(3, tps.getUndoSize());
                
        // CLEAR THEM ALL OUT AGAIN
        tps.clearAllTransactions();
        Assert.assertEquals(70, num.getNum());
        Assert.assertEquals(0, tps.getSize());
        Assert.assertEquals(0, tps.getRedoSize());
        Assert.assertEquals(0, tps.getUndoSize());
        
        // ADD 3 TRANSACTIONS (5, 10, and 15)
        tps.addTransaction(new AddToNum_Transaction(num, 5));
        tps.addTransaction(new AddToNum_Transaction(num, 10));
        tps.addTransaction(new AddToNum_Transaction(num, 20));
        Assert.assertEquals(105, num.getNum());
        Assert.assertEquals(3, tps.getSize());
        Assert.assertEquals(0, tps.getRedoSize());
        Assert.assertEquals(3, tps.getUndoSize());
    }
}