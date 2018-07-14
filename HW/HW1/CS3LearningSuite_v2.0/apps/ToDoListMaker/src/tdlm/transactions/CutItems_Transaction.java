package tdlm.transactions;

import jtps.jTPS_Transaction;
import static djf.AppPropertyType.APP_CLIPBOARD_FOOLPROOF_SETTINGS;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import tdlm.ToDoListMakerApp;
import tdlm.data.ToDoData;
import tdlm.data.ToDoItemPrototype;

/**
 *
 * @author McKillaGorilla
 */
public class CutItems_Transaction implements jTPS_Transaction {
    ToDoListMakerApp app;
    ArrayList<ToDoItemPrototype> itemsToCut;
    ArrayList<Integer> cutItemLocations;
    
    public CutItems_Transaction(ToDoListMakerApp initApp, ArrayList<ToDoItemPrototype> initItemsToCut) {
        app = initApp;
        itemsToCut = initItemsToCut;
    }

    @Override
    public void doTransaction() {
        ToDoData data = (ToDoData)app.getDataComponent();
        cutItemLocations = data.removeAll(itemsToCut);
        app.getFoolproofModule().updateControls(APP_CLIPBOARD_FOOLPROOF_SETTINGS);
    }

    @Override
    public void undoTransaction() {
        ToDoData data = (ToDoData)app.getDataComponent();
        data.addAll(itemsToCut, cutItemLocations);
        app.getFoolproofModule().updateControls(APP_CLIPBOARD_FOOLPROOF_SETTINGS);
    }   
}