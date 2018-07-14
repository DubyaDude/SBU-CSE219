package tdlm.workspace.controllers;

import djf.AppTemplate;
import djf.modules.AppGUIModule;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import tdlm.ToDoListMakerApp;
import static tdlm.ToDoPropertyType.TDLM_CATEGORY_COLUMN;
import static tdlm.ToDoPropertyType.TDLM_ITEMS_TABLE_VIEW;
import tdlm.data.ToDoItemPrototype;

/**
 *
 * @author McKillaGorilla
 */
public class ItemsTableController {
    ToDoListMakerApp app;

    public ItemsTableController(AppTemplate initApp) {
        app = (ToDoListMakerApp)initApp;
    }

    public void processChangeTableSize() {
        AppGUIModule gui = app.getGUIModule();
        TableView<ToDoItemPrototype> itemsTable = (TableView)gui.getGUINode(TDLM_ITEMS_TABLE_VIEW);
        ObservableList columns = itemsTable.getColumns();
        for (int i = 0; i < columns.size(); i++) {
            TableColumn column = (TableColumn)columns.get(i);
            column.setMinWidth(itemsTable.widthProperty().getValue()/columns.size());
            column.setMaxWidth(itemsTable.widthProperty().getValue()/columns.size());
        }
    }    
}
