package tdlm.transactions;

import jtps.jTPS_Transaction;
import tdlm.data.ToDoData;
import tdlm.data.ToDoItemPrototype;

/**
 *
 * @author ushaf
 */
public class EditItem_Transaction implements jTPS_Transaction {
    ToDoData data;
    ToDoItemPrototype beforeEditedItem;
    ToDoItemPrototype afterEditedItem;
    int index;

    public EditItem_Transaction(ToDoData initData, ToDoItemPrototype editedItem) {
        data = initData;
        beforeEditedItem = data.getSelectedItem();
        afterEditedItem = editedItem;
        index = data.getItemIndex(beforeEditedItem);
    }

    @Override
    public void doTransaction() {
        data.removeItem(beforeEditedItem);
        data.addItemAt(afterEditedItem, index);
        data.clearSelected();
        data.selectItem(afterEditedItem);
    }

    @Override
    public void undoTransaction() {
        data.removeItem(afterEditedItem);
        data.addItemAt(beforeEditedItem, index);
        data.clearSelected();
        data.selectItem(beforeEditedItem);
    }
}
