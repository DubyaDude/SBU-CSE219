package tdlm.transactions;

import jtps.jTPS_Transaction;
import tdlm.data.ToDoData;
import tdlm.data.ToDoItemPrototype;

/**
 *
 * @author ushaf
 */
public class MoveDownItem_Transaction implements jTPS_Transaction{
    ToDoItemPrototype item;
    ToDoData data;
    int indexToMove;
    
    public MoveDownItem_Transaction(ToDoData initData, ToDoItemPrototype selectedItem) {
        data=initData;
        item=selectedItem;
        indexToMove=data.getItemIndex(item);
    }

    @Override
    public void doTransaction() {
        data.moveItem(indexToMove, indexToMove+1);
        data.clearSelected();
        data.selectItem(item);
    }

    @Override
    public void undoTransaction() {
        data.moveItem(indexToMove+1, indexToMove);
        data.clearSelected();
        data.selectItem(item);
    }
}