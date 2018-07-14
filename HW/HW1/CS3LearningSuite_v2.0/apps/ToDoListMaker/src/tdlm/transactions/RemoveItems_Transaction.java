package tdlm.transactions;

import static djf.AppPropertyType.APP_CLIPBOARD_FOOLPROOF_SETTINGS;
import java.util.ArrayList;
import jtps.jTPS_Transaction;
import tdlm.ToDoListMakerApp;
import tdlm.data.ToDoData;
import tdlm.data.ToDoItemPrototype;

/**
 *
 * @author McKillaGorilla
 */
public class RemoveItems_Transaction implements jTPS_Transaction {
    ToDoListMakerApp app;
    ArrayList<ToDoItemPrototype> itemsToRemove;
    ArrayList<Integer> removedItemLocations;
    
    public RemoveItems_Transaction(ToDoListMakerApp initApp, ArrayList<ToDoItemPrototype> initItems) {
        app = initApp;
        itemsToRemove = initItems;
    }

    @Override
    public void doTransaction() {
        ToDoData data = (ToDoData)app.getDataComponent();
        removedItemLocations = data.removeAll(itemsToRemove);
    }

    @Override
    public void undoTransaction() {
        ToDoData data = (ToDoData)app.getDataComponent();
        data.addAll(itemsToRemove, removedItemLocations);
    }
}