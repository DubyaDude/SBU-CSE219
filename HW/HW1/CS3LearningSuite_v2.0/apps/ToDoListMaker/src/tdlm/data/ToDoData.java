package tdlm.data;

import djf.components.AppDataComponent;
import djf.modules.AppGUIModule;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import tdlm.ToDoListMakerApp;
import static tdlm.ToDoPropertyType.TDLM_ITEMS_TABLE_VIEW;
import static tdlm.ToDoPropertyType.TDLM_OWNER_TEXT_FIELD;
import static tdlm.ToDoPropertyType.TDLM_NAME_TEXT_FIELD;

/**
 *
 * @author McKillaGorilla
 */
public class ToDoData implements AppDataComponent {
    ToDoListMakerApp app;
    ObservableList<ToDoItemPrototype> items;
    TableViewSelectionModel itemsSelectionModel;
    StringProperty ownerProperty;
    StringProperty nameProperty;
    
    public ToDoData(ToDoListMakerApp initApp) {
        app = initApp;
        
        // GET ALL THE THINGS WE'LL NEED TO MANIUPLATE THE TABLE
        TableView tableView = (TableView) app.getGUIModule().getGUINode(TDLM_ITEMS_TABLE_VIEW);
        items = tableView.getItems();
        itemsSelectionModel = tableView.getSelectionModel();
        itemsSelectionModel.setSelectionMode(SelectionMode.MULTIPLE);
        
        // AND FOR LIST NAME AND OWNER DATA
        ownerProperty = ((TextField)app.getGUIModule().getGUINode(TDLM_OWNER_TEXT_FIELD)).textProperty();
        nameProperty = ((TextField)app.getGUIModule().getGUINode(TDLM_NAME_TEXT_FIELD)).textProperty();
    }
    
    public String getOwner() {
        return ownerProperty.getValue();
    }
    
    public Iterator<ToDoItemPrototype> itemsIterator() {
        return this.items.iterator();
    }
    
    public void setOwner(String initOwner) {
        ownerProperty.setValue(initOwner);
    }
    
    public String getName() {
        return nameProperty.getValue();
    }
    
    public void setName(String initOwner) {
        nameProperty.setValue(initOwner);
    }
        
    @Override
    public void reset() {
        AppGUIModule gui = app.getGUIModule();
        
        // CLEAR OUT THE TEXT FIELDS
        ownerProperty.setValue("");
        nameProperty.setValue("");
        
        // CLEAR OUT THE ITEMS FROM THE TABLE
        TableView tableView = (TableView)gui.getGUINode(TDLM_ITEMS_TABLE_VIEW);
        items = tableView.getItems();
        items.clear();
    }

    public boolean isItemSelected() {
        ObservableList<ToDoItemPrototype> selectedItems = this.getSelectedItems();
        return (selectedItems != null) && (selectedItems.size() == 1);
    }
    
    public boolean areItemsSelected() {
        ObservableList<ToDoItemPrototype> selectedItems = this.getSelectedItems();
        return (selectedItems != null) && (selectedItems.size() > 1);        
    }
    
    public boolean isItemTop() {
        int index=this.getItemIndex(this.getSelectedItem());
        return (index==0);
    }

    public boolean isItemBottom() {
        int index=this.getItemIndex(this.getSelectedItem());
        int total=this.getNumItems();
        return (index==this.getNumItems()-1);
    }
        
    public boolean isValidToDoItemEdit(ToDoItemPrototype itemToEdit, String category, String description, LocalDate startDate, LocalDate endDate, boolean completed) {
        return isValidNewToDoItem(category, description, startDate, endDate, completed);
    }

    public boolean isValidNewToDoItem(String category, String description, LocalDate startDate, LocalDate endDate, boolean completed) {
        if (category.trim().length() == 0)
            return false;
        if (description.trim().length() == 0)
            return false;
        if (startDate.isAfter(endDate))
            return false;
        return true;
    }

    public void addItem(ToDoItemPrototype itemToAdd) {
        items.add(itemToAdd);
    }

    public void removeItem(ToDoItemPrototype itemToAdd) {
        items.remove(itemToAdd);
    }

    public ToDoItemPrototype getSelectedItem() {
        if (!isItemSelected()) {
            return null;
        }
        return getSelectedItems().get(0);
    }
    public ObservableList<ToDoItemPrototype> getSelectedItems() {
        return (ObservableList<ToDoItemPrototype>)this.itemsSelectionModel.getSelectedItems();
    }

    public int getItemIndex(ToDoItemPrototype item) {
        return items.indexOf(item);
    }

    public void addItemAt(ToDoItemPrototype item, int itemIndex) {
        items.add(itemIndex, item);
    }

    public void moveItem(int oldIndex, int newIndex) {
        ToDoItemPrototype itemToMove = items.remove(oldIndex);
        items.add(newIndex, itemToMove);
    }

    public int getNumItems() {
        return items.size();
    }

    public void selectItem(ToDoItemPrototype itemToSelect) {
        this.itemsSelectionModel.select(itemToSelect);
    }
        
    public ArrayList<Integer> removeAll(ArrayList<ToDoItemPrototype> itemsToRemove) {
        ArrayList<Integer> itemIndices = new ArrayList();
        for (ToDoItemPrototype item: itemsToRemove) {
            itemIndices.add(items.indexOf(item));
        }
        for (ToDoItemPrototype item: itemsToRemove) {
            items.remove(item);
        }
        return itemIndices;
    }

    public void addAll(ArrayList<ToDoItemPrototype> itemsToAdd, ArrayList<Integer> addItemLocations) {
        for (int i = 0; i < itemsToAdd.size(); i++) {
            ToDoItemPrototype itemToAdd = itemsToAdd.get(i);
            Integer location = addItemLocations.get(i);
            items.add(location, itemToAdd);
        }
    }

    public ArrayList<ToDoItemPrototype> getCurrentItemsOrder() {
        ArrayList<ToDoItemPrototype> orderedItems = new ArrayList();
        for (ToDoItemPrototype item : items) {
            orderedItems.add(item);
        }
        return orderedItems;
    }

    public void clearSelected() {
        this.itemsSelectionModel.clearSelection();
    }

    public void sortItems(Comparator sortComparator) {
        Collections.sort(items, sortComparator);
    }

    public void rearrangeItems(ArrayList<ToDoItemPrototype> oldListOrder) {
        items.clear();
        for (ToDoItemPrototype item : oldListOrder) {
            items.add(item);
        }
    }
}