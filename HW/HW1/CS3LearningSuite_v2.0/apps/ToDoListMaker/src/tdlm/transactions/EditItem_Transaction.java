package tdlm.transactions;

import jtps.jTPS_Transaction;
import tdlm.data.ToDoData;
import tdlm.data.ToDoItemPrototype;

/**
 *
 * @author ushaf
 */
public class EditItem_Transaction implements jTPS_Transaction{
    ToDoData data;
    
    public EditItem_Transaction() {

    }

    @Override
    public void doTransaction() {
       
    }

    @Override
    public void undoTransaction() {

    }
}