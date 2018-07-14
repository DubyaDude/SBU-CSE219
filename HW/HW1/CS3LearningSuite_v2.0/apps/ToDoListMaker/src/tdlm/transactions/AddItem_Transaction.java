package tdlm.transactions;

import jtps.jTPS_Transaction;
import tdlm.data.ToDoData;
import tdlm.data.ToDoItemPrototype;

/**
 *
 * @author McKillaGorilla
 */
public class AddItem_Transaction implements jTPS_Transaction {
    ToDoData data;
    ToDoItemPrototype itemToAdd;
    
    public AddItem_Transaction(ToDoData initData, ToDoItemPrototype initNewItem) {
        data = initData;
        itemToAdd = initNewItem;
    }

    @Override
    public void doTransaction() {
        data.addItem(itemToAdd);        
    }

    @Override
    public void undoTransaction() {
        data.removeItem(itemToAdd);
    }
}
