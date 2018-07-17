package tdlm.workspace.controllers;

import java.util.ArrayList;
import tdlm.ToDoListMakerApp;
import tdlm.data.ToDoData;
import tdlm.data.ToDoItemPrototype;
import tdlm.workspace.dialogs.ToDoListItemDialog;
import tdlm.transactions.AddItem_Transaction;
import tdlm.transactions.RemoveItems_Transaction;
import tdlm.transactions.EditItem_Transaction;
import tdlm.transactions.MoveUpItem_Transaction;
import tdlm.transactions.MoveDownItem_Transaction;

/**
 *
 * @author McKillaGorilla
 */
public class ItemsController {
    ToDoListMakerApp app;
    ToDoListItemDialog itemDialog;
    
    public ItemsController(ToDoListMakerApp initApp) {
        app = initApp;
        
        itemDialog = new ToDoListItemDialog(app);
    }
    
    public void processAddItem() {
        itemDialog.showAddDialog();
        ToDoItemPrototype newItem = itemDialog.getNewItem();
        //
        if (newItem != null) {
            // IF IT HAS A UNIQUE NAME AND COLOR
            // THEN CREATE A TRANSACTION FOR IT
            // AND ADD IT
            ToDoData data = (ToDoData)app.getDataComponent();
            AddItem_Transaction transaction = new AddItem_Transaction(data, newItem);
            app.processTransaction(transaction);
        }    
        // OTHERWISE TELL THE USER WHAT THEY
        // HAVE DONE WRONG
        else {
            djf.ui.dialogs.AppDialogsFacade.showMessageDialog(app.getGUIModule().getWindow(), "Invalid Line", "Invalid data for a new line");
        }
    }
    
    public void processRemoveItems() {
        ToDoData data = (ToDoData)app.getDataComponent();
        if (data.isItemSelected() || data.areItemsSelected()) {
            ArrayList<ToDoItemPrototype> itemsToRemove = new ArrayList(data.getSelectedItems());
            RemoveItems_Transaction transaction = new RemoveItems_Transaction(app, itemsToRemove);
            app.processTransaction(transaction);
        }
    }
    
    public void processEditItem(){
        System.out.println("Edit Clicked");
        ToDoData data = (ToDoData)app.getDataComponent();
        itemDialog.showEditDialog((ToDoItemPrototype)data.getSelectedItem());
        ToDoItemPrototype editedItem=itemDialog.getEditItem();
        EditItem_Transaction transaction = new EditItem_Transaction(data, editedItem);
        app.processTransaction(transaction);
    }
    
    public void processMoveUpItem(){
        System.out.println("MoveUp Clicked");
        ToDoData data = (ToDoData)app.getDataComponent();
        MoveUpItem_Transaction transaction = new MoveUpItem_Transaction(data, (ToDoItemPrototype)data.getSelectedItem());
        app.processTransaction(transaction);
    }
    
    public void processMoveDownItem(){
        System.out.println("MoveDown Clicked");
        ToDoData data = (ToDoData)app.getDataComponent();
        MoveDownItem_Transaction transaction = new MoveDownItem_Transaction(data, (ToDoItemPrototype)data.getSelectedItem());
        app.processTransaction(transaction);
    }
}
