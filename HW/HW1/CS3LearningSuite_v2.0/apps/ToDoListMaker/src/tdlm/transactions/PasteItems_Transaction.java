package tdlm.transactions;

import java.util.ArrayList;
import jtps.jTPS_Transaction;
import tdlm.ToDoListMakerApp;
import tdlm.data.ToDoData;
import tdlm.data.ToDoItemPrototype;

/**
 *
 * @author McKillaGorilla
 */
public class PasteItems_Transaction implements jTPS_Transaction {
    ToDoListMakerApp app;
    ArrayList<ToDoItemPrototype> itemsToPaste;
    int pasteIndex;
    
    public PasteItems_Transaction(  ToDoListMakerApp initApp, 
                                    ArrayList<ToDoItemPrototype> initItemsToPaste,
                                    int initPasteIndex) {
        app = initApp;
        itemsToPaste = initItemsToPaste;
        pasteIndex = initPasteIndex;
    }

    @Override
    public void doTransaction() {
        ToDoData data = (ToDoData)app.getDataComponent();
        int index = pasteIndex+1;
        for (ToDoItemPrototype itemToPaste : itemsToPaste) {
            data.addItemAt(itemToPaste, index);
            index++;
        }
    }

    @Override
    public void undoTransaction() {
        ToDoData data = (ToDoData)app.getDataComponent();
        for (ToDoItemPrototype itemToPaste : itemsToPaste) {
            data.removeItem(itemToPaste);
        }
    }   
}