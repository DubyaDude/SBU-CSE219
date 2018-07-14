package tdlm.clipboard;

import djf.components.AppClipboardComponent;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import tdlm.ToDoListMakerApp;
import tdlm.data.ToDoData;
import tdlm.data.ToDoItemPrototype;
import tdlm.transactions.CutItems_Transaction;
import tdlm.transactions.PasteItems_Transaction;

/**
 *
 * @author McKillaGorilla
 */
public class ToDoClipboard implements AppClipboardComponent {
    ToDoListMakerApp app;
    ArrayList<ToDoItemPrototype> clipboardCutItems;
    ArrayList<ToDoItemPrototype> clipboardCopiedItems;
    
    public ToDoClipboard(ToDoListMakerApp initApp) {
        app = initApp;
        clipboardCutItems = null;
        clipboardCopiedItems = null;
    }
    
    @Override
    public void cut() {
        ToDoData data = (ToDoData)app.getDataComponent();
        if (data.isItemSelected() || data.areItemsSelected()) {
            clipboardCutItems = new ArrayList(data.getSelectedItems());
            clipboardCopiedItems = null;
            CutItems_Transaction transaction = new CutItems_Transaction((ToDoListMakerApp)app, clipboardCutItems);
            app.processTransaction(transaction);
        }
    }

    @Override
    public void copy() {
        ToDoData data = (ToDoData)app.getDataComponent();
        if (data.isItemSelected() || data.areItemsSelected()) {
            ArrayList<ToDoItemPrototype> tempItems = new ArrayList(data.getSelectedItems());
            copyToCopiedClipboard(tempItems);
        }
    }
    
    private void copyToCutClipboard(ArrayList<ToDoItemPrototype> itemsToCopy) {
        clipboardCutItems = copyItems(itemsToCopy);
        clipboardCopiedItems = null;        
        app.getFoolproofModule().updateAll();        
    }
    
    private void copyToCopiedClipboard(ArrayList<ToDoItemPrototype> itemsToCopy) {
        clipboardCutItems = null;
        clipboardCopiedItems = copyItems(itemsToCopy);
        app.getFoolproofModule().updateAll();        
    }
    
    private ArrayList<ToDoItemPrototype> copyItems(ArrayList<ToDoItemPrototype> itemsToCopy) {
        ArrayList<ToDoItemPrototype> tempCopy = new ArrayList();         
        for (ToDoItemPrototype itemToCopy : itemsToCopy) {
            ToDoItemPrototype copiedItem = (ToDoItemPrototype)itemToCopy.clone();
            tempCopy.add(copiedItem);
        }        
        return tempCopy;
    }

    @Override
    public void paste() {
        ToDoData data = (ToDoData)app.getDataComponent();
        if (data.isItemSelected()) {
            int selectedIndex = data.getItemIndex(data.getSelectedItem());  
            ArrayList<ToDoItemPrototype> pasteItems = clipboardCutItems;
            if ((clipboardCutItems != null)
                    && (!clipboardCutItems.isEmpty())) {
                PasteItems_Transaction transaction = new PasteItems_Transaction((ToDoListMakerApp)app, clipboardCutItems, selectedIndex);
                app.processTransaction(transaction);
                
                // NOW WE HAVE TO RE-COPY THE CUT ITEMS TO MAKE
                // SURE IF WE PASTE THEM AGAIN THEY ARE BRAND NEW OBJECTS
                copyToCutClipboard(clipboardCopiedItems);
            }
            else if ((clipboardCopiedItems != null)
                    && (!clipboardCopiedItems.isEmpty())) {
                PasteItems_Transaction transaction = new PasteItems_Transaction((ToDoListMakerApp)app, clipboardCopiedItems, selectedIndex);
                app.processTransaction(transaction);
            
                // NOW WE HAVE TO RE-COPY THE COPIED ITEMS TO MAKE
                // SURE IF WE PASTE THEM AGAIN THEY ARE BRAND NEW OBJECTS
                copyToCopiedClipboard(clipboardCopiedItems);
            }
        }
    }    


    @Override
    public boolean hasSomethingToCut() {
        return ((ToDoData)app.getDataComponent()).isItemSelected()
                || ((ToDoData)app.getDataComponent()).areItemsSelected();
    }

    @Override
    public boolean hasSomethingToCopy() {
        return ((ToDoData)app.getDataComponent()).isItemSelected()
                || ((ToDoData)app.getDataComponent()).areItemsSelected();
    }

    @Override
    public boolean hasSomethingToPaste() {
        if ((clipboardCutItems != null) && (!clipboardCutItems.isEmpty()))
            return true;
        else if ((clipboardCopiedItems != null) && (!clipboardCopiedItems.isEmpty()))
            return true;
        else
            return false;
    }
}