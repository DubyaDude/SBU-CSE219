package tdlm.transactions;

import javafx.collections.ObservableList;
import jtps.jTPS_Transaction;
import tdlm.ToDoListMakerApp;
import tdlm.data.ToDoData;
import tdlm.data.ToDoItemPrototype;

/**
 *
 * @author ushaf
 */
public class MoveDownItem_Transaction implements jTPS_Transaction{
    ToDoData data;
    ToDoItemPrototype item;
    int indexToMove;
    
    public MoveDownItem_Transaction(ToDoData initData, ToDoItemPrototype selectedItem) {
        this.data=initData;
        indexToMove=data.getItemIndex(selectedItem);
    }

    @Override
    public void doTransaction() {
        data.moveItem(indexToMove, indexToMove+1);
    }

    @Override
    public void undoTransaction() {
        data.moveItem(indexToMove+1, indexToMove);
    }
}