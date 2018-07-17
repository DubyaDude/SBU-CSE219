package tdlm.transactions;

import jtps.jTPS_Transaction;
import tdlm.data.ToDoData;
import tdlm.data.ToDoItemPrototype;

/**
 *
 * @author ushaf
 */
public class MoveUpItem_Transaction implements jTPS_Transaction{
    ToDoData data;
    ToDoItemPrototype item;
    int indexToMove;
    
    public MoveUpItem_Transaction(ToDoData initData, ToDoItemPrototype selectedItem) {
        this.data=initData;
        indexToMove=data.getItemIndex(selectedItem);
    }

    @Override
    public void doTransaction() {
        data.moveItem(indexToMove, indexToMove-1);
    }

    @Override
    public void undoTransaction() {
        data.moveItem(indexToMove-1, indexToMove);
    }
}